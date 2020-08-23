package com.ruoyi.web.controller.his.controller;

import com.ruoyi.common.annotation.Log;
import com.ruoyi.common.core.controller.BaseController;
import com.ruoyi.common.core.domain.AjaxResult;
import com.ruoyi.common.enums.BusinessType;
import com.ruoyi.common.utils.StringUtils;
import com.ruoyi.his.remote.HisBaseServices;
import com.ruoyi.his.service.ISmsService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

/**
 * 预约挂号Controller
 * 
 * @author whl
 * @date 2020-08-08
 */
@RestController
@Api("his接口")
@RequestMapping("/his")
public class HisCommonCallController extends BaseController
{

    @Autowired
    private HisBaseServices hisBaseServices;
    @Autowired
    private ISmsService smsService;

    /**
     * his接口调用
     */
    @Log(title = "his接口调用", businessType = BusinessType.HIS)
    @ApiOperation("his接口调用")
    @RequestMapping("/request")
    @ResponseBody
    @Cacheable(value="#api", key="#dataParam")
    public String invokeCall(@RequestParam("api") String api,@RequestParam("dataParam") String dataParam)
    {
        return hisBaseServices.requestHisService("/"+api.trim(),dataParam);
    }


    /**
     * 验证码短信
     *
     * @return
     */
    @RequestMapping("/sendMsg")
    @ResponseBody
    public AjaxResult sendMsg(@RequestParam("phone") String phone)throws Exception  {
        return smsService.sendVerificationCode(phone);
    }

    /**
     * 验证码是否正确
     *
     * @return
     */
    @RequestMapping("/verification")
    @ResponseBody
    @Cacheable(value="#phone", key="#verificationCode")
    public AjaxResult verification(@RequestParam("phone") String phone, @RequestParam("verificationCode") String verificationCode)throws Exception  {
        if(StringUtils.isEmpty(verificationCode)){
            return AjaxResult.error("验证码不能为空");
        }
        return smsService.checkVerificationCode(phone,verificationCode);
    }

    /**
     * 发送短信
     *
     * @return
     */
    @RequestMapping("/shortMessage")
    @ResponseBody
    public AjaxResult shortMessage(@RequestParam("phone") String phone, @RequestParam("message") String message)throws Exception  {
        return smsService.sendSmsMessage(phone,message);
    }
}
