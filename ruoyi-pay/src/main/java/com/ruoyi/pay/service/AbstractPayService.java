package com.ruoyi.pay.service;
import com.ruoyi.common.utils.spring.SpringUtils;
import com.ruoyi.pay.constant.HisPayType;

/***
 * his接口抽象类
 */
public abstract class AbstractPayService implements PayService{

    /**
     * 实例化接口
     * @param payType
     * @return
     */
    public static PayService servicesInstance(String payType){
        HisPayType hisPayType = HisPayType.getTypeByKey(payType);
        return (PayService)SpringUtils.getBean(hisPayType.getClazz());
    }
}
