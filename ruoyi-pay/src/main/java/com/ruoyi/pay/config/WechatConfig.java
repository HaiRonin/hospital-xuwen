package com.ruoyi.pay.config;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

/**
 * Swagger2的接口配置
 *
 * @author ruoyi
 */

@Configuration
@ConfigurationProperties(prefix = "his.wechat")
public class WechatConfig {

    public static String appId;

    public static String appsecret;

    public static String mchid;

    public static String key;

    public static String baseUrl;

    public static String refundUrl;

    public static String unifiedorderUrl;

    public static String refundCert;

    public String getAppId() {
        return appId;
    }

    public void setAppId(String appId) {
        this.appId = appId;
    }

    public String getAppsecret() {
        return appsecret;
    }

    public void setAppsecret(String appsecret) {
        this.appsecret = appsecret;
    }

    public String getMchid() {
        return mchid;
    }

    public void setMchid(String mchid) {
        this.mchid = mchid;
    }

    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }

    public String getBaseUrl() {
        return baseUrl;
    }

    public void setBaseUrl(String baseUrl) {
        this.baseUrl = baseUrl;
    }

    public String getRefundUrl() {
        return refundUrl;
    }

    public void setRefundUrl(String refundUrl) {
        this.refundUrl = refundUrl;
    }

    public String getUnifiedorderUrl() {
        return unifiedorderUrl;
    }

    public void setUnifiedorderUrl(String unifiedorderUrl) {
        this.unifiedorderUrl = unifiedorderUrl;
    }

    public String getRefundCert() {
        return refundCert;
    }

    public void setRefundCert(String refundCert) {
        this.refundCert = refundCert;
    }
}
