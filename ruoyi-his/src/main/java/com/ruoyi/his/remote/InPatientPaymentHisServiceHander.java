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
import com.ruoyi.his.remote.response.DoRegOut;
import com.ruoyi.his.remote.response.InPatientPaymentOut;
import com.ruoyi.his.service.IDepositPaymentService;
import com.ruoyi.his.service.IDoregInfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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
    boolean checkData(Long id) {
        DepositPayment depositPayment = depositPaymentService.selectDepositPaymentById(id);
        if(null == depositPayment){
            throw new HisException(String.format("%1$s记录不存在，不能进行此操作:",id));
        }
        if(PayStatusEnum.PAY_SUCCESS.getCode().equals(depositPayment.getSuccessfulPayment())){
            throw new HisException(String.format("%1$s记录不是支付成功状态，不能进行此操作:",id));
        }
        if(StringUtils.isEmpty(depositPayment.getTransactionId())){
            throw new HisException(String.format("%1$s记录支付流水为空，不能进行此操作:",id));
        }
        return true;
    }

    @Override
    public InPatientPaymentIn buildRequestData(Long id) {
        DepositPayment depositPayment = depositPaymentService.selectDepositPaymentById(id);
        if(null == depositPayment){
            throw new HisException(String.format("%1$s记录已经不存在，不能进行此操作:",id));
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
    public boolean afterInvokeCallSumbit(Long id, InPatientPaymentOut inPatientPaymentOut) {
        DepositPayment depositPayment = new DepositPayment();
        depositPayment.setId(id);
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

}
