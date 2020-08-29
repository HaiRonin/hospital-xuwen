package com.ruoyi.his.remote;

import com.ruoyi.his.remote.response.BaseResponse;

public interface HisWebServices {

    /***
     * 调用his接口下单
     * @param id
     * @return
     */
    public BaseResponse invokeCallSubmit(Long id);

}
