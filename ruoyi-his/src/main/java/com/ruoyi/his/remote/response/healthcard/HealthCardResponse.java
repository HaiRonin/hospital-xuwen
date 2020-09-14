package com.ruoyi.his.remote.response.healthcard;

import java.io.Serializable;

/**
 * Created by ASUS on 2019/1/25.
 */
public class HealthCardResponse  implements Serializable{

    private CommonResponse commonOut;

    private String rsp;

    public CommonResponse getCommonOut() {
        return commonOut;
    }

    public void setCommonOut(CommonResponse commonOut) {
        this.commonOut = commonOut;
    }

    public String getRsp() {
        return rsp;
    }

    public void setRsp(String rsp) {
        this.rsp = rsp;
    }
}
