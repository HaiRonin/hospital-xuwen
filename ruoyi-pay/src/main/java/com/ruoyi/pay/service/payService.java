package com.ruoyi.pay.service;

import com.ruoyi.common.model.HisPayOrder;

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
}
