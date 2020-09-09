package com.ruoyi.his.remote;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.ruoyi.common.exception.HisException;
import com.ruoyi.common.utils.RedisUtil;
import com.ruoyi.common.utils.StringUtils;
import com.ruoyi.his.config.HealthCarConfig;
import com.ruoyi.his.constant.Constants;
import com.ruoyi.his.remote.response.healthcard.HealthCardGetResponse;
import com.ruoyi.his.remote.response.healthcard.HealthCardRegisterResponse;
import com.ruoyi.his.remote.response.healthcard.HealthCardResponse;
import com.ruoyi.his.remote.response.healthcard.HealthCardTokenResponse;
import com.tencent.healthcard.impl.HealthCardServerImpl;
import com.tencent.healthcard.model.CommonIn;
import com.tencent.healthcard.model.HealthCardInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public class HealthCardServicelmpl implements HealthCardService {
    @Autowired
    private HealthCarConfig healthCarConfig;
    @Autowired
    private RedisUtil redisUtil;


    private HealthCardServerImpl healthCardServer;

    public HealthCardServerImpl gethealthCard() {
        if(null == healthCardServer){
            healthCardServer = new HealthCardServerImpl(healthCarConfig.appSecret, 5, 10);
        }
        return healthCardServer;
    }


    /***
     * 构建公共请求头
     * @return
     */
    public CommonIn buildCommonIn(){
        String requestId = UUID.randomUUID().toString().replaceAll("-", "").toUpperCase();
        CommonIn commonIn = new CommonIn(getToken(), requestId, healthCarConfig.hospitalId, healthCarConfig.channelNum);
        return commonIn;
    }

    @Override
    public String getToken() {
        String tokenKey = Constants.HEALTHOPEN_HEALTHCARD_HEALTHOPENAUTH_TOKEN;
        Object tokenObj = redisUtil.get(tokenKey);
        if(null != tokenObj){
            return tokenObj.toString();
        }
        //设置新的token
        String requestId = UUID.randomUUID().toString().replaceAll("-", "").toUpperCase();
        CommonIn commonIn = new CommonIn("", requestId, healthCarConfig.hospitalId, healthCarConfig.channelNum);
        //调用接口
        JSONObject appTokenObj = gethealthCard().getAppToken(commonIn, healthCarConfig.appId);
        if(StringUtils.isEmpty(appTokenObj)){
            throw new HisException("调用微信电子健康开放平台发生网络异常，请稍后再试");
        }
        HealthCardResponse response = JSON.parseObject(appTokenObj.toJSONString(),HealthCardResponse.class);
        if(!response.getCommonOut().isOk()){
            throw new HisException("获取健康码token时发生异常:"+response.getCommonOut().getErrMsg());
        }
        HealthCardTokenResponse tokenResponse = JSON.parseObject(response.getRsp(),HealthCardTokenResponse.class);
        redisUtil.set(Constants.HEALTHOPEN_HEALTHCARD_HEALTHOPENAUTH_TOKEN,tokenResponse.getAppToken(),tokenResponse.getExpiresIn()-60);
        return tokenResponse.getAppToken();
    }


    @Override
    public HealthCardRegisterResponse registerHealthCard(HealthCardInfo healthCardInfo) {
        JSONObject json = gethealthCard().registerHealthCard(buildCommonIn(),healthCardInfo);
        if(StringUtils.isEmpty(json)){
            throw new HisException("调用微信电子健康开放平台发生网络异常，请稍后再试");
        }
        HealthCardResponse response = JSON.parseObject(json.toJSONString(),HealthCardResponse.class);
        if(!response.getCommonOut().isOk()){
            throw new HisException("注册健康码时发生异常:"+response.getCommonOut().getErrMsg());
        }
        HealthCardRegisterResponse registerResponse = JSON.parseObject(response.getRsp(),HealthCardRegisterResponse.class);
        return registerResponse;
    }


    @Override
    public HealthCardGetResponse getHealthCardByHealthCode(String healthCode) {
        JSONObject json = gethealthCard().getHealthCardByHealthCode(buildCommonIn(),healthCode);
        if(StringUtils.isEmpty(json)){
            throw new HisException("调用微信电子健康开放平台发生网络异常，请稍后再试");
        }
        HealthCardResponse response = JSON.parseObject(json.toJSONString(),HealthCardResponse.class);
        if(!response.getCommonOut().isOk()){
            throw new HisException("获取健康码时发生异常:"+response.getCommonOut().getErrMsg());
        }
        HealthCardGetResponse healthCardGetResponse = JSON.parseObject(response.getRsp(),HealthCardGetResponse.class);
        return healthCardGetResponse;
    }
}
