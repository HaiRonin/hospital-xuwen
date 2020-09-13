package com.ruoyi.pay.service;

import com.ruoyi.common.model.HisPayOrder;

import java.util.Map;

public interface PayService {

    /***
     * 支付
     * @param hisPayOrder
     * @return
     */
    boolean pay(HisPayOrder hisPayOrder);

    /***
     * 退款
     * @param hisPayOrder
     * @return
     */
    boolean refund(HisPayOrder hisPayOrder);

    /**
     * 预支付
     *
     * @param hisPayOrder
     * @return
     */
    Map<String, String> prePay(HisPayOrder hisPayOrder);
}
