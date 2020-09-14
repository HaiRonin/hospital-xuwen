package com.ruoyi.common.enums;

import com.ruoyi.common.model.HisPayOrder;

/**
 * His业务操作类型
 *
 * @author ruoyi
 */
public enum HisPayType {
    ALI("2", "支付宝"),
    WECHAT("5", "微信"),
    WXAPP("6", "微信APP");
    private String key;
    private String desc;

    public String getKey() {
        return key;
    }

    HisPayType(String key, String desc) {
        this.key = key;
        this.desc = desc;
    }

    public static HisPayType getByKey(String key) {
        for (HisPayType type : HisPayType.values()) {
            if (type.key.equals(key)) {
                return type;
            }
        }
        return null;
    }

    public static String getDescByKey(String key) {
        HisPayType type = getByKey(key);
        if (null != type) {
            return type.desc;
        }
        return "";
    }
}
