package com.ruoyi.his.service.impl;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.ruoyi.his.mapper.LeaveHosPayMapper;
import com.ruoyi.his.domain.LeaveHosPay;
import com.ruoyi.his.service.ILeaveHosPayService;
import com.ruoyi.common.core.text.Convert;

/**
 * 出院结算Service业务层处理
 * 
 * @author whl
 * @date 2020-08-29
 */
@Service
public class LeaveHosPayServiceImpl implements ILeaveHosPayService 
{
    @Autowired
    private LeaveHosPayMapper leaveHosPayMapper;

    /**
     * 查询出院结算
     * 
     * @param id 出院结算ID
     * @return 出院结算
     */
    @Override
    public LeaveHosPay selectLeaveHosPayById(Long id)
    {
        return leaveHosPayMapper.selectLeaveHosPayById(id);
    }

    /**
     * 查询出院结算列表
     * 
     * @param leaveHosPay 出院结算
     * @return 出院结算
     */
    @Override
    public List<LeaveHosPay> selectLeaveHosPayList(LeaveHosPay leaveHosPay)
    {
        return leaveHosPayMapper.selectLeaveHosPayList(leaveHosPay);
    }

    /**
     * 新增出院结算
     * 
     * @param leaveHosPay 出院结算
     * @return 结果
     */
    @Override
    public int insertLeaveHosPay(LeaveHosPay leaveHosPay)
    {
        return leaveHosPayMapper.insertLeaveHosPay(leaveHosPay);
    }

    /**
     * 修改出院结算
     * 
     * @param leaveHosPay 出院结算
     * @return 结果
     */
    @Override
    public int updateLeaveHosPay(LeaveHosPay leaveHosPay)
    {
        return leaveHosPayMapper.updateLeaveHosPay(leaveHosPay);
    }

    /**
     * 删除出院结算对象
     * 
     * @param ids 需要删除的数据ID
     * @return 结果
     */
    @Override
    public int deleteLeaveHosPayByIds(String ids)
    {
        return leaveHosPayMapper.deleteLeaveHosPayByIds(Convert.toStrArray(ids));
    }

    /**
     * 删除出院结算信息
     * 
     * @param id 出院结算ID
     * @return 结果
     */
    @Override
    public int deleteLeaveHosPayById(Integer id)
    {
        return leaveHosPayMapper.deleteLeaveHosPayById(id);
    }
}
