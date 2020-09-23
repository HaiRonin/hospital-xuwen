package com.ruoyi.his.service.impl;

import java.util.List;

import com.alibaba.fastjson.JSONObject;
import com.ruoyi.common.exception.HisException;
import com.ruoyi.common.utils.DateUtils;
import com.ruoyi.common.utils.StringUtils;
import com.ruoyi.his.constant.HisBusinessTypeEnum;
import com.ruoyi.his.constant.PayStatusEnum;
import com.ruoyi.his.domain.DopayInfo;
import com.ruoyi.his.remote.AbstractHisServiceHandler;
import com.ruoyi.his.remote.HisBaseServices;
import com.ruoyi.his.remote.request.DoRegCancel;
import com.ruoyi.his.remote.response.BaseResponse;
import org.apache.commons.collections.CollectionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.ruoyi.his.mapper.DoregInfoMapper;
import com.ruoyi.his.domain.DoregInfo;
import com.ruoyi.his.service.IDoregInfoService;
import com.ruoyi.common.core.text.Convert;

/**
 * 预约挂号Service业务层处理
 * 
 * @author whl
 * @date 2020-08-08
 */
@Service
public class DoregInfoServiceImpl implements IDoregInfoService 
{
    @Autowired
    private DoregInfoMapper doregInfoMapper;
    @Autowired
    private HisBaseServices hisBaseServices;
    /**
     * 查询预约挂号
     * 
     * @param id 预约挂号ID
     * @return 预约挂号
     */
    @Override
    public DoregInfo selectDoregInfoById(Long id)
    {
        return doregInfoMapper.selectDoregInfoById(id);
    }

    /**
     * 查询预约挂号列表
     * 
     * @param doregInfo 预约挂号
     * @return 预约挂号
     */
    @Override
    public List<DoregInfo> selectDoregInfoList(DoregInfo doregInfo)
    {
        return doregInfoMapper.selectDoregInfoList(doregInfo);
    }

    /**
     * 新增预约挂号
     * 
     * @param doregInfo 预约挂号
     * @return 结果
     */
    @Override
    public int insertDoregInfo(DoregInfo doregInfo)
    {
        return doregInfoMapper.insertDoregInfo(doregInfo);
    }

    /**
     * 修改预约挂号
     * 
     * @param doregInfo 预约挂号
     * @return 结果
     */
    @Override
    public int updateDoregInfo(DoregInfo doregInfo)
    {
        return doregInfoMapper.updateDoregInfo(doregInfo);
    }

    /**
     * 删除预约挂号对象
     * 
     * @param ids 需要删除的数据ID
     * @return 结果
     */
    @Override
    public int deleteDoregInfoByIds(String ids)
    {
        return doregInfoMapper.deleteDoregInfoByIds(Convert.toStrArray(ids));
    }

    /**
     * 删除预约挂号信息
     * 
     * @param id 预约挂号ID
     * @return 结果
     */
    @Override
    public int deleteDoregInfoById(Long id)
    {
        return doregInfoMapper.deleteDoregInfoById(id);
    }

    /**
     * 查询预约挂号
     *
     * @param outTradeNo 订单号
     * @return 预约挂号
     */
    @Override
    public DoregInfo getDetailByOutTradeNo(String outTradeNo) {
        DoregInfo query = new DoregInfo();
        query.setOutTradeNo(outTradeNo);
        List<DoregInfo> list = selectDoregInfoList(query);
        if(CollectionUtils.isEmpty(list)){
            return null;
        }
        return list.stream().findFirst().get();
    }

    /**
     * 查询预约挂号
     *
     * @param transactionId 订单号
     * @return 预约挂号
     */
    @Override
    public DoregInfo getDetailByTransactionId(String transactionId) {
        DoregInfo query = new DoregInfo();
        query.setTransactionId(transactionId);
        List<DoregInfo> list = selectDoregInfoList(query);
        if(CollectionUtils.isEmpty(list)){
            return null;
        }
        return list.stream().findFirst().get();
    }


    /**
     * 查询预约挂号
     *
     * @param sourceMark 预约号码
     * @return 预约挂号
     */
    @Override
    public DoregInfo getDetailBySourceMark(String sourceMark) {
        DoregInfo query = new DoregInfo();
        query.setSourceMark(sourceMark);
        List<DoregInfo> list = selectDoregInfoList(query);
        if(CollectionUtils.isEmpty(list)){
            return null;
        }
        return list.stream().findFirst().get();
    }


    @Override
    public BaseResponse doRegCancel(DoRegCancel doRegCancel) {
        //数据检查
        if(StringUtils.isEmpty(doRegCancel.getSourceMark())){
            throw new HisException(String.format("取消预约失败，号源不存在"));
        }
        DoregInfo doregInfo = getDetailBySourceMark(doRegCancel.getSourceMark());
        if(null == doregInfo){
            throw new HisException(String.format("取消预约失败，订单不存在"));
        }
        //检查是否已经退过了
        if(!PayStatusEnum.ORDER_SUCCESS.getCode().equals(doregInfo.getSuccessfulPayment())){
            throw new HisException(String.format("订单已经退款或退款中，请不要重复操作"));
        }
        String result = hisBaseServices.requestHisService("/doRegCancel", JSONObject.toJSONString(doRegCancel));
        BaseResponse baseResponse = JSONObject.parseObject(result,BaseResponse.class);
        if(!baseResponse.isOk()){
            throw new HisException(String.format("取消预约失败，原因为"+baseResponse.getResultMsg()));
        }

        //更新为待退款
        DoregInfo doregInfoUpdate = new DoregInfo();
        doregInfoUpdate.setId(doregInfo.getId());
        doregInfoUpdate.setSuccessfulPayment(PayStatusEnum.REFUND_TODO.getCode());
        doregInfoUpdate.setUpdateTime(DateUtils.getNowDate());
        updateDoregInfo(doregInfoUpdate);
        //调用微信退款接口
        HisBusinessTypeEnum hisBusinessTypeEnum = HisBusinessTypeEnum.getTypeByKey(HisBusinessTypeEnum.DOREG.getKey());
        baseResponse = AbstractHisServiceHandler
                .servicesInstance(hisBusinessTypeEnum)
                .callRefund(doregInfo.getOutTradeNo());
        //更新为退款失败或成功
        if(baseResponse.isOk()){
            doregInfoUpdate.setSuccessfulPayment(PayStatusEnum.REFUND_SUCCESS.getCode());
            doregInfoUpdate.setUpdateTime(DateUtils.getNowDate());
            updateDoregInfo(doregInfoUpdate);
            return BaseResponse.success("取消预约成功");
        }
        else{
            doregInfoUpdate.setSuccessfulPayment(PayStatusEnum.REFUND_FAIL.getCode());
            doregInfoUpdate.setUpdateTime(DateUtils.getNowDate());
            updateDoregInfo(doregInfoUpdate);
            return BaseResponse.success("取消预约退款失败，请稍后再试，如有问题请联系医院");
        }
    }
}
