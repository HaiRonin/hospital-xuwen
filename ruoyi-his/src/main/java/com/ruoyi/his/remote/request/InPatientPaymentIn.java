package com.ruoyi.his.remote.request;


import java.io.Serializable;

public class InPatientPaymentIn extends BaseRequest implements Serializable {


    /**
     *  住院号
     *  */
    private String inHosNo="";

    /**
     * 床号
     * */
    private String bedNo="";

    /**
     * 支付类型
     * */
    private String payType="";

    /***
     *  支付交易流水号
     *  */
    private String payRecord="";

    /**
     * 预交金金额（保留小数点后两位）
     * */
    private String payMoney="";

    /**
     * 支付交易返回
     **/
     private String payReturn="";

    /***
     *终端号
     */
    private String terminalCode="";

}
