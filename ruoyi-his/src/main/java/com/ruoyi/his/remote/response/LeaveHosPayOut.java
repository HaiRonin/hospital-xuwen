package com.ruoyi.his.remote.response;


import java.io.Serializable;

public class LeaveHosPayOut extends BaseResponse implements Serializable {

    /**
     * 医院出院流水号
     */
    private String clearingNO;
    /***
     * 患者姓名
     */
    private String patientName;
    /***
     * 患者性别：0,女性；1，男性
     */
    private Integer patientSex;
    /***
     * 住院日期
     */
    private String inHosDate;
    /***
     * 0无住院，1申请中，2入院中，3已出院
     */
    private int inHosState;
    /***
     * 床号
     */
    private String bedNo;
    /***
     * 科室编码
     */
    private String departmentorganId;
    /***
     * 科室名称
     */
    private String departmentName;
    /***
     * 出院温馨提醒
     */
    private String reminder;

    public LeaveHosPayOut(String resultCode, String resultMsg, String clearingNO, String patientName, Integer patientSex, String inHosDate, int inHosState, String bedNo, String departmentorganId, String departmentName, String reminder) {
        super(resultCode, resultMsg);
        this.clearingNO = clearingNO;
        this.patientName = patientName;
        this.patientSex = patientSex;
        this.inHosDate = inHosDate;
        this.inHosState = inHosState;
        this.bedNo = bedNo;
        this.departmentorganId = departmentorganId;
        this.departmentName = departmentName;
        this.reminder = reminder;
    }

    public LeaveHosPayOut(String resultCode, String resultMsg) {
        super(resultCode, resultMsg);
    }

    public String getPatientName() {
        return patientName;
    }

    public void setPatientName(String patientName) {
        this.patientName = patientName;
    }

    public Integer getPatientSex() {
        return patientSex;
    }

    public void setPatientSex(Integer patientSex) {
        this.patientSex = patientSex;
    }

    public String getInHosDate() {
        return inHosDate;
    }

    public void setInHosDate(String inHosDate) {
        this.inHosDate = inHosDate;
    }

    public int getInHosState() {
        return inHosState;
    }

    public void setInHosState(int inHosState) {
        this.inHosState = inHosState;
    }

    public String getBedNo() {
        return bedNo;
    }

    public void setBedNo(String bedNo) {
        this.bedNo = bedNo;
    }

    public String getDepartmentorganId() {
        return departmentorganId;
    }

    public void setDepartmentorganId(String departmentorganId) {
        this.departmentorganId = departmentorganId;
    }

    public String getDepartmentName() {
        return departmentName;
    }

    public void setDepartmentName(String departmentName) {
        this.departmentName = departmentName;
    }

    public String getReminder() {
        return reminder;
    }

    public void setReminder(String reminder) {
        this.reminder = reminder;
    }

    public String getClearingNO() {
        return clearingNO;
    }

    public void setClearingNO(String clearingNO) {
        this.clearingNO = clearingNO;
    }
}
