package com.ruoyi.his.remote.request;


import java.io.Serializable;

public class DoCovIn extends BaseRequest implements Serializable {


    /** 卡号 */
    private String cardNo="";

    /** 姓名 */
    private String name="";

    /** 支付卡号 */
    private String payCardNo="";

    /**支付流水号
     **/
     private String payNo="";

    /***
     *支付金额
     */
    private String payAmount="";

    private String erhcCardNo="";

    public String getCardNo() {
        return cardNo;
    }

    public void setCardNo(String cardNo) {
        this.cardNo = cardNo;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
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

    public String getErhcCardNo() {
        return erhcCardNo;
    }

    public void setErhcCardNo(String erhcCardNo) {
        this.erhcCardNo = erhcCardNo;
    }
}
