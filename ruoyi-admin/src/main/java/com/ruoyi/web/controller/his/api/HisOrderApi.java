package com.ruoyi.web.controller.his.api;

import com.alibaba.fastjson.JSON;
import com.ruoyi.common.annotation.Log;
import com.ruoyi.common.core.controller.BaseController;
import com.ruoyi.common.core.domain.AjaxResult;
import com.ruoyi.common.enums.BusinessType;
import com.ruoyi.common.model.HisPayOrder;
import com.ruoyi.common.utils.StringUtils;
import com.ruoyi.common.utils.uuid.IdUtils;
import com.ruoyi.his.callservice.HisPayOrderServices;
import com.ruoyi.his.constant.HisBusinessTypeEnum;
import com.ruoyi.his.constant.PayStatusEnum;
import com.ruoyi.his.domain.DepositPayment;
import com.ruoyi.his.domain.DopayInfo;
import com.ruoyi.his.domain.DoregInfo;
import com.ruoyi.his.domain.LeaveHosPay;
import com.ruoyi.his.remote.AbstractHisServiceHandler;
import com.ruoyi.his.remote.request.DoRegCancel;
import com.ruoyi.his.remote.request.ToPayRecipeInfoIn;
import com.ruoyi.his.remote.response.BaseResponse;
import com.ruoyi.his.service.*;
import com.ruoyi.pay.config.WechatConfig;
import com.ruoyi.pay.constant.HisPayType;
import com.ruoyi.pay.service.AbstractPayService;
import com.ruoyi.pay.service.PayService;
import com.ruoyi.vo.OrderPayResultBO;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;
import java.util.Date;

/**
 * 预约挂号Controller
 * 
 * @author whl
 * @date 2020-08-08
 */
@RestController
@Api(value="his下单接口",tags={"his下单接口"})
@RequestMapping("/his/order")
@CrossOrigin
public class HisOrderApi extends BaseController
{
    @Autowired
    private IDoregInfoService doregInfoService;

    @Autowired
    private IDopayInfoService dopayInfoService;

    @Autowired
    private IDepositPaymentService depositPaymentService;

    @Autowired
    private ILeaveHosPayService leaveHosPayService;
    @Autowired
    private HisPayOrderServices hisPayOrderServices;

    /**
     * 取消预约
     */
    @Log(title = "本地调用", businessType = BusinessType.HIS_LOCALHOST)
    @ApiOperation("取消预约")
    @PostMapping("/doRegCancel")
    @ResponseBody
    public AjaxResult doRegCancel(@RequestBody DoRegCancel doRegCancel)
    {
        getRequest().setAttribute("api", "doRegCancel");
        getRequest().setAttribute("dataParam", JSON.toJSONString(doRegCancel));

        BaseResponse baseResponse = doregInfoService.doRegCancel(doRegCancel);
        return baseResponse.isOk()?AjaxResult.success(baseResponse.getResultMsg()):AjaxResult.error(baseResponse.getResultMsg());
    }

    /**
     * 新增预约挂号的记录
     */
    @Log(title = "本地调用", businessType = BusinessType.HIS_LOCALHOST)
    @ApiOperation("新增预约挂号的记录")
    @PostMapping("/outpatientPayment")
    @ResponseBody
    public AjaxResult outpatientPayment(@RequestBody DoregInfo doregInfo)
    {
        getRequest().setAttribute("api", "outpatientPayment");
        getRequest().setAttribute("dataParam", JSON.toJSONString(doregInfo));
        if(StringUtils.isEmpty(doregInfo.getPayType())){
            doregInfo.setPayType("5");
        }
        doregInfo.setAppId(WechatConfig.appId);
        doregInfo.setCreateBy(doregInfo.getSynUserName());
        doregInfo.setCreateTime(new Date());
        doregInfo.setSuccessfulPayment(PayStatusEnum.INIT.getCode());
        doregInfo.setOutTradeNo(IdUtils.getOrderNo("RE"));
        int iResult = doregInfoService.insertDoregInfo(doregInfo);
        if(iResult>0) {
            HisPayOrder order = new HisPayOrder();
            order.setPayType(doregInfo.getPayType());
            order.setAmount(doregInfo.getPayAmount());
            order.setOrderType("doreg");
            order.setOutTradeNo(doregInfo.getOutTradeNo());
            order.setOpenId(doregInfo.getOpenId());
            order.setRedirectUrl(doregInfo.getRedirectUrl());
            PayService payService = AbstractPayService.servicesInstance(order.getPayType());
//            order.setAmount(new BigDecimal("0.01"));
            doregInfo.setPrePaySign(payService.prePay(order));
        }
        return iResult>0?AjaxResult.success(doregInfo):AjaxResult.error("预约挂号失败");
    }

    /**
     * 新增缴费支付的记录
     */
    @Log(title = "本地调用", businessType = BusinessType.HIS_LOCALHOST)
    @ApiOperation("新增缴费支付的记录")
    @PostMapping("/newPayment")
    @ResponseBody
    public AjaxResult newPayment(@RequestBody ToPayRecipeInfoIn toPayRecipeInfoIn)
    {

        getRequest().setAttribute("api", "newPayment");
        getRequest().setAttribute("dataParam", JSON.toJSONString(toPayRecipeInfoIn));
        DopayInfo dopayInfo =  hisPayOrderServices.getToPayRecipeTotalAmount(toPayRecipeInfoIn);
        if(null == dopayInfo || StringUtils.isEmpty(dopayInfo.getHiFeeNos())
                || null == dopayInfo.getPayMoney() || dopayInfo.getPayMoney().compareTo(BigDecimal.ZERO) == 0){
            return AjaxResult.error("无法查询到缴费记录，无需缴费");
        }
        dopayInfo.setMedicareType(toPayRecipeInfoIn.getMedicareType());
        dopayInfo.setPayType(HisPayType.WECHAT.getKey());
        dopayInfo.setAppId(WechatConfig.appId);
        dopayInfo.setCreateBy(toPayRecipeInfoIn.getSynUserName());
        dopayInfo.setCreateTime(new Date());
        dopayInfo.setCreatTime(new Date());
        dopayInfo.setUpdateTime(new Date());
        dopayInfo.setSuccessfulPayment(PayStatusEnum.INIT.getCode());
        dopayInfo.setOutTradeNo(IdUtils.getOrderNo("DO"));
        dopayInfo.setUserNo(toPayRecipeInfoIn.getPatientNo());
        int iResult = dopayInfoService.insertDopayInfo(dopayInfo);
        if(iResult>0) {
            HisPayOrder order = new HisPayOrder();
            order.setPayType(dopayInfo.getPayType());
            order.setAmount(dopayInfo.getPayMoney().setScale(2));
            order.setOrderType("dopay");
            order.setOutTradeNo(dopayInfo.getOutTradeNo());
            order.setOpenId(toPayRecipeInfoIn.getOpenId());
            order.setRedirectUrl(dopayInfo.getRedirectUrl());
            PayService payService = AbstractPayService.servicesInstance(order.getPayType());
            dopayInfo.setPrePaySign(payService.prePay(order));
        }
        return iResult>0?AjaxResult.success(dopayInfo):AjaxResult.error("缴费支付失败");
    }

    /**
     * 新增押金补缴的记录
     */
    @Log(title = "本地调用", businessType = BusinessType.HIS_LOCALHOST)
    @ApiOperation("新增押金补缴的记录")
    @PostMapping("/payment")
    @ResponseBody
    public AjaxResult payment(@RequestBody DepositPayment depositPayment)
    {
        getRequest().setAttribute("api", "payment");
        getRequest().setAttribute("dataParam", JSON.toJSONString(depositPayment));
        if(StringUtils.isEmpty(depositPayment.getPayType())){
            depositPayment.setPayType("5");
        }
        depositPayment.setAppId(WechatConfig.appId);
        depositPayment.setCreateBy(depositPayment.getSynUserName());
        depositPayment.setCreateTime(new Date());
        depositPayment.setSuccessfulPayment(PayStatusEnum.INIT.getCode());
        depositPayment.setOutTradeNo(IdUtils.getOrderNo("DP"));
        int iResult = depositPaymentService.insertDepositPayment(depositPayment);
        if(iResult>0) {
            HisPayOrder order = new HisPayOrder();
            order.setPayType(depositPayment.getPayType());
            order.setAmount(depositPayment.getPayMoney());
            order.setOrderType("inpatientpayment");
            order.setOutTradeNo(depositPayment.getOutTradeNo());
            order.setOpenId(depositPayment.getOpenId());
            order.setRedirectUrl(depositPayment.getRedirectUrl());
            PayService payService = AbstractPayService.servicesInstance(order.getPayType());
            depositPayment.setPrePaySign(payService.prePay(order));
        }
        return iResult>0?AjaxResult.success(depositPayment):AjaxResult.error("押金补缴失败");
    }


    /**
     * 新增出院结算的记录
     */
    @Log(title = "本地调用", businessType = BusinessType.HIS_LOCALHOST)
    @ApiOperation("新增出院结算记录")
    @PostMapping("/leaveHosPay")
    @ResponseBody
    public AjaxResult leaveHosPay(@RequestBody LeaveHosPay leaveHosPay)
    {
        getRequest().setAttribute("api", "leaveHosPay");
        getRequest().setAttribute("dataParam", JSON.toJSONString(leaveHosPay));
        if(StringUtils.isEmpty(leaveHosPay.getPayType())){
            leaveHosPay.setPayType("5");
        }
        leaveHosPay.setAppId(WechatConfig.appId);
        leaveHosPay.setCreateBy(leaveHosPay.getSynUserName());
        leaveHosPay.setCreateTime(new Date());
        leaveHosPay.setSuccessfulPayment(PayStatusEnum.INIT.getCode());
        leaveHosPay.setOutTradeNo(IdUtils.getOrderNo("LH"));
        int iResult = leaveHosPayService.insertLeaveHosPay(leaveHosPay);

        if(iResult>0) {
            HisPayOrder order = new HisPayOrder();
            order.setPayType(leaveHosPay.getPayType());
            order.setAmount(leaveHosPay.getPayMoney());
            order.setOrderType("leavehospay");
            order.setOutTradeNo(leaveHosPay.getOutTradeNo());
            order.setOpenId(leaveHosPay.getOpenId());
            order.setRedirectUrl(leaveHosPay.getRedirectUrl());
            PayService payService = AbstractPayService.servicesInstance(order.getPayType());
            leaveHosPay.setPrePaySign(payService.prePay(order));
        }

        return iResult>0?AjaxResult.success(leaveHosPay):AjaxResult.error("出院结算失败");
    }

    /**
     * 订单支付
     */
    @Log(title = "本地调用", businessType = BusinessType.HIS_LOCALHOST)
    @ApiOperation("订单支付")
    @PostMapping("/order/orderPay")
    @ResponseBody
    public AjaxResult orderPay(@RequestBody OrderPayResultBO orderPayResultBO)
    {
        getRequest().setAttribute("api", "/order/orderPay");
        getRequest().setAttribute("dataParam", JSON.toJSONString(orderPayResultBO));
        HisBusinessTypeEnum hisBusinessTypeEnum = HisBusinessTypeEnum.getTypeByKey(orderPayResultBO.getOrderType());
        BaseResponse baseResponse = AbstractHisServiceHandler
                .servicesInstance(hisBusinessTypeEnum)
                .callPay(orderPayResultBO.getOutTradeNo());
        return baseResponse.isOk()?AjaxResult.success():AjaxResult.error();
    }

    /**
     * 支付成功或失败回调
     */
    @Log(title = "本地调用", businessType = BusinessType.HIS_LOCALHOST)
    @ApiOperation("支付成功或失败回调")
    @PostMapping("/order/payCallBack")
    @ResponseBody
    public AjaxResult orderPayCallBack(@RequestBody OrderPayResultBO orderPayResultBO)
    {
        getRequest().setAttribute("api", "/order/payCallBack");
        getRequest().setAttribute("dataParam", JSON.toJSONString(orderPayResultBO));
        HisBusinessTypeEnum hisBusinessTypeEnum = HisBusinessTypeEnum.getTypeByKey(orderPayResultBO.getOrderType());
        BaseResponse baseResponse = AbstractHisServiceHandler
                .servicesInstance(hisBusinessTypeEnum)
                .payedNotify(orderPayResultBO.isPaymentResults(),orderPayResultBO.getOutTradeNo(),orderPayResultBO.getTransactionId());
        return baseResponse.isOk()?AjaxResult.success():AjaxResult.error();
    }

    /**
     * 退款操作
     */
    @Log(title = "本地调用", businessType = BusinessType.HIS_LOCALHOST)
    @ApiOperation("订单退款")
    @PostMapping("/order/refund")
    @ResponseBody
    public AjaxResult orderRefund(@RequestBody OrderPayResultBO orderPayResultBO)
    {
        getRequest().setAttribute("api", "/order/refund");
        getRequest().setAttribute("dataParam", JSON.toJSONString(orderPayResultBO));
        HisBusinessTypeEnum hisBusinessTypeEnum = HisBusinessTypeEnum.getTypeByKey(orderPayResultBO.getOrderType());
        BaseResponse baseResponse = AbstractHisServiceHandler
                .servicesInstance(hisBusinessTypeEnum)
                .callRefund(orderPayResultBO.getOutTradeNo());
        return baseResponse.isOk()?AjaxResult.success():AjaxResult.error();
    }

    /**
     * 退款成功或失败回调
     */
    @Log(title = "本地调用", businessType = BusinessType.HIS_LOCALHOST)
    @ApiOperation("退款成功或失败")
    @PostMapping("/order/refundCallBack")
    @ResponseBody
    public AjaxResult orderRefundCallBack(@RequestBody OrderPayResultBO orderPayResultBO)
    {
        getRequest().setAttribute("api", "/order/refundCallBack");
        getRequest().setAttribute("dataParam", JSON.toJSONString(orderPayResultBO));
        HisBusinessTypeEnum hisBusinessTypeEnum = HisBusinessTypeEnum.getTypeByKey(orderPayResultBO.getOrderType());
        BaseResponse baseResponse = AbstractHisServiceHandler
                .servicesInstance(hisBusinessTypeEnum)
                .refundNotify(orderPayResultBO.isPaymentResults(),orderPayResultBO.getOutTradeNo(),orderPayResultBO.getTransactionId());
        return baseResponse.isOk()?AjaxResult.success():AjaxResult.error();
    }

    /**
     * 退款成功或失败回调
     */
    @Log(title = "本地调用", businessType = BusinessType.HIS_LOCALHOST)
    @ApiOperation("获取门诊缴费结果")
    @PostMapping("/order/dopayInfoResult")
    @ResponseBody
    public AjaxResult dopayInfoResult(@RequestBody OrderPayResultBO orderPayResultBO)
    {
        getRequest().setAttribute("api", "/order/dopayInfoResult");
        getRequest().setAttribute("dataParam", JSON.toJSONString(orderPayResultBO));
        DopayInfo dopayInfo = dopayInfoService.getDetailByOutTradeNo(orderPayResultBO.getOutTradeNo());
        if(null == dopayInfo){
            return AjaxResult.error("无取药信息。");
        }
        PayStatusEnum payStatusEnum = PayStatusEnum.getPayStatusEnumByCode(dopayInfo.getSuccessfulPayment());
        switch (payStatusEnum){
            case INIT:
                return AjaxResult.warn("正在处理中，请稍后。");
            case PAY_SUCCESS:
                return AjaxResult.warn("正在处理中，请稍后。");
            case PAY_FAIL:
                return AjaxResult.error("支付失败，缴费不成功。");
            case ORDER_SUCCESS:
                return AjaxResult.success( "缴费成功",dopayInfo.getResultMessage());
            case ORDER_FAIL:
                return AjaxResult.error("缴费失败,费用稍后原路返回。");
            case REFUND_SUCCESS:
                return AjaxResult.error("缴费失败,费用已原路返回。");
            default:
                return AjaxResult.error("无取药信息。");
        }
    }

}
