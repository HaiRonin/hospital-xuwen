package com.ruoyi.his.service;

import java.util.List;
import com.ruoyi.his.domain.LeaveHosPay;

/**
 * 出院结算Service接口
 * 
 * @author whl
 * @date 2020-08-29
 */
public interface ILeaveHosPayService 
{
    /**
     * 查询出院结算
     * 
     * @param id 出院结算ID
     * @return 出院结算
     */
    public LeaveHosPay selectLeaveHosPayById(Integer id);

    /**
     * 查询出院结算列表
     * 
     * @param leaveHosPay 出院结算
     * @return 出院结算集合
     */
    public List<LeaveHosPay> selectLeaveHosPayList(LeaveHosPay leaveHosPay);

    /**
     * 新增出院结算
     * 
     * @param leaveHosPay 出院结算
     * @return 结果
     */
    public int insertLeaveHosPay(LeaveHosPay leaveHosPay);

    /**
     * 修改出院结算
     * 
     * @param leaveHosPay 出院结算
     * @return 结果
     */
    public int updateLeaveHosPay(LeaveHosPay leaveHosPay);

    /**
     * 批量删除出院结算
     * 
     * @param ids 需要删除的数据ID
     * @return 结果
     */
    public int deleteLeaveHosPayByIds(String ids);

    /**
     * 删除出院结算信息
     * 
     * @param id 出院结算ID
     * @return 结果
     */
    public int deleteLeaveHosPayById(Integer id);
}
