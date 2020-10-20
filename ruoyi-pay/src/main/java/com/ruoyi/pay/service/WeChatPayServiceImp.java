package com.ruoyi.pay.service;

import com.alibaba.fastjson.JSON;
import com.ruoyi.common.enums.HisOrderType;
import com.ruoyi.common.model.HisPayOrder;
import com.ruoyi.pay.config.WechatConfig;
import com.ruoyi.pay.utils.WeixinH5PayUtils;
import com.ruoyi.pay.utils.WeixinPayUtils;
import com.ruoyi.pay.utils.WxSignCode;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

@Service
public class WeChatPayServiceImp extends AbstractPayService {
    private static final Logger LOG = LoggerFactory.getLogger(WeChatPayServiceImp.class);

    @Override
    public Map<String, String> prePay(HisPayOrder hisPayOrder) {

        LOG.info(">>>>>>>>>>微信预支付开始:" + JSON.toJSONString(hisPayOrder));


        Map<String, String> wxPayParams = new HashMap<String, String>();
        String orderId = hisPayOrder.getOutTradeNo();
        WxSignCode sign = new WxSignCode();
        //获取商户的配置参数
        sign.setAppid(WechatConfig.appId);
        sign.setMch_id(WechatConfig.mchid);
        sign.setBody(HisOrderType.getDescByKey(hisPayOrder.getOrderType()));
        sign.setNonce_str(WeixinPayUtils.createNoncestr());
        sign.setOpenid(hisPayOrder.getOpenId());
        sign.setOut_trade_no(orderId);//解决一个订单多个详情发起多个支付，导致订单号重复问题
        sign.setSpbill_create_ip("127.0.0.1");
        sign.setTotal_fee(String.valueOf(hisPayOrder.getAmount().multiply(BigDecimal.valueOf(100)).setScale(0, BigDecimal.ROUND_HALF_UP).intValue()));
        sign.setNotify_url(WechatConfig.baseUrl);
//        sign.setAttach(phone);//传递电话号码，用于回调更新详情信息
        LOG.info(">>>>>>>>>>微信预支付参数:" + JSON.toJSONString(sign));
        String code = WeixinPayUtils.getWxUnifiedOrderParamsXML(sign);
        LOG.info(">>>>>>>>>>微信预支付CODE:" + code + ",WechatConfig.unifiedorderUrl:" + WechatConfig.unifiedorderUrl);
        Map<String, String> jsonObject = WeixinPayUtils.httpRequest(WechatConfig.unifiedorderUrl, "POST", code);
        LOG.info(">>>>>>>>>>微信预支付结果:" + JSON.toJSONString(jsonObject));
        String prepayid = jsonObject.get("prepay_id");
        wxPayParams.put("appId", WechatConfig.appId);
        wxPayParams.put("timeStamp", Long.toString(new Date().getTime()));
        wxPayParams.put("nonceStr", WeixinPayUtils.createNoncestr());
        wxPayParams.put("package", "prepay_id=" + prepayid);
        wxPayParams.put("signType", "MD5");
        wxPayParams.put("paySign", WeixinPayUtils.WxSignCreate(wxPayParams));
        wxPayParams.put("prepayid", prepayid);
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
