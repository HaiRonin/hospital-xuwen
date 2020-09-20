package com.ruoyi.his.remote;

import com.alibaba.fastjson.JSON;
import com.ruoyi.common.exception.HisException;
import com.ruoyi.common.utils.DateUtils;
import com.ruoyi.his.constant.HisBusinessTypeEnum;
import com.ruoyi.his.constant.PayStatusEnum;
import com.ruoyi.his.domain.DepositPayment;
import com.ruoyi.his.domain.DoregInfo;
import com.ruoyi.his.remote.request.InPatientPaymentIn;
import com.ruoyi.his.remote.response.BaseResponse;
import com.ruoyi.his.remote.response.InPatientPaymentOut;
import com.ruoyi.his.service.IDepositPaymentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;

/**
 * 预约挂号Service业务层处理
 * 
 * @author whl
 * @date 2020-08-08
 */
@Service
public class InPatientPaymentHisServiceHander extends AbstractHisServiceHandler<InPatientPaymentIn,DepositPayment, InPatientPaymentOut> {
    @Autowired
    private IDepositPaymentService depositPaymentService;

    @Override
    public HisBusinessTypeEnum getBusinessType() {
        return HisBusinessTypeEnum.INPATIENTPAYMENT;
    }


    @Override
    DepositPayment getOrderDetail(String outTradeNo) {
        DepositPayment depositPayment = depositPaymentService.getDetailByOutTradeNo(outTradeNo);
        depositPayment.setAmount(depositPayment.getPayMoney());
        return depositPayment;
    }

    @Override
    int updateOrder(DepositPayment depositPayment) {
        return depositPaymentService.updateDepositPayment(depositPayment);
    }

    @Override
    public InPatientPaymentIn buildRequestData(String outTradeNo) {
        DepositPayment depositPayment = getOrderDetail(outTradeNo);
        InPatientPaymentIn inPatientPaymentIn = new InPatientPaymentIn();
        inPatientPaymentIn.setInHosNo(depositPayment.getInHosNo());
        inPatientPaymentIn.setPayType(depositPayment.getPayType());
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
    public BaseResponse afterInvokeCallSumbit(String outTradeNo, InPatientPaymentOut inPatientPaymentOut) {
        DepositPayment depositPaymentTemp = getOrderDetail(outTradeNo);
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
        return inPatientPaymentOut.isOk()?BaseResponse.success():BaseResponse.fail("操作失败，支付金额稍后将会原路返回");
    }

    @Override
    protected List<DepositPayment> getRefundOrderList() {
        DepositPayment query = new DepositPayment();
        query.setSuccessfulPayment(PayStatusEnum.ORDER_FAIL.getCode());
        List<DepositPayment> lstDopayInfo =depositPaymentService.selectDepositPaymentList(query);
        return lstDopayInfo;
    }
}
