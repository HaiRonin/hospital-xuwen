package com.ruoyi.his.remote.response.healthcard;

import java.io.Serializable;

/**
 * Created by ASUS on 2019/1/25.
 */
public class CommonResponse implements Serializable{


    /**
     * requestId : E856812660584E208D7421E1CAACE8C3
     * resultCode : 0
     * errMsg : success
     */

    private String requestId;
    private int resultCode;
    private String errMsg;

    public boolean isOk(){
        return 0 == this.resultCode;
    }

    public String getRequestId() {
        return requestId;
    }

    public void setRequestId(String requestId) {
        this.requestId = requestId;
    }

    public int getResultCode() {
        return resultCode;
    }

    public void setResultCode(int resultCode) {
        this.resultCode = resultCode;
    }

    public String getErrMsg() {
        return errMsg;
    }

    public void setErrMsg(String errMsg) {
        this.errMsg = errMsg;
    }
}
