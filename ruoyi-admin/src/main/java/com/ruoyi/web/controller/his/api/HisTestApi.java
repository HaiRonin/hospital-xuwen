package com.ruoyi.web.controller.his.api;

import com.ruoyi.common.annotation.Log;
import com.ruoyi.common.core.controller.BaseController;
import com.ruoyi.common.core.domain.AjaxResult;
import com.ruoyi.common.enums.BusinessType;
import com.ruoyi.his.constant.HisBusinessTypeEnum;
import com.ruoyi.his.domain.DopayInfo;
import com.ruoyi.his.domain.DoregInfo;
import com.ruoyi.his.remote.AbstractHisServiceHandler;
import com.ruoyi.his.remote.response.DoPayOut;
import com.ruoyi.his.remote.response.DoRegOut;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.*;

/**
 * 预约挂号Controller
 * 
 * @author whl
 * @date 2020-08-08
 */
@RestController
@Api("his测试接口")
@RequestMapping("/his/test")
public class HisTestApi extends BaseController
{
    /**
     * 2020.8.26
     * 测试预约挂号推his
     *
     * @return
     */
    @Log(title = "his接口调用", businessType = BusinessType.HIS)
    @ApiOperation("测试预约挂号推his")
    @ResponseBody
    @GetMapping(value = "/doregInfo")
    public AjaxResult doregInfo(@RequestParam("id") Long id){
        DoRegOut doRegOut = (DoRegOut)AbstractHisServiceHandler.servicesInstance(HisBusinessTypeEnum.DOREG).invokeCallSubmit(id);
        return AjaxResult.success(doRegOut);
    }

    /**
     * 2020.8.26
     * 测试预约挂号推his
     *
     * @return
     */
    @Log(title = "his接口调用", businessType = BusinessType.HIS)
    @ApiOperation("测试缴费支付推his")
    @ResponseBody
    @GetMapping(value = "/doPay")
    public AjaxResult doPay(@RequestParam("id") Long id){
        DoPayOut doPayOut = (DoPayOut)AbstractHisServiceHandler.servicesInstance(HisBusinessTypeEnum.DOPAY).invokeCallSubmit(id);
        return AjaxResult.success(doPayOut);
    }
}
