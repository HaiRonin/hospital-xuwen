package com.ruoyi.his.remote.response;


import java.util.List;

/***
 * 待交费列表数据
 */
public class ToPayRecipeInfoOut{
    /**
     * 00：表示成功，
     其他表示失败

     -9999:自定义的返回码，代表调用his失败，需要重新尝试
     */
    private String resultCode;

    /***
     * 返回信息
     */
    private String resultMsg;

    private List<HiFeeBean> hiFee;


    public List<HiFeeBean> getHiFee() {
        return hiFee;
    }

    public void setHiFee(List<HiFeeBean> hiFee) {
        this.hiFee = hiFee;
    }

    public static class HiFeeBean {

        /***
         * 缴费编号
         */
        private String hiFeeNo;
        /**
         * 自费金额
         */
        private Double patientAmount;
        /**
         * 患者名称
         */
        private String patientName;
        /***
         * 患者编号
         */
        private String patientNo;
        /***
         * 结算金额
         */
        private Double settleAmount;

        public String getHiFeeNo() {
            return hiFeeNo;
        }

        public void setHiFeeNo(String hiFeeNo) {
            this.hiFeeNo = hiFeeNo;
        }

        public Double getPatientAmount() {
            return patientAmount;
        }

        public void setPatientAmount(Double patientAmount) {
            this.patientAmount = patientAmount;
        }

        public String getPatientName() {
            return patientName;
        }

        public void setPatientName(String patientName) {
            this.patientName = patientName;
        }

        public String getPatientNo() {
            return patientNo;
        }

        public void setPatientNo(String patientNo) {
            this.patientNo = patientNo;
        }

        public Double getSettleAmount() {
            return settleAmount;
        }

        public void setSettleAmount(Double settleAmount) {
            this.settleAmount = settleAmount;
        }
    }

    public String getResultCode() {
        return resultCode;
    }

    public void setResultCode(String resultCode) {
        this.resultCode = resultCode;
    }

    public String getResultMsg() {
        return resultMsg;
    }

    public void setResultMsg(String resultMsg) {
        this.resultMsg = resultMsg;
    }
}
