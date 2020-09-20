package com.ruoyi.his.domain;

import com.ruoyi.common.annotation.Excel;
import io.swagger.annotations.ApiModelProperty;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

import java.math.BigDecimal;
import java.util.Date;

/**
 * 预约挂号对象 his_doreg_info
 * 
 * @author whl
 * @date 2020-08-29
 */
public class DoregInfo extends HisBaseEntity
{
    private static final long serialVersionUID = 1L;

    /** null */
    @ApiModelProperty(value = "id",hidden = true)
    private Long id;

    /** 商户ID */
    @Excel(name = "商户ID")
    @ApiModelProperty(value = "appId",hidden = true)
    private String appId;

    /** 用户名 */
    @Excel(name = "用户名")
    private String synUserName;

    /** 效验码 */
    @Excel(name = "效验码")
    private String synKey;

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

    /** 取号日期（号源日期） */
    @Excel(name = "取号日期", readConverterExp = "号=源日期")
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

    /** 已支金额 */
    @Excel(name = "已支金额")
    private BigDecimal payAmount;

    /** 科室id */
    @Excel(name = "科室id")
    private String departmentorganId;


    /** 号源编号 */
    @Excel(name = "号源编号")
    private String sourceMark;

    /** 医院就医码 */
    @Excel(name = "医院就医码")
    private String medicalCode;

    /** 挂号费 */
    @Excel(name = "挂号费")
    private String consultationFee;

    public void setId(Long id) 
    {
        this.id = id;
    }

    public Long getId() 
    {
        return id;
    }
    public void setAppId(String appId) 
    {
        this.appId = appId;
    }

    public String getAppId() 
    {
        return appId;
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

    public void setDepartmentorganId(String departmentorganId) 
    {
        this.departmentorganId = departmentorganId;
    }

    public String getDepartmentorganId() 
    {
        return departmentorganId;
    }

    public void setSourceMark(String sourceMark) 
    {
        this.sourceMark = sourceMark;
    }

    public String getSourceMark() 
    {
        return sourceMark;
    }
    public void setMedicalCode(String medicalCode) 
    {
        this.medicalCode = medicalCode;
    }

    public String getMedicalCode() 
    {
        return medicalCode;
    }
    public void setConsultationFee(String consultationFee) 
    {
        this.consultationFee = consultationFee;
    }

    public String getConsultationFee() 
    {
        return consultationFee;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this,ToStringStyle.MULTI_LINE_STYLE)
            .append("id", getId())
            .append("appId", getAppId())
            .append("synUserName", getSynUserName())
            .append("synKey", getSynKey())
            .append("organdoctorId", getOrgandoctorId())
            .append("cardNo", getCardNo())
            .append("patientNo", getPatientNo())
            .append("socialsecurityNO", getSocialsecurityNO())
            .append("sourceDate", getSourceDate())
            .append("timestypeNo", getTimestypeNo())
            .append("sourceTimeType", getSourceTimeType())
            .append("payType", getPayType())
            .append("payNo", getPayNo())
            .append("payAmount", getPayAmount())
            .append("createTime", getCreateTime())
            .append("successfulPayment", getSuccessfulPayment())
            .append("outTradeNo", getOutTradeNo())
            .append("transactionId", getTransactionId())
            .append("departmentorganId", getDepartmentorganId())
            .append("resultMsg", getResultMsg())
            .append("sourceMark", getSourceMark())
            .append("medicalCode", getMedicalCode())
            .append("consultationFee", getConsultationFee())
            .append("updateTime", getUpdateTime())
            .toString();
    }
}
