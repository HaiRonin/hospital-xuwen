package com.ruoyi.his.domain;

import java.math.BigDecimal;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.ruoyi.common.annotation.Excel;
import com.ruoyi.common.core.domain.BaseEntity;

/**
 * 押金补缴对象 his_deposit_payment
 * 
 * @author whl
 * @date 2020-08-29
 */
public class DepositPayment extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** null */
    private Long id;

    /** 用户名 */
    @Excel(name = "用户名")
    private String synUserName;

    /** 效验码 */
    @Excel(name = "效验码")
    private String synKey;

    /** 住院号 */
    @Excel(name = "住院号")
    private String inHosNo;

    /** 支付类型 */
    @Excel(name = "支付类型")
    private String payType;

    /** 支付交易流水号 */
    @Excel(name = "支付交易流水号")
    private String payRecord;

    /** 预交金金额 */
    @Excel(name = "预交金金额")
    private BigDecimal payMoney;

    /** 支付交易返回 */
    @Excel(name = "支付交易返回")
    private String payReturn;

    /** 支付状态 */
    @Excel(name = "支付状态")
    private String successfulPayment;

    /** 终端号 */
    @Excel(name = "终端号")
    private String terminalCode;

    /** 商户订单号 */
    @Excel(name = "商户订单号")
    private String outTradeNo;

    /** 微信订单号 */
    @Excel(name = "微信订单号")
    private String transactionId;

    /** 商户id */
    @Excel(name = "商户id")
    private String appId;

    /** 床号 */
    @Excel(name = "床号")
    private String bedNo;

    /** 患都姓名 */
    @Excel(name = "患都姓名")
    private String patientName;

    /** 科室名称 */
    @Excel(name = "科室名称")
    private String departmentName;

    /** 押金余额 */
    @Excel(name = "押金余额")
    private BigDecimal depositMoney;

    /** 医保类型名称 */
    @Excel(name = "医保类型名称")
    private String medicalTypeName;

    /** his接口返回信息 */
    @Excel(name = "his接口返回信息")
    private String resultMsg;

    /** his接口返回信息 */
    @Excel(name = "his接口返回信息")
    private String resultCode;

    public void setId(Long id) 
    {
        this.id = id;
    }

    public Long getId() 
    {
        return id;
    }
    public void setSynUserName(String synUserName) 
    {
        this.synUserName = synUserName;
    }

    public String getSynUserName() 
    {
        return synUserName;
    }
    public void setSynKey(String synKey) 
    {
        this.synKey = synKey;
    }

    public String getSynKey() 
    {
        return synKey;
    }
    public void setInHosNo(String inHosNo) 
    {
        this.inHosNo = inHosNo;
    }

    public String getInHosNo() 
    {
        return inHosNo;
    }
    public void setPayType(String payType) 
    {
        this.payType = payType;
    }

    public String getPayType() 
    {
        return payType;
    }
    public void setPayRecord(String payRecord) 
    {
        this.payRecord = payRecord;
    }

    public String getPayRecord() 
    {
        return payRecord;
    }
    public void setPayMoney(BigDecimal payMoney) 
    {
        this.payMoney = payMoney;
    }

    public BigDecimal getPayMoney() 
    {
        return payMoney;
    }
    public void setPayReturn(String payReturn) 
    {
        this.payReturn = payReturn;
    }

    public String getPayReturn() 
    {
        return payReturn;
    }
    public void setSuccessfulPayment(String successfulPayment) 
    {
        this.successfulPayment = successfulPayment;
    }

    public String getSuccessfulPayment() 
    {
        return successfulPayment;
    }
    public void setTerminalCode(String terminalCode) 
    {
        this.terminalCode = terminalCode;
    }

    public String getTerminalCode() 
    {
        return terminalCode;
    }
    public void setOutTradeNo(String outTradeNo) 
    {
        this.outTradeNo = outTradeNo;
    }

    public String getOutTradeNo() 
    {
        return outTradeNo;
    }
    public void setTransactionId(String transactionId) 
    {
        this.transactionId = transactionId;
    }

    public String getTransactionId() 
    {
        return transactionId;
    }
    public void setAppId(String appId) 
    {
        this.appId = appId;
    }

    public String getAppId() 
    {
        return appId;
    }
    public void setBedNo(String bedNo) 
    {
        this.bedNo = bedNo;
    }

    public String getBedNo() 
    {
        return bedNo;
    }
    public void setPatientName(String patientName) 
    {
        this.patientName = patientName;
    }

    public String getPatientName() 
    {
        return patientName;
    }
    public void setDepartmentName(String departmentName) 
    {
        this.departmentName = departmentName;
    }

    public String getDepartmentName() 
    {
        return departmentName;
    }
    public void setDepositMoney(BigDecimal depositMoney) 
    {
        this.depositMoney = depositMoney;
    }

    public BigDecimal getDepositMoney() 
    {
        return depositMoney;
    }
    public void setMedicalTypeName(String medicalTypeName) 
    {
        this.medicalTypeName = medicalTypeName;
    }

    public String getMedicalTypeName() 
    {
        return medicalTypeName;
    }
    public void setResultMsg(String resultMsg) 
    {
        this.resultMsg = resultMsg;
    }

    public String getResultMsg() 
    {
        return resultMsg;
    }
    public void setResultCode(String resultCode) 
    {
        this.resultCode = resultCode;
    }

    public String getResultCode() 
    {
        return resultCode;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this,ToStringStyle.MULTI_LINE_STYLE)
            .append("id", getId())
            .append("synUserName", getSynUserName())
            .append("synKey", getSynKey())
            .append("inHosNo", getInHosNo())
            .append("payType", getPayType())
            .append("payRecord", getPayRecord())
            .append("payMoney", getPayMoney())
            .append("payReturn", getPayReturn())
            .append("successfulPayment", getSuccessfulPayment())
            .append("terminalCode", getTerminalCode())
            .append("outTradeNo", getOutTradeNo())
            .append("transactionId", getTransactionId())
            .append("appId", getAppId())
            .append("createTime", getCreateTime())
            .append("updateTime", getUpdateTime())
            .append("bedNo", getBedNo())
            .append("patientName", getPatientName())
            .append("departmentName", getDepartmentName())
            .append("depositMoney", getDepositMoney())
            .append("medicalTypeName", getMedicalTypeName())
            .append("resultMsg", getResultMsg())
            .append("resultCode", getResultCode())
            .toString();
    }
}
