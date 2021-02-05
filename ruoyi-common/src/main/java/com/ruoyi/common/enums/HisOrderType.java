package com.ruoyi.common.enums;

/**
 * His业务操作类型
 *
 * @author ruoyi
 */
public enum HisOrderType {
    DOREG("doreg", "预约挂号"),
    DOPAY("dopay", "缴费支付"),
    INPATIENTPAYMENT("inpatientpayment", "住院押金补缴"),
    LEAVEHOSPAY("leavehospay", "离院结算"),
    COV("cov", "核酸检测"),
    ;
    private String key;
    private String desc;

    HisOrderType(String key, String desc) {
        this.key = key;
        this.desc = desc;
    }

    public static HisOrderType getByKey(String key) {
        for (HisOrderType type : HisOrderType.values()) {
            if (type.key.equals(key)) {
                return type;
            }
        }
        return null;
    }

    public static String getDescByKey(String key) {
        HisOrderType type = getByKey(key);
        if (null != type) {
            return type.desc;
        }
        return "";
    }

    /**
     * 根据订单前缀转换订单类型
     *
     * @param orderId
     * @return
     */
    public static String tranferTypeByOrderPrex(String orderId) {
        String orderType = "";
        if (orderId.startsWith("LH")) {
            orderType = LEAVEHOSPAY.key;
        } else if (orderId.startsWith("RE")) {
            orderType = DOREG.key;
        } else if (orderId.startsWith("DO")) {
            orderType = DOPAY.key;
        } else if (orderId.startsWith("DP")) {
            orderType = INPATIENTPAYMENT.key;
        }else if (orderId.startsWith("COV")) {
            orderType = COV.key;
        }
        return orderType;
    }
}
