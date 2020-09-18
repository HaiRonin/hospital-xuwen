package com.ruoyi.pay.service;

import com.ruoyi.common.enums.HisOrderType;
import com.ruoyi.common.model.HisPayOrder;
import com.ruoyi.pay.config.AlipayConfig;
import com.ruoyi.pay.utils.AlipayUtil;
import com.ruoyi.pay.utils.WeixinAppPayUtils;
import org.springframework.stereotype.Service;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

@Service
public class AliPayServiceImp extends AbstractPayService {


    @Override
    public Map<String, String> prePay(HisPayOrder hisPayOrder) {
        String orderId = hisPayOrder.getOutTradeNo() + "_" + new Date().getTime();
        Map<String, String> payParams = new HashMap<String, String>();// 里面的参数有用于签名，不能随便添加参数
        //金额单位是元
        String orderInfo = AlipayUtil.getOrderInfo(HisOrderType.getDescByKey(hisPayOrder.getOrderType()),
                HisOrderType.getDescByKey(hisPayOrder.getOrderType()), hisPayOrder.getAmount().doubleValue(),
                orderId);
        String sign = AlipayUtil.alipaySign(orderInfo);
        try {
            // 仅需对sign 做URL编码
            sign = URLEncoder.encode(sign, "UTF-8");
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        payParams.put("orderInfo", orderInfo);
        payParams.put("sign", sign);
        payParams.put("signType", AlipayConfig.sign_type);
        payParams.put("orderId", orderId);
        return payParams;
    }

    @Override
    public boolean pay(HisPayOrder hisPayOrder) {
        return false;
    }

    @Override
    public boolean refund(HisPayOrder hisPayOrder) {
        return false;
    }
}