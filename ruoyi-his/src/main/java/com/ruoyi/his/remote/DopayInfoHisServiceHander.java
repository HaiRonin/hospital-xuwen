package com.ruoyi.his.remote;

import com.alibaba.fastjson.JSON;
import com.ruoyi.common.exception.HisException;
import com.ruoyi.common.utils.DateUtils;
import com.ruoyi.common.utils.StringUtils;
import com.ruoyi.his.constant.HisBusinessTypeEnum;
import com.ruoyi.his.constant.PayStatusEnum;
import com.ruoyi.his.domain.DoregInfo;
import com.ruoyi.his.remote.request.DoRegIn;
import com.ruoyi.his.remote.request.DoRequestInfo;
import com.ruoyi.his.remote.response.DoRegOut;
import com.ruoyi.his.service.IDoregInfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * 缴费Service业务层处理
 * 
 * @author whl
 * @date 2020-08-28
 */
@Service
public class DopayInfoHisServiceHander extends AbstractHisServiceHandler<DoRegIn, DoRegOut> {
    @Autowired
    private IDoregInfoService doregInfoService;

    @Override
    public HisBusinessTypeEnum getBusinessType() {
        return HisBusinessTypeEnum.DOREG;
    }

    @Override
    boolean checkData(Long id) {
        DoregInfo doregInfoNew = doregInfoService.selectDoregInfoById(id);
        if(PayStatusEnum.PAY_SUCCESS.getCode().equals(doregInfoNew.getSuccessfulPayment())){
            throw new HisException(String.format("%1$s记录不是支付成功状态，不能进行此操作:",id));
        }
        if(StringUtils.isEmpty(doregInfoNew.getPayNo())){
            throw new HisException(String.format("%1$s记录支付流水为空，不能进行此操作:",id));
        }
        return true;
    }

    @Override
    public DoRegIn buildRequestData(Long id) {
        DoregInfo doregInfoNew = doregInfoService.selectDoregInfoById(id);
        if(null == doregInfoNew){
            throw new HisException(String.format("%1$s记录已经不存在，不能进行此操作:",id));
        }
        DoRequestInfo doRegInInfo = new DoRequestInfo();
        //医生编号
        doRegInInfo.setOrgandoctorId(doregInfoNew.getOrgandoctorId());
        //科室编号
        doRegInInfo.setDepartmentorganId(doregInfoNew.getDepartmentorganId());
        //患者身份证号
        doRegInInfo.setCardNo(doregInfoNew.getCardNo());
        //患者编号
        doRegInInfo.setPatientNo(doregInfoNew.getPatientNo());
        //社保号
        doRegInInfo.setSocialsecurityNO(doregInfoNew.getSocialsecurityNO());
        //取号时间（号源日期）
        doRegInInfo.setSourceDate(DateUtils.parseDateToStr(DateUtils.YYYY_MM_DD,doregInfoNew.getSourceDate()));
        //时间段标识 0表示无时间段
        doRegInInfo.setTimestypeNo(doregInfoNew.getTimestypeNo());
        //1,上午 2，中午3 下午 4，晚上 5，凌晨
        doRegInInfo.setSourceTimeType(doregInfoNew.getSourceTimeType());
        //1,银联，2支付宝 3，现场支付 4、医保账户，5、微信，6、云医院微信，7、云医院支付宝，8、诊疗卡
        doRegInInfo.setPayType(Integer.parseInt(doregInfoNew.getPayType()));
        //支付卡号
        doRegInInfo.setPayCardNo(doregInfoNew.getPayNo());
        //支付金额
        doRegInInfo.setPayAmount(String.valueOf(doregInfoNew.getPayAmount()));
        //支付流水号
        doRegInInfo.setPayNo(doregInfoNew.getPayNo());
        DoRegIn doRegIn = new DoRegIn();
        doRegIn.setDoRegIn(JSON.toJSONString(doRegInInfo));
        return doRegIn;
    }

    @Override
    protected DoRegOut transResult(String result) {
        return JSON.toJavaObject(JSON.parseObject(result), DoRegOut.class);
    }


    @Override
    public boolean afterInvokeCallSumbit(Long id, DoRegOut regOut) {
        DoregInfo doregInfo = new DoregInfo();
        doregInfo.setId(id);
        doregInfo.setMedicalCode(regOut.getMedicalCode());
        doregInfo.setSourceMark(regOut.getSourceMark());
        doregInfo.setConsultationFee(regOut.getConsultationFee());
        doregInfo.setUpdateTime(DateUtils.getNowDate());
        doregInfo.setSuccessfulPayment(regOut.isOk()?PayStatusEnum.ORDER_SUCCESS.getCode():PayStatusEnum.ORDER_FAIL.getCode());
        doregInfoService.updateDoregInfo(doregInfo);
        return true;
    }

}
