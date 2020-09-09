package com.ruoyi.his.remote.response.healthcard;

import java.io.Serializable;

/**
 * Created by ASUS on 2019/1/25.
 */
public class HealthCardResponse  implements Serializable{

    private HealthCardCommonResponse commonOut;

    private String rsp;

    public HealthCardCommonResponse getCommonOut() {
        return commonOut;
    }

    public void setCommonOut(HealthCardCommonResponse commonOut) {
        this.commonOut = commonOut;
    }

    public String getRsp() {
        return rsp;
    }

    public void setRsp(String rsp) {
        this.rsp = rsp;
    }
}
