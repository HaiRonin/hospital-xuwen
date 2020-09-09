package com.ruoyi.web.controller.his.api;

import com.alibaba.fastjson.JSON;
import com.ruoyi.common.annotation.Log;
import com.ruoyi.common.core.controller.BaseController;
import com.ruoyi.common.core.domain.AjaxResult;
import com.ruoyi.common.enums.BusinessType;
import com.ruoyi.his.constant.HisBusinessTypeEnum;
import com.ruoyi.his.remote.AbstractHisServiceHandler;
import com.ruoyi.his.remote.HealthCardService;
import com.ruoyi.his.remote.response.BaseResponse;
import com.ruoyi.his.remote.response.healthcard.HealthCardGetResponse;
import com.ruoyi.his.remote.response.healthcard.HealthCardRegisterResponse;
import com.tencent.healthcard.model.HealthCardInfo;
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
@Api("his模拟下单调用His接口测试")
@RequestMapping("/his/test")
public class HisTestApi extends BaseController
{
    @Autowired
    private HealthCardService healthCardService;


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
                                  @RequestParam("outTradeNo") String outTradeNo,@RequestParam("outTradeNo") String transactionId){
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
     * 2020.8.26
     * 测试预约挂号推his
     *{
     *   "address": "深圳龙华新区白石龙",
     *   "adminExt": "wdwlp999@163.com",
     *   "birthday": "1984-12-06",
     *   "gender": "男",
     *   "idNumber": "450922198412064615",
     *   "idType": "01",
     *   "isCheck": 0,
     *   "name": "吴海浪",
     *   "nation": "汉族",
     *   "phone1": "15919865119",
     *   "wechatCode": "071FoDkl2sDEA54N8enl2HLUYD3FoDkn"
     * }
     * @return
     */
    @Log(title = "his本地调用", businessType = BusinessType.HIS_LOCALHOST)
    @ApiOperation("获取健康码")
    @ResponseBody
    @GetMapping(value = "/getHealthCard")
    public AjaxResult getHealthCard(@RequestParam("healthCode") String healthCode){
        HealthCardGetResponse response = healthCardService.getHealthCardByHealthCode(healthCode);
        return AjaxResult.success(JSON.toJSONString(response));
    }

    /**
     * 2020.8.26
     * 获取健康码
     *
     * @return
     */
    @Log(title = "his本地调用", businessType = BusinessType.HIS_LOCALHOST)
    @ApiOperation("获取健康码")
    @ResponseBody
    @PostMapping(value = "/registerHealthCard")
    public AjaxResult registerHealthCard(@RequestBody HealthCardInfo healthCardInfo){
        HealthCardRegisterResponse response = healthCardService.registerHealthCard(healthCardInfo);
        return AjaxResult.success(JSON.toJSONString(response));
    }
}
