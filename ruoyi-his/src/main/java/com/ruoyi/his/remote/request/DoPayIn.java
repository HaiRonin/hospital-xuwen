package com.ruoyi.his.remote.request;


import java.io.Serializable;

public class DoPayIn extends BaseRequest implements Serializable {


    /** 缴费编号 */
    private String hiFeeNos="";

    /** 社保卡号 */
    private String socialsecurityNO="";

    /** 统筹结算流水号 */
    private String overRecord="";

    /** 统筹金额 */
    private String overMoney="";

    /**医保返回信息
     （市医保：门诊登记+处方上传+费用结算
     区医保：结算交易+读卡交易）
     **/
     private String medicareReturn="";

    /***
     *银行返回信息
     */
    private String bankReturn="";

    /***
     *终端号
     */
    private String terminalCode="";

    /***
     *0,非医保 1,市医保  2,区医保
     */
    private String medicareType="";

    /***
     *个人编号
     */
    private String userNo="";



    /***
     *支付流水号
     */
    private String payNo="";

    /***
     *支付卡号
     */
    private String payCardNo="";

    /***
     *支付金额
     */
    private String payAmount="";

    public String getHiFeeNos() {
        return hiFeeNos;
    }

    public void setHiFeeNos(String hiFeeNos) {
        this.hiFeeNos = hiFeeNos;
    }

    public String getSocialsecurityNO() {
        return socialsecurityNO;
    }

    public void setSocialsecurityNO(String socialsecurityNO) {
        this.socialsecurityNO = socialsecurityNO;
    }

    public String getOverRecord() {
        return overRecord;
    }

    public void setOverRecord(String overRecord) {
        this.overRecord = overRecord;
    }

    public String getOverMoney() {
        return overMoney;
    }

    public void setOverMoney(String overMoney) {
        this.overMoney = overMoney;
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

    public String getMedicareType() {
        return medicareType;
    }

    public void setMedicareType(String medicareType) {
        this.medicareType = medicareType;
    }

    public String getUserNo() {
        return userNo;
    }

    public void setUserNo(String userNo) {
        this.userNo = userNo;
    }


    public String getPayNo() {
        return payNo;
    }

    public void setPayNo(String payNo) {
        this.payNo = payNo;
    }

    public String getPayCardNo() {
        return payCardNo;
    }

    public void setPayCardNo(String payCardNo) {
        this.payCardNo = payCardNo;
    }

    public String getPayAmount() {
        return payAmount;
    }

    public void setPayAmount(String payAmount) {
        this.payAmount = payAmount;
    }
}
