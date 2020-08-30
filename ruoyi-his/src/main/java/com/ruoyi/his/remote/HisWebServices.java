package com.ruoyi.his.remote;

import com.ruoyi.his.remote.response.BaseResponse;

public interface HisWebServices {

    /***
     * 调用his接口下单
     * @param outTradeNo 订单号
     * @return
     */
    public BaseResponse invokeCallSubmit(String outTradeNo);

    /***
     * 调用微信接口退款
     * @param outTradeNo
     * @return
     */
    public BaseResponse invokeCallWechatRefund(String outTradeNo);

    /***
     * 支付成功，更新本地支付状态并调用his接口下单
     * @param outTradeNo 订单号
     * @return
     */
    public BaseResponse paySuccessful(String outTradeNo,String transactionId);

    /***
     * 支付失败，更新本地支付状态并调用微信退款接口
     * @param outTradeNo 订单号
     * @return
     */
    public BaseResponse payFailed(String outTradeNo);

}
