package com.ruoyi.his.mapper;

import java.util.List;
import com.ruoyi.his.domain.DoregInfo;

/**
 * 预约挂号Mapper接口
 * 
 * @author whl
 * @date 2020-08-08
 */
public interface DoregInfoMapper 
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
     * 删除预约挂号
     * 
     * @param id 预约挂号ID
     * @return 结果
     */
    public int deleteDoregInfoById(Long id);

    /**
     * 批量删除预约挂号
     * 
     * @param ids 需要删除的数据ID
     * @return 结果
     */
    public int deleteDoregInfoByIds(String[] ids);
}
