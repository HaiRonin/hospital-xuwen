package com.ruoyi.web.controller.his.api;

import com.ruoyi.common.annotation.Log;
import com.ruoyi.common.core.controller.BaseController;
import com.ruoyi.common.core.domain.AjaxResult;
import com.ruoyi.common.enums.BusinessType;
import com.ruoyi.common.json.JSONObject;
import com.ruoyi.common.utils.ServletUtils;
import com.ruoyi.common.utils.StringUtils;
import com.ruoyi.common.utils.uuid.IdUtils;
import com.ruoyi.his.constant.HisBusinessTypeEnum;
import com.ruoyi.his.constant.PayStatusEnum;
import com.ruoyi.his.domain.DepositPayment;
import com.ruoyi.his.domain.DopayInfo;
import com.ruoyi.his.domain.DoregInfo;
import com.ruoyi.his.domain.LeaveHosPay;
import com.ruoyi.his.remote.AbstractHisServiceHandler;
import com.ruoyi.his.remote.response.BaseResponse;
import com.ruoyi.his.service.IDepositPaymentService;
import com.ruoyi.his.service.IDopayInfoService;
import com.ruoyi.his.service.IDoregInfoService;
import com.ruoyi.his.service.ILeaveHosPayService;
import com.ruoyi.vo.OrderPayResultBO;
import com.ruoyi.web.core.config.WechatConfig;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Date;

/**
 * 预约挂号Controller
 * 
 * @author whl
 * @date 2020-08-08
 */
@RestController
@Api("his接口")
@RequestMapping("/his/order")
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


    /**
     * 新增预约挂号的记录
     */
    @Log(title = "本地调用", businessType = BusinessType.HIS_LOCALHOST)
    @ApiOperation("新增预约挂号的记录")
    @PostMapping("/outpatientPayment")
    @ResponseBody
    public AjaxResult outpatientPayment(@RequestBody DoregInfo doregInfo)
    {
        ServletUtils.getRequest().setAttribute("api", "outpatientPayment");
        ServletUtils.getRequest().setAttribute("dataParam", JSONObject.valueAsStr(doregInfo));
        if(StringUtils.isEmpty(doregInfo.getPayType())){
            doregInfo.setPayType("5");
        }
        doregInfo.setAppId(WechatConfig.appId);
        doregInfo.setCreateBy(doregInfo.getSynUserName());
        doregInfo.setCreateTime(new Date());
        doregInfo.setSuccessfulPayment(PayStatusEnum.INIT.getCode());
        doregInfo.setOutTradeNo(IdUtils.getOrderNo("RE"+doregInfo.getPatientNo()+"_"));
        int iResult = doregInfoService.insertDoregInfo(doregInfo);
        return iResult>0?AjaxResult.success(doregInfo):AjaxResult.error("预约挂号失败");
    }

    /**
     * 新增缴费支付的记录
     */
    @Log(title = "本地调用", businessType = BusinessType.HIS_LOCALHOST)
    @ApiOperation("新增缴费支付的记录")
    @PostMapping("/newPayment")
    @ResponseBody
    public AjaxResult newPayment(@RequestBody DopayInfo dopayInfo)
    {
        ServletUtils.getRequest().setAttribute("api", "newPayment");
        ServletUtils.getRequest().setAttribute("dataParam", JSONObject.valueAsStr(dopayInfo));
        if(StringUtils.isEmpty(dopayInfo.getPayType())){
            dopayInfo.setPayType("5");
        }
        dopayInfo.setAppId(WechatConfig.appId);
        dopayInfo.setCreateBy(dopayInfo.getSynUserName());
        dopayInfo.setCreateTime(new Date());
        dopayInfo.setSuccessfulPayment(PayStatusEnum.INIT.getCode());
        dopayInfo.setOutTradeNo(IdUtils.getOrderNo("DO"+dopayInfo.getHiFeeNos()+"_"));
        int iResult = dopayInfoService.insertDopayInfo(dopayInfo);
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
        ServletUtils.getRequest().setAttribute("api", "payment");
        ServletUtils.getRequest().setAttribute("dataParam", JSONObject.valueAsStr(depositPayment));
        if(StringUtils.isEmpty(depositPayment.getPayType())){
            depositPayment.setPayType("5");
        }
        depositPayment.setAppId(WechatConfig.appId);
        depositPayment.setCreateBy(depositPayment.getSynUserName());
        depositPayment.setCreateTime(new Date());
        depositPayment.setSuccessfulPayment(PayStatusEnum.INIT.getCode());
        depositPayment.setOutTradeNo(IdUtils.getOrderNo("DP"+depositPayment.getBedNo()+"_"));
        int iResult = depositPaymentService.insertDepositPayment(depositPayment);
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
        ServletUtils.getRequest().setAttribute("api", "leaveHosPay");
        ServletUtils.getRequest().setAttribute("dataParam", JSONObject.valueAsStr(leaveHosPay));
        if(StringUtils.isEmpty(leaveHosPay.getPayType())){
            leaveHosPay.setPayType("5");
        }
        leaveHosPay.setAppId(WechatConfig.appId);
        leaveHosPay.setCreateBy(leaveHosPay.getSynUserName());
        leaveHosPay.setCreateTime(new Date());
        leaveHosPay.setSuccessfulPayment(PayStatusEnum.INIT.getCode());
        leaveHosPay.setOutTradeNo(IdUtils.getOrderNo("LH"+leaveHosPay.getInHosNo()+"_"));
        int iResult = leaveHosPayService.insertLeaveHosPay(leaveHosPay);
        return iResult>0?AjaxResult.success(leaveHosPay):AjaxResult.error("出院结算失败");
    }


    /**
     * 支付失败/取消支付/超时支付
     */
    @Log(title = "本地调用", businessType = BusinessType.HIS_LOCALHOST)
    @ApiOperation("支付失败/取消支付/超时支付/撤单退款")
    @PostMapping("/orderDoPayCallBack/failed")
    @ResponseBody
    public AjaxResult orderDoPayCallBackFailed(@RequestBody OrderPayResultBO orderPayResultBO)
    {
        ServletUtils.getRequest().setAttribute("api", "orderDoPayCallBack/failed");
        ServletUtils.getRequest().setAttribute("dataParam", JSONObject.valueAsStr(orderPayResultBO));
        HisBusinessTypeEnum hisBusinessTypeEnum = HisBusinessTypeEnum.getTypeByKey(orderPayResultBO.getOrderType());
        BaseResponse baseResponse = AbstractHisServiceHandler.servicesInstance(hisBusinessTypeEnum).payFailed(orderPayResultBO.getOutTradeNo());
        return baseResponse.isOk()?AjaxResult.success():AjaxResult.error();
    }

    /**
     * 支付成功并下单
     */
    @Log(title = "本地调用", businessType = BusinessType.HIS_LOCALHOST)
    @ApiOperation("支付成功")
    @PostMapping("/orderDoPayCallBack/succeed")
    @ResponseBody
    public AjaxResult orderDoPayCallBackSucceed(@RequestBody OrderPayResultBO orderPayResultBO)
    {
        ServletUtils.getRequest().setAttribute("api", "orderDoPayCallBack/succeed");
        ServletUtils.getRequest().setAttribute("dataParam", JSONObject.valueAsStr(orderPayResultBO));
        HisBusinessTypeEnum hisBusinessTypeEnum = HisBusinessTypeEnum.getTypeByKey(orderPayResultBO.getOrderType());
        BaseResponse baseResponse = AbstractHisServiceHandler.servicesInstance(hisBusinessTypeEnum).paySuccessful(orderPayResultBO.getOutTradeNo(),orderPayResultBO.getTransactionId());
        return baseResponse.isOk()?AjaxResult.success():AjaxResult.error();
    }

}
