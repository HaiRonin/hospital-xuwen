package com.ruoyi.his.service.impl;

import java.util.List;
import com.ruoyi.common.utils.DateUtils;
import org.apache.commons.collections.CollectionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.ruoyi.his.mapper.DepositPaymentMapper;
import com.ruoyi.his.domain.DepositPayment;
import com.ruoyi.his.service.IDepositPaymentService;
import com.ruoyi.common.core.text.Convert;

/**
 * 押金补缴Service业务层处理
 * 
 * @author whl
 * @date 2020-08-29
 */
@Service
public class DepositPaymentServiceImpl implements IDepositPaymentService 
{
    @Autowired
    private DepositPaymentMapper depositPaymentMapper;

    /**
     * 查询押金补缴
     * 
     * @param id 押金补缴ID
     * @return 押金补缴
     */
    @Override
    public DepositPayment selectDepositPaymentById(Long id)
    {
        return depositPaymentMapper.selectDepositPaymentById(id);
    }

    /**
     * 查询押金补缴列表
     * 
     * @param depositPayment 押金补缴
     * @return 押金补缴
     */
    @Override
    public List<DepositPayment> selectDepositPaymentList(DepositPayment depositPayment)
    {
        return depositPaymentMapper.selectDepositPaymentList(depositPayment);
    }

    /**
     * 新增押金补缴
     * 
     * @param depositPayment 押金补缴
     * @return 结果
     */
    @Override
    public int insertDepositPayment(DepositPayment depositPayment)
    {
        depositPayment.setCreateTime(DateUtils.getNowDate());
        return depositPaymentMapper.insertDepositPayment(depositPayment);
    }

    /**
     * 修改押金补缴
     * 
     * @param depositPayment 押金补缴
     * @return 结果
     */
    @Override
    public int updateDepositPayment(DepositPayment depositPayment)
    {
        depositPayment.setUpdateTime(DateUtils.getNowDate());
        return depositPaymentMapper.updateDepositPayment(depositPayment);
    }

    /**
     * 删除押金补缴对象
     * 
     * @param ids 需要删除的数据ID
     * @return 结果
     */
    @Override
    public int deleteDepositPaymentByIds(String ids)
    {
        return depositPaymentMapper.deleteDepositPaymentByIds(Convert.toStrArray(ids));
    }

    /**
     * 删除押金补缴信息
     * 
     * @param id 押金补缴ID
     * @return 结果
     */
    @Override
    public int deleteDepositPaymentById(Long id)
    {
        return depositPaymentMapper.deleteDepositPaymentById(id);
    }

    /**
     * 查询押金补缴
     *
     * @param outTradeNo 订单号
     * @return 押金补缴
     */
    @Override
    public DepositPayment getDetailByOutTradeNo(String outTradeNo) {
        DepositPayment depositPaymentQuery = new DepositPayment();
        depositPaymentQuery.setOutTradeNo(outTradeNo);
        List<DepositPayment> list = selectDepositPaymentList(depositPaymentQuery);
        if(CollectionUtils.isEmpty(list)){
            return null;
        }
        return list.stream().findFirst().get();
    }
}
