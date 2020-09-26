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
import com.ruoyi.pay.service.AliPayServiceImp;
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
import java.net.URLDecoder;
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
@RequestMapping("/his/wechat/")
@CrossOrigin
public class PayThirdApi extends BaseController {

    private static final Logger LOG = LoggerFactory.getLogger(PayThirdApi.class);

    @Autowired
    private AliPayServiceImp aliPayServiceImp;

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
        order.setAmount(new BigDecimal("0.01"));
        Map<String, String> result = payService.prePay(order);

        return AjaxResult.success("预支付成功[" + HisOrderType.getDescByKey(order.getOrderType()) + "]", result);
    }

    /**
     * 微信通知
     *
     * @param request
     * @return
     */
    @Log(title = "微信支付回调", businessType = BusinessType.HIS)
    @ApiOperation("微信支付回调")
    @ResponseBody
    @RequestMapping(value = "/notify_weixin", method = RequestMethod.POST)
    public String wxPayCallBackNotify(HttpServletRequest request) {
        LOG.info(">>>>>>>>>>>>>>>>>>>微信支付回调开始");
        try {
            Map<String, String> map = WeixinMessageUtil.parseXml(request);

            LOG.info(">>>>>>>>>>>>>>>>>>>微信支付回调解析结果=" + map);

            // 微信交易订单号
            String transaction_id = map.get("transaction_id");
            // 系统订单号
            String out_trade_no = map.get("out_trade_no");
            LOG.info(">>>>>>>>>>>>>>>>>>>微信支付回调out_trade_no=" + out_trade_no + ",transaction_id=" + transaction_id);

            OrderPayResultBO bo = new OrderPayResultBO();
            bo.setOrderType(HisOrderType.tranferTypeByOrderPrex(out_trade_no));
            bo.setOutTradeNo(out_trade_no);
            bo.setTransactionId(transaction_id);
            bo.setPaymentResults(true);
            LOG.info(">>>>>>>>>>>>>>>>>>>微信hisOrderApi.orderPayCallBack调用入参：" + JSON.toJSONString(bo));
            AjaxResult callResult = hisOrderApi.orderPayCallBack(bo);
            LOG.info(">>>>>>>>>>>>>>>>>>>微信hisOrderApi.orderPayCallBack结果：" + JSON.toJSONString(callResult));
            if (Integer.parseInt(callResult.get(AjaxResult.CODE_TAG).toString()) == AjaxResult.Type.SUCCESS.value()) {
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
    @Log(title = "支付宝支付回调", businessType = BusinessType.HIS)
    @ApiOperation("支付宝支付回调")
    @ResponseBody
    @RequestMapping(value = "/notify_alipay", method = RequestMethod.POST)
    public String alipayCallBackNotify(HttpServletRequest request) {
        try {
            LOG.info(">>>>>>>>>>>>>>>>>>>支付宝支付回调开始>>>>>>>>>>>>>>>>>>>>>");
            Map<String, String> result = aliPayServiceImp.appAliPayNotify(request);
            LOG.info(">>>>>>>>>>>>>>>>>>>支付宝支付回调解析>>>>>>>>>>>>>>>>>>>>>" + result);

            String status = result.get("status");
            if ("SUCCESS".equals(status)) {
                // 交易订单号
                String trade_no = result.get("trade_no");
                // 系统订单号
                String out_trade_no = result.get("out_trade_no");

                OrderPayResultBO bo = new OrderPayResultBO();
                bo.setOrderType(HisOrderType.tranferTypeByOrderPrex(out_trade_no));
                bo.setOutTradeNo(out_trade_no);
                bo.setTransactionId(trade_no);
                bo.setPaymentResults(true);
                LOG.info(">>>>>>>>>>>>>>>>>>>支付宝hisOrderApi.orderPayCallBack调用入参：" + JSON.toJSONString(bo));
                AjaxResult callResult = hisOrderApi.orderPayCallBack(bo);
                LOG.info(">>>>>>>>>>>>>>>>>>>支付宝hisOrderApi.orderPayCallBack调用结果：" + JSON.toJSONString(callResult));
                if (Integer.parseInt(callResult.get(AjaxResult.CODE_TAG).toString()) == AjaxResult.Type.SUCCESS.value()) {
                    return "success";
                } else {
                    return "fail";
                }
            }
            LOG.info(">>>>>>>>>>>>>>>>>>>支付宝支付回调结束");

        } catch (Exception e) {
            e.printStackTrace();
        }

        return "fail";
    }

    /**
     * 微信退款
     *
     * @return
     */
    @Log(title = "微信退款", businessType = BusinessType.HIS)
    @ApiOperation("微信退款")
    @ResponseBody
    @RequestMapping(value = "/refund_weixin", method = RequestMethod.POST)
    public AjaxResult weixinRefund(HisPayOrder hisPayOrder) {
        PayService payService = AbstractPayService.servicesInstance(hisPayOrder.getPayType());
        boolean result = payService.refund(hisPayOrder);

        Map<String, String> map = new HashMap<String, String>();
        return AjaxResult.success("微信退款完成", result);
    }


    /**
     * 微信图片上传签名
     *
     * @return
     */
    @Log(title = "微信图片上传签名", businessType = BusinessType.HIS)
    @ApiOperation("微信图片上传签名")
    @ResponseBody
    @RequestMapping(value = "/weixinImgSign", method = RequestMethod.POST)
    public AjaxResult weixinImgSign(HttpServletRequest request, HttpServletResponse response) throws UnsupportedEncodingException {

        /*
         *以http://localhost/test.do?a=b&c=d为例
         *request.getRequestURL的结果是http://localhost/test.do
         *request.getQueryString的返回值是a=b&c=d
         */
        String urlString = request.getRequestURL().toString();
        String queryString = request.getQueryString();
        String queryStringEncode = null;
        String url;
        if (queryString != null) {
            queryStringEncode = URLDecoder.decode(queryString, "UTF-8");
            url = urlString + "?" + queryStringEncode;
        } else {
            url = urlString;
        }

        String nonceStr = WeixinPayUtils.createNoncestr();      //随机数
        long timeStamp = System.currentTimeMillis() / 1000;     //时间戳参数
        String signedUrl = url;
        String signature = null;       //签名
        String accessToken = "";
        String ticket = "";
        try {
            //1.4 jsapi_ticket
            accessToken = WeixinMessageUtil.getAccessToken(request, response);
            ticket = WeixinMessageUtil.getJsapiTicket(accessToken, request, response);
            //2.进行签名，获取signature
            signature = WeixinPayUtils.getJsApiSign(ticket, nonceStr, timeStamp, signedUrl);
        } catch (Exception e) {
            e.printStackTrace();
        }

        logger.info("accessToken:" + accessToken);
        logger.info("ticket:" + ticket);
        logger.info("nonceStr:" + nonceStr);
        logger.info("timeStamp:" + timeStamp);
        logger.info("signedUrl:" + signedUrl);
        logger.info("signature:" + signature);
        logger.info("appId:" + WechatConfig.appId);

        Map<String, String> result = new HashMap<>();
        result.put("appId", WechatConfig.appId);
        result.put("timestamp", "" + timeStamp);
        result.put("nonceStr", nonceStr);
        result.put("signature", signature);
        logger.info(">>>>>>>>>>>>>>签名结果：" + JSON.toJSONString(result));


        return AjaxResult.success("微信图片上传签名", result);
    }
}