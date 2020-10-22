package com.ruoyi.web.controller.his.controller;

import java.util.List;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import com.ruoyi.common.annotation.Log;
import com.ruoyi.common.enums.BusinessType;
import com.ruoyi.his.domain.AppointmentReg;
import com.ruoyi.his.service.IAppointmentRegService;
import com.ruoyi.common.core.controller.BaseController;
import com.ruoyi.common.core.domain.AjaxResult;
import com.ruoyi.common.utils.poi.ExcelUtil;
import com.ruoyi.common.core.page.TableDataInfo;

/**
 * 医院预约登记Controller
 * 
 * @author whl
 * @date 2020-10-22
 */
@Controller
@RequestMapping("/his/appointment")
public class AppointmentRegController extends BaseController
{
    private String prefix = "his/appointment";

    @Autowired
    private IAppointmentRegService appointmentRegService;

    @RequiresPermissions("his:appointment:view")
    @GetMapping()
    public String appointment()
    {
        return prefix + "/appointment";
    }

    /**
     * 查询医院预约登记列表
     */
    @RequiresPermissions("his:appointment:list")
    @PostMapping("/list")
    @ResponseBody
    public TableDataInfo list(AppointmentReg appointmentReg)
    {
        startPage();
        List<AppointmentReg> list = appointmentRegService.selectAppointmentRegList(appointmentReg);
        return getDataTable(list);
    }

    /**
     * 导出医院预约登记列表
     */
    @RequiresPermissions("his:appointment:export")
    @Log(title = "医院预约登记", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    @ResponseBody
    public AjaxResult export(AppointmentReg appointmentReg)
    {
        List<AppointmentReg> list = appointmentRegService.selectAppointmentRegList(appointmentReg);
        ExcelUtil<AppointmentReg> util = new ExcelUtil<AppointmentReg>(AppointmentReg.class);
        return util.exportExcel(list, "appointment");
    }

    /**
     * 新增医院预约登记
     */
    @GetMapping("/add")
    public String add()
    {
        return prefix + "/add";
    }

    /**
     * 新增保存医院预约登记
     */
    @RequiresPermissions("his:appointment:add")
    @Log(title = "医院预约登记", businessType = BusinessType.INSERT)
    @PostMapping("/add")
    @ResponseBody
    public AjaxResult addSave(AppointmentReg appointmentReg)
    {
        return toAjax(appointmentRegService.insertAppointmentReg(appointmentReg));
    }

    /**
     * 修改医院预约登记
     */
    @GetMapping("/edit/{id}")
    public String edit(@PathVariable("id") Long id, ModelMap mmap)
    {
        AppointmentReg appointmentReg = appointmentRegService.selectAppointmentRegById(id);
        mmap.put("appointmentReg", appointmentReg);
        return prefix + "/edit";
    }

    /**
     * 修改保存医院预约登记
     */
    @RequiresPermissions("his:appointment:edit")
    @Log(title = "医院预约登记", businessType = BusinessType.UPDATE)
    @PostMapping("/edit")
    @ResponseBody
    public AjaxResult editSave(AppointmentReg appointmentReg)
    {
        return toAjax(appointmentRegService.updateAppointmentReg(appointmentReg));
    }

    /**
     * 删除医院预约登记
     */
    @RequiresPermissions("his:appointment:remove")
    @Log(title = "医院预约登记", businessType = BusinessType.DELETE)
    @PostMapping( "/remove")
    @ResponseBody
    public AjaxResult remove(String ids)
    {
        return toAjax(appointmentRegService.deleteAppointmentRegByIds(ids));
    }
}
