package com.ruoyi.his.remote;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.ruoyi.common.core.domain.BaseEntity;
import com.ruoyi.common.exception.BusinessException;
import com.ruoyi.common.exception.HisException;
import com.ruoyi.common.utils.DateUtils;
import com.ruoyi.common.utils.StringUtils;
import com.ruoyi.common.utils.spring.SpringUtils;
import com.ruoyi.his.constant.HisBusinessTypeEnum;
import com.ruoyi.his.constant.PayStatusEnum;
import com.ruoyi.his.domain.HisBaseEntity;
import com.ruoyi.his.remote.request.BaseRequest;
import com.ruoyi.his.remote.response.BaseResponse;
import org.apache.commons.collections.CollectionUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.beans.Transient;
import java.util.List;

/***
 * his接口抽象类
 */
public abstract class AbstractHisServiceHandler<T extends BaseRequest,D extends HisBaseEntity, R extends BaseResponse> extends HisBaseServices implements HisWebServices{

    protected static Logger logger = LoggerFactory.getLogger(AbstractHisServiceHandler.class);
    /**
     * 接口apiCode
     * @return
     */
    abstract public HisBusinessTypeEnum getBusinessType();

    /***
     * 获取订单详情
     * @param outTradeNo
     * @return
     */
    abstract D getOrderDetail(String outTradeNo);

    /***
     * 更新订单
     * @param d
     * @return
     */
    abstract int updateOrder(D d);

    /***
     * 检查状态
     * @param outTradeNo
     */
    protected void checkData(String outTradeNo){
        D d = getOrderDetail(outTradeNo);
        if(null == d){
            throw new HisException(String.format("%1$s记录不存在，不能进行此操作:",outTradeNo));
        }
        if(PayStatusEnum.ORDER_SUCCESS.getCode().equals(d.getSuccessfulPayment()) ||
                PayStatusEnum.ORDER_FAIL.getCode().equals(d.getSuccessfulPayment())){
            throw new HisException(String.format("%1$s记录状态为[%2$s]，不能重复此操作:",
                    outTradeNo,PayStatusEnum.getPayStatusEnumByCode(d.getSuccessfulPayment()).getDesc()));
        }
        if(!PayStatusEnum.PAY_SUCCESS.getCode().equals(d.getSuccessfulPayment())){
            throw new HisException(String.format("%1$s记录不是支付成功状态，不能进行此操作:",outTradeNo));
        }
        if(StringUtils.isEmpty(d.getSuccessfulPayment())){
            throw new HisException(String.format("%1$s记录支付流水为空，不能进行此操作:",outTradeNo));
        }
    }

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
    protected BaseResponse paySuccessful(String outTradeNo,String transactionId){
        BaseResponse baseResponse = BaseResponse.success();
        D d = getOrderDetail(outTradeNo);
        d.setUpdateTime(DateUtils.getNowDate());
        d.setTransactionId(transactionId);
        d.setSuccessfulPayment(PayStatusEnum.PAY_SUCCESS.getCode());
        try {
            int iResult = updateOrder(d);
            if(iResult > 0){
                baseResponse = invokeCallSubmit(outTradeNo);
            }
        }catch (HisException ex){
            if(ex.getCode() != -9999){
                d.setSuccessfulPayment(PayStatusEnum.ORDER_FAIL.getCode());
                updateOrder(d);
                baseResponse.error(ex.getMessage());
            }
        }catch (Exception ex){
            d.setSuccessfulPayment(PayStatusEnum.ORDER_FAIL.getCode());
            updateOrder(d);
            baseResponse.error("缴费支付时发生错误,支付的金额稍后会自动原路返回，请注意查收");
        }
        return baseResponse;
    }

    /***
     * 支付失败，更新本地支付状态并调用微信退款接口
     * @param outTradeNo 订单号
     * @return
     */
    protected BaseResponse payFailed(String outTradeNo){
        D d = getOrderDetail(outTradeNo);
        d.setUpdateTime(DateUtils.getNowDate());
        d.setSuccessfulPayment(PayStatusEnum.PAY_FAIL.getCode());
        int iResult = updateOrder(d);
        return iResult>0?BaseResponse.success():BaseResponse.fail();
    }


    /***
     * 支付成功，更新本地支付状态并调用his接口下单
     * @param outTradeNo 订单号
     * @param transactionId 交易流水id
     * @return
     */
    protected BaseResponse refundSuccessful(String outTradeNo,String transactionId){
        D d = getOrderDetail(outTradeNo);
        d.setUpdateTime(DateUtils.getNowDate());
        d.setTransactionId(d.getTransactionId()+"||"+transactionId);
        d.setSuccessfulPayment(PayStatusEnum.REFUND_SUCCESS.getCode());
        int iResult = updateOrder(d);
        return iResult>0?BaseResponse.success():BaseResponse.fail();
    }

    /***
     * 支付失败，更新本地支付状态并调用微信退款接口
     * @param outTradeNo 订单号
     * @return
     */
    protected BaseResponse refundFailed(String outTradeNo){
        D d = getOrderDetail(outTradeNo);
        d.setUpdateTime(DateUtils.getNowDate());
        d.setSuccessfulPayment(PayStatusEnum.REFUND_FAIL.getCode());
        int iResult = updateOrder(d);
        return iResult>0?BaseResponse.success():BaseResponse.fail();
    }

    /***
     * 接口调用完后处理
     * @return
     */
    abstract public BaseResponse afterInvokeCallSumbit(String outTradeNo, R r);

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
            throw new HisException(-9999,"网络出现异常，稍后请进入个人中心查看订单支付结果");
        }
        //更新本地对象
        R result = (R) this.afterInvokeCallSumbit(outTradeNo,r);
        return result;
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


    @Override
    public boolean autoScanningRefund() {
       List<D> list = getRefundOrderList();
        logger.info("autoScanningRefund.order={}查找到size={}条件记录，需要退款",getBusinessType().getDesc(),
                CollectionUtils.isEmpty(list)?0:list.size());
       if(CollectionUtils.isEmpty(list)){
           return true;
       }
        list.stream().forEach(item->{
           //执行退款操作
        });
        return true;
    }

    /***
     * 获取指定时间之前的失败订单
     * @return
     */
    abstract protected List<D> getRefundOrderList();
}
