package com.ruoyi.his.service.impl;

import com.ruoyi.common.core.domain.AjaxResult;
import com.ruoyi.common.utils.RandomUtil;
import com.ruoyi.common.utils.RedisUtil;
import com.ruoyi.his.constant.Constants;
import com.ruoyi.his.service.ISmsService;
import com.ruoyi.his.utils.SmsSendUtil;
import org.apache.commons.lang3.ObjectUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

@Service
public class SmsServicelmpl implements ISmsService {

    @Autowired
    private RedisUtil redisUtil;

    /*
    * 验证码短信
    * */
    @Override
    public AjaxResult sendVerificationCode(String phone) {
        Object obj= redisUtil.get(Constants.MOBILE_VERIFICATION_CODE+phone);
        if(ObjectUtils.allNotNull(obj)){
            return AjaxResult.error("操作太频繁，请稍后再重新获取");
        }
        //生成验证码
        String  random = RandomUtil.createRandom(true,6);
        //短信内容(【签名】+短信内容)，系统提供的测试签名和内容，如需要发送自己的短信内容请在启瑞云平台申请签名和模板
        String message   = "【广东省农垦中心医院】您的验证码是:"+ random ;
        String info = SmsSendUtil.sendMsg(phone,message);
        //把验证码存入redis
        redisUtil.set(Constants.MOBILE_VERIFICATION_CODE+phone,random,60l);
        return AjaxResult.success(info);
    }

    @Override
    public AjaxResult checkVerificationCode(String phone,String verificationCode) {
        Map<String,String> result=new HashMap<String, String>();
        Object obj= redisUtil.get(Constants.MOBILE_VERIFICATION_CODE+phone);
        if(ObjectUtils.allNotNull(obj) && verificationCode.equals(obj.toString())){
            return AjaxResult.error("验证通过");
        }
        return AjaxResult.error("验证不通过");
    }



    @Override
    public AjaxResult sendSmsMessage(String phone,String message){
        String info = SmsSendUtil.sendMsg(phone,message);
        return AjaxResult.success(info);
    }
}
