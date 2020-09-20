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