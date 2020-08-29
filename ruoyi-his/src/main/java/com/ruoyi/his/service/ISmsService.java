package com.ruoyi.his.service;

import com.ruoyi.common.core.domain.AjaxResult;

public interface ISmsService {

    //验证码短信
     AjaxResult sendVerificationCode(String phone);

    //验证短信是否正确
     AjaxResult checkVerificationCode(String phone, String verificationCode);

    //短信发送
     AjaxResult  sendSmsMessage(String phone, String message);

}
