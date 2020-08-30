package com.ruoyi.his.remote;

import com.alibaba.fastjson.JSON;
import com.ruoyi.common.exception.HisException;
import com.ruoyi.common.utils.DateUtils;
import com.ruoyi.common.utils.StringUtils;
import com.ruoyi.his.constant.HisBusinessTypeEnum;
import com.ruoyi.his.constant.PayStatusEnum;
import com.ruoyi.his.domain.DepositPayment;
import com.ruoyi.his.domain.DoregInfo;
import com.ruoyi.his.remote.request.DoRegIn;
import com.ruoyi.his.remote.request.DoRequestInfo;
import com.ruoyi.his.remote.request.InPatientPaymentIn;
import com.ruoyi.his.remote.response.BaseResponse;
import com.ruoyi.his.remote.response.DoRegOut;
import com.ruoyi.his.remote.response.InPatientPaymentOut;
import com.ruoyi.his.service.IDepositPaymentService;
import com.ruoyi.his.service.IDoregInfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;

/**
 * 预约挂号Service业务层处理
 * 
 * @author whl
 * @date 2020-08-08
 */
@Service
public class InPatientPaymentHisServiceHander extends AbstractHisServiceHandler<InPatientPaymentIn, InPatientPaymentOut> {
    @Autowired
    private IDepositPaymentService depositPaymentService;

    @Override
    public HisBusinessTypeEnum getBusinessType() {
        return HisBusinessTypeEnum.INPATIENTPAYMENT;
    }

    @Override
    boolean checkData(String outTradeNo) {
        DepositPayment depositPayment = depositPaymentService.getDetailByOutTradeNo(outTradeNo);
        if(null == depositPayment){
            throw new HisException(String.format("%1$s记录不存在，不能进行此操作:",outTradeNo));
        }
        if(PayStatusEnum.PAY_SUCCESS.getCode().equals(depositPayment.getSuccessfulPayment())){
            throw new HisException(String.format("%1$s记录不是支付成功状态，不能进行此操作:",outTradeNo));
        }
        if(StringUtils.isEmpty(depositPayment.getTransactionId())){
            throw new HisException(String.format("%1$s记录支付流水为空，不能进行此操作:",outTradeNo));
        }
        return true;
    }

    @Override
    public InPatientPaymentIn buildRequestData(String outTradeNo) {
        DepositPayment depositPayment = depositPaymentService.getDetailByOutTradeNo(outTradeNo);
        if(null == depositPayment){
            throw new HisException(String.format("%1$s记录已经不存在，不能进行此操作:",outTradeNo));
        }
        InPatientPaymentIn inPatientPaymentIn = new InPatientPaymentIn();
        inPatientPaymentIn.setInHosNo(depositPayment.getInHosNo());
        inPatientPaymentIn.setPayType(Integer.parseInt(depositPayment.getPayType()));
        inPatientPaymentIn.setPayRecord(depositPayment.getTransactionId());
        inPatientPaymentIn.setPayMoney(depositPayment.getPayMoney().doubleValue());
        inPatientPaymentIn.setBedNo(depositPayment.getBedNo());

        return inPatientPaymentIn;
    }

    @Override
    protected InPatientPaymentOut transResult(String result) {
        return JSON.toJavaObject(JSON.parseObject(result), InPatientPaymentOut.class);
    }


    @Override
    public boolean afterInvokeCallSumbit(String outTradeNo, InPatientPaymentOut inPatientPaymentOut) {
        DepositPayment depositPaymentTemp = depositPaymentService.getDetailByOutTradeNo(outTradeNo);
        if(null == depositPaymentTemp){
            throw new HisException(String.format("%1$s记录已经不存在，不能进行此操作:",outTradeNo));
        }
        DepositPayment depositPayment = new DepositPayment();
        depositPayment.setId(depositPaymentTemp.getId());
        depositPayment.setResultMsg(inPatientPaymentOut.getResultMsg());
        depositPayment.setUpdateTime(DateUtils.getNowDate());
        depositPayment.setSuccessfulPayment(inPatientPaymentOut.isOk()?PayStatusEnum.ORDER_SUCCESS.getCode():PayStatusEnum.ORDER_FAIL.getCode());
        if(inPatientPaymentOut.isOk()){
            depositPayment.setPatientName(inPatientPaymentOut.getPatientName());
            depositPayment.setDepartmentName(inPatientPaymentOut.getDepartmentName());
            depositPayment.setBedNo(inPatientPaymentOut.getBedNo());
            depositPayment.setDepositMoney(BigDecimal.valueOf(inPatientPaymentOut.getDepositMoney()));
            depositPayment.setMedicalTypeName(inPatientPaymentOut.getMedicalTypeNmae());
        }
        depositPaymentService.updateDepositPayment(depositPayment);
        return true;
    }

    @Override
    public BaseResponse paySuccessful(String outTradeNo, String transactionId) {
        BaseResponse baseResponse = new BaseResponse("00","操作成功");
        DepositPayment depositPaymentTemp = depositPaymentService.getDetailByOutTradeNo(outTradeNo);
        if(null == depositPaymentTemp){
            throw new HisException(String.format("%1$s记录已经不存在，不能进行此操作:",outTradeNo));
        }
        DepositPayment depositPayment = new DepositPayment();
        depositPayment.setId(depositPaymentTemp.getId());
        depositPayment.setUpdateTime(DateUtils.getNowDate());
        depositPayment.setTransactionId(transactionId);
        depositPayment.setSuccessfulPayment(PayStatusEnum.PAY_SUCCESS.getCode());
        depositPaymentService.updateDepositPayment(depositPayment);
        try {
            baseResponse = invokeCallSubmit(outTradeNo);
        }catch (HisException ex){
            throw ex;
        }catch (Exception ex){
            new BaseResponse("00","缴费押金时发生错误,支付的金额稍后会自动原路返回，请注意查收");
        }
        return baseResponse;
    }

    @Override
    public BaseResponse payFailed(String outTradeNo) {
        DepositPayment depositPaymentTemp = depositPaymentService.getDetailByOutTradeNo(outTradeNo);
        if(null == depositPaymentTemp){
            throw new HisException(String.format("%1$s记录已经不存在，不能进行此操作:",outTradeNo));
        }
        DepositPayment depositPayment = new DepositPayment();
        depositPayment.setId(depositPaymentTemp.getId());
        depositPayment.setUpdateTime(DateUtils.getNowDate());
        depositPayment.setSuccessfulPayment(PayStatusEnum.PAY_FAIL.getCode());
        depositPaymentService.updateDepositPayment(depositPayment);
        return new BaseResponse("00","操作成功");
    }
}
