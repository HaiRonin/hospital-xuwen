package com.ruoyi.his.service.impl;

import java.beans.Transient;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.alibaba.fastjson.JSONObject;
import com.ruoyi.common.core.domain.AjaxResult;
import com.ruoyi.common.exception.HisException;
import com.ruoyi.common.utils.DateUtils;
import com.ruoyi.common.utils.RedisUtil;
import com.ruoyi.his.constant.Constants;
import com.ruoyi.his.remote.HisBaseServices;
import com.ruoyi.his.remote.response.BaseResponse;
import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang3.ObjectUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.ruoyi.his.mapper.HisUserMapper;
import com.ruoyi.his.domain.HisUser;
import com.ruoyi.his.service.IHisUserService;
import com.ruoyi.common.core.text.Convert;

/**
 * his用户Service业务层处理
 * 
 * @author whl
 * @date 2020-08-29
 */
@Service
public class HisUserServiceImpl implements IHisUserService 
{
    @Autowired
    private HisUserMapper hisUserMapper;

    @Autowired
    private RedisUtil redisUtil;
    @Autowired
    private HisBaseServices hisBaseServices;
    /**
     * 查询his用户
     * 
     * @param id his用户ID
     * @return his用户
     */
    @Override
    public HisUser selectHisUserById(Integer id)
    {
        return hisUserMapper.selectHisUserById(id);
    }

    /**
     * 查询his用户列表
     * 
     * @param hisUser his用户
     * @return his用户
     */
    @Override
    public List<HisUser> selectHisUserList(HisUser hisUser)
    {
        return hisUserMapper.selectHisUserList(hisUser);
    }

    /**
     * 新增his用户
     * 
     * @param hisUser his用户
     * @return 结果
     */
    @Override
    public int insertHisUser(HisUser hisUser)
    {
        hisUser.setCreateTime(DateUtils.getNowDate());
        return hisUserMapper.insertHisUser(hisUser);
    }

    /**
     * 修改his用户
     * 
     * @param hisUser his用户
     * @return 结果
     */
    @Override
    public int updateHisUser(HisUser hisUser)
    {
        hisUser.setUpdateTime(DateUtils.getNowDate());
        return hisUserMapper.updateHisUser(hisUser);
    }

    /**
     * 删除his用户对象
     * 
     * @param ids 需要删除的数据ID
     * @return 结果
     */
    @Override
    public int deleteHisUserByIds(String ids)
    {
        return hisUserMapper.deleteHisUserByIds(Convert.toStrArray(ids));
    }

    /**
     * 删除his用户信息
     * 
     * @param id his用户ID
     * @return 结果
     */
    @Override
    public int deleteHisUserById(Integer id)
    {
        return hisUserMapper.deleteHisUserById(id);
    }

    @Override
    public boolean userRegister(HisUser hisUser, String verificationCode) {
        Object obj= redisUtil.get(Constants.MOBILE_VERIFICATION_CODE+hisUser.getPhone());
        if(null ==  obj){
            throw new HisException(String.format("验证码过期，请重新获取"));
        }
        if(!verificationCode.equals(obj.toString())){
            throw new HisException(String.format("验证码校验不通过，请重新输入"));
        }
        Map<String,String> map = new HashMap<>();
        map.put("UserName",hisUser.getPhone());
        map.put("PassWord",hisUser.getPassword());
        String result = hisBaseServices.requestHisService("/Register", JSONObject.toJSONString(map));
        BaseResponse baseResponse = JSONObject.parseObject(result,BaseResponse.class);
        if(!baseResponse.isOk()){
            throw new HisException(String.format("注册失败，原因为"+baseResponse.getResultMsg()));
        }
        hisUser.setName(hisUser.getPhone());
        hisUser.setDelFlag("0");
        hisUser.setUpdateTime(DateUtils.getNowDate());
        hisUser.setCreateBy(hisUser.getPhone());
        hisUser.setUpdateBy(hisUser.getPhone());
        insertHisUser(hisUser);
        return true;
    }

    @Override
    public boolean modifyPassword(HisUser hisUser, String verificationCode) {
        Object obj= redisUtil.get(Constants.MOBILE_VERIFICATION_CODE+hisUser.getPhone());
        if(null ==  obj){
            throw new HisException(String.format("验证码过期，请重新获取"));
        }
        if(!verificationCode.equals(obj.toString())){
            throw new HisException(String.format("验证码校验不通过，请重新输入"));
        }
        Map<String,String> map = new HashMap<>();
        map.put("UserName",hisUser.getPhone());
        map.put("newPassWord",hisUser.getPassword());
        String result = hisBaseServices.requestHisService("/ModifyPassword", JSONObject.toJSONString(map));
        BaseResponse baseResponse = JSONObject.parseObject(result,BaseResponse.class);
        if(!baseResponse.isOk()){
            throw new HisException(String.format("密码修改失败，原因为"+baseResponse.getResultMsg()));
        }
        HisUser hisUserQuery = new HisUser();
        hisUserQuery.setPhone(hisUser.getPhone());
        List<HisUser> listHisUser = selectHisUserList(hisUserQuery);
        if(CollectionUtils.isEmpty(listHisUser)){
            hisUser.setName(hisUser.getPhone());
            hisUser.setDelFlag("0");
            hisUser.setUpdateTime(DateUtils.getNowDate());
            hisUser.setCreateBy(hisUser.getPhone());
            hisUser.setUpdateBy(hisUser.getPhone());
            insertHisUser(hisUser);
        }
        else{
            hisUserQuery = listHisUser.stream().findFirst().get();
            hisUserQuery.setPassword(hisUser.getPassword());
            hisUserQuery.setUpdateTime(DateUtils.getNowDate());
            updateHisUser(hisUserQuery);
        }
        return true;
    }


}
