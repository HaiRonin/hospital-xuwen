package com.ruoyi.his.domain;

import com.ruoyi.common.annotation.Excel;
import io.swagger.annotations.ApiModelProperty;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

import java.math.BigDecimal;
import java.util.Date;

/**
 * 出院结算对象 his_leave_hos_pay
 * 
 * @author whl
 * @date 2020-08-29
 */
public class LeaveHosPay extends HisBaseEntity
{
    private static final long serialVersionUID = 1L;

    /** null */
    @ApiModelProperty(value = "id",hidden = true)
    private Long id;

    /** app_id */
    @Excel(name = "app_id")
    @ApiModelProperty(value = "appId",hidden = true)
    private String appId;

    /** 用户名 */
    @Excel(name = "用户名")
    private String synUserName;

    /** 效验码 */
    @Excel(name = "效验码")
    private String synKey;

    /** 住院号 */
    @Excel(name = "住院号")
    private String inHosNo;

    /** 支付交易流水号 */
    @Excel(name = "支付交易流水号")
    private String payRecord;

    /** 支付金额 */
    @Excel(name = "支付金额")
    private BigDecimal payMoney;

    /** 医保统筹金额 */
    @Excel(name = "医保统筹金额")
    private BigDecimal overMoney;

    /** 社保卡号 */
    @Excel(name = "社保卡号")
    private String socialsecurityNO;

    /** 医保卡类型 */
    @Excel(name = "医保卡类型")
    private Integer medicareType;

    /** 医保返回信息 */
    @Excel(name = "医保返回信息")
    private String medicareReturn;

    /** 创建时间 */
    @Excel(name = "创建时间", width = 30, dateFormat = "yyyy-MM-dd")
    @ApiModelProperty(value = "创建时间",hidden = true)
    private Date creatTime;

    /** 银行返回信息 */
    @Excel(name = "银行返回信息")
    @ApiModelProperty(value = "银行返回信息",hidden = true)
    private String bankReturn;

    /** 医院出院流水号 */
    @Excel(name = "医院出院流水号")
    private String clearingNo;

    /** 患者姓名 */
    @Excel(name = "患者姓名")
    private String patientName;

    /** 患者性别 */
    @Excel(name = "患者性别")
    private Integer patientSex;

    /** 住院日期 */
    @Excel(name = "住院日期", width = 30, dateFormat = "yyyy-MM-dd")
    private Date inHosDate;

    /** 住院状态 */
    @Excel(name = "住院状态")
    private Long inHosState;

    /** 床号 */
    @Excel(name = "床号")
    private String bedNo;

    /** 科室编码 */
    @Excel(name = "科室编码")
    private String departmentOrganId;

    /** 科室名称 */
    @Excel(name = "科室名称")
    private String departmentName;

    /** 出院温馨提醒 */
    @Excel(name = "出院温馨提醒")
    @ApiModelProperty(value = "出院温馨提醒",hidden = true)
    private String reminder;

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
    public void setInHosNo(String inHosNo) 
    {
        this.inHosNo = inHosNo;
    }

    public String getInHosNo() 
    {
        return inHosNo;
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
    public void setOverMoney(BigDecimal overMoney) 
    {
        this.overMoney = overMoney;
    }

    public BigDecimal getOverMoney() 
    {
        return overMoney;
    }
    public void setSocialsecurityNO(String socialsecurityNO) 
    {
        this.socialsecurityNO = socialsecurityNO;
    }

    public String getSocialsecurityNO() 
    {
        return socialsecurityNO;
    }
    public void setMedicareType(Integer medicareType) 
    {
        this.medicareType = medicareType;
    }

    public Integer getMedicareType() 
    {
        return medicareType;
    }
    public void setMedicareReturn(String medicareReturn) 
    {
        this.medicareReturn = medicareReturn;
    }

    public String getMedicareReturn() 
    {
        return medicareReturn;
    }
    public void setCreatTime(Date creatTime) 
    {
        this.creatTime = creatTime;
    }

    public Date getCreatTime() 
    {
        return creatTime;
    }

    public void setBankReturn(String bankReturn) 
    {
        this.bankReturn = bankReturn;
    }

    public String getBankReturn() 
    {
        return bankReturn;
    }
    public void setClearingNo(String clearingNo) 
    {
        this.clearingNo = clearingNo;
    }

    public String getClearingNo() 
    {
        return clearingNo;
    }
    public void setPatientName(String patientName) 
    {
        this.patientName = patientName;
    }

    public String getPatientName() 
    {
        return patientName;
    }
    public void setPatientSex(Integer patientSex) 
    {
        this.patientSex = patientSex;
    }

    public Integer getPatientSex() 
    {
        return patientSex;
    }
    public void setInHosDate(Date inHosDate) 
    {
        this.inHosDate = inHosDate;
    }

    public Date getInHosDate() 
    {
        return inHosDate;
    }
    public void setInHosState(Long inHosState) 
    {
        this.inHosState = inHosState;
    }

    public Long getInHosState() 
    {
        return inHosState;
    }
    public void setBedNo(String bedNo) 
    {
        this.bedNo = bedNo;
    }

    public String getBedNo() 
    {
        return bedNo;
    }
    public void setDepartmentOrganId(String departmentOrganId) 
    {
        this.departmentOrganId = departmentOrganId;
    }

    public String getDepartmentOrganId() 
    {
        return departmentOrganId;
    }
    public void setDepartmentName(String departmentName) 
    {
        this.departmentName = departmentName;
    }

    public String getDepartmentName() 
    {
        return departmentName;
    }
    public void setReminder(String reminder) 
    {
        this.reminder = reminder;
    }

    public String getReminder() 
    {
        return reminder;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this,ToStringStyle.MULTI_LINE_STYLE)
            .append("id", getId())
            .append("appId", getAppId())
            .append("synUserName", getSynUserName())
            .append("synKey", getSynKey())
            .append("inHosNo", getInHosNo())
            .append("payType", getPayType())
            .append("payRecord", getPayRecord())
            .append("payMoney", getPayMoney())
            .append("overMoney", getOverMoney())
            .append("socialsecurityNO", getSocialsecurityNO())
            .append("medicareType", getMedicareType())
            .append("medicareReturn", getMedicareReturn())
            .append("creatTime", getCreatTime())
            .append("successfulPayment", getSuccessfulPayment())
            .append("outTradeNo", getOutTradeNo())
            .append("transactionId", getTransactionId())
            .append("bankReturn", getBankReturn())
            .append("clearingNo", getClearingNo())
            .append("patientName", getPatientName())
            .append("patientSex", getPatientSex())
            .append("inHosDate", getInHosDate())
            .append("inHosState", getInHosState())
            .append("bedNo", getBedNo())
            .append("departmentOrganId", getDepartmentOrganId())
            .append("departmentName", getDepartmentName())
            .append("reminder", getReminder())
            .append("resultMsg", getResultMsg())
            .append("resultCode", getResultCode())
            .toString();
    }
}
