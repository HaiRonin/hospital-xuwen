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
import com.ruoyi.his.domain.DopayInfo;
import com.ruoyi.his.service.IDopayInfoService;
import com.ruoyi.common.core.controller.BaseController;
import com.ruoyi.common.core.domain.AjaxResult;
import com.ruoyi.common.utils.poi.ExcelUtil;
import com.ruoyi.common.core.page.TableDataInfo;

/**
 * 门诊支付Controller
 * 
 * @author whl
 * @date 2020-08-29
 */
@Controller
@RequestMapping("/his/payInfo")
public class DopayInfoController extends BaseController
{
    private String prefix = "his/payInfo";

    @Autowired
    private IDopayInfoService dopayInfoService;

    @RequiresPermissions("his:payInfo:view")
    @GetMapping()
    public String payInfo()
    {
        return prefix + "/payInfo";
    }

    /**
     * 查询门诊支付列表
     */
    @RequiresPermissions("his:payInfo:list")
    @PostMapping("/list")
    @ResponseBody
    public TableDataInfo list(DopayInfo dopayInfo)
    {
        startPage();
        List<DopayInfo> list = dopayInfoService.selectDopayInfoList(dopayInfo);
        return getDataTable(list);
    }

    /**
     * 导出门诊支付列表
     */
    @RequiresPermissions("his:payInfo:export")
    @Log(title = "门诊支付", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    @ResponseBody
    public AjaxResult export(DopayInfo dopayInfo)
    {
        List<DopayInfo> list = dopayInfoService.selectDopayInfoList(dopayInfo);
        ExcelUtil<DopayInfo> util = new ExcelUtil<DopayInfo>(DopayInfo.class);
        return util.exportExcel(list, "payInfo");
    }

    /**
     * 新增门诊支付
     */
    @GetMapping("/add")
    public String add()
    {
        return prefix + "/add";
    }

    /**
     * 新增保存门诊支付
     */
    @RequiresPermissions("his:payInfo:add")
    @Log(title = "门诊支付", businessType = BusinessType.INSERT)
    @PostMapping("/add")
    @ResponseBody
    public AjaxResult addSave(DopayInfo dopayInfo)
    {
        return toAjax(dopayInfoService.insertDopayInfo(dopayInfo));
    }

    /**
     * 修改门诊支付
     */
    @GetMapping("/edit/{id}")
    public String edit(@PathVariable("id") Long id, ModelMap mmap)
    {
        DopayInfo dopayInfo = dopayInfoService.selectDopayInfoById(id);
        mmap.put("dopayInfo", dopayInfo);
        return prefix + "/edit";
    }

    /**
     * 修改保存门诊支付
     */
    @RequiresPermissions("his:payInfo:edit")
    @Log(title = "门诊支付", businessType = BusinessType.UPDATE)
    @PostMapping("/edit")
    @ResponseBody
    public AjaxResult editSave(DopayInfo dopayInfo)
    {
        return toAjax(dopayInfoService.updateDopayInfo(dopayInfo));
    }

    /**
     * 删除门诊支付
     */
    @RequiresPermissions("his:payInfo:remove")
    @Log(title = "门诊支付", businessType = BusinessType.DELETE)
    @PostMapping( "/remove")
    @ResponseBody
    public AjaxResult remove(String ids)
    {
        return toAjax(dopayInfoService.deleteDopayInfoByIds(ids));
    }
}
