package com.ruoyi.his.healthcard;

import com.alibaba.fastjson.JSONObject;
import com.tencent.healthcard.impl.HealthCardServerImpl;
import com.tencent.healthcard.model.CommonIn;

import java.util.UUID;

public class TestAppToken {
    public static void main(String[] args) {
        /**
         * 接口：调用获取接口调用凭证appToken接口
         */
        //appSecret
        String appSecret = "fd4dd78ccd2210e2f9aa231a7b874699";
        //设置连接超时5s，请求超时10s，默认二者均为10s
        HealthCardServerImpl healthCard = new HealthCardServerImpl(appSecret, 5, 10);
        //另外一种方式设置连接、请求超时时间
        healthCard.setConnectTimeout(5);
        healthCard.setReadTimeout(10);
        //构造公共输入参数commonIn
        String appToken = "";
        String requestId = UUID.randomUUID().toString().replaceAll("-", "").toUpperCase();
        String hospitalId = "31676";
        int channelNum = 0;
        CommonIn commonIn = new CommonIn(appToken, requestId, hospitalId, channelNum);
        //构造请求参数req
        String appId = "786f063eed41c3e6da9f636bf48b3b1a";
        //调用接口
        JSONObject appTokenObj = healthCard.getAppToken(commonIn, appId);
        //可以单独对某一个方法设置连接、请求超时时间，单位均为s，连接超时时间3s，请求超时时间20s
        JSONObject appTokenObj2 = healthCard.getAppToken(commonIn, appId, 3, 20);
        //打印响应
        System.out.println("appToken:" + appTokenObj.toJSONString());
        System.out.println("appToken with timeout:" + appTokenObj2.toJSONString());


    }
}
