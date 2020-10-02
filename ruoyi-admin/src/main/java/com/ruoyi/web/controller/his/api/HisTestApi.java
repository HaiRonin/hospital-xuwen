package com.ruoyi.web.controller.his.api;

import com.alibaba.fastjson.JSON;
import com.ruoyi.common.annotation.Log;
import com.ruoyi.common.core.controller.BaseController;
import com.ruoyi.common.core.domain.AjaxResult;
import com.ruoyi.common.enums.BusinessType;
import com.ruoyi.common.utils.ServletUtils;
import com.ruoyi.his.callservice.HisBaseServices;
import com.ruoyi.his.constant.HisBusinessTypeEnum;
import com.ruoyi.his.remote.AbstractHisServiceHandler;
import com.ruoyi.his.remote.response.BaseResponse;
import com.ruoyi.vo.HisRequestBO;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 *
 * 
 * @author whl
 * @date 2020-08-08
 */
@RestController
@Api(value="模拟下单调用His接口测试",tags={"模拟下单调用His接口测试"})
@RequestMapping("/his/test")
@CrossOrigin
public class HisTestApi extends BaseController
{
    @Autowired
    private HisBaseServices hisBaseServices;
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
        BaseResponse response = AbstractHisServiceHandler.servicesInstance(HisBusinessTypeEnum.DOREG).invokeCallSubmit(outTradeNo);
        return response.isOk()?AjaxResult.success():AjaxResult.error(response.getResultMsg());
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
        BaseResponse response = AbstractHisServiceHandler.servicesInstance(HisBusinessTypeEnum.DOPAY).invokeCallSubmit(outTradeNo);
        return response.isOk()?AjaxResult.success():AjaxResult.error(response.getResultMsg());
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
        BaseResponse response = AbstractHisServiceHandler.servicesInstance(HisBusinessTypeEnum.INPATIENTPAYMENT).invokeCallSubmit(outTradeNo);
        return response.isOk()?AjaxResult.success():AjaxResult.error(response.getResultMsg());
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
        BaseResponse response = AbstractHisServiceHandler.servicesInstance(HisBusinessTypeEnum.LEAVEHOSPAY).invokeCallSubmit(outTradeNo);
        return response.isOk()?AjaxResult.success():AjaxResult.error(response.getResultMsg());
    }


    /**
     * 2020.9.05
     * 支付成功后更新本地业务单据
     *
     * @return
     */
    @Log(title = "his本地调用", businessType = BusinessType.HIS_LOCALHOST)
    @ApiOperation("支付成功后更新本地业务单据")
    @ResponseBody
    @GetMapping(value = "/payedNotify")
    public AjaxResult payedNotify(@RequestParam("orderType") String orderType,@RequestParam("isSucceed") boolean isSucceed,
                                  @RequestParam("outTradeNo") String outTradeNo,@RequestParam("transactionId") String transactionId){
        BaseResponse response = AbstractHisServiceHandler.servicesInstance(HisBusinessTypeEnum.getTypeByKey(orderType)).payedNotify(isSucceed,outTradeNo,transactionId);
        return response.isOk()?AjaxResult.success():AjaxResult.error(response.getResultMsg());
    }

    /**
     * 2020.9.05
     * 退款成功后更新本地业务单据
     *
     * @return
     */
    @Log(title = "his本地调用", businessType = BusinessType.HIS_LOCALHOST)
    @ApiOperation("退款成功后更新本地业务单据")
    @ResponseBody
    @GetMapping(value = "/refundNotify")
    public AjaxResult refundNotify(@RequestParam("orderType") String orderType,@RequestParam("isSucceed") boolean isSucceed,
                                  @RequestParam("outTradeNo") String outTradeNo,@RequestParam("outTradeNo") String transactionId){
        BaseResponse response = AbstractHisServiceHandler.servicesInstance(HisBusinessTypeEnum.getTypeByKey(orderType)).refundNotify(isSucceed,outTradeNo,transactionId);
        return response.isOk()?AjaxResult.success():AjaxResult.error(response.getResultMsg());
    }

    /**
     * his接口测试
     */
    @Log(title = "his接口测试", businessType = BusinessType.HIS)
    @ApiOperation("his接口测试")
    @PostMapping("/request/{api}")
    @ResponseBody
    public String invokeCall(@PathVariable("api") String api,@RequestBody Object json){
        ServletUtils.getRequest().setAttribute("api", api);
        ServletUtils.getRequest().setAttribute("dataParam", JSON.toJSONString(json));
        return hisBaseServices.requestHisService(api,JSON.toJSONString(json));
    }
}
