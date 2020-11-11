package com.ruoyi.pay.utils;

import com.alibaba.fastjson.JSONObject;
import com.ruoyi.pay.config.WechatConfig;
import org.dom4j.Document;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.net.ssl.HttpsURLConnection;
import javax.net.ssl.SSLContext;
import javax.net.ssl.SSLSocketFactory;
import javax.net.ssl.TrustManager;
import java.io.*;
import java.net.ConnectException;
import java.net.URL;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.*;

/**
 * @Description : 微信公众号支付
 * @Author : yuanhualiang
 * @Date: 2020-09-06 22:51
 */
public class WeixinPayUtils {

    private static final Logger LOG = LoggerFactory.getLogger(WeixinPayUtils.class);

    /**
     * 拼接prepayid请求参数XML
     *
     * @param code
     * @return
     */
    public static String getWxUnifiedOrderParamsXML(WxSignCode code) {

        Map<String, String> sign = new HashMap<String, String>();

        sign.put("appid", code.getAppid());// 公众号标识,1
        sign.put("body", code.getBody());// 商品描述,0
        sign.put("mch_id", code.getMch_id());// 商户编号,1
//		sign.put("sub_mch_id", code.getSub_mch_id());// 商户编号,1
        sign.put("nonce_str", code.getNonce_str());// 随机字符串,1
        sign.put("openid", code.getOpenid());// 微信用户编号,0
        sign.put("out_trade_no", code.getOut_trade_no());// 订单编号,0
        sign.put("spbill_create_ip", code.getSpbill_create_ip());// 设备IP,0
        sign.put("total_fee", code.getTotal_fee());// 价钱 单位:分,0
//        sign.put("total_fee", "" + 1);// 价钱 单位:分,0
        sign.put("notify_url", code.getNotify_url());// 请求成功跳转地址,0
        sign.put("trade_type", "JSAPI");// 支付方式,1
//		sign.put("attach", code.getAttach());// 附加信息,1
        sign.put("sign", WxSignCreate(sign));// 签名

        return wxXml(sign);// 参数转化xml
    }

    /**
     * 转换xml格式
     *
     * @param sign
     * @return
     */
    public static String wxXml(Map<String, String> sign) {
        StringBuilder sb = new StringBuilder();
        List<Map.Entry<String, String>> infoIds = new ArrayList<Map.Entry<String, String>>(
                sign.entrySet());
        Collections.sort(infoIds, new Comparator<Map.Entry<String, String>>() {
            public int compare(Map.Entry<String, String> o1,
                               Map.Entry<String, String> o2) {
                return (o1.getKey()).toString().compareTo(o2.getKey());
            }
        });
        try {
            sb.append("<xml>");
            for (int i = 0; i < infoIds.size(); i++) {
                Map.Entry<String, String> item = infoIds.get(i);
                String key = item.getKey();
                String value = sign.get(key);
                if ("total_fee".equalsIgnoreCase(key)) {
                    sb.append("<" + key + ">" + value + "</" + key
                            + ">");
                } else {
                    sb.append("<" + key + ">" + "<![CDATA[" + value
                            + "]]></" + key + ">");
                }
            }
            sb.append("</xml>");
        } catch (Exception e) {
            System.out.println("参数转换xml异常:" + e.getMessage());
            return null;
        }
        return sb.toString();
    }

    /**
     * 拼接签名生成
     *
     * @param sign
     * @return
     */
    public static String WxSignCreate(Map<String, String> sign) {
        String create = "";// 拼接字符串
        List<Map.Entry<String, String>> infoIds = new ArrayList<Map.Entry<String, String>>(
                sign.entrySet());
        Collections.sort(infoIds, new Comparator<Map.Entry<String, String>>() {
            public int compare(Map.Entry<String, String> o1,
                               Map.Entry<String, String> o2) {
                return (o1.getKey()).toString().compareTo(o2.getKey());
            }
        });
        for (int i = 0; i < infoIds.size(); i++) {
            Map.Entry<String, String> item = infoIds.get(i);
            if (item.getKey() != "") {
                String key = item.getKey();
                String val = item.getValue();
                create += key + "=" + val + "&";
            }
        }

        if (create.isEmpty() == false) {
            create = create.substring(0, create.length() - 1);
        }
        return WxSignMD5(create);
    }

    /**
     * 请求签名MD5
     *
     * @param sign
     * @return
     */
    private static String WxSignMD5(String sign) {
        String signstr = sign + "&key=" + WechatConfig.key;
        return getMD5Str(signstr).toUpperCase();
    }

    /**
     * MD5加密
     *
     * @param s
     * @return
     */
    private final static String getMD5Str(String s) {
        char hexDigits[] = {'0', '1', '2', '3', '4', '5', '6', '7', '8', '9', 'a', 'b', 'c', 'd', 'e', 'f'};
        try {
            MessageDigest mdTemp = MessageDigest.getInstance("MD5");
            mdTemp.update(s.getBytes("UTF-8"));
            byte[] md = mdTemp.digest();
            int j = md.length;
            char str[] = new char[j * 2];
            int k = 0;
            for (int i = 0; i < j; i++) {
                byte byte0 = md[i];
                str[k++] = hexDigits[byte0 >>> 4 & 0xf];
                str[k++] = hexDigits[byte0 & 0xf];
            }
            return new String(str);
        } catch (Exception e) {
            return null;
        }
    }

    /**
     * 获取随机串
     *
     * @return
     */
    public static String createNoncestr() {
        String chars = "abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789";
        String res = "";
        for (int i = 0; i < 16; i++) {
            Random rd = new Random();
            res += chars.charAt(rd.nextInt(chars.length() - 1));
        }
        return res;
    }

    /**
     * 发起https请求并获取结果
     *
     * @param requestUrl    请求地址
     * @param requestMethod 请求方式（GET、POST）
     * @param outputStr     提交的数据
     * @return
     */
    public static Map<String, String> httpRequest(String requestUrl,
                                                  String requestMethod, String outputStr) {
        Map<String, String> map = new HashMap<String, String>();
        try {
            // 创建SSLContext对象，并使用我们指定的信任管理器初始化
            URL url = new URL(requestUrl);
            HttpsURLConnection httpUrlConn = (HttpsURLConnection) url
                    .openConnection();

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
            map = parseXml(inputStream);
            // 释放资源
            inputStream.close();
            inputStream = null;
            httpUrlConn.disconnect();
        } catch (ConnectException ce) {
            System.out.println("Weixin server connection timed out.");
        } catch (Exception e) {
            System.out.println("https request error:{}" + e);
        }
        return map;
    }

    /**
     * 解析xml
     *
     * @param inputStream
     * @return
     * @throws Exception
     */
    public static Map<String, String> parseXml(InputStream inputStream)
            throws Exception {
        // 将解析结果存储在HashMap中
        Map<String, String> map = new HashMap<String, String>();

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
     * jsapi签名
     *
     * @param jsTicket
     * @param nonceStr
     * @param timeStamp
     * @param url
     * @return
     * @throws Exception
     */
    public static String getJsApiSign(String jsTicket, String nonceStr, Long timeStamp, String url) throws Exception {
        String plainTex = "jsapi_ticket=" + jsTicket + "&noncestr=" + nonceStr + "&timestamp=" + timeStamp + "&url=" + url;
        System.out.println(plainTex);
        try {
            MessageDigest crypt = MessageDigest.getInstance("SHA-1");
            crypt.reset();
            crypt.update(plainTex.getBytes("UTF-8"));
            return byteToHex(crypt.digest());
        } catch (NoSuchAlgorithmException e) {
            throw new Exception(e.getMessage());
        } catch (UnsupportedEncodingException e) {
            throw new Exception(e.getMessage());
        }
    }

    /**
     * @param hash
     * @return String
     * @desc ：4.2 将bytes类型的数据转化为16进制类型
     */
    private static String byteToHex(byte[] hash) {
        Formatter formatter = new Formatter();
        for (byte b : hash) {
            formatter.format("%02x", new Object[]{Byte.valueOf(b)});
        }
        String result = formatter.toString();
        formatter.close();
        return result;
    }

}
