package com.ruoyi.web.controller.his.controller;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import com.ruoyi.his.constant.BodySymptomsEnum;
import com.ruoyi.system.domain.SysDictData;
import com.ruoyi.vo.SymptomsOrganExd;
import io.swagger.annotations.ApiOperation;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;
import com.ruoyi.common.annotation.Log;
import com.ruoyi.common.enums.BusinessType;
import com.ruoyi.his.domain.SymptomsOrgan;
import com.ruoyi.his.service.ISymptomsOrganService;
import com.ruoyi.common.core.controller.BaseController;
import com.ruoyi.common.core.domain.AjaxResult;
import com.ruoyi.common.utils.poi.ExcelUtil;
import com.ruoyi.common.core.page.TableDataInfo;

/**
 * 身体分类与科室Controller
 * 
 * @author whl
 * @date 2020-08-16
 */
@Controller
@RequestMapping("/his/symptomsOrgan")
public class SymptomsOrganController extends BaseController
{
    private String prefix = "his/symptomsOrgan";

    @Autowired
    private ISymptomsOrganService symptomsOrganService;

    @RequiresPermissions("his:symptomsOrgan:view")
    @GetMapping()
    public String symptomsOrgan()
    {
        return prefix + "/symptomsOrgan";
    }

    /**
     * 查询身体分类与科室列表
     */
    @RequiresPermissions("his:symptomsOrgan:list")
    @PostMapping("/list")
    @ResponseBody
    public TableDataInfo list(SymptomsOrgan symptomsOrgan)
    {
        startPage();
        List<SymptomsOrgan> list = symptomsOrganService.selectSymptomsOrganList(symptomsOrgan);
        return getDataTable(list);
    }

    /**
     * 导出身体分类与科室列表
     */
    @RequiresPermissions("his:symptomsOrgan:export")
    @Log(title = "身体分类与科室", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    @ResponseBody
    public AjaxResult export(SymptomsOrgan symptomsOrgan)
    {
        List<SymptomsOrgan> list = symptomsOrganService.selectSymptomsOrganList(symptomsOrgan);
        ExcelUtil<SymptomsOrgan> util = new ExcelUtil<SymptomsOrgan>(SymptomsOrgan.class);
        return util.exportExcel(list, "symptomsOrgan");
    }

    /**
     * 新增身体分类与科室
     */
    @GetMapping("/add")
    public String add()
    {
        return prefix + "/add";
    }

    /**
     * 新增保存身体分类与科室
     */
    @RequiresPermissions("his:symptomsOrgan:add")
    @Log(title = "身体分类与科室", businessType = BusinessType.INSERT)
    @PostMapping("/add")
    @ResponseBody
    public AjaxResult addSave(SymptomsOrgan symptomsOrgan)
    {
        return toAjax(symptomsOrganService.insertSymptomsOrgan(symptomsOrgan));
    }

    /**
     * 修改身体分类与科室
     */
    @GetMapping("/edit/{id}")
    public String edit(@PathVariable("id") Integer id, ModelMap mmap)
    {
        SymptomsOrgan symptomsOrgan = symptomsOrganService.selectSymptomsOrganById(id);
        mmap.put("symptomsOrgan", symptomsOrgan);
        return prefix + "/edit";
    }

    /**
     * 修改保存身体分类与科室
     */
    @RequiresPermissions("his:symptomsOrgan:edit")
    @Log(title = "身体分类与科室", businessType = BusinessType.UPDATE)
    @PostMapping("/edit")
    @ResponseBody
    public AjaxResult editSave(SymptomsOrgan symptomsOrgan)
    {
        return toAjax(symptomsOrganService.updateSymptomsOrgan(symptomsOrgan));
    }

    /**
     * 删除身体分类与科室
     */
    @RequiresPermissions("his:symptomsOrgan:remove")
    @Log(title = "身体分类与科室", businessType = BusinessType.DELETE)
    @PostMapping( "/remove")
    @ResponseBody
    public AjaxResult remove(String ids)
    {
        return toAjax(symptomsOrganService.deleteSymptomsOrganByIds(ids));
    }

}
