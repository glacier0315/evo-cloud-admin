package com.glacier.modules.sys.mapper;

import com.glacier.modules.sys.entity.UserRole;
import com.glacier.modules.sys.entity.dto.role.RoleUserDto;
import com.glacier.modules.sys.entity.dto.user.UserRoleDto;

/**
 * 用户角色关联关系 数据层
 *
 * @author glacier
 * @version 1.0
 * date 2019-12-18 15:29
 */
public interface UserRoleMapper {

    /**
     * 保存角色用户关系
     *
     * @param roleUserDto 角色用户关系
     * @return 保存行数
     */
    int insertBatchUser(RoleUserDto roleUserDto);

    /**
     * 移除角色用户关系
     *
     * @param roleUserDto 角色用户关系
     * @return 保存行数
     */
    int deleteBatchUser(RoleUserDto roleUserDto);

    /**
     * 保存用户角色关系
     *
     * @param userRoleDto 用户角色关系
     * @return 保存行数
     */
    int insertBatchRole(UserRoleDto userRoleDto);

    /**
     * 移除用户角色关系
     *
     * @param userRoleDto 用户角色关系
     * @return 删除记录数
     */
    int deleteBatchRole(UserRoleDto userRoleDto);

    /**
     * 删除 用户角色关联关系
     *
     * @param userId 用户id
     * @return 删除记录数
     */
    int deleteByUserId(String userId);

    /**
     * 删除 用户角色关联关系
     *
     * @param roleId 角色id
     * @return 删除记录数
     */
    int deleteByRoleId(String roleId);

    /**
     * 保存用户角色关系
     *
     * @param record 记录
     * @return 保存记录数
     */
    int insert(UserRole record);

}
