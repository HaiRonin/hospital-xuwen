package com.ruoyi.his.service.impl;

import java.util.List;

import com.ruoyi.his.domain.DopayInfo;
import org.apache.commons.collections.CollectionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.ruoyi.his.mapper.CovOrderMapper;
import com.ruoyi.his.domain.CovOrder;
import com.ruoyi.his.service.ICovOrderService;
import com.ruoyi.common.core.text.Convert;

/**
 * 核酸检测订单Service业务层处理
 * 
 * @author whl
 * @date 2021-02-02
 */
@Service
public class CovOrderServiceImpl implements ICovOrderService 
{
    @Autowired
    private CovOrderMapper covOrderMapper;

    /**
     * 查询核酸检测订单
     * 
     * @param id 核酸检测订单ID
     * @return 核酸检测订单
     */
    @Override
    public CovOrder selectCovOrderById(Long id)
    {
        return covOrderMapper.selectCovOrderById(id);
    }

    /**
     * 查询核酸检测订单列表
     * 
     * @param covOrder 核酸检测订单
     * @return 核酸检测订单
     */
    @Override
    public List<CovOrder> selectCovOrderList(CovOrder covOrder)
    {
        return covOrderMapper.selectCovOrderList(covOrder);
    }

    /**
     * 新增核酸检测订单
     * 
     * @param covOrder 核酸检测订单
     * @return 结果
     */
    @Override
    public int insertCovOrder(CovOrder covOrder)
    {
        return covOrderMapper.insertCovOrder(covOrder);
    }

    /**
     * 修改核酸检测订单
     * 
     * @param covOrder 核酸检测订单
     * @return 结果
     */
    @Override
    public int updateCovOrder(CovOrder covOrder)
    {
        return covOrderMapper.updateCovOrder(covOrder);
    }

    /**
     * 删除核酸检测订单对象
     * 
     * @param ids 需要删除的数据ID
     * @return 结果
     */
    @Override
    public int deleteCovOrderByIds(String ids)
    {
        return covOrderMapper.deleteCovOrderByIds(Convert.toStrArray(ids));
    }

    /**
     * 删除核酸检测订单信息
     * 
     * @param id 核酸检测订单ID
     * @return 结果
     */
    @Override
    public int deleteCovOrderById(Long id)
    {
        return covOrderMapper.deleteCovOrderById(id);
    }

    @Override
    public CovOrder getDetailByOutTradeNo(String outTradeNo) {
        CovOrder query = new CovOrder();
        query.setOutTradeNo(outTradeNo);
        List<CovOrder> list = selectCovOrderList(query);
        if(CollectionUtils.isEmpty(list)){
            return null;
        }
        return list.stream().findFirst().get();
    }
}
