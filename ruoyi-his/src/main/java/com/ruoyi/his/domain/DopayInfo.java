package com.ruoyi.his.domain;

import com.ruoyi.common.annotation.Excel;
import io.swagger.annotations.ApiModelProperty;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

import java.math.BigDecimal;
import java.util.Date;

/**
 * 门诊支付对象 his_dopay_info
 * 
 * @author whl
 * @date 2020-08-29
 */
public class DopayInfo extends HisBaseEntity
{
    private static final long serialVersionUID = 1L;

    /** null */
    @ApiModelProperty(value = "id",hidden = true)
    private Long id;

    /** 商户id */
    @Excel(name = "商户id")
    @ApiModelProperty(value = "appId",hidden = true)
    private String appId;

    /** 用户名 */
    @Excel(name = "用户名")
    private String synUserName;

    /** 效验码 */
    @Excel(name = "效验码")
    private String synKey;

    /** 缴费编号 */
    @Excel(name = "缴费编号")
    private String hiFeeNos;

    /** 社保卡号 */
    @Excel(name = "社保卡号")
    private String socialsecurityNO;

    /** 统筹结算流水号 */
    @Excel(name = "统筹结算流水号")
    private String overRecord;

    /** 统筹金额 */
    @Excel(name = "统筹金额")
    private String overMoney;

    /** 医保返回信息 */
    @Excel(name = "医保返回信息")
    @ApiModelProperty(value = "医保返回信息",hidden = true)
    private String medicareReturn;

    /** 银行返回信息 */
    @Excel(name = "银行返回信息")
    @ApiModelProperty(value = "银行返回信息",hidden = true)
    private String bankReturn;

    /** 终端号 */
    @Excel(name = "终端号")
    private String terminalCode;

    /** 个人编号 */
    @Excel(name = "个人编号")
    private String userNo;

    /** 医保卡类型 */
    @Excel(name = "医保卡类型")
    private Long medicareType;


    /** 支付卡号 */
    @Excel(name = "支付卡号")
    private String payCardNo;

    /** 支付交易流水 */
    @Excel(name = "支付交易流水")
    private String payRecord;

    /** 实际支付金额 */
    @Excel(name = "实际支付金额")
    private BigDecimal payMoney;

    /** 创建时间 */
    @Excel(name = "创建时间", width = 30, dateFormat = "yyyy-MM-dd")
    @ApiModelProperty(value = "创建时间",hidden = true)
    private Date creatTime;

    /** 返回信息 */
    @Excel(name = "返回信息")
    @ApiModelProperty(value = "返回信息",hidden = true)
    private String resultMessage;

    /** 检验条码信息 */
    @Excel(name = "检验条码信息")
    private String checkInfo;


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
    public void setHiFeeNos(String hiFeeNos) 
    {
        this.hiFeeNos = hiFeeNos;
    }

    public String getHiFeeNos() 
    {
        return hiFeeNos;
    }
    public void setSocialsecurityNO(String socialsecurityNO) 
    {
        this.socialsecurityNO = socialsecurityNO;
    }

    public String getSocialsecurityNO() 
    {
        return socialsecurityNO;
    }
    public void setOverRecord(String overRecord) 
    {
        this.overRecord = overRecord;
    }

    public String getOverRecord() 
    {
        return overRecord;
    }
    public void setOverMoney(String overMoney) 
    {
        this.overMoney = overMoney;
    }

    public String getOverMoney() 
    {
        return overMoney;
    }
    public void setMedicareReturn(String medicareReturn) 
    {
        this.medicareReturn = medicareReturn;
    }

    public String getMedicareReturn() 
    {
        return medicareReturn;
    }
    public void setBankReturn(String bankReturn) 
    {
        this.bankReturn = bankReturn;
    }

    public String getBankReturn() 
    {
        return bankReturn;
    }
    public void setTerminalCode(String terminalCode) 
    {
        this.terminalCode = terminalCode;
    }

    public String getTerminalCode() 
    {
        return terminalCode;
    }
    public void setUserNo(String userNo) 
    {
        this.userNo = userNo;
    }

    public String getUserNo() 
    {
        return userNo;
    }
    public void setMedicareType(Long medicareType) 
    {
        this.medicareType = medicareType;
    }

    public Long getMedicareType() 
    {
        return medicareType;
    }

    public void setPayCardNo(String payCardNo) 
    {
        this.payCardNo = payCardNo;
    }

    public String getPayCardNo() 
    {
        return payCardNo;
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
    public void setCreatTime(Date creatTime) 
    {
        this.creatTime = creatTime;
    }

    public Date getCreatTime() 
    {
        return creatTime;
    }

    public void setResultMessage(String resultMessage) 
    {
        this.resultMessage = resultMessage;
    }

    public String getResultMessage() 
    {
        return resultMessage;
    }
    public void setCheckInfo(String checkInfo) 
    {
        this.checkInfo = checkInfo;
    }

    public String getCheckInfo() 
    {
        return checkInfo;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this,ToStringStyle.MULTI_LINE_STYLE)
            .append("id", getId())
            .append("appId", getAppId())
            .append("synUserName", getSynUserName())
            .append("synKey", getSynKey())
            .append("hiFeeNos", getHiFeeNos())
            .append("socialsecurityNO", getSocialsecurityNO())
            .append("overRecord", getOverRecord())
            .append("overMoney", getOverMoney())
            .append("medicareReturn", getMedicareReturn())
            .append("bankReturn", getBankReturn())
            .append("terminalCode", getTerminalCode())
            .append("userNo", getUserNo())
            .append("medicareType", getMedicareType())
            .append("payType", getPayType())
            .append("payCardNo", getPayCardNo())
            .append("payRecord", getPayRecord())
            .append("payMoney", getPayMoney())
            .append("creatTime", getCreatTime())
            .append("successfulPayment", getSuccessfulPayment())
            .append("outTradeNo", getOutTradeNo())
            .append("transactionId", getTransactionId())
            .append("resultMessage", getResultMessage())
            .append("checkInfo", getCheckInfo())
            .append("resultMsg", getResultMsg())
            .append("resultCode", getResultCode())
            .toString();
    }
}
