package com.ruoyi.web.controller.his.controller;

import com.ruoyi.common.core.controller.BaseController;
import com.ruoyi.common.core.domain.AjaxResult;
import com.ruoyi.utils.pay.WeixinMessageUtil;
import com.ruoyi.utils.pay.WeixinPayUtils;
import com.ruoyi.web.core.config.WechatConfig;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;


/**
 * @Description : 支付请求
 * @Author : yuanhualiang
 * @Date: 2020-09-04 23:59
 */
@Controller
@RequestMapping("/his")
public class PayController extends BaseController {

    private static final Logger LOG = LoggerFactory.getLogger(PayController.class);

    @PostMapping("/wechat/pay")
    @ResponseBody
    public AjaxResult weixinPay(String orderId, HttpServletRequest request) {

        orderId = "1";//订单ID

        Map<String, String> wxPayParams = new HashMap<String, String>();// 里面的参数有用于微信的签名，不能随便添加参数
        Double price = new Double(0.01 * 100);//支付金额 单位是分
        String prepayid = WeixinPayUtils.getPrePayId(
                "预约支付", orderId,
                String.valueOf(price.longValue()));

        LOG.info(">>>>>>>>>>>>>>>>>>微信支付prepayid=" + prepayid);

        wxPayParams
                .put("appid",
                        WechatConfig.appId);
        wxPayParams.put("noncestr", WeixinPayUtils.CreateNoncestr());
        wxPayParams.put("package", "Sign=WXPay");
        wxPayParams
                .put("partnerid",
                        WechatConfig.mchid);
        wxPayParams.put("prepayid", prepayid);
        wxPayParams.put("timestamp",
                Long.toString(new Date().getTime() / 1000));

        LOG.info(">>>>>>>>>>>>>>>>>>微信支付签名参数：" + wxPayParams);
        String sign = WeixinPayUtils.WxSignCreate(wxPayParams);
        LOG.info(">>>>>>>>>>>>>>>>>>微信支付签名结果：" + sign);

        wxPayParams.put("sign", sign);
        wxPayParams.put("packageValue", "Sign=WXPay");

        return AjaxResult.success("支付成功", wxPayParams);
    }

    /**
     * 微信通知
     *
     * @param request
     * @return
     */
    @ResponseBody
    @RequestMapping(value = "/wechat/notify_weixin", method = RequestMethod.POST)
    public String WxPayCallBackNotify(HttpServletRequest request) {
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
}
