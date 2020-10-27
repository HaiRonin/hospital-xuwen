package com.ruoyi.his.domain;

import com.ruoyi.common.annotation.Excel;
import io.swagger.annotations.ApiModelProperty;

import java.math.BigDecimal;

/**
 * 押金补缴对象 his_deposit_payment
 * 
 * @author whl
 * @date 2020-08-29
 */
public class DepositPayment extends HisBaseEntity
{
    private static final long serialVersionUID = 1L;

    /** null */
    @ApiModelProperty(value = "id",hidden = true)
    private Long id;

    /** 用户名 */
    @Excel(name = "用户名")
    @ApiModelProperty(value = "synUserName",hidden = true)
    private String synUserName;

    /** 效验码 */
    @Excel(name = "效验码")
    @ApiModelProperty(value = "synKey",hidden = true)
    private String synKey;

    /** 住院号 */
    @Excel(name = "住院号")
    private String inHosNo;

    /** 支付交易流水号 */
    @ApiModelProperty(value = "payRecord",hidden = true)
    @Excel(name = "支付交易流水号")
    private String payRecord;

    /** 预交金金额 */
    @Excel(name = "预交金金额")
    private BigDecimal payMoney;

    /** 支付交易返回 */
    @ApiModelProperty(value = "payReturn",hidden = true)
    @Excel(name = "支付交易返回")
    private String payReturn;

    /** 终端号 */
    @Excel(name = "终端号")
    @ApiModelProperty(value = "terminalCode",hidden = true)
    private String terminalCode;

    /** 商户id */
    @Excel(name = "商户id")
    @ApiModelProperty(value = "appId",hidden = true)
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
    @ApiModelProperty(value = "depositMoney",hidden = true)
    private BigDecimal depositMoney;

    /** 医保类型名称 */
    @Excel(name = "医保类型名称")
    @ApiModelProperty(value = "medicalTypeName",hidden = true)
    private String medicalTypeName;


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

    public String getTerminalCode() {
        return terminalCode;
    }

    public void setTerminalCode(String terminalCode) {
        this.terminalCode = terminalCode;
    }
}
