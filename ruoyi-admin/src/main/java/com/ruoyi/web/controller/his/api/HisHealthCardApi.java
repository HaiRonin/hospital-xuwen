package com.ruoyi.web.controller.his.api;

import com.alibaba.fastjson.JSON;
import com.ruoyi.common.annotation.Log;
import com.ruoyi.common.core.controller.BaseController;
import com.ruoyi.common.core.domain.AjaxResult;
import com.ruoyi.common.enums.BusinessType;
import com.ruoyi.his.remote.HealthCardService;
import com.ruoyi.his.remote.request.healthcard.DynamicQRCodeResquest;
import com.ruoyi.his.remote.request.healthcard.RegisterResquest;
import com.ruoyi.his.remote.response.healthcard.CardGetResponse;
import com.ruoyi.his.remote.response.healthcard.DynamicQRCodeResponse;
import com.ruoyi.his.remote.response.healthcard.RegisterResponse;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 *
 * 
 * @author whl
 * @date 2020-08-08
 */
@RestController
@Api(value="His健康卡接口",tags={"His健康卡接口"})
@RequestMapping("/his/healthCard")
public class HisHealthCardApi extends BaseController
{
    @Autowired
    private HealthCardService healthCardService;

    /**
     * 2020.8.26
     * 测试预约挂号推his
     *{
     *   "address": "深圳龙华新区白石龙",
     *   "adminExt": "wdwlp999@163.com",
     *   "birthday": "1984-12-06",
     *   "gender": "男",
     *   "idNumber": "450922198412064615",
     *   "idType": "01",
     *   "isCheck": 0,
     *   "name": "吴海浪",
     *   "nation": "汉族",
     *   "phone1": "15919865119",
     *   "wechatCode": "071FoDkl2sDEA54N8enl2HLUYD3FoDkn"
     * }
     * @return
     */
    @Log(title = "his本地调用", businessType = BusinessType.HIS_LOCALHOST)
    @ApiOperation("注册健康码")
    @ResponseBody
    @PostMapping(value = "/registerHealthCard")
    public AjaxResult registerHealthCard(@RequestBody RegisterResquest registerResquest){
        RegisterResponse response = healthCardService.registerHealthCard(registerResquest);
        return AjaxResult.success(JSON.toJSONString(response));
    }

    /**
     * 2020.8.26
     * 获取健康码
     *
     * @return
     */
    @Log(title = "his本地调用", businessType = BusinessType.HIS_LOCALHOST)
    @ApiOperation("获取健康码")
    @ResponseBody
    @GetMapping(value = "/getHealthCard")
    public AjaxResult getHealthCard(@RequestParam("healthCode") String healthCode){
        CardGetResponse response = healthCardService.getHealthCardByHealthCode(healthCode);
        return AjaxResult.success(JSON.toJSONString(response));
    }

    /**
     * 2020.8.26
     * 获取健康卡二维码接口
     *
     * @return
     */
    @Log(title = "his本地调用", businessType = BusinessType.HIS_LOCALHOST)
    @ApiOperation("获取健康卡二维码接口")
    @ResponseBody
    @PostMapping(value = "/getDynamicQRCode")
    public AjaxResult registerHealthCard(@RequestBody DynamicQRCodeResquest dynamicQRCodeResquest){
        DynamicQRCodeResponse response = healthCardService.getDynamicQRCode(dynamicQRCodeResquest);
        return AjaxResult.success(JSON.toJSONString(response));
    }
}
