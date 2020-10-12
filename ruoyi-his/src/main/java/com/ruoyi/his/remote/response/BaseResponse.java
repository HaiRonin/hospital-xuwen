package com.ruoyi.his.remote.response;

import java.io.Serializable;

/**
 * Created by ASUS on 2019/1/25.
 */
public class BaseResponse implements Serializable{
    /**
     * 00：表示成功，
        其他表示失败

     -9999:自定义的返回码，代表调用his失败，需要重新尝试
     */
   private String resultCode;

    /***
     * 返回信息
     */
   private String resultMsg;

   public boolean isOk(){
       //400与00都代表成功，400是重复下单且成功的订单
       return "00".equals(this.getResultCode()) || "400".equals(this.getResultCode());
   }

   public void error(String errorMsg){
       this.resultCode="-1";
       this.resultMsg = errorMsg;
   }

    public static BaseResponse success(){
        return new BaseResponse("00","操作成功");
    }
    public static BaseResponse success(String msg){
        return new BaseResponse("00",msg);
    }


    public static BaseResponse fail(){
        return new BaseResponse("-1","操作失败");
    }
    public static BaseResponse fail(String msg){
        return new BaseResponse("-1",msg);
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
