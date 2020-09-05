package com.ruoyi.his.remote;

import com.alibaba.fastjson.JSON;
import com.ruoyi.common.exception.HisException;
import com.ruoyi.common.utils.DateUtils;
import com.ruoyi.common.utils.StringUtils;
import com.ruoyi.his.constant.HisBusinessTypeEnum;
import com.ruoyi.his.constant.PayStatusEnum;
import com.ruoyi.his.domain.LeaveHosPay;
import com.ruoyi.his.remote.request.LeaveHosPayIn;
import com.ruoyi.his.remote.response.BaseResponse;
import com.ruoyi.his.remote.response.LeaveHosPayOut;
import com.ruoyi.his.service.ILeaveHosPayService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * 离院结算Service业务层处理
 * 
 * @author whl
 * @date 2020-08-08
 */
@Service
public class LeaveHosPayHisServiceHander extends AbstractHisServiceHandler<LeaveHosPayIn,LeaveHosPay, LeaveHosPayOut> {
    @Autowired
    private ILeaveHosPayService leaveHosPayService;

    @Override
    public HisBusinessTypeEnum getBusinessType() {
        return HisBusinessTypeEnum.LEAVEHOSPAY;
    }


    @Override
    LeaveHosPay getOrderDetail(String outTradeNo) {
        return leaveHosPayService.getDetailByOutTradeNo(outTradeNo);
    }

    @Override
    int updateOrder(LeaveHosPay leaveHosPay) {
        return leaveHosPayService.updateLeaveHosPay(leaveHosPay);
    }

    @Override
    public LeaveHosPayIn buildRequestData(String outTradeNo) {
        LeaveHosPay leaveHosPay = getOrderDetail(outTradeNo);
        LeaveHosPayIn leaveHosPayIn = new LeaveHosPayIn();
        leaveHosPayIn.setInHosNo(leaveHosPay.getInHosNo());
        leaveHosPayIn.setPayType(Integer.parseInt(leaveHosPay.getPayType()));
        leaveHosPayIn.setPayRecord(leaveHosPay.getTransactionId());
        leaveHosPayIn.setPayMoney(leaveHosPay.getPayMoney().doubleValue());
        if(null != leaveHosPay.getOverMoney()){
            leaveHosPayIn.setOverMoney(leaveHosPay.getOverMoney().doubleValue());
        }
        if(null != leaveHosPay.getMedicareType()){
            leaveHosPayIn.setMedicareType(String.valueOf(leaveHosPay.getMedicareType()));
        }
        if(StringUtils.isNotEmpty(leaveHosPay.getMedicareReturn())){
            leaveHosPayIn.setMedicareType(leaveHosPay.getMedicareReturn());
        }
        return leaveHosPayIn;
    }

    @Override
    protected LeaveHosPayOut transResult(String result) {
        return JSON.toJavaObject(JSON.parseObject(result), LeaveHosPayOut.class);
    }


    @Override
    public BaseResponse afterInvokeCallSumbit(String outTradeNo, LeaveHosPayOut leaveHosPayOut) {
        LeaveHosPay leaveHosPayTemp = getOrderDetail(outTradeNo);
        LeaveHosPay leaveHosPay = new LeaveHosPay();
        leaveHosPay.setId(leaveHosPayTemp.getId());
        leaveHosPay.setResultMsg(leaveHosPayOut.getResultMsg());
        leaveHosPay.setUpdateTime(DateUtils.getNowDate());
        leaveHosPay.setSuccessfulPayment(leaveHosPayOut.isOk()?PayStatusEnum.ORDER_SUCCESS.getCode():PayStatusEnum.ORDER_FAIL.getCode());
        if(leaveHosPayOut.isOk()){
            leaveHosPay.setClearingNo(leaveHosPayOut.getClearingNO());
            leaveHosPay.setPatientName(leaveHosPayOut.getPatientName());
            leaveHosPay.setPatientSex(leaveHosPayOut.getPatientSex());
            leaveHosPay.setInHosState(new Long(leaveHosPayOut.getInHosState()));
            leaveHosPay.setInHosDate(DateUtils.dateTime(leaveHosPayOut.getInHosDate(),DateUtils.YYYY_MM_DD));
            leaveHosPay.setBedNo(leaveHosPayOut.getBedNo());
            leaveHosPay.setDepartmentName(leaveHosPayOut.getDepartmentName());
            leaveHosPay.setDepartmentOrganId(leaveHosPayOut.getDepartmentorganId());
            leaveHosPay.setReminder(leaveHosPayOut.getReminder());
        }
        leaveHosPayService.updateLeaveHosPay(leaveHosPay);
        return leaveHosPayOut.isOk()?BaseResponse.success():BaseResponse.fail("操作失败，支付金额稍后将会原路返回");
    }

}
