package com.ruoyi.his.remote;

import com.alibaba.fastjson.JSON;
import com.ruoyi.common.exception.HisException;
import com.ruoyi.common.utils.DateUtils;
import com.ruoyi.common.utils.StringUtils;
import com.ruoyi.his.constant.HisBusinessTypeEnum;
import com.ruoyi.his.constant.PayStatusEnum;
import com.ruoyi.his.domain.LeaveHosPay;
import com.ruoyi.his.remote.request.LeaveHosPayIn;
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
public class LeaveHosPayHisServiceHander extends AbstractHisServiceHandler<LeaveHosPayIn, LeaveHosPayOut> {
    @Autowired
    private ILeaveHosPayService leaveHosPayService;

    @Override
    public HisBusinessTypeEnum getBusinessType() {
        return HisBusinessTypeEnum.LEAVEHOSPAY;
    }

    @Override
    boolean checkData(Long id) {
        LeaveHosPay leaveHosPay = leaveHosPayService.selectLeaveHosPayById(id);
        if(null == leaveHosPay){
            throw new HisException(String.format("%1$s记录不存在，不能进行此操作:",id));
        }
        if(PayStatusEnum.PAY_SUCCESS.getCode().equals(leaveHosPay.getSuccessfulPayment())){
            throw new HisException(String.format("%1$s记录不是支付成功状态，不能进行此操作:",id));
        }
        if(StringUtils.isEmpty(leaveHosPay.getTransactionId())){
            throw new HisException(String.format("%1$s记录支付流水为空，不能进行此操作:",id));
        }
        return true;
    }

    @Override
    public LeaveHosPayIn buildRequestData(Long id) {
        LeaveHosPay leaveHosPay = leaveHosPayService.selectLeaveHosPayById(id);
        if(null == leaveHosPay){
            throw new HisException(String.format("%1$s记录已经不存在，不能进行此操作:",id));
        }
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
    public boolean afterInvokeCallSumbit(Long id, LeaveHosPayOut leaveHosPayOut) {
        LeaveHosPay leaveHosPay = new LeaveHosPay();
        leaveHosPay.setId(id);
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
        return true;
    }

}
