package com.glacier.modules.sys.mapper;

import com.glacier.modules.sys.entity.UserRole;

/**
 * 用户角色关联关系 数据层
 *
 * @author glacier
 * @version 1.0
 * @date 2019-12-18 15:29
 */
public interface UserRoleMapper {

    /**
     * 删除 用户角色关联关系
     *
     * @param userId 用户id
     * @return
     */
    int deleteByUserId(String userId);

    /**
     * 删除 用户角色关联关系
     *
     * @param roleId 角色id
     * @return
     */
    int deleteByRoleId(String roleId);

    /**
     * 保存用户角色关系
     *
     * @param record
     * @return
     */
    int insert(UserRole record);
}
