package com.ruoyi.vo;

import java.io.Serializable;

/**
 * <p>
 * 订单支付完成后回写对象
 * </p>
 *
 * @author whl
 * @since 2020-08-20
 */

public class OrderPayResultBO implements Serializable {

    /**
     * 订单类型，对应HisBusinessTypeEnum类中的key
     */
    private String orderType;

    /***
     * 订单号
     */
    private String outTradeNo;

    /**
     * 交易流水号，微信支付回调返回
     */
    private String transactionId;

    /***
     * 支付结果：true：成功，false：失败
     */
    private boolean paymentResults;

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

    public boolean isPaymentResults() {
        return paymentResults;
    }

    public void setPaymentResults(boolean paymentResults) {
        this.paymentResults = paymentResults;
    }
}
