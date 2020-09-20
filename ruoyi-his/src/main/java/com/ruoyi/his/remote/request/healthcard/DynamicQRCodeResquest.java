package com.ruoyi.his.remote.request.healthcard;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import java.io.Serializable;

/**
 * Created by ASUS on 2019/1/25.
 */
@ApiModel(value="获取健康卡二维码对象",description="获取健康卡二维码")
public class DynamicQRCodeResquest implements Serializable {

    /**
     * healthCardId : C220AE414CE6EE581037C311AE24518FCFE19C429BECD478C1A13976260FXXXX
     * idType : 01
     * idNumber : 432901198810228888
     * codeType : 0
     */
    /**
     * 健康卡ID
     */
    @ApiModelProperty(value="健康卡ID",name="healthCardId",example="B5A28173356BB199962ABE0493ED0416CC17A9DF801CBE416B08CAB5A97D4AB6")
    private String healthCardId;
    /***
     * 证件类型:01-居民身份证
     * 其它证件：https://open.tengmed.com/doc/#724
     */
    @ApiModelProperty(value="证件类型,具体证件：https://open.tengmed.com/doc/#74",name="idType",example="01")
    private String idType;
    /***
     * 证件号码
     */
    @ApiModelProperty(value="证件号码",name="idNumber",example="450922198412064615")
    private String idNumber;
    /***
     * 二维码类型:传0或者1，0返回动态码，1返回静态码
     */
    @ApiModelProperty(value="二维码类型:传0或者1，0返回动态码，1返回静态码",name="codeType",example="0")
    private String codeType;

    public String getHealthCardId() {
        return healthCardId;
    }

    public void setHealthCardId(String healthCardId) {
        this.healthCardId = healthCardId;
    }

    public String getIdType() {
        return idType;
    }

    public void setIdType(String idType) {
        this.idType = idType;
    }

    public String getIdNumber() {
        return idNumber;
    }

    public void setIdNumber(String idNumber) {
        this.idNumber = idNumber;
    }

    public String getCodeType() {
        return codeType;
    }

    public void setCodeType(String codeType) {
        this.codeType = codeType;
    }
}
