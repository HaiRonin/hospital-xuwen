package com.ruoyi.his.remote.response;


import java.util.List;

public class RegCancelByDocStopOut extends BaseResponse{


    private List<ReturnListBean> returnList;

    public RegCancelByDocStopOut(String resultCode, String resultMsg, List<ReturnListBean> returnList) {
        super(resultCode, resultMsg);
        this.returnList = returnList;
    }

    public RegCancelByDocStopOut(String resultCode, String resultMsg) {
        super(resultCode, resultMsg);
    }

    public List<ReturnListBean> getReturnList() {
        return returnList;
    }

    public void setReturnList(List<ReturnListBean> returnList) {
        this.returnList = returnList;
    }

    public static class ReturnListBean {
        /**
         * sourceMark :
         * patientNo :
         * sourceDate :
         * doctorName :
         * departmentorganId :
         * payType :
         * payCardNo :
         * payNo :
         * payAmount :
         * mobile :
         */

        private String sourceMark;
        private String patientNo;
        private String sourceDate;
        private String doctorName;
        private String departmentorganId;
        private String payType;
        private String payCardNo;
        private String payNo;
        private String payAmount;
        private String mobile;

        public String getSourceMark() {
            return sourceMark;
        }

        public void setSourceMark(String sourceMark) {
            this.sourceMark = sourceMark;
        }

        public String getPatientNo() {
            return patientNo;
        }

        public void setPatientNo(String patientNo) {
            this.patientNo = patientNo;
        }

        public String getSourceDate() {
            return sourceDate;
        }

        public void setSourceDate(String sourceDate) {
            this.sourceDate = sourceDate;
        }

        public String getDoctorName() {
            return doctorName;
        }

        public void setDoctorName(String doctorName) {
            this.doctorName = doctorName;
        }

        public String getDepartmentorganId() {
            return departmentorganId;
        }

        public void setDepartmentorganId(String departmentorganId) {
            this.departmentorganId = departmentorganId;
        }

        public String getPayType() {
            return payType;
        }

        public void setPayType(String payType) {
            this.payType = payType;
        }

        public String getPayCardNo() {
            return payCardNo;
        }

        public void setPayCardNo(String payCardNo) {
            this.payCardNo = payCardNo;
        }

        public String getPayNo() {
            return payNo;
        }

        public void setPayNo(String payNo) {
            this.payNo = payNo;
        }

        public String getPayAmount() {
            return payAmount;
        }

        public void setPayAmount(String payAmount) {
            this.payAmount = payAmount;
        }

        public String getMobile() {
            return mobile;
        }

        public void setMobile(String mobile) {
            this.mobile = mobile;
        }
    }
}
