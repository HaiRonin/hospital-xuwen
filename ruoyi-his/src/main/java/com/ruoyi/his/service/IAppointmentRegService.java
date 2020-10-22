package com.ruoyi.his.service;

import java.util.List;
import com.ruoyi.his.domain.AppointmentReg;

/**
 * 医院预约登记Service接口
 * 
 * @author whl
 * @date 2020-10-22
 */
public interface IAppointmentRegService 
{
    /**
     * 查询医院预约登记
     * 
     * @param id 医院预约登记ID
     * @return 医院预约登记
     */
    public AppointmentReg selectAppointmentRegById(Long id);

    /**
     * 查询医院预约登记列表
     * 
     * @param appointmentReg 医院预约登记
     * @return 医院预约登记集合
     */
    public List<AppointmentReg> selectAppointmentRegList(AppointmentReg appointmentReg);

    /**
     * 新增医院预约登记
     * 
     * @param appointmentReg 医院预约登记
     * @return 结果
     */
    public int insertAppointmentReg(AppointmentReg appointmentReg);

    /**
     * 修改医院预约登记
     * 
     * @param appointmentReg 医院预约登记
     * @return 结果
     */
    public int updateAppointmentReg(AppointmentReg appointmentReg);

    /**
     * 批量删除医院预约登记
     * 
     * @param ids 需要删除的数据ID
     * @return 结果
     */
    public int deleteAppointmentRegByIds(String ids);

    /**
     * 删除医院预约登记信息
     * 
     * @param id 医院预约登记ID
     * @return 结果
     */
    public int deleteAppointmentRegById(Long id);
}
