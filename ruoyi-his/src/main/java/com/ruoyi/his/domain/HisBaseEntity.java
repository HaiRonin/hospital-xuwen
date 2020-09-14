package com.ruoyi.his.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.ruoyi.common.annotation.Excel;
import com.ruoyi.common.core.domain.BaseEntity;
import io.swagger.annotations.ApiModelProperty;

import java.math.BigDecimal;
import java.util.Map;

/**
 * Entity基类
 *
 * @author ruoyi
 */
public class HisBaseEntity extends BaseEntity {
    private static final long serialVersionUID = 1L;

    /**
     * 返回信息
     */
    @Excel(name = "返回信息")
    @ApiModelProperty(value = "返回信息", hidden = true)
    private String resultMsg;

    /**
     * his接口返回信息
     */
    @Excel(name = "his接口返回码")
    @ApiModelProperty(value = "his接口返回码", hidden = true)
    private String resultCode;

    /**
     * 支付状态
     */
    @Excel(name = "支付状态")
    @ApiModelProperty(value = "支付状态", hidden = true)
    private String successfulPayment;

    /**
     * 支付订单号
     */
    @Excel(name = "支付订单号")
    @ApiModelProperty(value = "支付订单号", hidden = true)
    private String outTradeNo;

    /**
     * 支付流水订单号
     */
    @Excel(name = "支付流水号")
    @ApiModelProperty(value = "支付流水号", hidden = true)
    private String transactionId;

    /***
     * 枚举类：HisPayType
     */
    /**
     * 支付类型
     */
    @Excel(name = "支付类型")
    @ApiModelProperty(value = "支付类型")
    private String payType;

    /**
     * 交易金额
     */
    @ApiModelProperty(value = "交易金额", hidden = true)
    private BigDecimal amount;

    /***
     * 订单类型
     * 枚举类型：HisBusinessTypeEnum
     */
    @ApiModelProperty(value = "订单类型", hidden = true)
    private String orderType;

    //预支付签名参数
    @ApiModelProperty(value = "预支付签名参数", hidden = true)
    private Map<String, String> prePaySign;

    //用户OPENID
    @ApiModelProperty(value = "用户OPENID", hidden = true)
    private String openId;

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

    public String getPayType() {
        return payType;
    }

    public void setPayType(String payType) {
        this.payType = payType;
    }

    public BigDecimal getAmount() {
        return amount;
    }

    public void setAmount(BigDecimal amount) {
        this.amount = amount;
    }

    public String getOrderType() {
        return orderType;
    }

    public void setOrderType(String orderType) {
        this.orderType = orderType;
    }

    public String getResultMsg() {
        return resultMsg;
    }

    public void setResultMsg(String resultMsg) {
        this.resultMsg = resultMsg;
    }

    public String getResultCode() {
        return resultCode;
    }

    public void setResultCode(String resultCode) {
        this.resultCode = resultCode;
    }

    public String getOpenId() {
        return openId;
    }

    public void setOpenId(String openId) {
        this.openId = openId;
    }

    public Map<String, String> getPrePaySign() {
        return prePaySign;
    }

    public void setPrePaySign(Map<String, String> prePaySign) {
        this.prePaySign = prePaySign;
    }
}
