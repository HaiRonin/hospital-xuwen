package com.ruoyi.pay.service;

import com.ruoyi.common.enums.HisOrderType;
import com.ruoyi.common.enums.HisPayType;

public interface PayService {

    /***
     * 支付
     * @param orderType
     * @param payType
     * @param orderNo
     * @return
     */
    boolean pay(HisOrderType orderType, HisPayType payType, String orderNo);

    /***
     * 退款
     * @param orderType
     * @param payType
     * @param orderNo
     * @return
     */
    boolean refund(HisOrderType orderType, HisPayType payType, String orderNo);
}
