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
import com.ruoyi.his.domain.CovOrder;
import com.ruoyi.his.service.ICovOrderService;
import com.ruoyi.common.core.controller.BaseController;
import com.ruoyi.common.core.domain.AjaxResult;
import com.ruoyi.common.utils.poi.ExcelUtil;
import com.ruoyi.common.core.page.TableDataInfo;

/**
 * 核酸检测订单Controller
 * 
 * @author whl
 * @date 2021-02-02
 */
@Controller
@RequestMapping("/his/covOrder")
public class CovOrderController extends BaseController
{
    private String prefix = "his/covOrder";

    @Autowired
    private ICovOrderService covOrderService;

    @RequiresPermissions("his:covOrder:view")
    @GetMapping()
    public String covOrder()
    {
        return prefix + "/covOrder";
    }

    /**
     * 查询核酸检测订单列表
     */
    @RequiresPermissions("his:covOrder:list")
    @PostMapping("/list")
    @ResponseBody
    public TableDataInfo list(CovOrder covOrder)
    {
        startPage();
        List<CovOrder> list = covOrderService.selectCovOrderList(covOrder);
        return getDataTable(list);
    }

    /**
     * 导出核酸检测订单列表
     */
    @RequiresPermissions("his:covOrder:export")
    @Log(title = "核酸检测订单", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    @ResponseBody
    public AjaxResult export(CovOrder covOrder)
    {
        List<CovOrder> list = covOrderService.selectCovOrderList(covOrder);
        ExcelUtil<CovOrder> util = new ExcelUtil<CovOrder>(CovOrder.class);
        return util.exportExcel(list, "covOrder");
    }

    /**
     * 新增核酸检测订单
     */
    @GetMapping("/add")
    public String add()
    {
        return prefix + "/add";
    }

    /**
     * 新增保存核酸检测订单
     */
    @RequiresPermissions("his:covOrder:add")
    @Log(title = "核酸检测订单", businessType = BusinessType.INSERT)
    @PostMapping("/add")
    @ResponseBody
    public AjaxResult addSave(CovOrder covOrder)
    {
        return toAjax(covOrderService.insertCovOrder(covOrder));
    }

    /**
     * 修改核酸检测订单
     */
    @GetMapping("/edit/{id}")
    public String edit(@PathVariable("id") Long id, ModelMap mmap)
    {
        CovOrder covOrder = covOrderService.selectCovOrderById(id);
        mmap.put("covOrder", covOrder);
        return prefix + "/edit";
    }

    /**
     * 修改保存核酸检测订单
     */
    @RequiresPermissions("his:covOrder:edit")
    @Log(title = "核酸检测订单", businessType = BusinessType.UPDATE)
    @PostMapping("/edit")
    @ResponseBody
    public AjaxResult editSave(CovOrder covOrder)
    {
        return toAjax(covOrderService.updateCovOrder(covOrder));
    }

    /**
     * 删除核酸检测订单
     */
    @RequiresPermissions("his:covOrder:remove")
    @Log(title = "核酸检测订单", businessType = BusinessType.DELETE)
    @PostMapping( "/remove")
    @ResponseBody
    public AjaxResult remove(String ids)
    {
        return toAjax(covOrderService.deleteCovOrderByIds(ids));
    }
}
