package com.ruoyi.web.controller.his.controller;

import com.ruoyi.common.annotation.Log;
import com.ruoyi.common.core.controller.BaseController;
import com.ruoyi.common.enums.BusinessType;
import com.ruoyi.his.remote.HisBaseServices;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * 预约挂号Controller
 * 
 * @author whl
 * @date 2020-08-08
 */
@Controller
@Api("his接口")
@RequestMapping("/his")
public class HisServiceCallController extends BaseController
{

    @Autowired
    private HisBaseServices hisBaseServices;

    /**
     * his接口调用
     */
    @Log(title = "his接口调用", businessType = BusinessType.HIS)
    @ApiOperation("his接口调用")
    @RequestMapping("/request")
    @ResponseBody
    public String invokeCall(@RequestParam("api") String api,@RequestParam("dataParam") String dataParam)
    {
        return hisBaseServices.requestHisService("/"+api,dataParam);
    }
}
