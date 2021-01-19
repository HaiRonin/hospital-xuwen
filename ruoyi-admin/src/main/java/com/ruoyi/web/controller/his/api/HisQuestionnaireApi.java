package com.ruoyi.web.controller.his.api;

import com.ruoyi.common.core.controller.BaseController;
import com.ruoyi.common.core.domain.AjaxResult;
import com.ruoyi.system.domain.SysDictData;
import com.ruoyi.system.service.ISysConfigService;
import com.ruoyi.system.service.ISysDictDataService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.apache.commons.collections.CollectionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * 医院新冠问卷调查Controller
 * 
 * @author whl
 * @date 2021-01-22
 */
@RestController
@Api(value = "医院新冠问卷调查", tags = {"医院新冠问卷调查"})
@RequestMapping("/his/questionnaire")
@CrossOrigin
public class HisQuestionnaireApi extends BaseController
{
    @Autowired
    private ISysConfigService configService;
    @Autowired
    private ISysDictDataService dictDataService;
    /**
     * 2021.1.22
     * 问卷调查接口配置
     *
     * @return
     */
    @ApiOperation("问卷调查接口配置")
    @ResponseBody
    @GetMapping(value = "/getConfig")
    public AjaxResult getAppointmentInfo(){
        Map<String,Object> map = new HashMap<String,Object>();
        String state = configService.selectConfigByKey("his.questionnaire.xinguan");
        map.put("state",state);
        map.put("desc","state=1-内测,白名单可访问,state=2-正式开放,用户可访问；state=3-关闭下架,任何人不可访问");
        //启用或者关闭
        if("2".equals(state) || "3".equals(state)){
            return AjaxResult.success(map);
        }
        //内测状态
        else{
            SysDictData dictData = new SysDictData();
            dictData.setDictType("his.white.phone.list");
            List<SysDictData> lstSysDictData = dictDataService.selectDictDataList(dictData);
            if(CollectionUtils.isNotEmpty(lstSysDictData)){
                List<String> phones = lstSysDictData.stream().map(p -> p.getDictValue()).collect(Collectors.toList());
                map.put("whiteList",phones);
            }
        }
        return AjaxResult.success(map);
    }
}
