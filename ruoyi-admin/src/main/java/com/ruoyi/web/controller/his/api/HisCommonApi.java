package com.ruoyi.web.controller.his.api;

import com.ruoyi.common.annotation.Log;
import com.ruoyi.common.core.domain.AjaxResult;
import com.ruoyi.common.enums.BusinessType;
import com.ruoyi.common.utils.BarcodeUtil;
import com.ruoyi.common.utils.StringUtils;
import com.ruoyi.common.utils.uuid.IdUtils;
import com.ruoyi.his.constant.BodySymptomsEnum;
import com.ruoyi.his.constant.HisBusinessTypeEnum;
import com.ruoyi.his.domain.DoregInfo;
import com.ruoyi.his.domain.HisUser;
import com.ruoyi.his.domain.SymptomsOrgan;
import com.ruoyi.his.remote.AbstractHisServiceHandler;
import com.ruoyi.his.remote.HisBaseServices;
import com.ruoyi.his.remote.response.DoRegOut;
import com.ruoyi.his.service.IDoregInfoService;
import com.ruoyi.his.service.IHisUserService;
import com.ruoyi.his.service.ISmsService;
import com.ruoyi.his.service.ISymptomsOrganService;
import com.ruoyi.system.domain.SysDictData;
import com.ruoyi.system.service.ISysDictDataService;
import com.ruoyi.vo.*;
import com.ruoyi.web.core.config.WechatConfig;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.Date;
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
    @Autowired
    private IDoregInfoService doregInfoService;
    @Autowired
    private IHisUserService hisUserService;
    /**
     * his接口调用
     */
    @Log(title = "his接口调用", businessType = BusinessType.HIS)
    @ApiOperation("his接口调用")
    @PostMapping("/request")
    @ResponseBody
    public String invokeCall(@RequestBody HisRequestBO hisRequestBO)
    {
        return hisBaseServices.requestHisService("/"+hisRequestBO.getApi().trim(),hisRequestBO.getDataParam());
    }


    /**
     * 验证码短信
     *
     * @return
     */
    @GetMapping("/user/sendMsg")
    @ResponseBody
    @ApiOperation("获取验证码短信")
    public AjaxResult sendMsg(@Validated String phone) {
        return smsService.sendVerificationCode(phone);
    }

    /**
     * 验证码是否正确
     *
     * @return
     */
    @ApiOperation("用户注册")
    @PostMapping("/user/register")
    @ResponseBody
    public AjaxResult userRegister(@Validated @RequestBody UserRegBO userRegBO){
        HisUser hisUser = new HisUser();
        hisUser.setPhone(userRegBO.getPhone());
        hisUser.setPassword(userRegBO.getPassword());
        return AjaxResult.error(hisUserService.userRegister(hisUser,userRegBO.getVerificationCode())?"注册成功":"注册失败");
    }

    /**
     * 发送短信
     *
     * @return
     */
    @ApiOperation("发送短信")
    @PostMapping("/user/shortMessage")
    @ResponseBody
    public AjaxResult shortMessage(@Validated @RequestBody SmsMsgBO smsMsgBO) {
        if(StringUtils.isEmpty(smsMsgBO.getMessage())){
            return AjaxResult.error("短信内容不能为空");
        }
        return smsService.sendSmsMessage(smsMsgBO.getPhone(),smsMsgBO.getMessage());
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
    @PostMapping(value = "/getBodyListPart")
    @ResponseBody
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
    @PostMapping(value = "/getOrganList")
    @ResponseBody
//    @Cacheable(value="#bodyPart", key="#sex+'-'+#age")
    public AjaxResult getOrganList(@RequestBody SymptomsOrganBO symptomsOrganBO) {
        String sexCode = BodySymptomsEnum.getCodeByName(symptomsOrganBO.getSex()+"_"+symptomsOrganBO.getAge());
        SymptomsOrgan symptomsOrgan = new SymptomsOrgan();
        symptomsOrgan.setBodyPart(symptomsOrganBO.getBodyPart());
        symptomsOrgan.setSex(sexCode);
        List<SymptomsOrgan> list = symptomsOrganService.selectSymptomsOrganList(symptomsOrgan);
        List<SymptomsOrganExd> listSymptomsOrganExd = new ArrayList<SymptomsOrganExd>();
        for(SymptomsOrgan item:list) {
            SymptomsOrganExd symptomsOrganExd = new SymptomsOrganExd();
            symptomsOrganExd.setSymptoms(item.getSymptoms());
            symptomsOrganExd.setSex(sexCode);
            symptomsOrganExd.setBodyPart(symptomsOrganBO.getBodyPart());
            listSymptomsOrganExd.add(symptomsOrganExd);
        }
        return AjaxResult.success(listSymptomsOrganExd);
    }

    /**
     * 2020.8.26
     * 新增支付挂号的记录
     *
     * @return
     */
    @ApiOperation("新增挂号的记录")
    @ResponseBody
    @PostMapping(value = "/outpatientPayment")
    public AjaxResult outpatientPayment(@RequestBody DoregInfo doregInfo){
        doregInfo.setAppId(WechatConfig.appId);
        doregInfo.setCreateTime(new Date());
        doregInfo.setPayType("5");
        doregInfo.setSuccessfulPayment("0");
        String orderNo = IdUtils.getOrderNo("RE");
        doregInfo.setOutTradeNo(orderNo);
        int iCount = doregInfoService.insertDoregInfo(doregInfo);
        if (iCount > 0){
            return AjaxResult.success(doregInfo);
        }
        return AjaxResult.error("支付挂号操作失败");
    }

    /**
     * 2020.8.26
     * 测试预约挂号推his
     *
     * @return
     */
    @ApiOperation("测试预约挂号推his")
    @ResponseBody
    @PostMapping(value = "/testDoregInfo")
    public AjaxResult testDoregInfo(@RequestBody DoregInfo doregInfo){
        DoRegOut doRegOut = (DoRegOut)AbstractHisServiceHandler.servicesInstance(HisBusinessTypeEnum.DOREG).invokeCallSubmit(doregInfo.getId());
        return AjaxResult.success(doRegOut);
    }
}
