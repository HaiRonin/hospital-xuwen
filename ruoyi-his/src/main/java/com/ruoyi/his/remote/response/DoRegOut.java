package com.ruoyi.his.remote.response;


import com.alibaba.fastjson.JSON;

public class DoRegOut extends BaseResponse{

    /***
     * {"consultationFee":"3","medicalCode":"","resultCode":"00","resultMsg":"挂号成功","sourceMark":"202009231222"}
     */
    /***
     * 号源编号
     */
    private String sourceMark;

    /***
     *医院就医码
     */
    private String medicalCode;

    /***
     *挂号费
     */
    private String consultationFee;

    public DoRegOut(String resultCode, String resultMsg, String sourceMark, String medicalCode, String consultationFee) {
        super(resultCode, resultMsg);
        this.sourceMark = sourceMark;
        this.medicalCode = medicalCode;
        this.consultationFee = consultationFee;
    }

    public DoRegOut(String resultCode, String resultMsg) {
        super(resultCode, resultMsg);
    }

    public String getSourceMark() {
        return sourceMark;
    }

    public void setSourceMark(String sourceMark) {
        this.sourceMark = sourceMark;
    }

    public String getMedicalCode() {
        return medicalCode;
    }

    public void setMedicalCode(String medicalCode) {
        this.medicalCode = medicalCode;
    }

    public String getConsultationFee() {
        return consultationFee;
    }

    public void setConsultationFee(String consultationFee) {
        this.consultationFee = consultationFee;
    }

}
