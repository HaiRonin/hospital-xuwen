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
import com.ruoyi.his.domain.DepositPayment;
import com.ruoyi.his.service.IDepositPaymentService;
import com.ruoyi.common.core.controller.BaseController;
import com.ruoyi.common.core.domain.AjaxResult;
import com.ruoyi.common.utils.poi.ExcelUtil;
import com.ruoyi.common.core.page.TableDataInfo;

/**
 * 押金补缴Controller
 * 
 * @author whl
 * @date 2020-08-29
 */
@Controller
@RequestMapping("/his/payment")
public class DepositPaymentController extends BaseController
{
    private String prefix = "his/payment";

    @Autowired
    private IDepositPaymentService depositPaymentService;

    @RequiresPermissions("his:payment:view")
    @GetMapping()
    public String payment()
    {
        return prefix + "/payment";
    }

    /**
     * 查询押金补缴列表
     */
    @RequiresPermissions("his:payment:list")
    @PostMapping("/list")
    @ResponseBody
    public TableDataInfo list(DepositPayment depositPayment)
    {
        startPage();
        List<DepositPayment> list = depositPaymentService.selectDepositPaymentList(depositPayment);
        return getDataTable(list);
    }

    /**
     * 导出押金补缴列表
     */
    @RequiresPermissions("his:payment:export")
    @Log(title = "押金补缴", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    @ResponseBody
    public AjaxResult export(DepositPayment depositPayment)
    {
        List<DepositPayment> list = depositPaymentService.selectDepositPaymentList(depositPayment);
        ExcelUtil<DepositPayment> util = new ExcelUtil<DepositPayment>(DepositPayment.class);
        return util.exportExcel(list, "payment");
    }

    /**
     * 新增押金补缴
     */
    @GetMapping("/add")
    public String add()
    {
        return prefix + "/add";
    }

    /**
     * 新增保存押金补缴
     */
    @RequiresPermissions("his:payment:add")
    @Log(title = "押金补缴", businessType = BusinessType.INSERT)
    @PostMapping("/add")
    @ResponseBody
    public AjaxResult addSave(DepositPayment depositPayment)
    {
        return toAjax(depositPaymentService.insertDepositPayment(depositPayment));
    }

    /**
     * 修改押金补缴
     */
    @GetMapping("/edit/{id}")
    public String edit(@PathVariable("id") Long id, ModelMap mmap)
    {
        DepositPayment depositPayment = depositPaymentService.selectDepositPaymentById(id);
        mmap.put("depositPayment", depositPayment);
        return prefix + "/edit";
    }

    /**
     * 修改保存押金补缴
     */
    @RequiresPermissions("his:payment:edit")
    @Log(title = "押金补缴", businessType = BusinessType.UPDATE)
    @PostMapping("/edit")
    @ResponseBody
    public AjaxResult editSave(DepositPayment depositPayment)
    {
        return toAjax(depositPaymentService.updateDepositPayment(depositPayment));
    }

    /**
     * 删除押金补缴
     */
    @RequiresPermissions("his:payment:remove")
    @Log(title = "押金补缴", businessType = BusinessType.DELETE)
    @PostMapping( "/remove")
    @ResponseBody
    public AjaxResult remove(String ids)
    {
        return toAjax(depositPaymentService.deleteDepositPaymentByIds(ids));
    }
}
