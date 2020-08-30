package com.ruoyi.web.controller.his.api;

import com.ruoyi.common.annotation.Log;
import com.ruoyi.common.core.controller.BaseController;
import com.ruoyi.common.core.domain.AjaxResult;
import com.ruoyi.common.enums.BusinessType;
import com.ruoyi.his.constant.HisBusinessTypeEnum;
import com.ruoyi.his.remote.AbstractHisServiceHandler;
import com.ruoyi.his.remote.response.DoPayOut;
import com.ruoyi.his.remote.response.DoRegOut;
import com.ruoyi.his.remote.response.InPatientPaymentOut;
import com.ruoyi.his.remote.response.LeaveHosPayOut;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.*;

/**
 *
 * 
 * @author whl
 * @date 2020-08-08
 */
@RestController
@Api("his模拟下单调用His接口测试")
@RequestMapping("/his/test")
public class HisTestApi extends BaseController
{
    /**
     * 2020.8.26
     * 测试预约挂号推his
     *
     * @return
     */
    @Log(title = "his本地调用", businessType = BusinessType.HIS_LOCALHOST)
    @ApiOperation("预约挂号推his")
    @ResponseBody
    @GetMapping(value = "/doregInfo")
    public AjaxResult doregInfo(@RequestParam("outTradeNo") String outTradeNo){
        DoRegOut doRegOut = (DoRegOut)AbstractHisServiceHandler.servicesInstance(HisBusinessTypeEnum.DOREG).invokeCallSubmit(outTradeNo);
        return AjaxResult.success(doRegOut);
    }

    /**
     * 2020.8.26
     * 测试预约挂号推his
     *
     * @return
     */
    @Log(title = "his本地调用", businessType = BusinessType.HIS_LOCALHOST)
    @ApiOperation("缴费支付推his")
    @ResponseBody
    @GetMapping(value = "/doPay")
    public AjaxResult doPay(@RequestParam("outTradeNo") String outTradeNo){
        DoPayOut doPayOut = (DoPayOut)AbstractHisServiceHandler.servicesInstance(HisBusinessTypeEnum.DOPAY).invokeCallSubmit(outTradeNo);
        return AjaxResult.success(doPayOut);
    }

    /**
     * 2020.8.26
     * 测试预约挂号推his
     *
     * @return
     */
    @Log(title = "his本地调用", businessType = BusinessType.HIS_LOCALHOST)
    @ApiOperation("住院押金补缴推his")
    @ResponseBody
    @GetMapping(value = "/inpatientpayment")
    public AjaxResult inpatientpayment(@RequestParam("outTradeNo") String outTradeNo){
        InPatientPaymentOut inPatientPaymentOut = (InPatientPaymentOut)AbstractHisServiceHandler.servicesInstance(HisBusinessTypeEnum.INPATIENTPAYMENT).invokeCallSubmit(outTradeNo);
        return AjaxResult.success(inPatientPaymentOut);
    }

    /**
     * 2020.8.26
     * 测试预约挂号推his
     *
     * @return
     */
    @Log(title = "his本地调用", businessType = BusinessType.HIS_LOCALHOST)
    @ApiOperation("离院结算推his")
    @ResponseBody
    @GetMapping(value = "/leavehospay")
    public AjaxResult leavehospay(@RequestParam("outTradeNo") String outTradeNo){
        LeaveHosPayOut leaveHosPayOut = (LeaveHosPayOut)AbstractHisServiceHandler.servicesInstance(HisBusinessTypeEnum.LEAVEHOSPAY).invokeCallSubmit(outTradeNo);
        return AjaxResult.success(leaveHosPayOut);
    }
}
