package com.ruoyi.web.controller.his.controller;

import java.util.List;

import com.ruoyi.pay.utils.WeixinLoginUtils;
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
import com.ruoyi.his.domain.HisUser;
import com.ruoyi.his.service.IHisUserService;
import com.ruoyi.common.core.controller.BaseController;
import com.ruoyi.common.core.domain.AjaxResult;
import com.ruoyi.common.utils.poi.ExcelUtil;
import com.ruoyi.common.core.page.TableDataInfo;

/**
 * his用户Controller
 * 
 * @author whl
 * @date 2020-08-29
 */
@Controller
@RequestMapping("/his/user")
public class HisUserController extends BaseController
{
    private String prefix = "his/user";

    @Autowired
    private IHisUserService hisUserService;

    @RequiresPermissions("his:user:view")
    @GetMapping()
    public String user()
    {
        return prefix + "/user";
    }

    /**
     * 查询his用户列表
     */
    @RequiresPermissions("his:user:list")
    @PostMapping("/list")
    @ResponseBody
    public TableDataInfo list(HisUser hisUser)
    {
        startPage();
        List<HisUser> list = hisUserService.selectHisUserList(hisUser);
        return getDataTable(list);
    }

    /**
     * 导出his用户列表
     */
    @RequiresPermissions("his:user:export")
    @Log(title = "his用户", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    @ResponseBody
    public AjaxResult export(HisUser hisUser)
    {
        List<HisUser> list = hisUserService.selectHisUserList(hisUser);
        ExcelUtil<HisUser> util = new ExcelUtil<HisUser>(HisUser.class);
        return util.exportExcel(list, "user");
    }

    /**
     * 新增his用户
     */
    @GetMapping("/add")
    public String add()
    {
        return prefix + "/add";
    }

    /**
     * 新增保存his用户
     */
    @RequiresPermissions("his:user:add")
    @Log(title = "his用户", businessType = BusinessType.INSERT)
    @PostMapping("/add")
    @ResponseBody
    public AjaxResult addSave(HisUser hisUser)
    {
        return toAjax(hisUserService.insertHisUser(hisUser));
    }

    /**
     * 修改his用户
     */
    @GetMapping("/edit/{id}")
    public String edit(@PathVariable("id") Integer id, ModelMap mmap)
    {
        HisUser hisUser = hisUserService.selectHisUserById(id);
        mmap.put("hisUser", hisUser);
        return prefix + "/edit";
    }

    /**
     * 修改保存his用户
     */
    @RequiresPermissions("his:user:edit")
    @Log(title = "his用户", businessType = BusinessType.UPDATE)
    @PostMapping("/edit")
    @ResponseBody
    public AjaxResult editSave(HisUser hisUser)
    {
        return toAjax(hisUserService.updateHisUser(hisUser));
    }

    /**
     * 删除his用户
     */
    @RequiresPermissions("his:user:remove")
    @Log(title = "his用户", businessType = BusinessType.DELETE)
    @PostMapping( "/remove")
    @ResponseBody
    public AjaxResult remove(String ids)
    {
        return toAjax(hisUserService.deleteHisUserByIds(ids));
    }

    /**
     * 获取openId
     */
    @Log(title = "获取openId", businessType = BusinessType.HIS)
    @PostMapping( "/getOpenId")
    @ResponseBody
    public AjaxResult getOpenId(String code)
    {
        return AjaxResult.success(WeixinLoginUtils.getOpenIdFromWeixin(code));
    }
}
