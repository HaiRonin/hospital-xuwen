package com.ruoyi.his.remote.response;


import java.util.List;

public class DoPayOut extends BaseResponse{

    /**
     * checkInfo :
     * resultmessage : [{"clearingNO":"201006165598","dispensaryWin":"","guidelinesInfo":"","hiFeeNo":"","medicalCode":"202007311193","reserve":"","transactionTime":"2020-08-02 08:24:57"}]
     */

    private String checkInfo;

    private List<ResultmessageBean> resultmessage;

    public DoPayOut(String resultCode, String resultMsg) {
        super(resultCode, resultMsg);
    }

    public String getCheckInfo() {
        return checkInfo;
    }

    public void setCheckInfo(String checkInfo) {
        this.checkInfo = checkInfo;
    }

    public List<ResultmessageBean> getResultmessage() {
        return resultmessage;
    }

    public void setResultmessage(List<ResultmessageBean> resultmessage) {
        this.resultmessage = resultmessage;
    }

    public static class ResultmessageBean {
        /**
         * clearingNO : 201006165598
         * dispensaryWin :
         * guidelinesInfo :
         * hiFeeNo :
         * medicalCode : 202007311193
         * reserve :
         * transactionTime : 2020-08-02 08:24:57
         */

        private String clearingNO;
        private String dispensaryWin;
        private String guidelinesInfo;
        private String hiFeeNo;
        private String medicalCode;
        private String reserve;
        private String transactionTime;

        public String getClearingNO() {
            return clearingNO;
        }

        public void setClearingNO(String clearingNO) {
            this.clearingNO = clearingNO;
        }

        public String getDispensaryWin() {
            return dispensaryWin;
        }

        public void setDispensaryWin(String dispensaryWin) {
            this.dispensaryWin = dispensaryWin;
        }

        public String getGuidelinesInfo() {
            return guidelinesInfo;
        }

        public void setGuidelinesInfo(String guidelinesInfo) {
            this.guidelinesInfo = guidelinesInfo;
        }

        public String getHiFeeNo() {
            return hiFeeNo;
        }

        public void setHiFeeNo(String hiFeeNo) {
            this.hiFeeNo = hiFeeNo;
        }

        public String getMedicalCode() {
            return medicalCode;
        }

        public void setMedicalCode(String medicalCode) {
            this.medicalCode = medicalCode;
        }

        public String getReserve() {
            return reserve;
        }

        public void setReserve(String reserve) {
            this.reserve = reserve;
        }

        public String getTransactionTime() {
            return transactionTime;
        }

        public void setTransactionTime(String transactionTime) {
            this.transactionTime = transactionTime;
        }
    }
}
