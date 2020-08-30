package com.ruoyi.his.remote.request;


import java.io.Serializable;

public class LeaveHosPayIn extends BaseRequest implements Serializable {

    /***
     * 住院号
     */
    private String inHosNo="";
    /***
     * 支付类型
     */
    private Integer payType;
    /***
     * 支付交易流水号
     */
    private String payRecord="";
    /***
     * 预交金金额（保留小数点后两位）
     */
    private double payMoney;
    /***
     * 医保统筹金额
     */
    private Double overMoney;
    /***
     * 医保结算流水号
     */
    private String medicareRecord="";
    /***
     * 社保卡号
     */
    private String socialsecurityNO="";

    /***
     * 0,非医保 1,市医保  2,区医保
     */
    private String medicareType="";

    /***
     * 医保返回信息
     */
    private String medicareReturn="";

    /***
     * 银行返回信息
     */
    private String bankReturn="";

    /***
     * 终端号
     */
    private String terminalCode="";

    /***
     * 个人编号
     */
    private String userNo="";

    public String getInHosNo() {
        return inHosNo;
    }

    public void setInHosNo(String inHosNo) {
        this.inHosNo = inHosNo;
    }

    public Integer getPayType() {
        return payType;
    }

    public void setPayType(Integer payType) {
        this.payType = payType;
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

    public Double getOverMoney() {
        return overMoney;
    }

    public void setOverMoney(Double overMoney) {
        this.overMoney = overMoney;
    }

    public String getMedicareRecord() {
        return medicareRecord;
    }

    public void setMedicareRecord(String medicareRecord) {
        this.medicareRecord = medicareRecord;
    }

    public String getSocialsecurityNO() {
        return socialsecurityNO;
    }

    public void setSocialsecurityNO(String socialsecurityNO) {
        this.socialsecurityNO = socialsecurityNO;
    }

    public String getMedicareType() {
        return medicareType;
    }

    public void setMedicareType(String medicareType) {
        this.medicareType = medicareType;
    }

    public String getMedicareReturn() {
        return medicareReturn;
    }

    public void setMedicareReturn(String medicareReturn) {
        this.medicareReturn = medicareReturn;
    }

    public String getBankReturn() {
        return bankReturn;
    }

    public void setBankReturn(String bankReturn) {
        this.bankReturn = bankReturn;
    }

    public String getTerminalCode() {
        return terminalCode;
    }

    public void setTerminalCode(String terminalCode) {
        this.terminalCode = terminalCode;
    }

    public String getUserNo() {
        return userNo;
    }

    public void setUserNo(String userNo) {
        this.userNo = userNo;
    }
}
