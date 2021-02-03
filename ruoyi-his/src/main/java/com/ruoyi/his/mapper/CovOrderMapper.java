package com.ruoyi.his.mapper;

import java.util.List;
import com.ruoyi.his.domain.CovOrder;

/**
 * 核酸检测订单Mapper接口
 * 
 * @author whl
 * @date 2021-02-02
 */
public interface CovOrderMapper 
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
     * 删除核酸检测订单
     * 
     * @param id 核酸检测订单ID
     * @return 结果
     */
    public int deleteCovOrderById(Long id);

    /**
     * 批量删除核酸检测订单
     * 
     * @param ids 需要删除的数据ID
     * @return 结果
     */
    public int deleteCovOrderByIds(String[] ids);
}
