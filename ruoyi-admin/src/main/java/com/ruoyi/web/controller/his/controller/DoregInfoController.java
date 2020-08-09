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
import com.ruoyi.his.domain.DoregInfo;
import com.ruoyi.his.service.IDoregInfoService;
import com.ruoyi.common.core.controller.BaseController;
import com.ruoyi.common.core.domain.AjaxResult;
import com.ruoyi.common.utils.poi.ExcelUtil;
import com.ruoyi.common.core.page.TableDataInfo;

/**
 * 预约挂号Controller
 * 
 * @author whl
 * @date 2020-08-08
 */
@Controller
@RequestMapping("/his/doreg")
public class DoregInfoController extends BaseController
{
    private String prefix = "his/doreg";

    @Autowired
    private IDoregInfoService doregInfoService;

    @RequiresPermissions("his:doreg:view")
    @GetMapping()
    public String doreg()
    {
        return prefix + "/doreg";
    }

    /**
     * 查询预约挂号列表
     */
    @RequiresPermissions("his:doreg:list")
    @PostMapping("/list")
    @ResponseBody
    public TableDataInfo list(DoregInfo doregInfo)
    {
        startPage();
        List<DoregInfo> list = doregInfoService.selectDoregInfoList(doregInfo);
        return getDataTable(list);
    }

    /**
     * 导出预约挂号列表
     */
    @RequiresPermissions("his:doreg:export")
    @Log(title = "预约挂号", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    @ResponseBody
    public AjaxResult export(DoregInfo doregInfo)
    {
        List<DoregInfo> list = doregInfoService.selectDoregInfoList(doregInfo);
        ExcelUtil<DoregInfo> util = new ExcelUtil<DoregInfo>(DoregInfo.class);
        return util.exportExcel(list, "doreg");
    }

    /**
     * 新增预约挂号
     */
    @GetMapping("/add")
    public String add()
    {
        return prefix + "/add";
    }

    /**
     * 新增保存预约挂号
     */
    @RequiresPermissions("his:doreg:add")
    @Log(title = "预约挂号", businessType = BusinessType.INSERT)
    @PostMapping("/add")
    @ResponseBody
    public AjaxResult addSave(DoregInfo doregInfo)
    {
        return toAjax(doregInfoService.insertDoregInfo(doregInfo));
    }

    /**
     * 修改预约挂号
     */
    @GetMapping("/edit/{id}")
    public String edit(@PathVariable("id") Long id, ModelMap mmap)
    {
        DoregInfo doregInfo = doregInfoService.selectDoregInfoById(id);
        mmap.put("doregInfo", doregInfo);
        return prefix + "/edit";
    }

    /**
     * 修改保存预约挂号
     */
    @RequiresPermissions("his:doreg:edit")
    @Log(title = "预约挂号", businessType = BusinessType.UPDATE)
    @PostMapping("/edit")
    @ResponseBody
    public AjaxResult editSave(DoregInfo doregInfo)
    {
        return toAjax(doregInfoService.updateDoregInfo(doregInfo));
    }

    /**
     * 删除预约挂号
     */
    @RequiresPermissions("his:doreg:remove")
    @Log(title = "预约挂号", businessType = BusinessType.DELETE)
    @PostMapping( "/remove")
    @ResponseBody
    public AjaxResult remove(String ids)
    {
        return toAjax(doregInfoService.deleteDoregInfoByIds(ids));
    }
}
