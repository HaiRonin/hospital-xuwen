package com.ruoyi.utils.pay;

import javax.net.ssl.X509TrustManager;
import java.security.cert.CertificateException;
import java.security.cert.X509Certificate;

/**
 * @Description : 证书信任器
 * @Author : yuanhualiang
 * @Date: 2020-09-12 12:24
 */
public class MyX509TrustManager implements X509TrustManager {
    public void checkClientTrusted(X509Certificate[] chain, String authType)
            throws CertificateException {
    }

    public void checkServerTrusted(X509Certificate[] chain, String authType)
            throws CertificateException {
    }

    public X509Certificate[] getAcceptedIssuers() {
        return null;
    }
}
