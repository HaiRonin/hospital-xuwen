package com.ruoyi.his.remote;

import com.ruoyi.his.remote.request.healthcard.DynamicQRCodeResquest;
import com.ruoyi.his.remote.request.healthcard.RegisterResquest;
import com.ruoyi.his.remote.response.healthcard.CardGetResponse;
import com.ruoyi.his.remote.response.healthcard.DynamicQRCodeResponse;
import com.ruoyi.his.remote.response.healthcard.RegisterResponse;

public interface HealthCardService {

    /**
     * 获取token
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
}
