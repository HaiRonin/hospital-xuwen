package com.ruoyi.his.remote.response;


import java.io.Serializable;

public class InPatientPaymentOut extends BaseResponse implements Serializable {

    /***
     * 患者姓名
     */
    private String patientName;

    /***
     *科室名称
     */
    private String departmentName;

    /***
     *床号
     */
    private String bedNo;
    /***
     *押金余额（保留小数点后两位）
     */
    private double depositMoney;
    /***
     *医保类型名称
     */
    private String medicalTypeNmae;

    public InPatientPaymentOut(String resultCode, String resultMsg) {
        super(resultCode, resultMsg);
    }

    public String getPatientName() {
        return patientName;
    }

    public void setPatientName(String patientName) {
        this.patientName = patientName;
    }

    public String getDepartmentName() {
        return departmentName;
    }

    public void setDepartmentName(String departmentName) {
        this.departmentName = departmentName;
    }

    public String getBedNo() {
        return bedNo;
    }

    public void setBedNo(String bedNo) {
        this.bedNo = bedNo;
    }

    public double getDepositMoney() {
        return depositMoney;
    }

    public void setDepositMoney(double depositMoney) {
        this.depositMoney = depositMoney;
    }

    public String getMedicalTypeNmae() {
        return medicalTypeNmae;
    }

    public void setMedicalTypeNmae(String medicalTypeNmae) {
        this.medicalTypeNmae = medicalTypeNmae;
    }
}
