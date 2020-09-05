package com.ruoyi.his.remote;

import com.alibaba.fastjson.JSON;
import com.ruoyi.common.exception.HisException;
import com.ruoyi.common.utils.DateUtils;
import com.ruoyi.common.utils.StringUtils;
import com.ruoyi.his.constant.HisBusinessTypeEnum;
import com.ruoyi.his.constant.PayStatusEnum;
import com.ruoyi.his.domain.DopayInfo;
import com.ruoyi.his.domain.DoregInfo;
import com.ruoyi.his.remote.request.DoPayIn;
import com.ruoyi.his.remote.request.DoRegIn;
import com.ruoyi.his.remote.request.DoRequestInfo;
import com.ruoyi.his.remote.response.BaseResponse;
import com.ruoyi.his.remote.response.DoPayOut;
import com.ruoyi.his.remote.response.DoRegOut;
import com.ruoyi.his.service.IDopayInfoService;
import com.ruoyi.his.service.IDoregInfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * 缴费Service业务层处理
 * 
 * @author whl
 * @date 2020-08-28
 */
@Service
public class DopayInfoHisServiceHander extends AbstractHisServiceHandler<DoPayIn, DoPayOut> {
    @Autowired
    private IDopayInfoService dopayInfoService;

    @Override
    public HisBusinessTypeEnum getBusinessType() {
        return HisBusinessTypeEnum.DOPAY;
    }

    @Override
    boolean checkData(String outTradeNo) {
        DopayInfo dopayInfo = dopayInfoService.getDetailByOutTradeNo(outTradeNo);
        if(null == dopayInfo){
            throw new HisException(String.format("%1$s记录不存在，不能进行此操作:",outTradeNo));
        }
        if(PayStatusEnum.ORDER_SUCCESS.getCode().equals(dopayInfo.getSuccessfulPayment()) ||
                PayStatusEnum.ORDER_FAIL.getCode().equals(dopayInfo.getSuccessfulPayment())){
            throw new HisException(String.format("%1$s记录状态为[%1$s]，不能重复此操作:",outTradeNo,PayStatusEnum.getByCode(dopayInfo.getSuccessfulPayment())));
        }
        if(PayStatusEnum.PAY_SUCCESS.getCode().equals(dopayInfo.getSuccessfulPayment())){
            throw new HisException(String.format("%1$s记录不是支付成功状态，不能进行此操作:",outTradeNo));
        }
        if(StringUtils.isEmpty(dopayInfo.getTransactionId())){
            throw new HisException(String.format("%1$s记录支付流水为空，不能进行此操作:",outTradeNo));
        }
        return true;
    }

    /***
     * {
     *     "socialsecurityNO":"",
     *     "userNo":"",
     *     "overMoney":"",
     *     "medicareReturn":"",
     *     "payCardNo":"",
     *     "synKey":"",
     *     "overRecord":"",
     *     "synUserName":"",
     *     "payNo":"4200000596202008020458500188",
     *     "payType":"5",
     *     "payAmount":211.8,
     *     "bankReturn":"",
     *     "hiFeeNos":"2020073111931",
     *     "medicareType":1,
     *     "terminalCode":""
     * }
     * @param outTradeNo
     * @return
     */
    @Override
    public DoPayIn buildRequestData(String outTradeNo) {
        DopayInfo dopayInfo = dopayInfoService.getDetailByOutTradeNo(outTradeNo);
        if(null == dopayInfo){
            throw new HisException(String.format("%1$s记录已经不存在，不能进行此操作:",outTradeNo));
        }
        DoPayIn doPayIn = new DoPayIn();
        doPayIn.setHiFeeNos(dopayInfo.getHiFeeNos());
        doPayIn.setPayType(dopayInfo.getPayType());
        //支付流水号
        doPayIn.setPayNo(dopayInfo.getTransactionId());
        doPayIn.setPayAmount(dopayInfo.getPayMoney().toString());
        doPayIn.setMedicareType(String.valueOf(dopayInfo.getMedicareType()));
        return doPayIn;
    }

    @Override
    protected DoPayOut transResult(String result) {
        return JSON.toJavaObject(JSON.parseObject(result), DoPayOut.class);
    }


    @Override
    public boolean afterInvokeCallSumbit(String outTradeNo, DoPayOut doPayOut) {
        DopayInfo dopayInfoTemp = dopayInfoService.getDetailByOutTradeNo(outTradeNo);
        if(null == dopayInfoTemp){
            throw new HisException(String.format("%1$s记录已经不存在，不能进行此操作:",outTradeNo));
        }
        DopayInfo dopayInfo = new DopayInfo();
        dopayInfo.setId(dopayInfoTemp.getId());
        dopayInfo.setUpdateTime(DateUtils.getNowDate());
        dopayInfo.setResultCode(doPayOut.getResultCode());
        dopayInfo.setResultMsg(doPayOut.getResultMsg());
        dopayInfo.setSuccessfulPayment(doPayOut.isOk()?PayStatusEnum.ORDER_SUCCESS.getCode():PayStatusEnum.ORDER_FAIL.getCode());
        if(doPayOut.isOk()){
            dopayInfo.setResultMessage(JSON.toJSONString(doPayOut.getResultmessage()));
            dopayInfo.setCheckInfo(doPayOut.getCheckInfo());
        }
        dopayInfoService.updateDopayInfo(dopayInfo);
        return true;
    }




    @Override
    public BaseResponse paySuccessful(String outTradeNo, String transactionId) {
        BaseResponse baseResponse = new BaseResponse("00","操作成功");
        DopayInfo dopayInfoTemp = dopayInfoService.getDetailByOutTradeNo(outTradeNo);
        if(null == dopayInfoTemp){
            throw new HisException(String.format("%1$s记录已经不存在，不能进行此操作:",outTradeNo));
        }
        DopayInfo dopayInfo = new DopayInfo();
        dopayInfo.setId(dopayInfoTemp.getId());
        dopayInfo.setUpdateTime(DateUtils.getNowDate());
        dopayInfo.setTransactionId(transactionId);
        dopayInfo.setSuccessfulPayment(PayStatusEnum.PAY_SUCCESS.getCode());
        dopayInfoService.updateDopayInfo(dopayInfo);
        try {
            baseResponse = invokeCallSubmit(outTradeNo);
        }catch (HisException ex){
            throw ex;
        }catch (Exception ex){
            new BaseResponse("00","缴费支付时发生错误,支付的金额稍后会自动原路返回，请注意查收");
        }
        return baseResponse;
    }

    @Override
    public BaseResponse payFailed(String outTradeNo) {
        DopayInfo dopayInfoTemp = dopayInfoService.getDetailByOutTradeNo(outTradeNo);
        if(null == dopayInfoTemp){
            throw new HisException(String.format("%1$s记录已经不存在，不能进行此操作:",outTradeNo));
        }
        DopayInfo dopayInfo = new DopayInfo();
        dopayInfo.setId(dopayInfoTemp.getId());
        dopayInfo.setUpdateTime(DateUtils.getNowDate());
        dopayInfo.setSuccessfulPayment(PayStatusEnum.PAY_FAIL.getCode());
        return new BaseResponse("00","操作成功");
    }

    @Override
    protected BaseResponse refundSuccessful(String outTradeNo, String transactionId) {
        BaseResponse baseResponse = new BaseResponse("00","操作成功");
        DopayInfo dopayInfoTemp = dopayInfoService.getDetailByOutTradeNo(outTradeNo);
        if(null == dopayInfoTemp){
            throw new HisException(String.format("%1$s记录已经不存在，不能进行此操作:",outTradeNo));
        }
        DopayInfo dopayInfo = new DopayInfo();
        dopayInfo.setId(dopayInfoTemp.getId());
        dopayInfo.setUpdateTime(DateUtils.getNowDate());
        dopayInfo.setTransactionId(dopayInfoTemp.getTransactionId()+"||"+transactionId);
        dopayInfo.setSuccessfulPayment(PayStatusEnum.REFUND_SUCCESS.getCode());
        dopayInfoService.updateDopayInfo(dopayInfo);
        return baseResponse;
    }

    @Override
    protected BaseResponse refundFailed(String outTradeNo) {
        BaseResponse baseResponse = new BaseResponse("00","操作成功");
        DopayInfo dopayInfoTemp = dopayInfoService.getDetailByOutTradeNo(outTradeNo);
        if(null == dopayInfoTemp){
            throw new HisException(String.format("%1$s记录已经不存在，不能进行此操作:",outTradeNo));
        }
        DopayInfo dopayInfo = new DopayInfo();
        dopayInfo.setId(dopayInfoTemp.getId());
        dopayInfo.setUpdateTime(DateUtils.getNowDate());
        dopayInfo.setSuccessfulPayment(PayStatusEnum.REFUND_FAIL.getCode());
        dopayInfoService.updateDopayInfo(dopayInfo);
        return baseResponse;
    }
}
