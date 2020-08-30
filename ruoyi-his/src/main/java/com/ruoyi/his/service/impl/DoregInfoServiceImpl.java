package com.ruoyi.his.service.impl;

import java.util.List;

import com.ruoyi.his.domain.DopayInfo;
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
}
