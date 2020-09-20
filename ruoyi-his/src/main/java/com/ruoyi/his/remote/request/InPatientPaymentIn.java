package com.ruoyi.his.remote.request;


import java.io.Serializable;

public class InPatientPaymentIn extends BaseRequest implements Serializable {

    /***
     * 住院号
     */
    private String inHosNo="";
    /***
     * 支付交易流水号
     */
    private String payRecord="";
    /***
     * 预交金金额（保留小数点后两位）
     */
    private double payMoney;
    /***
     * 住院号
     */
    private String bedNo="";
    /***
     * 支付交易返回
     */
    private String payReturn="";
    /***
     * 终端号
     */
    private String terminalCode="";

    public String getInHosNo() {
        return inHosNo;
    }

    public void setInHosNo(String inHosNo) {
        this.inHosNo = inHosNo;
    }

    public String getPayRecord() {
        return payRecord;
    }

    public void setPayRecord(String payRecord) {
        this.payRecord = payRecord;
    }

    public double getPayMoney() {
        return payMoney;
    }

    public void setPayMoney(double payMoney) {
        this.payMoney = payMoney;
    }

    public String getBedNo() {
        return bedNo;
    }

    public void setBedNo(String bedNo) {
        this.bedNo = bedNo;
    }

    public String getPayReturn() {
        return payReturn;
    }

    public void setPayReturn(String payReturn) {
        this.payReturn = payReturn;
    }

    public String getTerminalCode() {
        return terminalCode;
    }

    public void setTerminalCode(String terminalCode) {
        this.terminalCode = terminalCode;
    }
}
