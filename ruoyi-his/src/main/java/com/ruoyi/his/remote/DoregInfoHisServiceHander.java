package com.ruoyi.his.remote;

import com.alibaba.fastjson.JSON;
import com.ruoyi.common.exception.HisException;
import com.ruoyi.common.utils.DateUtils;
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

import java.util.List;

/**
 * 预约挂号Service业务层处理
 * 
 * @author whl
 * @date 2020-08-08
 */
@Service
public class DoregInfoHisServiceHander extends AbstractHisServiceHandler<DoRegIn,DoregInfo, DoRegOut> {
    @Autowired
    private IDoregInfoService doregInfoService;

    @Override
    public HisBusinessTypeEnum getBusinessType() {
        return HisBusinessTypeEnum.DOREG;
    }


    @Override
    DoregInfo getOrderDetail(String outTradeNo) {
        DoregInfo doregInfo = doregInfoService.getDetailByOutTradeNo(outTradeNo);
        doregInfo.setAmount(doregInfo.getPayAmount());
        return doregInfo;
    }

    @Override
    int updateOrder(DoregInfo doregInfo) {
        return doregInfoService.updateDoregInfo(doregInfo);
    }

    @Override
    public DoRegIn buildRequestData(String outTradeNo) {
        DoregInfo doregInfoNew = getOrderDetail(outTradeNo);
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
    public BaseResponse afterInvokeCallSumbit(String outTradeNo, DoRegOut regOut) {
        DoregInfo doregInfoTemp = getOrderDetail(outTradeNo);
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
        return regOut.isOk()?BaseResponse.success():BaseResponse.fail("操作失败，支付金额稍后将会原路返回");
    }

    @Override
    protected List<DoregInfo> getRefundOrderList() {
        DoregInfo query = new DoregInfo();
        query.setSuccessfulPayment(PayStatusEnum.ORDER_FAIL.getCode());
        List<DoregInfo> lstDopayInfo =doregInfoService.selectDoregInfoList(query);
        return lstDopayInfo;
    }
}
