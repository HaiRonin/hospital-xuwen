package com.ruoyi.his.remote;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.ruoyi.common.exception.HisException;
import com.ruoyi.common.utils.RedisUtil;
import com.ruoyi.common.utils.StringUtils;
import com.ruoyi.common.utils.bean.BeanUtils;
import com.ruoyi.his.config.HealthCarConfig;
import com.ruoyi.his.constant.Constants;
import com.ruoyi.his.remote.request.healthcard.DynamicQRCodeResquest;
import com.ruoyi.his.remote.request.healthcard.RegisterResquest;
import com.ruoyi.his.remote.response.healthcard.*;
import com.tencent.healthcard.impl.HealthCardServerImpl;
import com.tencent.healthcard.model.CommonIn;
import com.tencent.healthcard.model.HealthCardInfo;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public class HealthCardServicelmpl implements HealthCardService {

    private static final Logger LOG = LoggerFactory.getLogger(HealthCardServicelmpl.class);

    @Autowired
    private HealthCarConfig healthCarConfig;
    @Autowired
    private RedisUtil redisUtil;


    private HealthCardServerImpl healthCardServer;

    public HealthCardServerImpl gethealthCard() {
        if (null == healthCardServer) {
            healthCardServer = new HealthCardServerImpl(healthCarConfig.appSecret, 5, 10);
        }
        return healthCardServer;
    }


    /***
     * 构建公共请求头
     * @return
     */
    public CommonIn buildCommonIn() {
        String requestId = UUID.randomUUID().toString().replaceAll("-", "").toUpperCase();
        CommonIn commonIn = new CommonIn(getToken(), requestId, healthCarConfig.hospitalId, healthCarConfig.channelNum);
        return commonIn;
    }

    @Override
    public String getToken() {
        String tokenKey = Constants.HEALTHOPEN_HEALTHCARD_HEALTHOPENAUTH_TOKEN;
        Object tokenObj = redisUtil.get(tokenKey);
        if (null != tokenObj) {
            return tokenObj.toString();
        }
        //设置新的token
        String requestId = UUID.randomUUID().toString().replaceAll("-", "").toUpperCase();
        CommonIn commonIn = new CommonIn("", requestId, healthCarConfig.hospitalId, healthCarConfig.channelNum);
        //调用接口
        JSONObject appTokenObj = gethealthCard().getAppToken(commonIn, healthCarConfig.appId);
        if (StringUtils.isEmpty(appTokenObj)) {
            throw new HisException("调用微信电子健康开放平台发生网络异常，请稍后再试");
        }
        HealthCardResponse response = JSON.parseObject(appTokenObj.toJSONString(), HealthCardResponse.class);
        if (!response.getCommonOut().isOk()) {
            throw new HisException("获取健康码token时发生异常:" + response.getCommonOut().getErrMsg());
        }
        TokenResponse tokenResponse = JSON.parseObject(response.getRsp(), TokenResponse.class);
        redisUtil.set(Constants.HEALTHOPEN_HEALTHCARD_HEALTHOPENAUTH_TOKEN, tokenResponse.getAppToken(), tokenResponse.getExpiresIn() - 60);
        return tokenResponse.getAppToken();
    }


    @Override
    public RegisterResponse registerHealthCard(RegisterResquest registerResquest) {
        HealthCardInfo healthCardInfo = new HealthCardInfo();
        BeanUtils.copyBeanProp(healthCardInfo, registerResquest);

        JSONObject json = gethealthCard().registerHealthCard(buildCommonIn(), healthCardInfo);
        if (StringUtils.isEmpty(json)) {
            throw new HisException("调用微信电子健康开放平台发生网络异常，请稍后再试");
        }
        HealthCardResponse response = JSON.parseObject(json.toJSONString(), HealthCardResponse.class);
        if (!response.getCommonOut().isOk()) {
            throw new HisException("注册健康码时发生异常:" + response.getCommonOut().getErrMsg());
        }
        RegisterResponse registerResponse = JSON.parseObject(response.getRsp(), RegisterResponse.class);
        return registerResponse;
    }


    @Override
    public CardGetResponse getHealthCardByHealthCode(String healthCode) {
        JSONObject json = gethealthCard().getHealthCardByHealthCode(buildCommonIn(), healthCode);
        if (StringUtils.isEmpty(json)) {
            throw new HisException("调用微信电子健康开放平台发生网络异常，请稍后再试");
        }
        LOG.info("getHealthCardByHealthCode.healthCode={},response={}",healthCode,json.toString());
        HealthCardResponse response = JSON.parseObject(json.toJSONString(), HealthCardResponse.class);
        if (!response.getCommonOut().isOk()) {
            throw new HisException("获取健康码时发生异常:" + response.getCommonOut().getErrMsg());
        }
        CardGetResponse cardGetResponse = JSON.parseObject(response.getRsp(), CardGetResponse.class);
        return cardGetResponse;
    }


    @Override
    public DynamicQRCodeResponse getDynamicQRCode(DynamicQRCodeResquest dynamicQRCodeResquest) {
        JSONObject json = gethealthCard().getDynamicQRCode(buildCommonIn(),
                dynamicQRCodeResquest.getHealthCardId(), dynamicQRCodeResquest.getIdType(),
                dynamicQRCodeResquest.getIdNumber(), dynamicQRCodeResquest.getCodeType());
        if (StringUtils.isEmpty(json)) {
            throw new HisException("调用微信电子健康开放平台发生网络异常，请稍后再试");
        }
        HealthCardResponse response = JSON.parseObject(json.toJSONString(), HealthCardResponse.class);
        if (!response.getCommonOut().isOk()) {
            throw new HisException("获取健康卡二维码时发生异常:" + response.getCommonOut().getErrMsg());
        }
        DynamicQRCodeResponse qrCodeResponse = JSON.parseObject(response.getRsp(), DynamicQRCodeResponse.class);
        return qrCodeResponse;
    }

    @Override
    public OcrInfoResponse getOcrInfo(String imageContent) {
        JSONObject json = gethealthCard().ocrInfo(buildCommonIn(), imageContent);
        if (StringUtils.isEmpty(json)) {
            throw new HisException("调用微信电子健康开放平台发生网络异常，请稍后再试");
        }
        LOG.info(">>>>>>>>>>>>>>调用健康卡身份证照片OCR接口结果1：" + JSON.toJSONString(json));
        HealthCardResponse response = JSON.parseObject(json.toJSONString(), HealthCardResponse.class);
        LOG.info(">>>>>>>>>>>>>>调用健康卡身份证照片OCR接口结果2：" + JSON.toJSONString(response));
        if (!response.getCommonOut().isOk()) {
            throw new HisException("调用健康卡身份证照片OCR接口发生异常:" + response.getCommonOut().getErrMsg());
        }
        OcrInfoResponse ocrInfoResponse = JSON.parseObject(response.getRsp()).getObject("cardInfo", OcrInfoResponse.class);
        return ocrInfoResponse;
    }

    @Override
    public String getOrderIdByOutAppId(String imageContent) {
        JSONObject json = gethealthCard().getOrderIdByOutAppId(buildCommonIn(), healthCarConfig.getAppId(), imageContent);
        if (StringUtils.isEmpty(json)) {
            throw new HisException("调用微信电子健康开放平台发生网络异常，请稍后再试");
        }
        LOG.info(">>>>>>>>>>>>>>调用获取卡包订单ID接口接口结果1：" + JSON.toJSONString(json));
        HealthCardResponse response = JSON.parseObject(json.toJSONString(), HealthCardResponse.class);
        LOG.info(">>>>>>>>>>>>>>调用获取卡包订单ID接口接口结果2：" + JSON.toJSONString(response));
        if (!response.getCommonOut().isOk()) {
            throw new HisException("调用健康卡身份证照片OCR接口发生异常:" + response.getCommonOut().getErrMsg());
        }
        return JSON.parseObject(response.getRsp()).getString("orderId");
    }


}
