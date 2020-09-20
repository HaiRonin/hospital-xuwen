package com.ruoyi.his.remote.response;



public class DoRegCancelOut extends BaseResponse{

    /**
     * departmentorganId : null
     * payNo : null
     * payType : null
     * regDate : null
     * regStatus : null
     * sourceTimeType : null
     */

    private String departmentorganId;
    private String payNo;
    private String payType;
    private String regDate;
    private String regStatus;
    private String sourceTimeType;

    public DoRegCancelOut(String resultCode, String resultMsg) {
        super(resultCode, resultMsg);
    }

    public String getDepartmentorganId() {
        return departmentorganId;
    }

    public void setDepartmentorganId(String departmentorganId) {
        this.departmentorganId = departmentorganId;
    }

    public String getPayNo() {
        return payNo;
    }

    public void setPayNo(String payNo) {
        this.payNo = payNo;
    }

    public String getPayType() {
        return payType;
    }

    public void setPayType(String payType) {
        this.payType = payType;
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

    public String getSourceTimeType() {
        return sourceTimeType;
    }

    public void setSourceTimeType(String sourceTimeType) {
        this.sourceTimeType = sourceTimeType;
    }
}
