package com.ruoyi.utils.pay;

import com.ruoyi.web.core.config.WxAppConfig;
import org.dom4j.Document;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.net.ssl.HttpsURLConnection;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.UnsupportedEncodingException;
import java.net.ConnectException;
import java.net.URL;
import java.security.MessageDigest;
import java.util.*;

/**
 * @Description : 微信APP支付
 * @Author : yuanhualiang
 * @Date: 2020-09-04 23:59
 */
public class WeixinAppPayUtils {

    private static final Logger LOG = LoggerFactory.getLogger(WeixinAppPayUtils.class);

    /**
     * 获取预支付ID
     *
     * @param strBody
     * @param orderNo
     * @param totalFee
     * @return
     */
    public static String getPrePayId(String strBody, String orderNo, String totalFee) {
        WxSignCode sign = new WxSignCode();
        // 获取商户的配置参数
        sign.setAppid(WxAppConfig.appId);
        sign.setMch_id(WxAppConfig.mchid);
        sign.setBody(strBody);
        sign.setNonce_str(WeixinAppPayUtils.createNoncestr());
        //sign.setOpenid(UserUtils.getOpenId());
        sign.setOut_trade_no(orderNo);// 需要考虑一个订是否能发起多次支付，导致订单号重复问题
        sign.setSpbill_create_ip("127.0.0.1");
        sign.setTotal_fee(totalFee);
        sign.setNotify_url(WxAppConfig.baseUrl);
        sign.setAttach("");// 传递电话号码，订单详情，用于回调更新详情信息
        LOG.info(">>>>>>>>>>>>>>>微信支付预支付签名参数：" + sign);
        String code = WeixinAppPayUtils.getWxUnifiedOrderParamsXML(sign);
        LOG.info(">>>>>>>>>>>>>>>微信支付预支付签名结果：" + code);
        String url = "https://api.mch.weixin.qq.com/pay/unifiedorder";
        Map<String, String> jsonObject = WeixinAppPayUtils.httpRequest(url, "POST", code);
        LOG.info(">>>>>>>>>>>>>>>微信支付预支付请求结果：" + jsonObject);
        String prepayid = jsonObject.get("prepay_id");
        return prepayid;
    }

    /**
     * 发起https请求并获取结果
     *
     * @param requestUrl    请求地址
     * @param requestMethod 请求方式（GET、POST）
     * @param outputStr     提交的数据
     * @return
     */
    public static Map<String, String> httpRequest(String requestUrl, String requestMethod, String outputStr) {
        Map<String, String> map = new HashMap<String, String>();
        try {
            // 创建SSLContext对象，并使用我们指定的信任管理器初始化
            URL url = new URL(requestUrl);
            HttpsURLConnection httpUrlConn = (HttpsURLConnection) url.openConnection();

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
     * 解析XML
     *
     * @param inputStream
     * @return
     * @throws Exception
     */
    @SuppressWarnings("unchecked")
    public static Map<String, String> parseXml(InputStream inputStream) throws Exception {
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
        sign.put("nonce_str", code.getNonce_str());// 随机字符串,1
        // sign.put("openid", code.getOpenid());// 微信用户编号,0
        sign.put("out_trade_no", code.getOut_trade_no());// 订单编号,0
        sign.put("spbill_create_ip", code.getSpbill_create_ip());// 设备IP,0
        sign.put("total_fee", code.getTotal_fee());// 价钱 单位:分,0
        sign.put("notify_url", code.getNotify_url());// 请求成功跳转地址,0
        sign.put("trade_type", "APP");// 支付方式,1
        // sign.put("attach", code.getAttach());// 附加信息,1
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
        List<Map.Entry<String, String>> infoIds = new ArrayList<Map.Entry<String, String>>(sign.entrySet());
        Collections.sort(infoIds, new Comparator<Map.Entry<String, String>>() {
            public int compare(Map.Entry<String, String> o1, Map.Entry<String, String> o2) {
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
                    sb.append("<" + key + ">" + value + "</" + key + ">");
                } else {
                    sb.append("<" + key + ">" + "<![CDATA[" + value + "]]></" + key + ">");
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
        List<Map.Entry<String, String>> infoIds = new ArrayList<Map.Entry<String, String>>(sign.entrySet());
        Collections.sort(infoIds, new Comparator<Map.Entry<String, String>>() {
            public int compare(Map.Entry<String, String> o1, Map.Entry<String, String> o2) {
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
        String signstr = sign
                + "&key="
                + WxAppConfig.key;
        String m = "";
        try {
            m = getMessageDigest(signstr.getBytes("UTF-8")).toUpperCase();
        } catch (UnsupportedEncodingException e) {
        }
        return m;
    }

    /**
     * MD5加密
     *
     * @param buffer
     * @return
     */
    private final static String getMessageDigest(byte[] buffer) {
        char hexDigits[] = {'0', '1', '2', '3', '4', '5', '6', '7', '8', '9', 'a', 'b', 'c', 'd', 'e', 'f'};
        try {
            MessageDigest mdTemp = MessageDigest.getInstance("MD5");
            mdTemp.update(buffer);
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
     * 获取随机串
     *
     * @param len
     * @return
     */
    public static String createNoncestr(int len) {
        String chars = "abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789";
        String res = "";
        for (int i = 0; i < len; i++) {
            Random rd = new Random();
            res += chars.charAt(rd.nextInt(chars.length() - 1));
        }
        return res;
    }

    public static void main(String[] args) {
        System.out.println(createNoncestr(3));
    }
}
