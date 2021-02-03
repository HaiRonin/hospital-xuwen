package com.ruoyi.his.service;

import java.util.List;
import com.ruoyi.his.domain.CovOrder;
import com.ruoyi.his.domain.DopayInfo;

/**
 * 核酸检测订单Service接口
 * 
 * @author whl
 * @date 2021-02-02
 */
public interface ICovOrderService 
{
    /**
     * 查询核酸检测订单
     * 
     * @param id 核酸检测订单ID
     * @return 核酸检测订单
     */
    public CovOrder selectCovOrderById(Long id);

    /**
     * 查询核酸检测订单列表
     * 
     * @param covOrder 核酸检测订单
     * @return 核酸检测订单集合
     */
    public List<CovOrder> selectCovOrderList(CovOrder covOrder);

    /**
     * 新增核酸检测订单
     * 
     * @param covOrder 核酸检测订单
     * @return 结果
     */
    public int insertCovOrder(CovOrder covOrder);

    /**
     * 修改核酸检测订单
     * 
     * @param covOrder 核酸检测订单
     * @return 结果
     */
    public int updateCovOrder(CovOrder covOrder);

    /**
     * 批量删除核酸检测订单
     * 
     * @param ids 需要删除的数据ID
     * @return 结果
     */
    public int deleteCovOrderByIds(String ids);

    /**
     * 删除核酸检测订单信息
     * 
     * @param id 核酸检测订单ID
     * @return 结果
     */
    public int deleteCovOrderById(Long id);


    /**
     * 根据订单号查询核酸缴费记录
     *
     * @param outTradeNo 订单号
     * @return 查询核酸缴费
     */
    public CovOrder getDetailByOutTradeNo(String outTradeNo);
}
