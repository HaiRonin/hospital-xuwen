package com.ruoyi.his.remote;

import com.alibaba.fastjson.JSONObject;
import com.ruoyi.his.remote.request.healthcard.DynamicQRCodeResquest;
import com.ruoyi.his.remote.request.healthcard.RegisterResquest;
import com.ruoyi.his.remote.request.healthcard.ReportDataResquest;
import com.ruoyi.his.remote.response.healthcard.CardGetResponse;
import com.ruoyi.his.remote.response.healthcard.DynamicQRCodeResponse;
import com.ruoyi.his.remote.response.healthcard.OcrInfoResponse;
import com.ruoyi.his.remote.response.healthcard.RegisterResponse;
import com.tencent.healthcard.model.ReportHISData;

public interface HealthCardService {

    /**
     * 获取token
     *
     * @return
     */
    String getToken();

    /***
     * 注册健康码
     * @param healthCardInfo
     * @return
     */
    RegisterResponse registerHealthCard(RegisterResquest healthCardInfo);

    /***
     * 通过健康卡授权码获取健康卡接口
     * @param healthCode
     * @return
     */
    CardGetResponse getHealthCardByHealthCode(String healthCode);

    /***
     * 获取健康卡二维码接口
     * @return
     */
    DynamicQRCodeResponse getDynamicQRCode(DynamicQRCodeResquest dynamicQRCodeResquest);


    /***
     * 身份证照片OCR接口
     * @return
     */
    OcrInfoResponse getOcrInfo(String imageContent);

    /**
     * 获取卡包订单ID接口
     *
     * @param qrCodeText
     * @return
     */
    String getOrderIdByOutAppId(String qrCodeText);

    /**
     * 接口用于接收医院上报的健康卡使用数据，适用于 用卡数据监测流程 ，
     * ISV可上报线上用卡数据和线下用卡数据。请求参数为用户信息和健康卡使用场景
     * @param reportDataResquest
     */
    void reportHISData(ReportDataResquest reportDataResquest);
}
