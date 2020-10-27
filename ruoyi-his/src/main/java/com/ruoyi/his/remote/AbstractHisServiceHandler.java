package com.ruoyi.his.remote;

import com.alibaba.fastjson.JSON;
import com.ruoyi.common.exception.HisException;
import com.ruoyi.common.json.JSONObject;
import com.ruoyi.common.model.HisPayOrder;
import com.ruoyi.common.utils.DateUtils;
import com.ruoyi.common.utils.StringUtils;
import com.ruoyi.common.utils.spring.SpringUtils;
import com.ruoyi.his.callservice.HisBaseServices;
import com.ruoyi.his.constant.HisBusinessTypeEnum;
import com.ruoyi.his.constant.PayStatusEnum;
import com.ruoyi.his.domain.HisBaseEntity;
import com.ruoyi.his.remote.request.BaseRequest;
import com.ruoyi.his.remote.response.BaseResponse;
import com.ruoyi.pay.constant.HisPayType;
import com.ruoyi.pay.service.AbstractPayService;
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
        if(StringUtils.isEmpty(d.getTransactionId())){
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
     * 下单失败后调用退款接口
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
            //更新支付流水与支付状态
            int iResult = updateOrder(d);
            if(iResult > 0){
                baseResponse = invokeCallSubmit(outTradeNo);
                //下单失败，发起退款
                if(!baseResponse.isOk()){
                    refudnAndUpdateOrder(d);
                }
            }
        }catch (Exception ex){
            d.setSuccessfulPayment(PayStatusEnum.ORDER_FAIL.getCode());
            refudnAndUpdateOrder(d);
            baseResponse.error("缴费支付时发生错误,支付的金额会自动原路返还，请注意查收");
        }
        return baseResponse;
    }


    /***
     * 下单失败后发起退款
     * @param d
     * @return
     */
    private boolean refudnAndUpdateOrder(D d){
        d.setSuccessfulPayment(PayStatusEnum.REFUND_TODO.getCode());
        BaseResponse baseResponse = callRefund(d.getOutTradeNo());
        d.setSuccessfulPayment(baseResponse.isOk()?PayStatusEnum.REFUND_SUCCESS.getCode():PayStatusEnum.REFUND_FAIL.getCode());
        d.setResultMsg((baseResponse.isOk()?PayStatusEnum.REFUND_SUCCESS.getDesc():PayStatusEnum.REFUND_FAIL.getDesc()));
        updateOrder(d);
        return true;
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
     * @return
     */
    protected BaseResponse refundSuccessful(String outTradeNo){
        D d = getOrderDetail(outTradeNo);
        d.setUpdateTime(DateUtils.getNowDate());
        d.setSuccessfulPayment(PayStatusEnum.REFUND_SUCCESS.getCode());
        d.setResultMsg(PayStatusEnum.REFUND_SUCCESS.getDesc());
        updateOrder(d);
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
        d.setResultMsg(PayStatusEnum.REFUND_FAIL.getDesc());
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
        //访并发处理
        logger.info("AbstractHisServiceHandler.{}.payedNotify.outTradeNo={},transactionId={},isSucceed={}",
                getBusinessType().getDesc(),outTradeNo,transactionId,isSucceed);
        BaseResponse baseResponse = isSucceed?paySuccessful(outTradeNo,transactionId):payFailed(outTradeNo);
        return baseResponse;
    }

    @Override
    public BaseResponse refundNotify(boolean isSucceed, String outTradeNo, String transactionId) {
        logger.info("AbstractHisServiceHandler.{}.refundNotify.outTradeNo={},transactionId={},isSucceed={}",
                getBusinessType().getDesc(),outTradeNo,transactionId,isSucceed);
        return isSucceed?refundSuccessful(outTradeNo):refundFailed(outTradeNo);
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
        //付款方式转换
        payTypeTransform(t);
        //his接口请求下单
        String response = calltHisService(JSON.toJSONString(t));
        //返回结果数据转换本地对象
        R r = transResult(response);
        //更新本地对象,即使本地更新错误了，也不会抛出异常终止运行，而是以his接口返回的结果为准
        try {
            this.afterInvokeCallSumbit(outTradeNo,r);
        }catch (Exception ex){
            logger.info("AbstractHisServiceHandler.invokeCallSubmit.outTradeNo={},更新本地发生异常",
                    outTradeNo);
            ex.printStackTrace();
        }
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
            JSON.toJSONString(BaseResponse.fail("无法调用His接口"));
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
            BaseResponse baseResponse = null;
            try {
                //执行退款操作
                baseResponse = callRefund(item.getOutTradeNo());
                refundNotify(baseResponse.isOk(),item.getOutTradeNo(),item.getTransactionId());
            }catch (Exception ex){
                logger.error("autoScanningRefund.callRefund.order={}.OutTradeNo={}，error={}",
                        getBusinessType().getDesc(),item.getOutTradeNo(),ex.getMessage());
            }

        });
        return true;
    }

    /***
     * 获取下单失败的订单退款
     * @return
     */
    abstract protected List<D> getRefundOrderList();


    /***
     * 调用支付接口
     * @param outTradeNo
     * @return
     */
    @Override
    public BaseResponse callPay(String outTradeNo) {
        D d = getOrderDetail(outTradeNo);
        HisPayOrder hisPayOrder = buildHisPayOrder(d);
        boolean isOK = AbstractPayService.servicesInstance(hisPayOrder.getPayType()).pay(hisPayOrder);
        return isOK?BaseResponse.success():BaseResponse.fail("支付失败"+getBusinessType().getDesc()+"失败");
    }

    /***
     * 调用退款接口
     * @param outTradeNo
     * @return
     */
    @Override
    public BaseResponse callRefund(String outTradeNo) {
        D d = getOrderDetail(outTradeNo);
        HisPayOrder hisPayOrder = buildHisPayOrder(d);
        logger.info("调用退款接口.callRefund.outTradeNo={},hisPayOrder={}",outTradeNo, JSONObject.valueAsStr(hisPayOrder));
        boolean isOK = AbstractPayService.servicesInstance(hisPayOrder.getPayType()).refund(hisPayOrder);
        logger.info("调用退款接口.callRefund.outTradeNo={},isOK={}",outTradeNo, isOK);
        return isOK?BaseResponse.success():BaseResponse.fail(getBusinessType().getDesc()+"退款失败");
    }

    /***
     * 构建支付对象
     * @param d
     * @return
     */
    private HisPayOrder buildHisPayOrder(D d){
        HisPayOrder hisPayOrder = new HisPayOrder();
        hisPayOrder.setOrderType(getBusinessType().getKey());
        hisPayOrder.setPayType(d.getPayType());
        hisPayOrder.setAmount(d.getAmount());
        hisPayOrder.setOutTradeNo(d.getOutTradeNo());
        hisPayOrder.setTransactionId(d.getTransactionId());
        return hisPayOrder;
    }

    /***
     * 因为徐闻只有微信公众号的支付方式
     * @param t
     */
    private void payTypeTransform(T t){
//        if(StringUtils.equalsAny(t.getPayType(),HisPayType.WECHAT.getKey(),
//                HisPayType.WXAPP.getKey(),HisPayType.WXH5.getKey())){
//            t.setPayType(HisPayType.WECHAT_GZH.getKey());
//        }
        t.setPayType(HisPayType.WECHAT_GZH.getKey());
    }

    @Override
    public boolean regCancelByDocStopForRefund() {
        return false;
    }
}
