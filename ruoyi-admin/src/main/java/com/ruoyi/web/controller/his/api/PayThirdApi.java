package com.ruoyi.web.controller.his.api;

import com.alibaba.fastjson.JSON;
import com.google.common.collect.Maps;
import com.ruoyi.common.annotation.Log;
import com.ruoyi.common.core.controller.BaseController;
import com.ruoyi.common.core.domain.AjaxResult;
import com.ruoyi.common.enums.BusinessType;
import com.ruoyi.common.enums.HisOrderType;
import com.ruoyi.common.enums.HisPayType;
import com.ruoyi.common.model.HisPayOrder;
import com.ruoyi.common.utils.StringUtils;
import com.ruoyi.pay.service.AbstractPayService;
import com.ruoyi.pay.service.PayService;
import com.ruoyi.pay.utils.*;
import com.ruoyi.pay.config.AlipayConfig;
import com.ruoyi.pay.config.WechatConfig;
import com.ruoyi.vo.OrderPayResultBO;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
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
 * @Description : 第三方支付请求
 * @Author : yuanhualiang
 * @Date: 2020-09-04 23:59
 */
@Controller
@Api(value = "第三方支付请求", tags = {"第三方支付请求"})
@RequestMapping("/hospital/wechat/")
public class PayThirdApi extends BaseController {

    private static final Logger LOG = LoggerFactory.getLogger(PayThirdApi.class);

    /**
     * 订单请求
     */
    @Autowired
    private HisOrderApi hisOrderApi;


    /**
     * 获取openId
     * 微信授权code只能用一次，下次从cookie取
     */
    @Log(title = "获取openId", businessType = BusinessType.HIS)
    @ApiOperation("获取OPENID")
    @GetMapping("/getOpenid")
    @ResponseBody
    public AjaxResult getOpenId(String code, HttpServletRequest request, HttpServletResponse response) {
        Map<String, String> resultData = Maps.newHashMap();
        resultData.put("openId", WeixinLoginUtils.thirtypartyUserLogin(request, response));
        return AjaxResult.success("获取OPENID", resultData);
    }

    /**
     * 预支付
     *
     * @param order
     * @param request
     * @param response
     * @return
     */
    @ApiOperation("预支付")
    @PostMapping("pay")
    @ResponseBody
    public AjaxResult pay(HisPayOrder order, HttpServletRequest request, HttpServletResponse response) {
        PayService payService = AbstractPayService.servicesInstance(order.getPayType());
        Map<String, String> result = payService.prePay(order);

        return AjaxResult.success("预支付成功[" + HisOrderType.getDescByKey(order.getOrderType()) + "]", result);
    }

    /**
     * 微信APP支付
     *
     * @param orderPrice 订单金额
     * @param request
     * @return
     */
    @ApiOperation("微信APP预支付")
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
    @ApiOperation("支付宝预支付")
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
    @ApiOperation("微信公众号预支付")
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
            String out_trade_no = map.get("out_trade_no");
            String orderId = out_trade_no.split("_")[0];
            LOG.info(">>>>>>>>>>>>>>>>>>>微信支付回调out_trade_no=" + orderId + ",transaction_id=" + transaction_id);

            OrderPayResultBO bo = new OrderPayResultBO();
            bo.setOrderType(HisPayType.WECHAT.getKey());
            bo.setOutTradeNo(orderId);
            bo.setTransactionId(transaction_id);
            bo.setPaymentResults(true);
            AjaxResult result = hisOrderApi.orderPayCallBack(bo);
            LOG.info(">>>>>>>>>>>>>>>>>>>微信支付回调下单结果：" + JSON.toJSONString(result));
            if(result.get(AjaxResult.CODE_TAG) == AjaxResult.Type.SUCCESS) {
                return "SUCCESS";
            } else {
                return "FAIL";
            }


        } catch (Exception e) {
            e.printStackTrace();
            return "FAIL";
        }
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