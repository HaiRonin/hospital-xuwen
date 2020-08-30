package com.ruoyi.his.remote;

import com.ruoyi.his.remote.response.BaseResponse;

public interface HisWebServices {

    /***
     * 调用his接口下单
     * @param outTradeNo 订单号
     * @return
     */
    public BaseResponse invokeCallSubmit(String outTradeNo);

}
