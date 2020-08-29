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
import com.ruoyi.his.domain.LeaveHosPay;
import com.ruoyi.his.service.ILeaveHosPayService;
import com.ruoyi.common.core.controller.BaseController;
import com.ruoyi.common.core.domain.AjaxResult;
import com.ruoyi.common.utils.poi.ExcelUtil;
import com.ruoyi.common.core.page.TableDataInfo;

/**
 * 出院结算Controller
 * 
 * @author whl
 * @date 2020-08-29
 */
@Controller
@RequestMapping("/his/leaveHosPay")
public class LeaveHosPayController extends BaseController
{
    private String prefix = "his/leaveHosPay";

    @Autowired
    private ILeaveHosPayService leaveHosPayService;

    @RequiresPermissions("his:leaveHosPay:view")
    @GetMapping()
    public String leaveHosPay()
    {
        return prefix + "/leaveHosPay";
    }

    /**
     * 查询出院结算列表
     */
    @RequiresPermissions("his:leaveHosPay:list")
    @PostMapping("/list")
    @ResponseBody
    public TableDataInfo list(LeaveHosPay leaveHosPay)
    {
        startPage();
        List<LeaveHosPay> list = leaveHosPayService.selectLeaveHosPayList(leaveHosPay);
        return getDataTable(list);
    }

    /**
     * 导出出院结算列表
     */
    @RequiresPermissions("his:leaveHosPay:export")
    @Log(title = "出院结算", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    @ResponseBody
    public AjaxResult export(LeaveHosPay leaveHosPay)
    {
        List<LeaveHosPay> list = leaveHosPayService.selectLeaveHosPayList(leaveHosPay);
        ExcelUtil<LeaveHosPay> util = new ExcelUtil<LeaveHosPay>(LeaveHosPay.class);
        return util.exportExcel(list, "leaveHosPay");
    }

    /**
     * 新增出院结算
     */
    @GetMapping("/add")
    public String add()
    {
        return prefix + "/add";
    }

    /**
     * 新增保存出院结算
     */
    @RequiresPermissions("his:leaveHosPay:add")
    @Log(title = "出院结算", businessType = BusinessType.INSERT)
    @PostMapping("/add")
    @ResponseBody
    public AjaxResult addSave(LeaveHosPay leaveHosPay)
    {
        return toAjax(leaveHosPayService.insertLeaveHosPay(leaveHosPay));
    }

    /**
     * 修改出院结算
     */
    @GetMapping("/edit/{id}")
    public String edit(@PathVariable("id") Integer id, ModelMap mmap)
    {
        LeaveHosPay leaveHosPay = leaveHosPayService.selectLeaveHosPayById(id);
        mmap.put("leaveHosPay", leaveHosPay);
        return prefix + "/edit";
    }

    /**
     * 修改保存出院结算
     */
    @RequiresPermissions("his:leaveHosPay:edit")
    @Log(title = "出院结算", businessType = BusinessType.UPDATE)
    @PostMapping("/edit")
    @ResponseBody
    public AjaxResult editSave(LeaveHosPay leaveHosPay)
    {
        return toAjax(leaveHosPayService.updateLeaveHosPay(leaveHosPay));
    }

    /**
     * 删除出院结算
     */
    @RequiresPermissions("his:leaveHosPay:remove")
    @Log(title = "出院结算", businessType = BusinessType.DELETE)
    @PostMapping( "/remove")
    @ResponseBody
    public AjaxResult remove(String ids)
    {
        return toAjax(leaveHosPayService.deleteLeaveHosPayByIds(ids));
    }
}
