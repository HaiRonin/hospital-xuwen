package com.ruoyi.his.remote.request;


import java.io.Serializable;

/***
 * 取消预约
 */
public class DoRegCancel extends BaseRequest implements Serializable {

    /**
     * UserName : 13418803185
     * DoctorName : 李业彪
     * IsCancel : 否
     * IsConsultation : 否
     * IsPreReg : 预约号
     * LocationName : 门诊呼吸内科
     * departmentorganId : uykkmt
     * isPrint : 否
     * patientName : 周楷鹏
     * payNo : 4200000682202009060913754121
     * regDate :
     * regStatus : 1
     * sourceDate : 2020-09-11
     * sourceMark : 202009060400
     * sourceTimeType : 3
     * taketheNo : 否
     * timestypeNo : 下午 15:30-16:30
     * patientNo : 99202008290495
     * payAmount : 3
     * payCardNo :
     */

    private String UserName;
    private String DoctorName;
    private String IsCancel;
    private String IsConsultation;
    private String IsPreReg;
    private String LocationName;
    private String departmentorganId;
    private String isPrint;
    private String patientName;
    private String payNo;
    private String regDate;
    private String regStatus;
    private String sourceDate;
    private String sourceMark;
    private String sourceTimeType;
    private String taketheNo;
    private String timestypeNo;
    private String patientNo;
    private String payAmount;
    private String payCardNo;

    public String getUserName() {
        return UserName;
    }

    public void setUserName(String UserName) {
        this.UserName = UserName;
    }

    public String getDoctorName() {
        return DoctorName;
    }

    public void setDoctorName(String DoctorName) {
        this.DoctorName = DoctorName;
    }

    public String getIsCancel() {
        return IsCancel;
    }

    public void setIsCancel(String IsCancel) {
        this.IsCancel = IsCancel;
    }

    public String getIsConsultation() {
        return IsConsultation;
    }

    public void setIsConsultation(String IsConsultation) {
        this.IsConsultation = IsConsultation;
    }

    public String getIsPreReg() {
        return IsPreReg;
    }

    public void setIsPreReg(String IsPreReg) {
        this.IsPreReg = IsPreReg;
    }

    public String getLocationName() {
        return LocationName;
    }

    public void setLocationName(String LocationName) {
        this.LocationName = LocationName;
    }

    public String getDepartmentorganId() {
        return departmentorganId;
    }

    public void setDepartmentorganId(String departmentorganId) {
        this.departmentorganId = departmentorganId;
    }

    public String getIsPrint() {
        return isPrint;
    }

    public void setIsPrint(String isPrint) {
        this.isPrint = isPrint;
    }

    public String getPatientName() {
        return patientName;
    }

    public void setPatientName(String patientName) {
        this.patientName = patientName;
    }

    public String getPayNo() {
        return payNo;
    }

    public void setPayNo(String payNo) {
        this.payNo = payNo;
    }

    public String getRegDate() {
        return regDate;
    }

    public void setRegDate(String regDate) {
        this.regDate = regDate;
    }

    public String getRegStatus() {
        return regStatus;
    }

    public void setRegStatus(String regStatus) {
        this.regStatus = regStatus;
    }

    public String getSourceDate() {
        return sourceDate;
    }

    public void setSourceDate(String sourceDate) {
        this.sourceDate = sourceDate;
    }

    public String getSourceMark() {
        return sourceMark;
    }

    public void setSourceMark(String sourceMark) {
        this.sourceMark = sourceMark;
    }

    public String getSourceTimeType() {
        return sourceTimeType;
    }

    public void setSourceTimeType(String sourceTimeType) {
        this.sourceTimeType = sourceTimeType;
    }

    public String getTaketheNo() {
        return taketheNo;
    }

    public void setTaketheNo(String taketheNo) {
        this.taketheNo = taketheNo;
    }

    public String getTimestypeNo() {
        return timestypeNo;
    }

    public void setTimestypeNo(String timestypeNo) {
        this.timestypeNo = timestypeNo;
    }

    public String getPatientNo() {
        return patientNo;
    }

    public void setPatientNo(String patientNo) {
        this.patientNo = patientNo;
    }

    public String getPayAmount() {
        return payAmount;
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
