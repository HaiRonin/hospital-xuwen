package com.ruoyi.pay.service;

import com.alipay.api.AlipayApiException;
import com.alipay.api.AlipayClient;
import com.alipay.api.DefaultAlipayClient;
import com.alipay.api.domain.AlipayTradeAppPayModel;
import com.alipay.api.request.AlipayTradeAppPayRequest;
import com.alipay.api.response.AlipayTradeAppPayResponse;
import com.ruoyi.common.enums.HisOrderType;
import com.ruoyi.common.model.HisPayOrder;
import com.ruoyi.pay.config.AlipayConfig;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

@Service
public class AliPayServiceImp extends AbstractPayService {

    private static final Logger LOG = LoggerFactory.getLogger(AliPayServiceImp.class);

    @Override
    public Map<String, String> prePay(HisPayOrder hisPayOrder) {
        String orderId = hisPayOrder.getOutTradeNo();
        Map<String, String> payParams = new HashMap<String, String>();// 里面的参数有用于签名，不能随便添加参数
//        //金额单位是元
//        String orderInfo = AlipayUtil.getOrderInfo(HisOrderType.getDescByKey(hisPayOrder.getOrderType()),
//                HisOrderType.getDescByKey(hisPayOrder.getOrderType()), hisPayOrder.getAmount().doubleValue(),
//                orderId);
//        String sign = AlipayUtil.alipaySign(orderInfo);
//        try {
//            // 仅需对sign 做URL编码
//            sign = URLEncoder.encode(sign, "UTF-8");
//        } catch (UnsupportedEncodingException e) {
//            e.printStackTrace();
//        }
//        payParams.put("orderInfo", orderInfo + "&sign=\"" + sign + "\"&sign_type=\"RSA\"");
//        payParams.put("sign", sign);
//        payParams.put("signType", AlipayConfig.sign_type);
//        payParams.put("orderId", orderId);

        try {
            AlipayClient alipayClient = new DefaultAlipayClient("https://openapi.alipay.com/gateway.do",
                    AlipayConfig.appid, AlipayConfig.private_key, "json", AlipayConfig.input_charset,
                    AlipayConfig.ali_public_key, AlipayConfig.sign_type);

            // 发起App支付请求
            AlipayTradeAppPayRequest request = new AlipayTradeAppPayRequest();
            AlipayTradeAppPayModel model = new AlipayTradeAppPayModel();
            // 订单描述
            model.setBody(HisOrderType.getDescByKey(hisPayOrder.getOrderType()));
            // 订单标题
            model.setSubject(HisOrderType.getDescByKey(hisPayOrder.getOrderType()));
            // 商户订单号 就是商户后台生成的订单号
            model.setOutTradeNo(orderId);
            // 该笔订单允许的最晚付款时间，逾期将关闭交易。取值范围：1m～15d。m-分钟，h-小时，d-天，1c-当天 (屁股后面的字母一定要带，不然报错)
            model.setTimeoutExpress("30m");
            // 订单总金额 ，默认单位为元，精确到小数点后两位，取值范围[0.01,100000000]
            model.setTotalAmount("0.01");
            // 销售产品码 不必填
            model.setProductCode(HisOrderType.getDescByKey(hisPayOrder.getOrderType()));
            request.setBizModel(model);
            request.setNotifyUrl(AlipayConfig.baseUrl);
            // 通过api的方法请求阿里接口获得反馈
            AlipayTradeAppPayResponse response = alipayClient.sdkExecute(request);
            String responseBody = response.getBody();
            LOG.info(">>>>>>>>>>>支付宝签名结果：" + responseBody);
            if (response.isSuccess()) {
                payParams.put("orderInfo", responseBody);
                LOG.info("调用成功，订单信息：" + responseBody);
            } else {
                payParams.put("orderInfo", "签名失败");
                LOG.info(">>>>>>>>>>>支付宝签名失败：" + new String(responseBody.getBytes("UTF-8")));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

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
