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
import org.springframework.transaction.annotation.Transactional;

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
    boolean checkData(String outTradeNo) {
        LeaveHosPay leaveHosPay = leaveHosPayService.getDetailByOutTradeNo(outTradeNo);
        if(null == leaveHosPay){
            throw new HisException(String.format("%1$s记录不存在，不能进行此操作:",outTradeNo));
        }
        if(PayStatusEnum.PAY_SUCCESS.getCode().equals(leaveHosPay.getSuccessfulPayment())){
            throw new HisException(String.format("%1$s记录不是支付成功状态，不能进行此操作:",outTradeNo));
        }
        if(StringUtils.isEmpty(leaveHosPay.getTransactionId())){
            throw new HisException(String.format("%1$s记录支付流水为空，不能进行此操作:",outTradeNo));
        }
        return true;
    }

    @Override
    public LeaveHosPayIn buildRequestData(String outTradeNo) {
        LeaveHosPay leaveHosPay = leaveHosPayService.getDetailByOutTradeNo(outTradeNo);
        if(null == leaveHosPay){
            throw new HisException(String.format("%1$s记录已经不存在，不能进行此操作:",outTradeNo));
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
    public boolean afterInvokeCallSumbit(String outTradeNo, LeaveHosPayOut leaveHosPayOut) {
        LeaveHosPay leaveHosPayTemp = leaveHosPayService.getDetailByOutTradeNo(outTradeNo);
        if(null == leaveHosPayTemp){
            throw new HisException(String.format("%1$s记录已经不存在，不能进行此操作:",outTradeNo));
        }
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
        return true;
    }


    @Override
    public BaseResponse paySuccessful(String outTradeNo, String transactionId) {
        BaseResponse baseResponse = new BaseResponse("00","操作成功");
        LeaveHosPay leaveHosPayTemp = leaveHosPayService.getDetailByOutTradeNo(outTradeNo);
        if(null == leaveHosPayTemp){
            throw new HisException(String.format("%1$s记录已经不存在，不能进行此操作:",outTradeNo));
        }
        LeaveHosPay leaveHosPay = new LeaveHosPay();
        leaveHosPay.setId(leaveHosPayTemp.getId());
        leaveHosPay.setUpdateTime(DateUtils.getNowDate());
        leaveHosPay.setTransactionId(transactionId);
        leaveHosPay.setSuccessfulPayment(PayStatusEnum.PAY_SUCCESS.getCode());
        leaveHosPayService.updateLeaveHosPay(leaveHosPay);
        try {
            baseResponse = invokeCallSubmit(outTradeNo);
        }catch (HisException ex){
            throw ex;
        }catch (Exception ex){
            new BaseResponse("00","出院缴费发生错误,支付的金额稍后会自动原路返回，请注意查收");
        }
        return baseResponse;
    }

    @Override
    public BaseResponse payFailed(String outTradeNo) {
        LeaveHosPay leaveHosPayTemp = leaveHosPayService.getDetailByOutTradeNo(outTradeNo);
        if(null == leaveHosPayTemp){
            throw new HisException(String.format("%1$s记录已经不存在，不能进行此操作:",outTradeNo));
        }
        LeaveHosPay leaveHosPay = new LeaveHosPay();
        leaveHosPay.setId(leaveHosPayTemp.getId());
        leaveHosPay.setUpdateTime(DateUtils.getNowDate());
        leaveHosPay.setSuccessfulPayment(PayStatusEnum.PAY_FAIL.getCode());
        leaveHosPayService.updateLeaveHosPay(leaveHosPay);
        return new BaseResponse("00","操作成功");
    }
}
