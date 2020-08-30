package com.ruoyi.his.remote.response;



public class DoPayOut extends BaseResponse{

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

    public DoPayOut(String resultCode, String resultMsg) {
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
