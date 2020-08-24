package com.ruoyi.web.controller.his.api;

import com.ruoyi.common.annotation.Log;
import com.ruoyi.common.core.controller.BaseController;
import com.ruoyi.common.core.domain.AjaxResult;
import com.ruoyi.common.enums.BusinessType;
import com.ruoyi.common.utils.BarcodeUtil;
import com.ruoyi.common.utils.StringUtils;
import com.ruoyi.his.constant.BodySymptomsEnum;
import com.ruoyi.his.domain.SymptomsOrgan;
import com.ruoyi.his.remote.HisBaseServices;
import com.ruoyi.his.service.ISmsService;
import com.ruoyi.his.service.ISymptomsOrganService;
import com.ruoyi.system.domain.SysDictData;
import com.ruoyi.system.service.ISysDictDataService;
import com.ruoyi.vo.BodyPart;
import com.ruoyi.vo.SymptomsOrganExd;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

/**
 * 预约挂号Controller
 * 
 * @author whl
 * @date 2020-08-08
 */
@RestController
@Api("his接口")
@RequestMapping("/his")
public class HisCommonApi
{

    @Autowired
    private HisBaseServices hisBaseServices;
    @Autowired
    private ISmsService smsService;
    @Autowired
    private ISysDictDataService  dictDataService;
    @Autowired
    private ISymptomsOrganService symptomsOrganService;
    /**
     * his接口调用
     */
    @Log(title = "his接口调用", businessType = BusinessType.HIS)
    @ApiOperation("his接口调用")
    @GetMapping("/request")
    @ResponseBody
    @Cacheable(value="#api", key="#dataParam")
    public String invokeCall(@RequestParam("api") String api,@RequestParam("dataParam") String dataParam)
    {
        return hisBaseServices.requestHisService("/"+api.trim(),dataParam);
    }


    /**
     * 验证码短信
     *
     * @return
     */
    @GetMapping("/user/sendMsg")
    @ResponseBody
    @ApiOperation("获取验证码短信")
    public AjaxResult sendMsg(@RequestParam("phone") String phone)throws Exception  {
        return smsService.sendVerificationCode(phone);
    }

    /**
     * 验证码是否正确
     *
     * @return
     */
    @ApiOperation("验证码校验")
    @GetMapping("/user/verification")
    @ResponseBody
    @Cacheable(value="#phone", key="#verificationCode")
    public AjaxResult verification(@RequestParam("phone") String phone, @RequestParam("verificationCode") String verificationCode)throws Exception  {
        if(StringUtils.isEmpty(verificationCode)){
            return AjaxResult.error("验证码不能为空");
        }
        return smsService.checkVerificationCode(phone,verificationCode);
    }

    /**
     * 发送短信
     *
     * @return
     */
    @ApiOperation("发送短信")
    @GetMapping("/user/shortMessage")
    @ResponseBody
    public AjaxResult shortMessage(@RequestParam("phone") String phone, @RequestParam("message") String message)throws Exception  {
        return smsService.sendSmsMessage(phone,message);
    }


    /**
     * 生成条形码
     * @return
     */
    @ApiOperation("生成条形码")
    @GetMapping("/user/barCode")
    @ResponseBody
    @Cacheable(value="barCode", key="#CardNo")
    public AjaxResult barCode(@RequestParam("CardNo") String CardNo) {
        return AjaxResult.success(BarcodeUtil.generateFile(CardNo));
    }


    /**
     * 获取所有的身体部位
     * @return
     */
    @ApiOperation("获取所有的身体部位")
    @GetMapping(value = "/getBodyListPart")
    @ResponseBody
//    @Cacheable(value="bodyListPart")
    public AjaxResult getBodyListPart() {
        SysDictData dictData = new SysDictData();
        dictData.setDictType("his_body_part");
        List<SysDictData> lstSysDictData = dictDataService.selectDictDataList(dictData);
        List<BodyPart> listBodyPart = new ArrayList<BodyPart>();
        for(SysDictData item:lstSysDictData) {
            BodyPart bodyPart = new BodyPart();
            bodyPart.setBodyPart(item.getDictLabel());
            bodyPart.setId(item.getDictValue());
            bodyPart.setSort(item.getDictSort());
            listBodyPart.add(bodyPart);
        }
        return AjaxResult.success(listBodyPart);
    }



    /**
     * 根据身体部位获取对应的病症
     * @return
     */
    @ApiOperation("根据身体部位获取对应的病症")
    @GetMapping(value = "/getOrganList")
    @ResponseBody
//    @Cacheable(value="#bodyPart", key="#sex+'-'+#age")
    public AjaxResult getOrganList(@RequestParam("bodyPart") String bodyPart,
                                   @RequestParam("sex") String sex,@RequestParam("age") String age) {
        String sexCode = BodySymptomsEnum.getCodeByName(sex+"_"+age);
        SymptomsOrgan symptomsOrgan = new SymptomsOrgan();
        symptomsOrgan.setBodyPart(bodyPart);
        symptomsOrgan.setSex(sexCode);
        List<SymptomsOrgan> list = symptomsOrganService.selectSymptomsOrganList(symptomsOrgan);
        List<SymptomsOrganExd> listSymptomsOrganExd = new ArrayList<SymptomsOrganExd>();
        for(SymptomsOrgan item:list) {
            SymptomsOrganExd symptomsOrganExd = new SymptomsOrganExd();
            symptomsOrganExd.setSymptoms(item.getSymptoms());
            symptomsOrganExd.setSex(sexCode);
            symptomsOrganExd.setBodyPart(bodyPart);
            listSymptomsOrganExd.add(symptomsOrganExd);
        }
        return AjaxResult.success(listSymptomsOrganExd);
    }
}
