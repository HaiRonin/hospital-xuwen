package com.ruoyi.his.service;

import java.util.List;
import com.ruoyi.his.domain.DoregInfo;
import com.ruoyi.his.remote.request.DoRegCancel;
import com.ruoyi.his.remote.response.BaseResponse;

/**
 * 预约挂号Service接口
 * 
 * @author whl
 * @date 2020-08-08
 */
public interface IDoregInfoService 
{
    /**
     * 查询预约挂号
     * 
     * @param id 预约挂号ID
     * @return 预约挂号
     */
    public DoregInfo selectDoregInfoById(Long id);

    /**
     * 查询预约挂号列表
     * 
     * @param doregInfo 预约挂号
     * @return 预约挂号集合
     */
    public List<DoregInfo> selectDoregInfoList(DoregInfo doregInfo);

    /**
     * 新增预约挂号
     * 
     * @param doregInfo 预约挂号
     * @return 结果
     */
    public int insertDoregInfo(DoregInfo doregInfo);

    /**
     * 修改预约挂号
     * 
     * @param doregInfo 预约挂号
     * @return 结果
     */
    public int updateDoregInfo(DoregInfo doregInfo);

    /**
     * 批量删除预约挂号
     * 
     * @param ids 需要删除的数据ID
     * @return 结果
     */
    public int deleteDoregInfoByIds(String ids);

    /**
     * 删除预约挂号信息
     * 
     * @param id 预约挂号ID
     * @return 结果
     */
    public int deleteDoregInfoById(Long id);

    /**
     * 查询预约挂号
     *
     * @param outTradeNo 预约挂号ID
     * @return 预约挂号
     */
    public DoregInfo getDetailByOutTradeNo(String outTradeNo);

    /***
     * 根据支付交易流水号查询记录
     * @param transactionId
     * @return
     */
    public DoregInfo getDetailByTransactionId(String transactionId);
    /***
     * 取消预约
     * @param doRegCancel
     * @return
     */
    public BaseResponse doRegCancel(DoRegCancel doRegCancel);
}
