package com.ruoyi.his.remote.request;


import java.io.Serializable;

public class BaseRequest implements Serializable {


    /***
     * 用户名
     */
    private String synUserName = "";

    /***
     * 效验码
     */
    private String synKey = "";

    /** 支付类型 */
    private String payType="";

    public String getSynUserName() {
        return synUserName;
    }

    public void setSynUserName(String synUserName) {
        this.synUserName = synUserName;
    }

    public String getSynKey() {
        return synKey;
    }

    public void setSynKey(String synKey) {
        this.synKey = synKey;
    }

    public String getPayType() {
        return payType;
    }

    public void setPayType(String payType) {
        this.payType = payType;
    }

}
