package com.ruoyi.web.controller.his.api;

import com.alibaba.fastjson.JSON;
import com.ruoyi.common.annotation.Log;
import com.ruoyi.common.config.Global;
import com.ruoyi.common.core.controller.BaseController;
import com.ruoyi.common.core.domain.AjaxResult;
import com.ruoyi.common.enums.AppClientType;
import com.ruoyi.common.enums.BusinessType;
import com.ruoyi.common.utils.BarcodeUtil;
import com.ruoyi.common.utils.ServletUtils;
import com.ruoyi.common.utils.StringUtils;
import com.ruoyi.common.utils.file.FileUtils;
import com.ruoyi.framework.web.service.ConfigService;
import com.ruoyi.his.callservice.HisBaseServices;
import com.ruoyi.his.constant.BodySymptomsEnum;
import com.ruoyi.his.domain.HisUser;
import com.ruoyi.his.domain.SymptomsOrgan;
import com.ruoyi.his.service.IHisUserService;
import com.ruoyi.his.service.ISmsService;
import com.ruoyi.his.service.ISymptomsOrganService;
import com.ruoyi.system.domain.SysDictData;
import com.ruoyi.system.service.ISysDictDataService;
import com.ruoyi.vo.*;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 预约挂号Controller
 * 
 * @author whl
 * @date 2020-08-08
 */
@RestController
@Api(value="His公共接口",tags={"His公共接口"})
@RequestMapping("/his")
@CrossOrigin
public class HisCommonApi extends BaseController
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
    private IHisUserService hisUserService;
    @Autowired
    private ConfigService configService;
    /**
     * his接口调用
     */
    @Log(title = "his远程接口", businessType = BusinessType.HIS)
    @ApiOperation("his接口调用")
    @PostMapping("/request")
    @ResponseBody
    public String invokeCall(@RequestBody HisRequestBO hisRequestBO){
        ServletUtils.getRequest().setAttribute("api", hisRequestBO.getApi());
        ServletUtils.getRequest().setAttribute("dataParam", hisRequestBO.getDataParam());
        return hisBaseServices.requestHisService("/"+hisRequestBO.getApi().trim(),hisRequestBO.getDataParam());
    }


    /**
     * 验证码短信
     *
     * @return
     */
    @Log(title = "his接口调用", businessType = BusinessType.HIS)
    @GetMapping("/user/sendMsg")
    @ResponseBody
    @ApiOperation("获取验证码短信")
    public AjaxResult sendMsg(@RequestParam("phone") @Validated String phone) {
        ServletUtils.getRequest().setAttribute("api", "/user/sendMsg");
        ServletUtils.getRequest().setAttribute("dataParam", phone);
        String msg = "【广东省农垦中心医院】您的验证码是:%1$s";
        return smsService.sendVerificationCode(phone,msg);
    }

    /**
     * 用户注册
     *
     * @return
     */
    @Log(title = "his接口调用", businessType = BusinessType.HIS)
    @ApiOperation("用户注册")
    @PostMapping("/user/register")
    @ResponseBody
    public AjaxResult userRegister(@Validated @RequestBody UserRegBO userRegBO){
        ServletUtils.getRequest().setAttribute("api", "register");
        ServletUtils.getRequest().setAttribute("dataParam",  JSON.toJSONString(userRegBO));
        HisUser hisUser = new HisUser();
        hisUser.setPhone(userRegBO.getPhone());
        hisUser.setPassword(userRegBO.getPassword());
        hisUser.setOpenId(userRegBO.getOpenId());
        boolean isOK = hisUserService.userRegister(hisUser,userRegBO.getVerificationCode());
        return isOK?AjaxResult.success("注册成功"):AjaxResult.error("注册失败");
    }


    /**
     * 修改密码
     *
     * @return
     */
    @Log(title = "his远程接口", businessType = BusinessType.HIS)
    @ApiOperation("修改密码")
    @PostMapping("/user/modifyPassword")
    @ResponseBody
    public AjaxResult modifyPassword(@Validated @RequestBody UserRegBO userRegBO){
        ServletUtils.getRequest().setAttribute("api", "modifyPassword");
        ServletUtils.getRequest().setAttribute("dataParam",  JSON.toJSONString(userRegBO));
        HisUser hisUser = new HisUser();
        hisUser.setPhone(userRegBO.getPhone());
        hisUser.setPassword(userRegBO.getPassword());
        boolean isOK = hisUserService.modifyPassword(hisUser,userRegBO.getVerificationCode());
        return isOK?AjaxResult.success("修改成功"):AjaxResult.error("修改失败");
    }

    /**
     * 发送短信
     *
     * @return
     */
    @Log(title = "his接口调用", businessType = BusinessType.HIS)
    @ApiOperation("发送短信")
    @PostMapping("/user/shortMessage")
    @ResponseBody
    public AjaxResult shortMessage(@Validated @RequestBody SmsMsgBO smsMsgBO) {
        ServletUtils.getRequest().setAttribute("api", "/user/shortMessage");
        ServletUtils.getRequest().setAttribute("dataParam",  JSON.toJSONString(smsMsgBO));
        if(StringUtils.isEmpty(smsMsgBO.getMessage())){
            return AjaxResult.error("短信内容不能为空");
        }
        return smsService.sendSmsMessage(smsMsgBO.getPhone(),smsMsgBO.getMessage());
    }


    /**
     * 生成条形码
     * @return
     */
    @Log(title = "本地调用", businessType = BusinessType.HIS_LOCALHOST)
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
    @Log(title = "本地调用", businessType = BusinessType.HIS_LOCALHOST)
    @ApiOperation("获取所有的身体部位")
    @PostMapping(value = "/getBodyListPart")
    @ResponseBody
    public AjaxResult getBodyListPart() {
        ServletUtils.getRequest().setAttribute("api", "getBodyListPart");
        ServletUtils.getRequest().setAttribute("dataParam", "");
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
    @Log(title = "本地调用", businessType = BusinessType.HIS_LOCALHOST)
    @ApiOperation("根据身体部位获取对应的病症")
    @PostMapping(value = "/getOrganList")
    @ResponseBody
//    @Cacheable(value="#bodyPart", key="#sex+'-'+#age")
    public AjaxResult getOrganList(@RequestBody SymptomsOrganBO symptomsOrganBO) {
        ServletUtils.getRequest().setAttribute("api", "getOrganList");
        ServletUtils.getRequest().setAttribute("dataParam", JSON.toJSONString(symptomsOrganBO));
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
     * 科室介绍
     *
     * @return
     */
    @Log(title = "本地调用", businessType = BusinessType.HIS_LOCALHOST)
    @ApiOperation("根据病症获取科室")
    @PostMapping(value = "/diagnosis")
    @ResponseBody
    public AjaxResult diagnosis(@RequestBody SymptomsOrganBO symptomsOrganBO) {
        getRequest().setAttribute("api", "diagnosis");
        getRequest().setAttribute("dataParam", JSON.toJSONString(symptomsOrganBO));
        SymptomsOrgan symptomsOrgan = new SymptomsOrgan();
        symptomsOrgan.setBodyPart(symptomsOrganBO.getBodyPart());
        symptomsOrgan.setSex(symptomsOrganBO.getSex());
        symptomsOrgan.setSymptoms(symptomsOrganBO.getSymptoms());
        List<SymptomsOrgan> list = symptomsOrganService.selectSymptomsOrganList(symptomsOrgan);
        return AjaxResult.success(list);
    }

    /**
     * 本地资源通用下载
     */
    @GetMapping("/app/version/{type}")
    @ApiOperation("获取客户端版本号")
    public AjaxResult getVersion(@PathVariable("type") String type)
    {
        AppClientType appClientType = AppClientType.getByType(type);
        String version = configService.getKey(appClientType.getInfo());
        Map<String,String> versionMap = new HashMap<>();
        versionMap.put("version",version);
        versionMap.put("type",appClientType.getType());
        return AjaxResult.success(versionMap);
    }

    /**
     * 本地资源通用下载
     */
    @GetMapping("/download/app/{type}")
    @ApiOperation("下载客户端")
    public void resourceDownload(@PathVariable("type") String type,HttpServletResponse response)throws Exception
    {
        AppClientType appClientType = AppClientType.getByType(type);
        String version = configService.getKey(appClientType.getInfo());
        // 本地资源路径
        String localPath = Global.getProfile();
        // 数据库资源地址
        String appName = appClientType.getName()+version + ".apk";
        String downloadPath = localPath +"app/" + appName;
        response.setCharacterEncoding("utf-8");
        response.setContentType("multipart/form-data");
        response.setHeader("Content-Disposition","attachment;fileName=" + appName);
        FileUtils.writeBytes(downloadPath, response.getOutputStream());
    }
}
