package com.ruoyi.pay.service;

import com.ruoyi.common.enums.HisOrderType;
import com.ruoyi.common.model.HisPayOrder;
import com.ruoyi.pay.config.WechatConfig;
import com.ruoyi.pay.utils.WeixinAppPayUtils;
import com.ruoyi.pay.utils.WeixinH5PayUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

@Service
public class WxH5PayServiceImp extends AbstractPayService {

    private static final Logger LOG = LoggerFactory.getLogger(WxH5PayServiceImp.class);

    @Override
    public Map<String, String> prePay(HisPayOrder hisPayOrder) {
        String orderId = hisPayOrder.getOutTradeNo();

        Map<String, String> wxPayParams = new HashMap<String, String>();// 里面的参数有用于微信的签名，不能随便添加参数
        Double price = new Double(hisPayOrder.getAmount().doubleValue() * 100);//支付金额 单位是分
        String payUrl = WeixinH5PayUtils.getPrePayUrl(
                HisOrderType.getDescByKey(hisPayOrder.getOrderType()), orderId,
                String.valueOf(price.longValue()));

        LOG.info(">>>>>>>>>>>>>>>>>>微信支付payUrl=" + payUrl);

        wxPayParams.put("payUrl", payUrl + "&redirect_url=" + hisPayOrder.getRedirectUrl());
        return wxPayParams;
    }

    @Override
    public boolean pay(HisPayOrder hisPayOrder) {
        return false;
    }

    @Override
    public boolean refund(HisPayOrder hisPayOrder) {
        return WeixinH5PayUtils.refund(hisPayOrder.getTransactionId(), hisPayOrder.getOutTradeNo(), hisPayOrder.getAmount());
    }
}
