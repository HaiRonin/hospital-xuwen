package com.ruoyi.common.model;

import java.io.Serializable;
import java.math.BigDecimal;

/***
 * HIS订单支付信息
 */
public class HisPayOrder implements Serializable {

    /**
     * 支付类型，对应枚举类：HisPayType
     */
    private String payType;

    /**
     * 支付类型，对应枚举类：HisOrderType
     */
    private String orderType;

    /***
     * his业务订单号
     */
    private String outTradeNo;

    /***
     * 交易流水号
     */
    private String transactionId;

    /**
     * 交易金额
     */
    private BigDecimal amount;

    /**
     * 微信公众号用户OPENID
     */
    private String openId;

    /**
     * H5支付重定向地址
     */
    private String redirectUrl;

    public String getPayType() {
        return payType;
    }

    public void setPayType(String payType) {
        this.payType = payType;
    }

    public String getOrderType() {
        return orderType;
    }

    public void setOrderType(String orderType) {
        this.orderType = orderType;
    }

    public String getOutTradeNo() {
        return outTradeNo;
    }

    public void setOutTradeNo(String outTradeNo) {
        this.outTradeNo = outTradeNo;
    }

    public String getTransactionId() {
        return transactionId;
    }

    public void setTransactionId(String transactionId) {
        this.transactionId = transactionId;
    }

    public BigDecimal getAmount() {
        return amount;
    }

    public void setAmount(BigDecimal amount) {
        this.amount = amount;
    }


    public String getOpenId() {
        return openId;
    }

    public void setOpenId(String openId) {
        this.openId = openId;
    }

    public String getRedirectUrl() {
        return redirectUrl;
    }

    public void setRedirectUrl(String redirectUrl) {
        this.redirectUrl = redirectUrl;
    }
}
