package com.ruoyi.pay.service;

import com.ruoyi.common.model.HisPayOrder;
import org.springframework.stereotype.Service;

@Service
public class WeChatPayServiceImp extends AbstractPayService {


    @Override
    public boolean pay(HisPayOrder hisPayOrder) {
        return false;
    }

    @Override
    public boolean refund(HisPayOrder hisPayOrder) {
        return false;
    }
}
