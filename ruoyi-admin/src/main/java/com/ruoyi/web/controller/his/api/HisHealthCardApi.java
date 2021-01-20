package com.ruoyi.web.controller.his.api;

import com.alibaba.fastjson.JSON;
import com.ruoyi.common.annotation.Log;
import com.ruoyi.common.core.controller.BaseController;
import com.ruoyi.common.core.domain.AjaxResult;
import com.ruoyi.common.enums.BusinessType;
import com.ruoyi.common.utils.RedisUtil;
import com.ruoyi.common.utils.ServletUtils;
import com.ruoyi.common.utils.StringUtils;
import com.ruoyi.his.callservice.HisBaseServices;
import com.ruoyi.his.remote.HealthCardService;
import com.ruoyi.his.remote.request.healthcard.DynamicQRCodeResquest;
import com.ruoyi.his.remote.request.healthcard.RegisterResquest;
import com.ruoyi.his.remote.request.healthcard.ReportDataResquest;
import com.ruoyi.his.remote.response.healthcard.CardGetResponse;
import com.ruoyi.his.remote.response.healthcard.DynamicQRCodeResponse;
import com.ruoyi.his.remote.response.healthcard.OcrInfoResponse;
import com.ruoyi.his.remote.response.healthcard.RegisterResponse;
import com.ruoyi.his.service.ISmsService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

/**
 * @author whl
 * @date 2020-08-08
 */
@RestController
@Api(value = "His健康卡接口", tags = {"His健康卡接口"})
@RequestMapping("/his/healthCard")
@CrossOrigin
public class HisHealthCardApi extends BaseController {
    @Autowired
    private HealthCardService healthCardService;
    @Autowired
    private ISmsService smsService;
    @Autowired
    private HisBaseServices hisBaseServices;
    @Autowired
    private RedisUtil redisUtil;
    //健康二维码缓存KEY
    private static final String HEALTH_CARD_QRCODE_IMG_CACHE = "HEALTH_CARD_QRCODE_IMG_";
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
    public AjaxResult registerHealthCard(@RequestBody RegisterResquest registerResquest) {
//        if(StringUtils.isEmpty(registerResquest.getSmsCode())){
//            return AjaxResult.error("请输入验证码");
//        }
//        if(!smsService.checkVerificationCode(registerResquest.getPhone1(),registerResquest.getSmsCode())){
//            return AjaxResult.error("短信验证码不通过");
//        }
        RegisterResponse response = null;
        try {
            response = healthCardService.registerHealthCard(registerResquest);
            //健康码创建失败，不能建档
        } catch (Exception e) {
            logger.error(">>>>>>>>创建健康码异常：" + e.getMessage(), e);
            return AjaxResult.error(e.getMessage());
        }
        try {
            String result = addPatients(response, registerResquest);
            if(!"00".equals(JSON.parseObject(result).getString("resultCode"))) {
                return AjaxResult.error("操作失败");
            }
        } catch (Exception e) {
            logger.error(">>>>>>>>添加健康卡-就诊人异常：" + e.getMessage(), e);
            return AjaxResult.error("操作失败");
        }
        return AjaxResult.success("操作成功");
    }

    /**
     * 注册HIS就诊人
     *
     * @param response
     * @param registerResquest
     */
    private String addPatients(RegisterResponse response, RegisterResquest registerResquest) {
        String healthCardId = "";
        String qrCode = "";
        String phid = "";
        if (null != response) {
            healthCardId = response.getHealthCardId();
            qrCode = response.getQrCodeText();
            phid = response.getPhid();
        }
        Map<String, Object> dataParam = new HashMap<String, Object>();
        dataParam.put("synUserName", registerResquest.getSynUserName());
        dataParam.put("synKey", "");
        dataParam.put("UserName", registerResquest.getSynUserName());
        dataParam.put("Mobile", registerResquest.getPhone1());
        dataParam.put("Sex", "男".equals(registerResquest.getGender()) ? "1" : "0");
        dataParam.put("CardNo", null==registerResquest.getCardNo()?"":registerResquest.getCardNo());
        dataParam.put("Name", registerResquest.getName());
        dataParam.put("IDCardno", registerResquest.getIdNumber());
        dataParam.put("address", null==registerResquest.getAddress()?"":registerResquest.getAddress());
        dataParam.put("HealthyCardNo", null==healthCardId?"":healthCardId);
        dataParam.put("QrCode", null==qrCode?"":qrCode);
        dataParam.put("EMPI", null==phid?"":phid);
        logger.info(">>>>>>>>添加健康卡-就诊人入参：" + JSON.toJSONString(dataParam));
        String result = hisBaseServices.requestHisService("/AddPatients", JSON.toJSONString(dataParam));
        logger.info(">>>>>>>>添加健康卡-就诊人结果：" + result);
        return result;
    }


    /**
     * 2020.8.26
     * 获取健康码 17E578A4C7DAE1E49DEC2A70E6AA179B
     *
     * @return
     */
//    @Log(title = "his本地调用", businessType = BusinessType.HIS_LOCALHOST)
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
//    @Log(title = "his本地调用", businessType = BusinessType.HIS_LOCALHOST)
    @ApiOperation("获取健康卡二维码接口")
    @ResponseBody
    @PostMapping(value = "/getDynamicQRCode")
    public AjaxResult registerHealthCard(@RequestBody DynamicQRCodeResquest dynamicQRCodeResquest) {
        String cacheKey = HEALTH_CARD_QRCODE_IMG_CACHE + dynamicQRCodeResquest.getHealthCardId();
        if (null != redisUtil.get(cacheKey)) {
            logger.info(">>>>>>>>>>从缓存获取健康码：" + cacheKey);
            DynamicQRCodeResponse response = (DynamicQRCodeResponse)redisUtil.get(cacheKey);
            return AjaxResult.success(JSON.toJSONString(response));
        }
        DynamicQRCodeResponse response = healthCardService.getDynamicQRCode(dynamicQRCodeResquest);
        if (StringUtils.isNotEmpty(response.getQrCodeImg())) {
            redisUtil.set(cacheKey, response, 24 * 3600);
        }
        return AjaxResult.success(JSON.toJSONString(response));
    }

    /**
     * 验证码短信
     *
     * @return
     */
//    @Log(title = "his本地调用", businessType = BusinessType.HIS)
    @GetMapping("/sendCode")
    @ResponseBody
    @ApiOperation("获取验证码短信")
    public AjaxResult sendCode(@RequestParam("phone") @Validated String phone) {
        ServletUtils.getRequest().setAttribute("api", "/user/sendMsg");
        ServletUtils.getRequest().setAttribute("dataParam", phone);
        String msg ="【广东省农垦中心医院】您的验证码是:%1$s";
        return smsService.sendVerificationCode(phone,msg);
    }

    /**
     * 2020.9.26
     * 身份证照片OCR接口
     *
     * @return
     */
//    @Log(title = "his本地调用", businessType = BusinessType.HIS_LOCALHOST)
    @ApiOperation("身份证照片OCR接口")
    @ResponseBody
    @PostMapping(value = "/ocrInfo")
    public AjaxResult ocrInfo(String imageContent) {
        OcrInfoResponse response = healthCardService.getOcrInfo(imageContent);
        return AjaxResult.success(response);
    }

    /**
     * 2020.9.26
     * 获取卡包订单ID接口
     *
     * @return
     */
//    @Log(title = "his本地调用", businessType = BusinessType.HIS_LOCALHOST)
    @ApiOperation("获取卡包订单ID接口")
    @ResponseBody
    @PostMapping(value = "/getOrderIdByOutAppId")
    public AjaxResult getOrderIdByOutAppId(String imageContent) {
        String orderId = healthCardService.getOrderIdByOutAppId(imageContent);
        return AjaxResult.success(orderId);
    }

    /**
     * 2020.10.26
     * 用卡数据监测上报
     *
     * @return
     */
//    @Log(title = "his本地调用", businessType = BusinessType.HIS_LOCALHOST)
    @ApiOperation("用卡数据监测上报")
    @ResponseBody
    @PostMapping(value = "/reportHISData")
    public AjaxResult reportHISData(@RequestBody ReportDataResquest reportDataResquest) {
        healthCardService.reportHISData(reportDataResquest);
        return AjaxResult.success();
    }
}