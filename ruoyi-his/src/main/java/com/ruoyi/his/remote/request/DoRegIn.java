package com.ruoyi.his.remote.request;


import java.io.Serializable;

public class DoRegIn extends BaseRequest implements Serializable {

    private String organdoctorId ="";

    private String departmentorganId ="";

    private String cardNo ="";

    private String patientNo ="";

    private String socialsecurityNO ="";

    private String sourceDate;

    private String timestypeNo;

    private Integer sourceTimeType;

    private Integer payType;

    private String payCardNo ="";

    private String payNo ="";

    private String payAmount;

    public String getOrgandoctorId() {
        return organdoctorId;
    }

    public String getDepartmentorganId() {
        return departmentorganId;
    }

    public String getCardNo() {
        return cardNo;
    }

    public String getPatientNo() {
        return patientNo;
    }

    public String getSocialsecurityNO() {
        return socialsecurityNO;
    }

    public String getSourceDate() {
        return sourceDate;
    }

    public String getTimestypeNo() {
        return timestypeNo;
    }

    public int getSourceTimeType() {
        return sourceTimeType;
    }

    public int getPayType() {
        return payType;
    }

    public String getPayNo() {
        return payNo;
    }

    public String getPayAmount() {
        return payAmount;
    }

    public void setOrgandoctorId(String organdoctorId) {
        this.organdoctorId = organdoctorId;
    }

    public void setDepartmentorganId(String departmentorganId) {
        this.departmentorganId = departmentorganId;
    }

    public void setCardNo(String cardNo) {
        this.cardNo = cardNo;
    }

    public void setPatientNo(String patientNo) {
        this.patientNo = patientNo;
    }

    public void setSocialsecurityNO(String socialsecurityNO) {
        this.socialsecurityNO = socialsecurityNO;
    }

    public void setSourceDate(String sourceDate) {
        this.sourceDate = sourceDate;
    }

    public void setTimestypeNo(String timestypeNo) {
        this.timestypeNo = timestypeNo;
    }

    public void setSourceTimeType(Integer sourceTimeType) {
        this.sourceTimeType = sourceTimeType;
    }

    public void setPayType(Integer payType) {
        this.payType = payType;
    }

    public void setPayNo(String payNo) {
        this.payNo = payNo;
    }

    public void setPayAmount(String payAmount) {
        this.payAmount = payAmount;
    }

    public String getPayCardNo() {
        return payCardNo;
    }

    public void setPayCardNo(String payCardNo) {
        this.payCardNo = payCardNo;
    }
}
