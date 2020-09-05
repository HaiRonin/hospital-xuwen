package com.ruoyi.his.remote;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.ruoyi.common.exception.BusinessException;
import com.ruoyi.common.utils.StringUtils;
import com.ruoyi.common.utils.spring.SpringUtils;
import com.ruoyi.his.constant.HisBusinessTypeEnum;
import com.ruoyi.his.remote.request.BaseRequest;
import com.ruoyi.his.remote.response.BaseResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.beans.Transient;

/***
 * his接口抽象类
 */
public abstract class AbstractHisServiceHandler<T extends BaseRequest,R extends BaseResponse> extends HisBaseServices implements HisWebServices{

    private static Logger logger = LoggerFactory.getLogger(AbstractHisServiceHandler.class);
    /**
     * 接口apiCode
     * @return
     */
    abstract public HisBusinessTypeEnum getBusinessType();

    /***
     * 数据检查
     * @param outTradeNo
     * @return
     */
    abstract boolean checkData(String outTradeNo);

    /***
     * 构建需要请求的消息体
     * @return
     */
    public abstract T buildRequestData(String outTradeNo);

    /***
     * 转换成具体的返回对象
     * @return
     */
    abstract protected R transResult(String result);

    /***
     * 支付成功，更新本地支付状态并调用his接口下单
     * @param outTradeNo 订单号
     * @param transactionId 交易流水id
     * @return
     */
    abstract protected BaseResponse paySuccessful(String outTradeNo,String transactionId);

    /***
     * 支付失败，更新本地支付状态并调用微信退款接口
     * @param outTradeNo 订单号
     * @return
     */
    abstract protected BaseResponse payFailed(String outTradeNo);


    /***
     * 支付成功，更新本地支付状态并调用his接口下单
     * @param outTradeNo 订单号
     * @param transactionId 交易流水id
     * @return
     */
    abstract protected BaseResponse refundSuccessful(String outTradeNo,String transactionId);

    /***
     * 支付失败，更新本地支付状态并调用微信退款接口
     * @param outTradeNo 订单号
     * @return
     */
    abstract protected BaseResponse refundFailed(String outTradeNo);

    /***
     * 接口调用完后处理
     * @return
     */
    abstract public boolean afterInvokeCallSumbit(String outTradeNo, R r);

    @Override
    public BaseResponse payedNotify(boolean isSucceed, String outTradeNo, String transactionId) {
        logger.info("AbstractHisServiceHandler.{}.payedNotify.outTradeNo={},transactionId={},isSucceed={}",
                getBusinessType().getDesc(),outTradeNo,transactionId,isSucceed);
        return isSucceed?paySuccessful(outTradeNo,transactionId):payFailed(outTradeNo);
    }

    @Override
    public BaseResponse refundNotify(boolean isSucceed, String outTradeNo, String transactionId) {
        logger.info("AbstractHisServiceHandler.{}.refundNotify.outTradeNo={},transactionId={},isSucceed={}",
                getBusinessType().getDesc(),outTradeNo,transactionId,isSucceed);
        return isSucceed?refundSuccessful(outTradeNo,transactionId):refundFailed(outTradeNo);
    }

    /***
     * 发送His接口请求
     * @return
     */
    @Override
    @Transient
    public R invokeCallSubmit(String outTradeNo) {
        //数据检查
        checkData(outTradeNo);
        //his接口对象构造
        T t = buildRequestData(outTradeNo);
        //his接口请求下单
        String response = calltHisService(JSON.toJSONString(t));
        //返回结果数据转换本地对象
        R r = transResult(response);
        if(r.isReTry()){
            r.setResultMsg("网络出现异常，请稍后进入个人中心查看订单支付结果");
            return r;
        }
        //更新本地对象
        this.afterInvokeCallSumbit(outTradeNo,r);
        return r;
    }




    /***
     * 构建his请求
     * @param dataParam
     * @return
     */
    private String calltHisService(String dataParam) {
        String result = requestHisService(getBusinessType().getApiUrl(),dataParam);
        if(StringUtils.isBlank(result)){
            logger.error("AbstractHisServiceHandler.{}.calltHisService.error.nodata.return",getBusinessType().getDesc());
            JSON.toJSONString(new BaseResponse("-9999","无法调用His接口"));
        }
        return result;
    }

    /**
     * 实例化接口
     * @param businessTypeEnum
     * @return
     */
    public static HisWebServices servicesInstance(HisBusinessTypeEnum businessTypeEnum){
        HisBusinessTypeEnum hisBusinessTypeEnum = HisBusinessTypeEnum.getTypeByKey(businessTypeEnum.getKey());
        return (HisWebServices)SpringUtils.getBean(hisBusinessTypeEnum.getClazz());
    }



}
