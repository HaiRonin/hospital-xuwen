package com.ruoyi.his.remote.request;


import com.ruoyi.common.annotation.Excel;

public class DoPayIn extends BaseRequest{


    /** 缴费编号 */
    private String hiFeeNos;

    /** 社保卡号 */
    private String socialsecurityNO;

    /** 统筹结算流水号 */
    private String overRecord;

    /** 统筹金额 */
    private String overMoney;


}
