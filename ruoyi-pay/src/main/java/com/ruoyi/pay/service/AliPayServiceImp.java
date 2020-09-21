package com.ruoyi.pay.service;

import com.alibaba.fastjson.JSON;
import com.alipay.api.AlipayApiException;
import com.alipay.api.AlipayClient;
import com.alipay.api.DefaultAlipayClient;
import com.alipay.api.domain.AlipayTradeAppPayModel;
import com.alipay.api.internal.util.AlipaySignature;
import com.alipay.api.request.AlipayTradeAppPayRequest;
import com.alipay.api.response.AlipayTradeAppPayResponse;
import com.ruoyi.common.enums.HisOrderType;
import com.ruoyi.common.model.HisPayOrder;
import com.ruoyi.pay.config.AlipayConfig;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

@Service
public class AliPayServiceImp extends AbstractPayService {

    private static final Logger LOG = LoggerFactory.getLogger(AliPayServiceImp.class);

    @Override
    public Map<String, String> prePay(HisPayOrder hisPayOrder) {
        String orderId = hisPayOrder.getOutTradeNo();
        Map<String, String> payParams = new HashMap<String, String>();// 里面的参数有用于签名，不能随便添加参数
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
//            model.setTotalAmount("" + hisPayOrder.getAmount().doubleValue());
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
            LOG.info(">>>>>>>>>>>支付宝签名失败：" + e.getMessage(), e);
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

    public Map<String, String> appAliPayNotify(HttpServletRequest request) {
        LOG.info("----------------阿里服务器消费手机回调-------" + request);

        Map<String, String> result = new HashMap<>();

        Map<String, String> params = new HashMap<String, String>();
        Map requestParams = request.getParameterMap();
        LOG.info("----------------阿里服务器回调入参1-------" + JSON.toJSONString(requestParams));
        for (Iterator iter = requestParams.keySet().iterator(); iter.hasNext(); ) {
            String name = (String) iter.next();
            String[] values = (String[]) requestParams.get(name);
            String valueStr = "";
            for (int i = 0; i < values.length; i++) {
                valueStr = (i == values.length - 1) ? valueStr + values[i] : valueStr + values[i] + ",";
            }
            // 乱码解决，这段代码在出现乱码时使用。如果mysign和sign不相等也可以使用这段代码转化
            // valueStr = new String(valueStr.getBytes("ISO-8859-1"), "gbk");
            params.put(name, valueStr);
        }
        LOG.info("----------------阿里服务器回调入参2-------" + JSON.toJSONString(requestParams));
        String out_trade_no = "";
        String trade_no = "";
        String status = "FAIL";
        String total_amount = "";
        boolean flag = false;
        try {
            // 调用SDK验证签名
            flag = AlipaySignature.rsaCheckV1(params, AlipayConfig.ali_public_key,
                    AlipayConfig.input_charset, AlipayConfig.sign_type);
            // 商户订单号
            out_trade_no = new String(request.getParameter("out_trade_no").getBytes("ISO-8859-1"), "UTF-8");
            // 支付宝交易号
            trade_no = new String(request.getParameter("trade_no").getBytes("ISO-8859-1"), "UTF-8");
            // 交易状态
            String trade_status = new String(request.getParameter("trade_status").getBytes("ISO-8859-1"), "UTF-8");
            total_amount = new String(request.getParameter("total_amount").getBytes("ISO-8859-1"), "UTF-8");
            if (flag && trade_status.equals("TRADE_SUCCESS")) {
                status = "SUCCESS";
            }

        } catch (Exception e) {
            LOG.info(">>>>>>>>>>>支付宝回调解析失败：" + e.getMessage(), e);
        } finally {
            result.put("out_trade_no", out_trade_no);
            result.put("trade_no", trade_no);
            result.put("total_amount", total_amount);
            result.put("status", status);
            result.put("flag", "" + flag);

            LOG.info(">>>>>>>>>>>支付宝回调解析结果：", result);
        }
        return result;
    }
}
