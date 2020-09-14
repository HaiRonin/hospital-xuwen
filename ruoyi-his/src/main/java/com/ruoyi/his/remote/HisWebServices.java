package com.ruoyi.his.remote;

import com.ruoyi.his.remote.response.BaseResponse;
import org.springframework.scheduling.annotation.Async;

public interface HisWebServices {

    /***
     * 调用his接口下单
     * @param outTradeNo 订单号
     * @return
     */
    BaseResponse invokeCallSubmit(String outTradeNo);

    /***
     * 调用支付接口
     * @param outTradeNo
     * @return
     */
    BaseResponse callPay(String outTradeNo);

    /***
     * 调用退款接口
     * @param outTradeNo
     * @return
     */
    BaseResponse callRefund(String outTradeNo);

    /***
     * 退款通知
     * @param outTradeNo 订单号
     * @param transactionId 退款成功流水号
     * @return
     */
    BaseResponse refundNotify(boolean isSucceed,String outTradeNo,String transactionId);

    /***
     * 支付回调通知
     * @param isSucceed 是否支付成功
     * @param outTradeNo 订单号
     * @param transactionId 支付成功流水号
     * @return
     */
    BaseResponse payedNotify(boolean isSucceed,String outTradeNo,String transactionId);

    /***
     * 定时扫描下单失败的订单执行退款
     * @return
     */
    @Async
    boolean autoScanningRefund();

}
