package com.ruoyi.his.service.impl;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.ruoyi.his.mapper.SymptomsOrganMapper;
import com.ruoyi.his.domain.SymptomsOrgan;
import com.ruoyi.his.service.ISymptomsOrganService;
import com.ruoyi.common.core.text.Convert;

/**
 * 身体分类与科室Service业务层处理
 * 
 * @author whl
 * @date 2020-08-16
 */
@Service
public class SymptomsOrganServiceImpl implements ISymptomsOrganService 
{
    @Autowired
    private SymptomsOrganMapper symptomsOrganMapper;

    /**
     * 查询身体分类与科室
     * 
     * @param id 身体分类与科室ID
     * @return 身体分类与科室
     */
    @Override
    public SymptomsOrgan selectSymptomsOrganById(Integer id)
    {
        return symptomsOrganMapper.selectSymptomsOrganById(id);
    }

    /**
     * 查询身体分类与科室列表
     * 
     * @param symptomsOrgan 身体分类与科室
     * @return 身体分类与科室
     */
    @Override
    public List<SymptomsOrgan> selectSymptomsOrganList(SymptomsOrgan symptomsOrgan)
    {
        return symptomsOrganMapper.selectSymptomsOrganList(symptomsOrgan);
    }

    /**
     * 新增身体分类与科室
     * 
     * @param symptomsOrgan 身体分类与科室
     * @return 结果
     */
    @Override
    public int insertSymptomsOrgan(SymptomsOrgan symptomsOrgan)
    {
        return symptomsOrganMapper.insertSymptomsOrgan(symptomsOrgan);
    }

    /**
     * 修改身体分类与科室
     * 
     * @param symptomsOrgan 身体分类与科室
     * @return 结果
     */
    @Override
    public int updateSymptomsOrgan(SymptomsOrgan symptomsOrgan)
    {
        return symptomsOrganMapper.updateSymptomsOrgan(symptomsOrgan);
    }

    /**
     * 删除身体分类与科室对象
     * 
     * @param ids 需要删除的数据ID
     * @return 结果
     */
    @Override
    public int deleteSymptomsOrganByIds(String ids)
    {
        return symptomsOrganMapper.deleteSymptomsOrganByIds(Convert.toStrArray(ids));
    }

    /**
     * 删除身体分类与科室信息
     * 
     * @param id 身体分类与科室ID
     * @return 结果
     */
    @Override
    public int deleteSymptomsOrganById(Integer id)
    {
        return symptomsOrganMapper.deleteSymptomsOrganById(id);
    }
}
