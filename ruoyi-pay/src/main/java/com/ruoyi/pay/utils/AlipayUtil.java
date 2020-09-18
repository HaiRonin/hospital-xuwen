package com.ruoyi.pay.utils;


import com.ruoyi.pay.config.AlipayConfig;

import java.security.KeyFactory;
import java.security.PrivateKey;
import java.security.spec.PKCS8EncodedKeySpec;

/**
 * @Description : 支付宝支付工具类
 * @Author : yuanhualiang
 * @Date: 2020-09-12 12:34
 */
public class AlipayUtil {

    public static final String SIGN_ALGORITHMS = "SHA1WithRSA";

    /**
     * @param subject 商品名称
     * @param body    商品描述
     * @param price
     * @return
     */
    public static String getOrderInfo(String subject, String body, double price, String outTradeNo) {

        StringBuffer orderInfo = new StringBuffer(512);
        orderInfo.append("partner=\"");
        orderInfo.append(AlipayConfig.partner);
        orderInfo.append("\"");
        orderInfo.append("&seller_id=\"");
        orderInfo.append(AlipayConfig.seller_account);
        orderInfo.append("\"");
        orderInfo.append("&out_trade_no=\"");
        orderInfo.append(outTradeNo);
        orderInfo.append("\"");
        orderInfo.append("&subject=\"");
        orderInfo.append(subject);
        orderInfo.append("\"");
        orderInfo.append("&body=\"");
        orderInfo.append(body);
        orderInfo.append("\"");
        orderInfo.append("&total_fee=\"");
        orderInfo.append(price);
        orderInfo.append("\"");
        orderInfo.append("&notify_url=\"");
        orderInfo.append(AlipayConfig.baseUrl);
        orderInfo.append("\"");
        orderInfo.append("&service=\"");
        orderInfo.append("mobile.securitypay.pay");
        orderInfo.append("\"");
        orderInfo.append("&payment_type=\"");
        orderInfo.append("1");
        orderInfo.append("\"");
        orderInfo.append("&_input_charset=\"");
        orderInfo.append(AlipayConfig.input_charset);
        orderInfo.append("\"");
        orderInfo.append("&it_b_pay=\"");
        orderInfo.append("30m");
        orderInfo.append("\"");
        return orderInfo.toString();
    }

    /**
     * 支付宝签名
     *
     * @param content 参与签名的内容
     * @return
     */
    public static String alipaySign(String content) {
        try {
            PKCS8EncodedKeySpec priPKCS8 = new PKCS8EncodedKeySpec(
                    Base64.decode(AlipayConfig.private_key));
            KeyFactory keyf = KeyFactory.getInstance(AlipayConfig.sign_type);
            PrivateKey priKey = keyf.generatePrivate(priPKCS8);

            java.security.Signature signature = java.security.Signature
                    .getInstance(SIGN_ALGORITHMS);

            signature.initSign(priKey);
            signature.update(content.getBytes(AlipayConfig.input_charset));

            byte[] signed = signature.sign();

            return Base64.encode(signed);
        } catch (Exception e) {
            e.printStackTrace();
        }

        return null;
    }
}
