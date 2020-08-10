package com.ruoyi.his.remote;

import com.ruoyi.common.exception.BusinessException;
import com.ruoyi.his.constant.HisBusinessTypeEnum;
import com.ruoyi.his.domain.DoregInfo;
import com.ruoyi.his.domain.HisResponse;
import com.ruoyi.his.remote.request.DoRegIn;
import com.ruoyi.his.service.IDoregInfoService;
import org.apache.http.client.utils.DateUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.Date;

/**
 * 预约挂号Service业务层处理
 * 
 * @author whl
 * @date 2020-08-08
 */
@Service
public class DoregInfoHisServiceHander extends AbstractHisServiceHandler<DoRegIn> {
    @Autowired
    private IDoregInfoService doregInfoService;

    @Override
    public HisBusinessTypeEnum getBusinessType() {
        return HisBusinessTypeEnum.DOREG;
    }

    @Override
    boolean checkData(Long id) {
        return true;
    }

    @Override
    public DoRegIn getBusinessData(Long id) {
       // DoregInfo doregInfoNew = doregInfoService.selectDoregInfoById(id);
        DoregInfo doregInfoNew = getTest();
        if(null == doregInfoNew){
            throw new BusinessException(String.format("%1$s记录已经不存在，不能进行此操作:",id));
        }
        DoRegIn doRegInInfo = new DoRegIn();
        doRegInInfo.setCardNo(doregInfoNew.getCardNo());
        doRegInInfo.setDepartmentorganId(doregInfoNew.getDepartmentorganId());
        doRegInInfo.setOrgandoctorId(doregInfoNew.getOrgandoctorId());
        doRegInInfo.setPatientNo(doregInfoNew.getPatientNo());
        doRegInInfo.setPayAmount(String.valueOf(doregInfoNew.getPayAmount()));
        doRegInInfo.setPayNo(doregInfoNew.getPayNo());
        doRegInInfo.setPayType(Integer.parseInt(doregInfoNew.getPayType()));
        doRegInInfo.setSocialsecurityNO("");
        doRegInInfo.setSourceDate(DateUtils.formatDate(doregInfoNew.getSourceDate(),DateUtils.PATTERN_RFC1036));
        doRegInInfo.setSourceTimeType(doregInfoNew.getSourceTimeType());
        doRegInInfo.setTimestypeNo(Integer.parseInt(doregInfoNew.getTimestypeNo()));
        return doRegInInfo;
    }

    @Override
    public String afterInvokeCall(Long id, HisResponse hisResponse) {
        return null;
    }


    /***
     * 测试数据
     * @return
     */
    private DoregInfo getTest(){
        DoregInfo doregInfoNew = new DoregInfo();
        doregInfoNew.setCardNo("1111");
        doregInfoNew.setPatientNo("2222");
        doregInfoNew.setPayAmount(BigDecimal.TEN);
        doregInfoNew.setPayNo("22222");
        doregInfoNew.setPayType("5");
        doregInfoNew.setSourceDate(new Date());
        doregInfoNew.setSourceTimeType(0);
        doregInfoNew.setTimestypeNo("0");
        return doregInfoNew;
    }
}
