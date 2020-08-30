package com.ruoyi.his.service;

import com.ruoyi.his.domain.DepositPayment;

import java.util.List;

/**
 * 押金补缴Service接口
 * 
 * @author whl
 * @date 2020-08-29
 */
public interface IDepositPaymentService 
{
    /**
     * 查询押金补缴
     * 
     * @param id 押金补缴ID
     * @return 押金补缴
     */
    public DepositPayment selectDepositPaymentById(Long id);

    /**
     * 查询押金补缴列表
     * 
     * @param depositPayment 押金补缴
     * @return 押金补缴集合
     */
    public List<DepositPayment> selectDepositPaymentList(DepositPayment depositPayment);

    /**
     * 新增押金补缴
     * 
     * @param depositPayment 押金补缴
     * @return 结果
     */
    public int insertDepositPayment(DepositPayment depositPayment);

    /**
     * 修改押金补缴
     * 
     * @param depositPayment 押金补缴
     * @return 结果
     */
    public int updateDepositPayment(DepositPayment depositPayment);

    /**
     * 批量删除押金补缴
     * 
     * @param ids 需要删除的数据ID
     * @return 结果
     */
    public int deleteDepositPaymentByIds(String ids);

    /**
     * 删除押金补缴信息
     * 
     * @param id 押金补缴ID
     * @return 结果
     */
    public int deleteDepositPaymentById(Long id);


    /**
     * 查询押金补缴
     *
     * @param outTradeNo 订单号
     * @return 押金补缴
     */
    public DepositPayment getDetailByOutTradeNo(String outTradeNo);
}
