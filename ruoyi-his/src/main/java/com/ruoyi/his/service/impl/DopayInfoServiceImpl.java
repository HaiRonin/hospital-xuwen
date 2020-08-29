package com.ruoyi.his.service.impl;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.ruoyi.his.mapper.DopayInfoMapper;
import com.ruoyi.his.domain.DopayInfo;
import com.ruoyi.his.service.IDopayInfoService;
import com.ruoyi.common.core.text.Convert;

/**
 * 门诊支付Service业务层处理
 * 
 * @author whl
 * @date 2020-08-29
 */
@Service
public class DopayInfoServiceImpl implements IDopayInfoService 
{
    @Autowired
    private DopayInfoMapper dopayInfoMapper;

    /**
     * 查询门诊支付
     * 
     * @param id 门诊支付ID
     * @return 门诊支付
     */
    @Override
    public DopayInfo selectDopayInfoById(Long id)
    {
        return dopayInfoMapper.selectDopayInfoById(id);
    }

    /**
     * 查询门诊支付列表
     * 
     * @param dopayInfo 门诊支付
     * @return 门诊支付
     */
    @Override
    public List<DopayInfo> selectDopayInfoList(DopayInfo dopayInfo)
    {
        return dopayInfoMapper.selectDopayInfoList(dopayInfo);
    }

    /**
     * 新增门诊支付
     * 
     * @param dopayInfo 门诊支付
     * @return 结果
     */
    @Override
    public int insertDopayInfo(DopayInfo dopayInfo)
    {
        return dopayInfoMapper.insertDopayInfo(dopayInfo);
    }

    /**
     * 修改门诊支付
     * 
     * @param dopayInfo 门诊支付
     * @return 结果
     */
    @Override
    public int updateDopayInfo(DopayInfo dopayInfo)
    {
        return dopayInfoMapper.updateDopayInfo(dopayInfo);
    }

    /**
     * 删除门诊支付对象
     * 
     * @param ids 需要删除的数据ID
     * @return 结果
     */
    @Override
    public int deleteDopayInfoByIds(String ids)
    {
        return dopayInfoMapper.deleteDopayInfoByIds(Convert.toStrArray(ids));
    }

    /**
     * 删除门诊支付信息
     * 
     * @param id 门诊支付ID
     * @return 结果
     */
    @Override
    public int deleteDopayInfoById(Long id)
    {
        return dopayInfoMapper.deleteDopayInfoById(id);
    }
}
