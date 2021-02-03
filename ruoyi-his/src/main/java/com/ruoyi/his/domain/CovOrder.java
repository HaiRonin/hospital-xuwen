package com.ruoyi.his.domain;

import java.math.BigDecimal;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.ruoyi.common.annotation.Excel;
import com.ruoyi.common.core.domain.BaseEntity;

/**
 * 核酸检测订单对象 his_cov_order
 * 
 * @author ruoyi
 * @date 2021-02-03
 */
@ApiModel("核酸检测订单对象")
public class CovOrder extends HisBaseEntity
{
    private static final long serialVersionUID = 1L;

    /** null */
    @ApiModelProperty(value = "id",hidden = true)
    private Long id;

    /** 商户id */
    @Excel(name = "商户id")
    @ApiModelProperty(value = "商户id",hidden = true)
    private String appId;

    /** 卡号 */
    @Excel(name = "卡号")
    @ApiModelProperty(value = "就诊卡号")
    private String cardNo;

    /** 姓名 */
    @Excel(name = "姓名")
    @ApiModelProperty(value = "姓名")
    private String name;

    /** 支付金额 */
    @Excel(name = "支付金额")
    @ApiModelProperty(value = "支付金额")
    private BigDecimal payAmount;

    /** 支付卡号 */
    @Excel(name = "支付卡号")
    @ApiModelProperty(value = "支付卡号",hidden = true)
    private String payCarNo;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getAppId() {
        return appId;
    }

    public void setAppId(String appId) {
        this.appId = appId;
    }

    public String getCardNo() {
        return cardNo;
    }

    public void setCardNo(String cardNo) {
        this.cardNo = cardNo;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public BigDecimal getPayAmount() {
        return payAmount;
    }

    public void setPayAmount(BigDecimal payAmount) {
        this.payAmount = payAmount;
    }

    public String getPayCarNo() {
        return payCarNo;
    }

    public void setPayCarNo(String payCarNo) {
        this.payCarNo = payCarNo;
    }
}
