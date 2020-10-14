package com.ruoyi.pay.utils;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.ruoyi.common.utils.RedisUtil;
import com.ruoyi.pay.config.WechatConfig;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Map;

/**
 * @Description : 微信登录工具
 * @Author : yuanhualiang
 * @Date: 2020-09-12 12:14
 */
public class WeixinLoginUtils {

    private final static String UTF_8 = "UTF-8";

    private final static String OPENID_TOKEN = "OPENID_TOKEN";

    private static final Logger LOG = LoggerFactory.getLogger(WeixinLoginUtils.class);

    private static final String QUERY_USER_INFO_URL = "https://api.weixin.qq.com/cgi-bin/user/info?access_token=ACCESS_TOKEN&openid=OPENID&lang=zh_CN";


    /**
     * @param redisUtil
     * @return
     */
    public static String getOpenId(String code, RedisUtil redisUtil) {
        // 微信，alipay登录入口
        String openId = "";
        LOG.info(">>>>>>>>>>>>>>>微信认证CODE:" + code);
        if (StringUtils.isNotEmpty(code)) {
            Object cacheOpenId = redisUtil.get(OPENID_TOKEN + "_" + code);
            if (null != cacheOpenId) {
                LOG.info(">>>>>>>>>>>>>>>命中缓存获取OPENID:" + cacheOpenId);
                return (String) cacheOpenId;
            }

            openId = getOpenIdFromWeixin(code, redisUtil);
            LOG.info(">>>>>>>>>>>>>>>微信获取OPENID:" + openId);
        }

        return openId;
    }

    /**
     * 微信登录
     *
     * @param code
     * @param redisUtil
     * @return
     */
    private static String getOpenIdFromWeixin(String code, RedisUtil redisUtil) {
        String openId = getOpenIdFromWeixin(code);
        if (StringUtils.isNotEmpty(openId) && !"null".equals(openId)) {
            redisUtil.set(OPENID_TOKEN + "_" + code, openId, Integer.MAX_VALUE);
        }
        return openId;
    }

    /**
     * 通过微信授权获取openId
     *
     * @param code
     * @return
     */
    private static String getOpenIdFromWeixin(String code) {
        String url = "https://api.weixin.qq.com/sns/oauth2/access_token?" +
                "appid=" + WechatConfig.appId + "" +
                "&secret=" + WechatConfig.appsecret + "" +
                "&code=" + code + "&grant_type=authorization_code";
        JSONObject jsonObject = WeixinMessageUtil.httpRequestForSSL(url, "POST", null);
        LOG.info(">>>>>>>>>>>>>>>微信open_id获取结果:" + jsonObject + "，地址：" + url);
        String openId = null;
        if (jsonObject != null) {
            Map map = (Map) JSONObject.parseObject(JSONObject.toJSONString(jsonObject), Map.class);
            openId = String.valueOf(map.get("openid"));
        }
        return openId;
    }

    /**
     * 获取公众号用户信息
     *
     * @param code
     * @param redisUtil
     * @return
     */
    public static WeixinUserInfo getUserInfoFromWexin(String code, RedisUtil redisUtil) {
        String openId = getOpenId(code, redisUtil);
        String accessToken = WeixinMessageUtil.getAccessToken(redisUtil);
        String url = QUERY_USER_INFO_URL.replace("ACCESS_TOKEN", accessToken).replace("OPENID", openId);
        JSONObject jsonObject = WeixinMessageUtil.httpRequestForSSL(url, "GET", null);
        LOG.info(">>>>>>>>>>>>>>>>>>>>>>>获取公众号用户信息：" + JSON.toJSONString(jsonObject));
        if (null != jsonObject) {
            return (WeixinUserInfo) JSONObject.parseObject(JSON.toJSONString(jsonObject), WeixinUserInfo.class);
        }
        return null;
    }
}
