package com.ruoyi.pay.service;

import com.ruoyi.common.enums.HisOrderType;
import com.ruoyi.common.enums.HisPayType;
import org.springframework.stereotype.Service;

@Service
public class AliPayServiceImp implements PayService {

    @Override
    public boolean pay(HisOrderType orderType, HisPayType payType, String orderNo) {
        return false;
    }

    @Override
    public boolean refund(HisOrderType orderType, HisPayType payType, String orderNo) {
        return false;
    }
}
