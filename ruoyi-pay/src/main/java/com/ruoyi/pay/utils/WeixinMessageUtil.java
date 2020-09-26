package com.ruoyi.pay.utils;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONException;
import com.alibaba.fastjson.JSONObject;
import com.ruoyi.common.utils.StringUtils;
import com.ruoyi.pay.config.WechatConfig;
import org.apache.commons.codec.binary.*;
import org.dom4j.Document;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.net.ssl.HttpsURLConnection;
import javax.net.ssl.SSLContext;
import javax.net.ssl.SSLSocketFactory;
import javax.net.ssl.TrustManager;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.ConnectException;
import java.net.URL;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @Description : 微信消息工具
 * @Author : yuanhualiang
 * @Date: 2020-09-05 00:33
 */
public class WeixinMessageUtil {

    private static final Logger LOG = LoggerFactory.getLogger(WeixinMessageUtil.class);

    private static final String COOKIE_ACCESS_TOKEN = "COOKIE_ACCESS_TOKEN";

    private static final String COOKIE_JSAPI_TICKET = "WX_SHARE_JSAPI_TICKET";

    /**
     * 获取TOKEN
     *
     * @return
     */
    public static String getAccessToken(HttpServletRequest request, HttpServletResponse response) {

        String accessTokenStr = CookieUtils.getCookieValue(request, COOKIE_ACCESS_TOKEN);
        if (StringUtils.isNotEmpty(accessTokenStr)) {
            return accessTokenStr;
        }

        AccessToken accessToken = null;
        String accessTokenUrl = "https://api.weixin.qq.com/cgi-bin/token?grant_type=client_credential&appid=#APPID#&secret=#APPSECRET#";
        String requestUrl = accessTokenUrl.replace(
                "#APPID#", WechatConfig.appId).replace(
                "#APPSECRET#", WechatConfig.appsecret);
        JSONObject jsonObject = httpRequestForSSL(requestUrl, "GET", null);
        LOG.info(">>>>>>>>>>>>>>>>>获取accessToken结果：" + JSON.toJSONString(jsonObject));
        // 如果请求成功
        if (null != jsonObject) {
            try {
                accessToken = new AccessToken();
                accessToken.setToken(jsonObject.getString("access_token"));
                accessToken.setExpiresIn(jsonObject.getIntValue("expires_in"));
            } catch (JSONException e) {
                accessToken = null;
                // 获取token失败
                LOG.error("获取token失败 errcode:{} errmsg:{}",
                        jsonObject.getIntValue("errcode"),
                        jsonObject.getString("errmsg"));
            }
        }
        if (null != accessToken) {
            accessTokenStr = accessToken.getToken();
            if (StringUtils.isNotEmpty(accessTokenStr) && !"null".equals(accessTokenStr)) {
                CookieUtils.setCookie(response,
                        COOKIE_ACCESS_TOKEN, accessTokenStr,
                        2 * 60 * 60);
            }
        } else {
            accessTokenStr = "";
        }
        return accessTokenStr;
    }

    /**
     * 解析XML请求
     *
     * @param request
     * @return
     * @throws Exception
     */
    public static Map<String, String> parseXml(HttpServletRequest request)
            throws Exception {
        // 将解析结果存储在HashMap中
        Map<String, String> map = new HashMap<String, String>();

        // 从request中取得输入流
        InputStream inputStream = request.getInputStream();
        // 读取输入流
        SAXReader reader = new SAXReader();
        Document document = reader.read(inputStream);
        // 得到xml根元素
        Element root = document.getRootElement();
        // 得到根元素的所有子节点
        List<Element> elementList = root.elements();

        // 遍历所有子节点
        for (Element e : elementList)
            map.put(e.getName(), e.getText());

        // 释放资源
        inputStream.close();
        inputStream = null;

        return map;
    }

    /**
     * 发起https请求并获取结果
     *
     * @param requestUrl    请求地址
     * @param requestMethod 请求方式（GET、POST）
     * @param outputStr     提交的数据
     * @return JSONObject(通过JSONObject.get(key)的方式获取json对象的属性值)
     */
    public static JSONObject httpRequestForSSL(String requestUrl, String requestMethod, String outputStr) {
        JSONObject jsonObject = null;
        StringBuffer buffer = new StringBuffer();
        try {
            // 创建SSLContext对象，并使用我们指定的信任管理器初始化
            TrustManager[] tm = {new MyX509TrustManager()};
            SSLContext sslContext = SSLContext.getInstance("SSL", "SunJSSE");
            sslContext.init(null, tm, new java.security.SecureRandom());
            // 从上述SSLContext对象中得到SSLSocketFactory对象
            SSLSocketFactory ssf = sslContext.getSocketFactory();

            URL url = new URL(requestUrl);
            HttpsURLConnection httpUrlConn = (HttpsURLConnection) url.openConnection();
            httpUrlConn.setSSLSocketFactory(ssf);

            httpUrlConn.setDoOutput(true);
            httpUrlConn.setDoInput(true);
            httpUrlConn.setUseCaches(false);

            // 设置请求方式（GET/POST）
            httpUrlConn.setRequestMethod(requestMethod);

            if ("GET".equalsIgnoreCase(requestMethod))
                httpUrlConn.connect();

            // 当有数据需要提交时
            if (null != outputStr) {
                OutputStream outputStream = httpUrlConn.getOutputStream();
                // 注意编码格式，防止中文乱码
                outputStream.write(outputStr.getBytes("UTF-8"));
                outputStream.close();
            }

            // 将返回的输入流转换成字符串
            InputStream inputStream = httpUrlConn.getInputStream();
            InputStreamReader inputStreamReader = new InputStreamReader(inputStream, "utf-8");
            BufferedReader bufferedReader = new BufferedReader(inputStreamReader);

            String str = null;
            while ((str = bufferedReader.readLine()) != null) {
                buffer.append(str);
            }
            bufferedReader.close();
            inputStreamReader.close();
            // 释放资源
            inputStream.close();
            inputStream = null;
            httpUrlConn.disconnect();
            jsonObject = JSONObject.parseObject(buffer.toString());
        } catch (ConnectException ce) {
            LOG.error("Weixin server connection timed out.");
        } catch (Exception e) {
            LOG.error("https request error:{}", e);
        }
        return jsonObject;
    }

    /**
     * 获取公众号ticket
     *
     * @param accessToken
     * @param response
     * @return
     * @throws Exception
     */
    public static String getJsapiTicket(String accessToken, HttpServletRequest request, HttpServletResponse response) {


        String ticket = CookieUtils.getCookieValue(request, COOKIE_JSAPI_TICKET);
        if (null != ticket) {
            return ticket;
        } else {
            String token = WeixinMessageUtil.getAccessToken(request, response);
            String ticketUrl = "https://api.weixin.qq.com/cgi-bin/ticket/getticket?access_token="
                    + token + "&type=jsapi";
            JSONObject jsonObject = httpRequestForSSL(ticketUrl, "GET",
                    null);
            LOG.info(">>>>>>>>>>>>>>>>>获取ticket结果：" + JSON.toJSONString(jsonObject));
            ticket = jsonObject.getString("ticket");
            if (StringUtils.isNotEmpty(ticket) && !"null".equals(ticket)) {
                CookieUtils.setCookie(response,
                        COOKIE_JSAPI_TICKET, ticket,
                        7200 * 1000);
            }
            return ticket;
        }
    }
}
