package com.ruoyi.his.service.impl;

import java.util.List;
import com.ruoyi.common.utils.DateUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.ruoyi.his.mapper.AppointmentRegMapper;
import com.ruoyi.his.domain.AppointmentReg;
import com.ruoyi.his.service.IAppointmentRegService;
import com.ruoyi.common.core.text.Convert;

/**
 * 医院预约登记Service业务层处理
 * 
 * @author whl
 * @date 2020-10-22
 */
@Service
public class AppointmentRegServiceImpl implements IAppointmentRegService 
{
    @Autowired
    private AppointmentRegMapper appointmentRegMapper;

    /**
     * 查询医院预约登记
     * 
     * @param id 医院预约登记ID
     * @return 医院预约登记
     */
    @Override
    public AppointmentReg selectAppointmentRegById(Long id)
    {
        return appointmentRegMapper.selectAppointmentRegById(id);
    }

    /**
     * 查询医院预约登记列表
     * 
     * @param appointmentReg 医院预约登记
     * @return 医院预约登记
     */
    @Override
    public List<AppointmentReg> selectAppointmentRegList(AppointmentReg appointmentReg)
    {
        return appointmentRegMapper.selectAppointmentRegList(appointmentReg);
    }

    /**
     * 新增医院预约登记
     * 
     * @param appointmentReg 医院预约登记
     * @return 结果
     */
    @Override
    public int insertAppointmentReg(AppointmentReg appointmentReg)
    {
        appointmentReg.setCreateTime(DateUtils.getNowDate());
        return appointmentRegMapper.insertAppointmentReg(appointmentReg);
    }

    /**
     * 修改医院预约登记
     * 
     * @param appointmentReg 医院预约登记
     * @return 结果
     */
    @Override
    public int updateAppointmentReg(AppointmentReg appointmentReg)
    {
        appointmentReg.setUpdateTime(DateUtils.getNowDate());
        return appointmentRegMapper.updateAppointmentReg(appointmentReg);
    }

    /**
     * 删除医院预约登记对象
     * 
     * @param ids 需要删除的数据ID
     * @return 结果
     */
    @Override
    public int deleteAppointmentRegByIds(String ids)
    {
        return appointmentRegMapper.deleteAppointmentRegByIds(Convert.toStrArray(ids));
    }

    /**
     * 删除医院预约登记信息
     * 
     * @param id 医院预约登记ID
     * @return 结果
     */
    @Override
    public int deleteAppointmentRegById(Long id)
    {
        return appointmentRegMapper.deleteAppointmentRegById(id);
    }
}
