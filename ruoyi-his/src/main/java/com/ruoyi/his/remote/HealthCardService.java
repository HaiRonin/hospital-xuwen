package com.ruoyi.his.remote;

import com.ruoyi.his.remote.response.healthcard.HealthCardGetResponse;
import com.ruoyi.his.remote.response.healthcard.HealthCardRegisterResponse;
import com.tencent.healthcard.model.HealthCardInfo;

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
    HealthCardRegisterResponse registerHealthCard(HealthCardInfo healthCardInfo);

    /***
     * 通过健康卡授权码获取健康卡接口
     * @param healthCode
     * @return
     */
    HealthCardGetResponse getHealthCardByHealthCode(String healthCode);
}
