package com.ruoyi.his.service;

import java.util.List;
import com.ruoyi.his.domain.SymptomsOrgan;

/**
 * 身体分类与科室Service接口
 * 
 * @author whl
 * @date 2020-08-16
 */
public interface ISymptomsOrganService 
{
    /**
     * 查询身体分类与科室
     * 
     * @param id 身体分类与科室ID
     * @return 身体分类与科室
     */
    public SymptomsOrgan selectSymptomsOrganById(Integer id);

    /**
     * 查询身体分类与科室列表
     * 
     * @param symptomsOrgan 身体分类与科室
     * @return 身体分类与科室集合
     */
    public List<SymptomsOrgan> selectSymptomsOrganList(SymptomsOrgan symptomsOrgan);

    /**
     * 新增身体分类与科室
     * 
     * @param symptomsOrgan 身体分类与科室
     * @return 结果
     */
    public int insertSymptomsOrgan(SymptomsOrgan symptomsOrgan);

    /**
     * 修改身体分类与科室
     * 
     * @param symptomsOrgan 身体分类与科室
     * @return 结果
     */
    public int updateSymptomsOrgan(SymptomsOrgan symptomsOrgan);

    /**
     * 批量删除身体分类与科室
     * 
     * @param ids 需要删除的数据ID
     * @return 结果
     */
    public int deleteSymptomsOrganByIds(String ids);

    /**
     * 删除身体分类与科室信息
     * 
     * @param id 身体分类与科室ID
     * @return 结果
     */
    public int deleteSymptomsOrganById(Integer id);
}
