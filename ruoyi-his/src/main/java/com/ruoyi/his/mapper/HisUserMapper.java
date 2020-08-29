package com.ruoyi.his.mapper;

import java.util.List;
import com.ruoyi.his.domain.HisUser;

/**
 * his用户Mapper接口
 * 
 * @author whl
 * @date 2020-08-29
 */
public interface HisUserMapper 
{
    /**
     * 查询his用户
     * 
     * @param id his用户ID
     * @return his用户
     */
    public HisUser selectHisUserById(Integer id);

    /**
     * 查询his用户列表
     * 
     * @param hisUser his用户
     * @return his用户集合
     */
    public List<HisUser> selectHisUserList(HisUser hisUser);

    /**
     * 新增his用户
     * 
     * @param hisUser his用户
     * @return 结果
     */
    public int insertHisUser(HisUser hisUser);

    /**
     * 修改his用户
     * 
     * @param hisUser his用户
     * @return 结果
     */
    public int updateHisUser(HisUser hisUser);

    /**
     * 删除his用户
     * 
     * @param id his用户ID
     * @return 结果
     */
    public int deleteHisUserById(Integer id);

    /**
     * 批量删除his用户
     * 
     * @param ids 需要删除的数据ID
     * @return 结果
     */
    public int deleteHisUserByIds(String[] ids);
}
