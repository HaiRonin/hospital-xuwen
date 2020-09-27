package com.ruoyi.his.remote;

public interface HisCallServices {

    /***
     * 调用his接口
     * @param apiUrl
     * @param dataParam
     * @return
     */
    String requestHisService(String apiUrl, String dataParam);
}
