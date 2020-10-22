package com.ruoyi.web.controller.his.api;

import com.ruoyi.common.annotation.Log;
import com.ruoyi.common.core.controller.BaseController;
import com.ruoyi.common.core.domain.AjaxResult;
import com.ruoyi.common.core.page.TableDataInfo;
import com.ruoyi.common.enums.BusinessType;
import com.ruoyi.common.utils.DateUtils;
import com.ruoyi.common.utils.poi.ExcelUtil;
import com.ruoyi.his.constant.HisBusinessTypeEnum;
import com.ruoyi.his.domain.AppointmentReg;
import com.ruoyi.his.remote.AbstractHisServiceHandler;
import com.ruoyi.his.remote.response.BaseResponse;
import com.ruoyi.his.service.IAppointmentRegService;
import com.ruoyi.system.service.ISysConfigService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.apache.commons.collections.CollectionUtils;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.apache.shiro.crypto.hash.Hash;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 医院预约登记Controller
 * 
 * @author whl
 * @date 2020-10-22
 */
@RestController
@Api(value = "His预约登记接口", tags = {"His预约登记接口"})
@RequestMapping("/his/appointment")
@CrossOrigin
public class HisAppointmentApi extends BaseController
{
    @Autowired
    private ISysConfigService configService;
    @Autowired
    private IAppointmentRegService appointmentRegService;
    /**
     * 2020.8.26
     * His预约登记接口
     *
     * @return
     */
    @Log(title = "his本地调用", businessType = BusinessType.HIS_LOCALHOST)
    @ApiOperation("预约登记接口配置")
    @ResponseBody
    @GetMapping(value = "/getConfig")
    public AjaxResult getAppointmentInfo(){
        String maxNum = configService.selectConfigByKey("his.appointment.max");
        String tips = configService.selectConfigByKey("his.appointment.tips");
        Map<String,Object> map = new HashMap<String,Object>();
        map.put("maxNum",maxNum);
        map.put("tips",tips);
        return AjaxResult.success(map);
    }

    @Log(title = "his本地调用", businessType = BusinessType.HIS_LOCALHOST)
    @ApiOperation("预约登记接口保存")
    @ResponseBody
    @PostMapping(value = "/save")
    public AjaxResult saveAppointment(@RequestBody AppointmentReg appointmentReg){
        String currentDay = DateUtils.parseDateToStr("yyyy-MM-dd",appointmentReg.getAppointmentTime());
        //检查当天是否已经预约过了
        List<AppointmentReg> lstAppointmentReg=appointmentRegService.selectAppointmentRegList(appointmentReg);
        if(CollectionUtils.isNotEmpty(lstAppointmentReg)){
            return AjaxResult.error("您当天["+currentDay+"]已经预约，请勿重新预约登记。");
        }
        //检查当天是否已经超过记录
        String maxNum = configService.selectConfigByKey("his.appointment.max");
        String startDate = currentDay+" 00:00:00";
        String endDate = currentDay+" 23:59:59";
        int currentNum = appointmentRegService.getCountByDay(startDate,endDate);
        if(currentNum >= Integer.valueOf(maxNum)){
            return AjaxResult.error("您预约的时间["+currentDay+"]当天预约人数已满，请选择其它时间预约。");
        }


        appointmentReg.setCreateBy(appointmentReg.getName());
        appointmentReg.setUpdateBy(appointmentReg.getName());
        appointmentReg.setCreateTime(DateUtils.getNowDate());
        appointmentReg.setUpdateTime(DateUtils.getNowDate());
        int result = appointmentRegService.insertAppointmentReg(appointmentReg);
        return result>0?AjaxResult.success("预约登记成功"):AjaxResult.error("预约登记失败");
    }
}
