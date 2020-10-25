package com.ruoyi.his.remote.request.healthcard;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import java.io.Serializable;

/**
 * Created by ASUS on 2019/1/25.
 */
@ApiModel(value = "注册健康卡对象", description = "注册健康卡")
public class RegisterResquest implements Serializable {


    /**
     * wechatCode : 4D6FFFE544AE4CE1B5E5FA2DC1566E1C
     * name : 张三
     * gender : 男
     * nation : 汉族
     * birthday : 1998-09-08
     * idNumber : 101102199809089988
     * idType : 01
     * relation : 1
     * address : 广东省深圳市南山区深南大道10000号
     * phone1 : 18808808808
     * phone2 : 18808808808
     * patid : 1003243
     */
    /**
     * 微信身份码
     * 1) wechatCode有效期为30分钟且只能使用一次，注册成功时立即失效，注册失败（如核身失败）时，wechatCode不会失效，用户可继续修改信息注册
     */
    @ApiModelProperty(value = "微信身份码", name = "wechatCode", example = "4D6FFFE544AE4CE1B5E5FA2DC1566E1C")
    private String wechatCode;
    /***
     * 姓名
     */
    @ApiModelProperty(value = "张三", name = "name", example = "男")
    private String name;
    /**
     * 性别:男、女
     */
    @ApiModelProperty(value = "性别", name = "gender", example = "男")
    private String gender;
    /***
     * 民族:汉族、满族等
     */
    @ApiModelProperty(value = "民族", name = "nation", example = "汉族")
    private String nation;
    /***
     * 出生年月日
     * 	格式：yyyy-MM-dd
     */
    @ApiModelProperty(value = "出生年月日,格式：yyyy-MM-dd", name = "birthday", example = "2020-08-01")
    private String birthday;
    /***
     * 证件号码
     */
    @ApiModelProperty(value = "证件号码", name = "idNumber", example = "101102199809089988")
    private String idNumber;
    /***
     * 证件类型:01-居民身份证
     * 其它证件：https://open.tengmed.com/doc/#74
     */
    @ApiModelProperty(value = "证件类型,具体证件：https://open.tengmed.com/doc/#74", name = "idType", example = "01")
    private String idType;

    /***
     * 联系方式1
     */
    @ApiModelProperty(value = "联系方式1：", name = "phone1", example = "18808808808")
    private String phone1;

    @ApiModelProperty(value = "地址：", name = "address", example = "广东省深圳市南山区深南大道10000号")
    private String address;

    /***
     * 短信验证码
     */
    @ApiModelProperty(value = "短信验证码", name = "smsCode", example = "男")
    private String smsCode;

    @ApiModelProperty(value = "就诊卡号", name = "cardNo", example = "123456")
    private String cardNo;

    public String getWechatCode() {
        return wechatCode;
    }

    public void setWechatCode(String wechatCode) {
        this.wechatCode = wechatCode;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public String getNation() {
        return nation;
    }

    public void setNation(String nation) {
        this.nation = nation;
    }

    public String getBirthday() {
        return birthday;
    }

    public void setBirthday(String birthday) {
        this.birthday = birthday;
    }

    public String getIdNumber() {
        return idNumber;
    }

    public void setIdNumber(String idNumber) {
        this.idNumber = idNumber;
    }

    public String getIdType() {
        return idType;
    }

    public void setIdType(String idType) {
        this.idType = idType;
    }


    public String getPhone1() {
        return phone1;
    }

    public void setPhone1(String phone1) {
        this.phone1 = phone1;
    }

    public String getSmsCode() {
        return smsCode;
    }

    public void setSmsCode(String smsCode) {
        this.smsCode = smsCode;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getCardNo() {
        return cardNo;
    }

    public void setCardNo(String cardNo) {
        this.cardNo = cardNo;
    }
}
