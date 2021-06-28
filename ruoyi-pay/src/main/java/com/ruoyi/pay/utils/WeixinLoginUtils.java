package com.ruoyi.pay.utils;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.ruoyi.pay.config.WechatConfig;
import org.apache.commons.lang3.StringUtils;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.UnsupportedEncodingException;
import java.util.Map;

import org.apache.commons.codec.binary.Base64;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @Description : 微信登录工具
 * @Author : yuanhualiang
 * @Date: 2020-09-12 12:14
 */
public class WeixinLoginUtils {

    private final static String UTF_8 = "UTF-8";
    
    private final static String OPENID_TOKEN = "OPENID_TOKEN";

    private static final Logger LOG = LoggerFactory.getLogger(WeixinLoginUtils.class);

    public static String thirtypartyUserLogin(HttpServletRequest request, HttpServletResponse response) {
        // 微信，alipay登录入口
        String openId = getOpenIdFromCookie(request, response);

        LOG.info(">>>>>>>>>>>>>>>通过cookie获取openId:" + openId);

        LOG.info(">>>>>>>>>>>>>>>微信认证CODE:" + request.getParameter("code"));

        if (StringUtils.isEmpty(openId) && StringUtils.isNotEmpty(request.getParameter("code"))) {
            openId = weixinLogin(request, response);
            LOG.info(">>>>>>>>>>>>>>>微信获取OPENID:" + openId);
        }

        return openId;
    }

    /**
     * 微信登录
     *
     * @param request
     * @param response
     */
    private static String weixinLogin(HttpServletRequest request, HttpServletResponse response) {
        String code = request.getParameter("code");
        String openId = getOpenIdFromWeixin(code);
        if (StringUtils.isNotEmpty(openId) && !"null".equals(openId)) {
            try {
                CookieUtils.setCookie(response,
                        OPENID_TOKEN, new String(Base64.encodeBase64(openId.getBytes(UTF_8)), UTF_8),
                        Integer.MAX_VALUE);
            } catch (UnsupportedEncodingException e) {
                e.printStackTrace();
            }
        }
        return openId;
    }

    /**
     * 通过cookie获取OPENID
     *
     * @param request
     * @param response
     * @return
     */
    public static String getOpenIdFromCookie(HttpServletRequest request, HttpServletResponse response) {
        String openId = CookieUtils.getCookieValue(request, OPENID_TOKEN);
        if (StringUtils.isNotEmpty(openId)) {
            try {
                return new String(Base64.decodeBase64(openId.getBytes(UTF_8)), UTF_8);
            } catch (UnsupportedEncodingException e) {
                e.printStackTrace();
            }
        }
        return "";
    }

    /**
     * 通过微信授权获取openId
     *
     * @param code
     * @return
     */
    public static String getOpenIdFromWeixin(String code) {
        String url = "https://api.weixin.qq.com/sns/oauth2/access_token?" +
                "appid=" + WechatConfig.appId + "" +
                "&secret=" + WechatConfig.appsecret + "" +
                "&code=" + code + "&grant_type=authorization_code";
        JSONObject jsonObject = WeixinMessageUtil.httpRequestForSSL(url, "POST", null);
        LOG.info(">>>>>>>>>>>>>>>微信access_token获取结果:" + jsonObject + "，地址：" + url);
        String openId = null;
        if (jsonObject != null) {
            Map map = (Map) JSONObject.parseObject(JSONObject.toJSONString(jsonObject), Map.class);
            openId = String.valueOf(map.get("openid"));
        }
        return openId;
    }
}
