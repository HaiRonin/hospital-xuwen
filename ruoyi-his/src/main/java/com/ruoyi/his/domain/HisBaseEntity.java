package com.ruoyi.his.domain;

import com.ruoyi.common.annotation.Excel;
import com.ruoyi.common.core.domain.BaseEntity;

/**
 * Entity基类
 * 
 * @author ruoyi
 */
public class HisBaseEntity extends BaseEntity
{
    private static final long serialVersionUID = 1L;
    /** 支付状态 */
    @Excel(name = "支付状态")
    private String successfulPayment;

    /** 支付交易流水号 */
    @Excel(name = "支付交易流水号")
    private String outTradeNo;

    /** 微信订单号 */
    @Excel(name = "微信订单号")
    private String transactionId;

    public String getSuccessfulPayment() {
        return successfulPayment;
    }

    public void setSuccessfulPayment(String successfulPayment) {
        this.successfulPayment = successfulPayment;
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
}
