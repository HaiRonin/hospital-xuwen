package com.ruoyi.vo;

public class HealthCarTokenResponse {


    /**
     * commonOut : {"requestId":"93AD3C72BE9940079228B6288951C1CA","appId":"786f063eed41c3e6da9f636bf48b3b1a","resultCode":0,"errMsg":"success","cardManagementId":"guangdong5c747aaa93a5dd48aedec6"}
     * rsp : {"expiresIn":7200,"appToken":"5558F4F107F3EFF08E3DF31279F47BE8"}
     */

    private CommonOutBean commonOut;
    private RspBean rsp;

    public CommonOutBean getCommonOut() {
        return commonOut;
    }

    public void setCommonOut(CommonOutBean commonOut) {
        this.commonOut = commonOut;
    }

    public RspBean getRsp() {
        return rsp;
    }

    public void setRsp(RspBean rsp) {
        this.rsp = rsp;
    }

    public static class CommonOutBean {
        /**
         * requestId : 93AD3C72BE9940079228B6288951C1CA
         * appId : 786f063eed41c3e6da9f636bf48b3b1a
         * resultCode : 0
         * errMsg : success
         * cardManagementId : guangdong5c747aaa93a5dd48aedec6
         */

        private String requestId;
        private String appId;
        private int resultCode;
        private String errMsg;
        private String cardManagementId;

        public String getRequestId() {
            return requestId;
        }

        public void setRequestId(String requestId) {
            this.requestId = requestId;
        }

        public String getAppId() {
            return appId;
        }

        public void setAppId(String appId) {
            this.appId = appId;
        }

        public int getResultCode() {
            return resultCode;
        }

        public void setResultCode(int resultCode) {
            this.resultCode = resultCode;
        }

        public String getErrMsg() {
            return errMsg;
        }

        public void setErrMsg(String errMsg) {
            this.errMsg = errMsg;
        }

        public String getCardManagementId() {
            return cardManagementId;
        }

        public void setCardManagementId(String cardManagementId) {
            this.cardManagementId = cardManagementId;
        }
    }

    public static class RspBean {
        /**
         * expiresIn : 7200
         * appToken : 5558F4F107F3EFF08E3DF31279F47BE8
         */

        private int expiresIn;
        private String appToken;

        public int getExpiresIn() {
            return expiresIn;
        }

        public void setExpiresIn(int expiresIn) {
            this.expiresIn = expiresIn;
        }

        public String getAppToken() {
            return appToken;
        }

        public void setAppToken(String appToken) {
            this.appToken = appToken;
        }
    }
}
