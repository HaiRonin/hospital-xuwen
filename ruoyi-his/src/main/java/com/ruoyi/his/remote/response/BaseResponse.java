package com.ruoyi.his.remote.response;

import java.io.Serializable;

/**
 * Created by ASUS on 2019/1/25.
 */
public class BaseResponse implements Serializable{
    /**
     * 00：表示成功，
        其他表示失败
     */
   private String resultCode;

    /***
     * 返回信息
     */
   private String resultMsg;

   public boolean isOk(){
       return "00".equals(this.getResultCode());
   }

    public BaseResponse(String resultCode, String resultMsg){
        this.resultCode=resultCode;
        this.resultMsg = resultMsg;
    }

    public String getResultCode() {
        return resultCode;
    }

    public void setResultCode(String resultCode) {
        this.resultCode = resultCode;
    }

    public String getResultMsg() {
        return resultMsg;
    }

    public void setResultMsg(String resultMsg) {
        this.resultMsg = resultMsg;
    }

}
