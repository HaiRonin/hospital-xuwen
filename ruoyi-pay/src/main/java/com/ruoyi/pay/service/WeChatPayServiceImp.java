package com.ruoyi.pay.service;

import com.ruoyi.common.core.domain.AjaxResult;
import com.ruoyi.common.enums.HisOrderType;
import com.ruoyi.common.model.HisPayOrder;
import com.ruoyi.common.utils.StringUtils;
import com.ruoyi.pay.config.WechatConfig;
import com.ruoyi.pay.utils.WeixinAppPayUtils;
import com.ruoyi.pay.utils.WeixinLoginUtils;
import com.ruoyi.pay.utils.WeixinPayUtils;
import com.ruoyi.pay.utils.WxSignCode;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

@Service
public class WeChatPayServiceImp extends AbstractPayService {


    @Override
    public Map<String, String> prePay(HisPayOrder hisPayOrder) {
        Map<String, String> wxPayParams = new HashMap<String, String>();
        String orderId = hisPayOrder.getOutTradeNo() + "_" + new Date().getTime();
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
        String code = WeixinPayUtils.getWxUnifiedOrderParamsXML(sign);
        Map<String, String> jsonObject = WeixinPayUtils.httpRequest(WechatConfig.unifiedorderUrl, "POST", code);
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
        return false;
    }
}
