package com.ruoyi.his.service;

import com.ruoyi.common.core.domain.AjaxResult;

import java.util.Map;

public interface ISmsService {

    //验证码短信
    public AjaxResult sendVerificationCode(String phone)throws Exception;

    //验证短信是否正确
    public AjaxResult checkVerificationCode(String phone, String verificationCode);

    //短信发送
    public AjaxResult  sendSmsMessage(String phone, String message)throws Exception;

}
