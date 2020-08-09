package com.ruoyi.his.domain;

import java.math.BigDecimal;
import java.util.Date;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.ruoyi.common.annotation.Excel;
import com.ruoyi.common.core.domain.BaseEntity;

/**
 * 预约挂号对象 his_doreg_info
 * 
 * @author whl
 * @date 2020-08-08
 */
public class DoregInfo extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** 序号 */
    private Long id;

    /** 医生编号 */
    @Excel(name = "医生编号")
    private String organdoctorId;

    /** 患者身份证号 */
    @Excel(name = "患者身份证号")
    private String cardNo;

    /** 患者编号 */
    @Excel(name = "患者编号")
    private String patientNo;

    /** 社保号 */
    @Excel(name = "社保号")
    private String socialsecurityNO;

    /** 取号日期 */
    @Excel(name = "取号日期", width = 30, dateFormat = "yyyy-MM-dd")
    private Date sourceDate;

    /** 时间段标识 */
    @Excel(name = "时间段标识")
    private String timestypeNo;

    /** 时间段标识 */
    @Excel(name = "时间段标识")
    private Integer sourceTimeType;

    /** 支付流水号 */
    @Excel(name = "支付流水号")
    private String payNo;

    /** 支付金额 */
    @Excel(name = "支付金额")
    private BigDecimal payAmount;

    /** 商户ID */
    @Excel(name = "商户ID")
    private String appId;

    /** 创建时间 */
    @Excel(name = "创建时间", width = 30, dateFormat = "yyyy-MM-dd")
    private Date creatTime;

    /** 支付状态 */
    @Excel(name = "支付状态")
    private String successfulPayment;

    /** 商户订单号 */
    @Excel(name = "商户订单号")
    private String outTradeNo;

    /** 微信订单号 */
    @Excel(name = "微信订单号")
    private String transactionId;

    /** 科室id */
    @Excel(name = "科室id")
    private String departmentorganId;

    /** 支付方式 */
    @Excel(name = "支付方式")
    private String payType;

    /** 返回信息 */
    @Excel(name = "返回信息")
    private String resultMsg;

    public void setId(Long id) 
    {
        this.id = id;
    }

    public Long getId() 
    {
        return id;
    }
    public void setOrgandoctorId(String organdoctorId) 
    {
        this.organdoctorId = organdoctorId;
    }

    public String getOrgandoctorId() 
    {
        return organdoctorId;
    }
    public void setCardNo(String cardNo) 
    {
        this.cardNo = cardNo;
    }

    public String getCardNo() 
    {
        return cardNo;
    }
    public void setPatientNo(String patientNo) 
    {
        this.patientNo = patientNo;
    }

    public String getPatientNo() 
    {
        return patientNo;
    }
    public void setSocialsecurityNO(String socialsecurityNO) 
    {
        this.socialsecurityNO = socialsecurityNO;
    }

    public String getSocialsecurityNO() 
    {
        return socialsecurityNO;
    }
    public void setSourceDate(Date sourceDate) 
    {
        this.sourceDate = sourceDate;
    }

    public Date getSourceDate() 
    {
        return sourceDate;
    }
    public void setTimestypeNo(String timestypeNo) 
    {
        this.timestypeNo = timestypeNo;
    }

    public String getTimestypeNo() 
    {
        return timestypeNo;
    }
    public void setSourceTimeType(Integer sourceTimeType) 
    {
        this.sourceTimeType = sourceTimeType;
    }

    public Integer getSourceTimeType() 
    {
        return sourceTimeType;
    }
    public void setPayNo(String payNo) 
    {
        this.payNo = payNo;
    }

    public String getPayNo() 
    {
        return payNo;
    }
    public void setPayAmount(BigDecimal payAmount) 
    {
        this.payAmount = payAmount;
    }

    public BigDecimal getPayAmount() 
    {
        return payAmount;
    }
    public void setAppId(String appId) 
    {
        this.appId = appId;
    }

    public String getAppId() 
    {
        return appId;
    }
    public void setCreatTime(Date creatTime) 
    {
        this.creatTime = creatTime;
    }

    public Date getCreatTime() 
    {
        return creatTime;
    }
    public void setSuccessfulPayment(String successfulPayment) 
    {
        this.successfulPayment = successfulPayment;
    }

    public String getSuccessfulPayment() 
    {
        return successfulPayment;
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
    public void setDepartmentorganId(String departmentorganId) 
    {
        this.departmentorganId = departmentorganId;
    }

    public String getDepartmentorganId() 
    {
        return departmentorganId;
    }
    public void setPayType(String payType) 
    {
        this.payType = payType;
    }

    public String getPayType() 
    {
        return payType;
    }
    public void setResultMsg(String resultMsg) 
    {
        this.resultMsg = resultMsg;
    }

    public String getResultMsg() 
    {
        return resultMsg;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this,ToStringStyle.MULTI_LINE_STYLE)
            .append("id", getId())
            .append("organdoctorId", getOrgandoctorId())
            .append("cardNo", getCardNo())
            .append("patientNo", getPatientNo())
            .append("socialsecurityNO", getSocialsecurityNO())
            .append("sourceDate", getSourceDate())
            .append("timestypeNo", getTimestypeNo())
            .append("sourceTimeType", getSourceTimeType())
            .append("payNo", getPayNo())
            .append("payAmount", getPayAmount())
            .append("appId", getAppId())
            .append("creatTime", getCreatTime())
            .append("successfulPayment", getSuccessfulPayment())
            .append("outTradeNo", getOutTradeNo())
            .append("transactionId", getTransactionId())
            .append("departmentorganId", getDepartmentorganId())
            .append("payType", getPayType())
            .append("resultMsg", getResultMsg())
            .toString();
    }
}
