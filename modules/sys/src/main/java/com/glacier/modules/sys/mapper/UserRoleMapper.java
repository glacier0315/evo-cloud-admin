package com.glacier.modules.sys.mapper;

import com.glacier.modules.sys.entity.UserRole;
import org.apache.ibatis.annotations.Param;

import java.util.List;

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
     * @param userRole 用户角色
     * @return
     */
    int delete(UserRole userRole);

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

    /**
     * 保存用户角色关系
     *
     * @param roleId
     * @param userIds
     * @return
     */
    int insertBatchUser(@Param("roleId") String roleId, @Param("userIds") List<String> userIds);

    /**
     * 保存用户角色关系
     *
     * @param userId
     * @param roleIds
     * @return
     */
    int insertBatchRole(@Param("userId") String userId, @Param("roleIds") List<String> roleIds);
}
