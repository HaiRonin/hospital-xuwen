package com.ruoyi.pay.constant;

import com.ruoyi.pay.service.AliPayServiceImp;
import com.ruoyi.pay.service.WeChatPayServiceImp;
import com.ruoyi.pay.service.WxAppPayServiceImp;

/**
 * His业务操作类型
 * 
 * @author ruoyi
 */
public enum HisPayType
{
    ALI("2", "支付宝", AliPayServiceImp.class),
    WECHAT("5", "微信", WeChatPayServiceImp.class),
    WXAPP("6", "微信APP", WxAppPayServiceImp.class);
    private String key;
    private String desc;
    private Class clazz;

    public String getKey() {
        return key;
    }

    public String getDesc() {
        return desc;
    }

    public Class getClazz() {
        return clazz;
    }

    HisPayType(String key, String desc, Class clazz) {
        this.key=key;
        this.desc=desc;
        this.clazz=clazz;
    }

    public static HisPayType getTypeByKey(String key){
        HisPayType[] values = HisPayType.values();
        for(HisPayType payType : values  ){
            if(payType.key.equals(key)){
                return payType;
            }
        }
        return null;
    }
}
