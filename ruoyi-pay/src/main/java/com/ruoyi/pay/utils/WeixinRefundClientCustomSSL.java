package com.ruoyi.pay.utils;

import com.ruoyi.pay.config.WechatConfig;
import org.apache.http.HttpEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.conn.ssl.SSLConnectionSocketFactory;
import org.apache.http.conn.ssl.SSLContexts;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.net.ssl.SSLContext;
import java.io.File;
import java.io.FileInputStream;
import java.security.KeyStore;

/**
 * 微信退款
 * 创建一个自定义的SSLContext安全连接
 */
public class WeixinRefundClientCustomSSL {

    private static final Logger logger = LoggerFactory.getLogger(WeixinRefundClientCustomSSL.class);

    /**
     * 发起退款
     *
     * @param url
     * @param data
     * @return
     * @throws Exception
     */
    public static String doRefund(String url, String data) throws Exception {

        logger.info(">>>>>>>>>>微信退款地址：" + url);
        logger.info(">>>>>>>>>>微信退款参数：" + data);
        /**
         * 注意PKCS12证书 是从微信商户平台-》账户设置-》 API安全 中下载的
         */
        FileInputStream instream = null;
        try {
            KeyStore keyStore = KeyStore.getInstance("PKCS12");
            instream = new FileInputStream(new File(WechatConfig.refundCert));//P12文件目录
            /**
             * 此处要改
             * */
            keyStore.load(instream, WechatConfig.mchid.toCharArray());//这里写密码..默认是你的MCHID
            // Trust own CA and all self-signed certs
            /**
             * 此处要改
             * */
            SSLContext sslcontext = SSLContexts.custom()
                    .loadKeyMaterial(keyStore, WechatConfig.mchid.toCharArray())//这里也是写密码的
                    .build();
            // Allow TLSv1 protocol only
            SSLConnectionSocketFactory sslsf = new SSLConnectionSocketFactory(
                    sslcontext,
                    new String[]{"TLSv1"},
                    null,
                    SSLConnectionSocketFactory.BROWSER_COMPATIBLE_HOSTNAME_VERIFIER);
            CloseableHttpClient httpclient = HttpClients.custom()
                    .setSSLSocketFactory(sslsf)
                    .build();
            try {
                HttpPost httpost = new HttpPost(url); // 设置响应头信息
                httpost.addHeader("Connection", "keep-alive");
                httpost.addHeader("Accept", "*/*");
                httpost.addHeader("Content-Type", "application/x-www-form-urlencoded; charset=UTF-8");
                httpost.addHeader("Host", "api.mch.weixin.qq.com");
                httpost.addHeader("X-Requested-With", "XMLHttpRequest");
                httpost.addHeader("Cache-Control", "max-age=0");
                httpost.addHeader("User-Agent", "Mozilla/4.0 (compatible; MSIE 8.0; Windows NT 6.0) ");
                httpost.setEntity(new StringEntity(data, "UTF-8"));
                CloseableHttpResponse response = httpclient.execute(httpost);
                try {
                    HttpEntity entity = response.getEntity();

                    String jsonStr = EntityUtils.toString(response.getEntity(), "UTF-8");
                    EntityUtils.consume(entity);
                    return jsonStr;
                } finally {
                    response.close();
                }
            } finally {
                httpclient.close();
            }
        } finally {
            instream.close();
        }


    }

}
