package com.ruoyi.pay.service;

import com.ruoyi.common.enums.HisOrderType;
import com.ruoyi.common.model.HisPayOrder;
import com.ruoyi.pay.config.WechatConfig;
import com.ruoyi.pay.utils.WeixinAppPayUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

@Service
public class WxAppPayServiceImp extends AbstractPayService {

    private static final Logger LOG = LoggerFactory.getLogger(WxAppPayServiceImp.class);

    @Override
    public Map<String, String> prePay(HisPayOrder hisPayOrder) {
        String orderId = hisPayOrder.getOutTradeNo();

        Map<String, String> wxPayParams = new HashMap<String, String>();// 里面的参数有用于微信的签名，不能随便添加参数
        Double price = new Double(hisPayOrder.getAmount().doubleValue() * 100);//支付金额 单位是分
        String prepayid = WeixinAppPayUtils.getPrePayId(
                HisOrderType.getDescByKey(hisPayOrder.getOrderType()), orderId,
                String.valueOf(price.longValue()));

        LOG.info(">>>>>>>>>>>>>>>>>>微信支付prepayid=" + prepayid);

        wxPayParams
                .put("appid",
                        WechatConfig.appId);
        wxPayParams.put("noncestr", WeixinAppPayUtils.createNoncestr());
        wxPayParams.put("package", "Sign=WXPay");
        wxPayParams
                .put("partnerid",
                        WechatConfig.mchid);
        wxPayParams.put("prepayid", prepayid);
        wxPayParams.put("timestamp",
                Long.toString(new Date().getTime() / 1000));

        LOG.info(">>>>>>>>>>>>>>>>>>微信支付签名参数：" + wxPayParams);
        String sign = WeixinAppPayUtils.WxSignCreate(wxPayParams);
        LOG.info(">>>>>>>>>>>>>>>>>>微信支付签名结果：" + sign);

        wxPayParams.put("sign", sign);
        wxPayParams.put("packageValue", "Sign=WXPay");

        return wxPayParams;
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
