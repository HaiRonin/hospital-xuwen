package com.ruoyi.his.remote;

import com.alibaba.fastjson.JSON;
import com.ruoyi.common.exception.HisException;
import com.ruoyi.common.utils.DateUtils;
import com.ruoyi.common.utils.StringUtils;
import com.ruoyi.his.constant.HisBusinessTypeEnum;
import com.ruoyi.his.constant.PayStatusEnum;
import com.ruoyi.his.domain.DopayInfo;
import com.ruoyi.his.domain.DoregInfo;
import com.ruoyi.his.remote.request.DoRegIn;
import com.ruoyi.his.remote.response.BaseResponse;
import com.ruoyi.his.remote.response.DoRegOut;
import com.ruoyi.his.service.IDoregInfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * 预约挂号Service业务层处理
 * 
 * @author whl
 * @date 2020-08-08
 */
@Service
public class DoregInfoHisServiceHander extends AbstractHisServiceHandler<DoRegIn, DoRegOut> {
    @Autowired
    private IDoregInfoService doregInfoService;

    @Override
    public HisBusinessTypeEnum getBusinessType() {
        return HisBusinessTypeEnum.DOREG;
    }

    @Override
    boolean checkData(String outTradeNo) {
        DoregInfo doregInfoNew = doregInfoService.getDetailByOutTradeNo(outTradeNo);
        if(null == doregInfoNew){
            throw new HisException(String.format("%1$s记录不存在，不能进行此操作:",outTradeNo));
        }
        if(PayStatusEnum.ORDER_SUCCESS.getCode().equals(doregInfoNew.getSuccessfulPayment()) ||
                PayStatusEnum.ORDER_FAIL.getCode().equals(doregInfoNew.getSuccessfulPayment())){
            throw new HisException(String.format("%1$s记录状态为[%1$s]，不能重复此操作:",outTradeNo,PayStatusEnum.getByCode(doregInfoNew.getSuccessfulPayment())));
        }
        if(PayStatusEnum.PAY_SUCCESS.getCode().equals(doregInfoNew.getSuccessfulPayment())){
            throw new HisException(String.format("%1$s记录不是支付成功状态，不能进行此操作:",outTradeNo));
        }
        if(StringUtils.isEmpty(doregInfoNew.getPayNo())){
            throw new HisException(String.format("%1$s记录支付流水为空，不能进行此操作:",outTradeNo));
        }
        return true;
    }

    @Override
    public DoRegIn buildRequestData(String outTradeNo) {
        DoregInfo doregInfoNew = doregInfoService.getDetailByOutTradeNo(outTradeNo);
        if(null == doregInfoNew){
            throw new HisException(String.format("%1$s记录已经不存在，不能进行此操作:",outTradeNo));
        }
        DoRegIn doRegInInfo = new DoRegIn();
        //医生编号
        doRegInInfo.setOrgandoctorId(doregInfoNew.getOrgandoctorId());
        //科室编号
        doRegInInfo.setDepartmentorganId(doregInfoNew.getDepartmentorganId());
        //患者身份证号
        doRegInInfo.setCardNo(doregInfoNew.getCardNo());
        //患者编号
        doRegInInfo.setPatientNo(doregInfoNew.getPatientNo());
        //取号时间（号源日期）
        doRegInInfo.setSourceDate(DateUtils.parseDateToStr(DateUtils.YYYY_MM_DD,doregInfoNew.getSourceDate()));
        //时间段标识 0表示无时间段
        if(null != doregInfoNew.getTimestypeNo()){
            doRegInInfo.setTimestypeNo(doregInfoNew.getTimestypeNo());
        }
        //1,上午 2，中午3 下午 4，晚上 5，凌晨
        doRegInInfo.setSourceTimeType(doregInfoNew.getSourceTimeType());
        //1,银联，2支付宝 3，现场支付 4、医保账户，5、微信，6、云医院微信，7、云医院支付宝，8、诊疗卡
        doRegInInfo.setPayType(Integer.parseInt(doregInfoNew.getPayType()));
        //支付流水号
        doRegInInfo.setPayNo(doregInfoNew.getPayNo());
        //支付金额
        doRegInInfo.setPayAmount(String.valueOf(doregInfoNew.getPayAmount()));

        return doRegInInfo;
    }

    @Override
    protected DoRegOut transResult(String result) {
        return JSON.toJavaObject(JSON.parseObject(result), DoRegOut.class);
    }


    @Override
    public boolean afterInvokeCallSumbit(String outTradeNo, DoRegOut regOut) {
        DoregInfo doregInfoTemp = doregInfoService.getDetailByOutTradeNo(outTradeNo);
        if(null == doregInfoTemp){
            throw new HisException(String.format("%1$s记录已经不存在，不能进行此操作:",outTradeNo));
        }
        DoregInfo doregInfo = new DoregInfo();
        doregInfo.setId(doregInfoTemp.getId());
        doregInfo.setResultMsg(regOut.getResultMsg());
        doregInfo.setUpdateTime(DateUtils.getNowDate());
        doregInfo.setSuccessfulPayment(regOut.isOk()?PayStatusEnum.ORDER_SUCCESS.getCode():PayStatusEnum.ORDER_FAIL.getCode());
        if(regOut.isOk()){
            doregInfo.setMedicalCode(regOut.getMedicalCode());
            doregInfo.setSourceMark(regOut.getSourceMark());
            doregInfo.setConsultationFee(regOut.getConsultationFee());
        }
        doregInfoService.updateDoregInfo(doregInfo);
        return true;
    }


    @Override
    public BaseResponse paySuccessful(String outTradeNo, String transactionId) {
        BaseResponse baseResponse = new BaseResponse("00","操作成功");
        DoregInfo doregInfoTemp = doregInfoService.getDetailByOutTradeNo(outTradeNo);
        if(null == doregInfoTemp){
            throw new HisException(String.format("%1$s记录已经不存在，不能进行此操作:",outTradeNo));
        }
        DoregInfo doregInfo = new DoregInfo();
        doregInfo.setId(doregInfoTemp.getId());
        doregInfo.setUpdateTime(DateUtils.getNowDate());
        doregInfo.setTransactionId(transactionId);
        doregInfo.setSuccessfulPayment(PayStatusEnum.PAY_SUCCESS.getCode());
        doregInfoService.updateDoregInfo(doregInfo);
        try {
            baseResponse = invokeCallSubmit(outTradeNo);
        }catch (HisException ex){
            throw ex;
        }catch (Exception ex){
            new BaseResponse("00","预约挂号支付时发生错误,支付的金额稍后会自动原路返回，请注意查收");
        }
        return baseResponse;
    }

    @Override
    public BaseResponse payFailed(String outTradeNo) {
        DoregInfo doregInfoTemp = doregInfoService.getDetailByOutTradeNo(outTradeNo);
        if(null == doregInfoTemp){
            throw new HisException(String.format("%1$s记录已经不存在，不能进行此操作:",outTradeNo));
        }
        DoregInfo doregInfo = new DoregInfo();
        doregInfo.setId(doregInfoTemp.getId());
        doregInfo.setUpdateTime(DateUtils.getNowDate());
        doregInfo.setSuccessfulPayment(PayStatusEnum.PAY_FAIL.getCode());
        doregInfoService.updateDoregInfo(doregInfo);
        return new BaseResponse("00","操作成功");
    }

    @Override
    protected BaseResponse refundSuccessful(String outTradeNo, String transactionId) {
        DoregInfo doregInfoTemp = doregInfoService.getDetailByOutTradeNo(outTradeNo);
        if(null == doregInfoTemp){
            throw new HisException(String.format("%1$s记录已经不存在，不能进行此操作:",outTradeNo));
        }
        DoregInfo doregInfo = new DoregInfo();
        doregInfo.setId(doregInfoTemp.getId());
        doregInfo.setUpdateTime(DateUtils.getNowDate());
        doregInfo.setTransactionId(doregInfoTemp.getTransactionId()+"||"+transactionId);
        doregInfo.setSuccessfulPayment(PayStatusEnum.REFUND_SUCCESS.getCode());
        doregInfoService.updateDoregInfo(doregInfo);
        return new BaseResponse("00","操作成功");
    }

    @Override
    protected BaseResponse refundFailed(String outTradeNo) {
        DoregInfo doregInfoTemp = doregInfoService.getDetailByOutTradeNo(outTradeNo);
        if(null == doregInfoTemp){
            throw new HisException(String.format("%1$s记录已经不存在，不能进行此操作:",outTradeNo));
        }
        DoregInfo doregInfo = new DoregInfo();
        doregInfo.setId(doregInfoTemp.getId());
        doregInfo.setUpdateTime(DateUtils.getNowDate());
        doregInfo.setSuccessfulPayment(PayStatusEnum.REFUND_FAIL.getCode());
        doregInfoService.updateDoregInfo(doregInfo);
        return new BaseResponse("00","操作成功");
    }

}
