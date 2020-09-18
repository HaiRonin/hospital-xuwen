package com.ruoyi.pay.config;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

/**
 * @Description : 支付宝配置
 * @Author : yuanhualiang
 * @Date: 2020-09-12 23:37
 */
@Configuration
@ConfigurationProperties(prefix = "his.aliapp")
public class AlipayConfig {

    public static String partner;

    public static String seller_account;

    public static String private_key;

    public static String ali_public_key;

    public static String baseUrl;

    public static String sign_type = "RSA";

    public static String input_charset = "utf-8";

    public String getPartner() {
        return partner;
    }

    public void setPartner(String partner) {
        this.partner = partner;
    }

    public String getSeller_account() {
        return seller_account;
    }

    public void setSeller_account(String seller_account) {
        this.seller_account = seller_account;
    }

    public String getPrivate_key() {
        return private_key;
    }

    public void setPrivate_key(String private_key) {
        this.private_key = private_key;
    }

    public String getAli_public_key() {
        return ali_public_key;
    }

    public void setAli_public_key(String ali_public_key) {
        this.ali_public_key = ali_public_key;
    }

    public String getBaseUrl() {
        return baseUrl;
    }

    public void setBaseUrl(String baseUrl) {
        this.baseUrl = baseUrl;
    }
}
