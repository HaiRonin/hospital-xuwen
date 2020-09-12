package com.ruoyi.web.controller.his.controller;

import com.ruoyi.common.core.controller.BaseController;
import com.ruoyi.common.core.domain.AjaxResult;
import com.ruoyi.common.utils.StringUtils;
import com.ruoyi.utils.pay.*;
import com.ruoyi.web.core.config.AlipayConfig;
import com.ruoyi.web.core.config.WechatConfig;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.UnsupportedEncodingException;
import java.math.BigDecimal;
import java.net.URLEncoder;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;


/**
 * @Description : 支付请求
 * @Author : yuanhualiang
 * @Date: 2020-09-04 23:59
 */
@Controller
@RequestMapping("/hospital/pay/")
public class PayController extends BaseController {

    private static final Logger LOG = LoggerFactory.getLogger(PayController.class);

    /**
     * 微信APP支付
     *
     * @param orderPrice 订单金额
     * @param request
     * @return
     */
    @PostMapping("/wechat_apppay")
    @ResponseBody
    public AjaxResult wxAppPay(Double orderPrice, HttpServletRequest request) {

        String orderId = new Date().getTime() + "_wxapp_" + WeixinAppPayUtils.createNoncestr(3);

        Map<String, String> wxPayParams = new HashMap<String, String>();// 里面的参数有用于微信的签名，不能随便添加参数
        Double price = new Double(orderPrice * 100);//支付金额 单位是分
        String prepayid = WeixinAppPayUtils.getPrePayId(
                "预约支付", orderId,
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

        return AjaxResult.success("微信APP预支付成功", wxPayParams);
    }

    /**
     * 支付宝支付
     *
     * @param orderPrice
     * @param request
     * @return
     */
    @PostMapping("/ali_apppay")
    @ResponseBody
    public AjaxResult aliAppPay(Double orderPrice, HttpServletRequest request) {

        String orderId = new Date().getTime() + "_aliapp_" + WeixinAppPayUtils.createNoncestr(3);
        Map<String, String> payParams = new HashMap<String, String>();// 里面的参数有用于签名，不能随便添加参数
        //金额单位是元
        String orderInfo = AlipayUtil.getOrderInfo("预约支付",
                "预约支付", orderPrice,
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

        return AjaxResult.success("支付宝预支付成功", payParams);
    }

    /**
     * 微信公众号支付
     *
     * @param orderPrice 订单金额
     * @param request
     * @return
     */
    @PostMapping("/wechat_pay")
    @ResponseBody
    public AjaxResult weixinPay(Double orderPrice, HttpServletRequest request, HttpServletResponse response) {

        String orderId = new Date().getTime() + "_wx_" + WeixinAppPayUtils.createNoncestr(3);

        WxSignCode sign = new WxSignCode();
        //获取商户的配置参数
        sign.setAppid(WechatConfig.appId);
        sign.setMch_id(WechatConfig.mchid);
        sign.setBody("预约支付");
        sign.setNonce_str(WeixinPayUtils.createNoncestr());
        String openId = WeixinLoginUtils.getOpenIdFromCookie(request, response);
        if (StringUtils.isEmpty(openId)) {
            return AjaxResult.error("openId获取失败");
        }
        sign.setOpenid(openId);
        sign.setOut_trade_no(orderId);//解决一个订单多个详情发起多个支付，导致订单号重复问题
        sign.setSpbill_create_ip("127.0.0.1");
        sign.setTotal_fee(String.valueOf(BigDecimal.valueOf(orderPrice * 100).setScale(0, BigDecimal.ROUND_HALF_UP).intValue()));
        sign.setNotify_url(WechatConfig.baseUrl);
//        sign.setAttach(phone);//传递电话号码，用于回调更新详情信息
        String code = WeixinPayUtils.getWxUnifiedOrderParamsXML(sign);
        String url = "https://api.mch.weixin.qq.com/pay/unifiedorder";
        Map<String, String> jsonObject = WeixinPayUtils.httpRequest(WechatConfig.unifiedorderUrl, "POST", code);
        String prepayid = jsonObject.get("prepay_id");
        Map<String, String> wxPayParams = new HashMap<String, String>();
        wxPayParams.put("appId", WechatConfig.appId);
        wxPayParams.put("timeStamp", Long.toString(new Date().getTime()));
        wxPayParams.put("nonceStr", WeixinPayUtils.createNoncestr());
        wxPayParams.put("package", "prepay_id=" + prepayid);
        wxPayParams.put("signType", "MD5");
        wxPayParams.put("paySign", WeixinPayUtils.WxSignCreate(wxPayParams));
        wxPayParams.put("prepayid", prepayid);

        return AjaxResult.success("微信预支付成功", wxPayParams);
    }

    /**
     * 微信通知
     *
     * @param request
     * @return
     */
    @ResponseBody
    @RequestMapping(value = "/notify_weixin", method = RequestMethod.POST)
    public String wxPayCallBackNotify(HttpServletRequest request) {
        try {
            Map<String, String> map = WeixinMessageUtil.parseXml(request);

            LOG.info(">>>>>>>>>>>>>>>>>>>微信支付回调解析结果=" + map);

            // 微信交易订单号
            String transaction_id = map.get("transaction_id");
            // 系统订单号
            String orderId = map.get("out_trade_no");

            LOG.info(">>>>>>>>>>>>>>>>>>>微信支付回调订单ID=" + LOG);

        } catch (Exception e) {
            e.printStackTrace();
            return "FAIL";
        }

        return "SUCCESS";
    }

    /**
     * 支付宝支付回调
     *
     * @param request
     * @return
     */
    @ResponseBody
    @RequestMapping(value = "/notify_alipay", method = RequestMethod.POST)
    public String alipayCallBackNotify(HttpServletRequest request) {
        try {
            Map<String, String> map = WeixinMessageUtil.parseXml(request);

            LOG.info(">>>>>>>>>>>>>>>>>>>支付宝支付回调解析结果=" + map);

            // 交易订单号
            String trade_no = request.getParameter("trade_no");
            // 系统订单号
            String orderId = request.getParameter("out_trade_no");

            LOG.info(">>>>>>>>>>>>>>>>>>>支付宝支付回调订单ID=" + LOG);

        } catch (Exception e) {
            e.printStackTrace();
            return "fail";
        }

        return "success";
    }
}
