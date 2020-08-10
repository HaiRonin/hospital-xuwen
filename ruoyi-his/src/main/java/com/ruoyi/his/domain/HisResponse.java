package com.ruoyi.his.domain;

import java.io.Serializable;

/**
 * Created by ASUS on 2019/1/25.
 */
public class HisResponse implements Serializable{
    /**
     * 00：表示成功，
        其他表示失败
     */
   private String resultCode;

    /***
     * 返回信息
     */
   private String resultMsg;

    /**
     * 返回消息内容 SON字符串
     */
   private String regDoctor;

   public boolean isOk(){
       return "00".equals(this.getResultCode());
   }

    public HisResponse (String resultCode, String resultMsg){
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

    public String getRegDoctor() {
        return regDoctor;
    }

    public void setRegDoctor(String regDoctor) {
        this.regDoctor = regDoctor;
    }
}
