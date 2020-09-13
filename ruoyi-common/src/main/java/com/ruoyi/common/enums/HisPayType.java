package com.ruoyi.common.enums;

/**
 * His业务操作类型
 * 
 * @author ruoyi
 */
public enum HisPayType
{
    Ali("2", "支付宝" ),
    WeChat("5", "微信"),;
    private String key;
    private String desc;

    HisPayType(String key, String desc) {
        this.key=key;
        this.desc=desc;
    }
}
