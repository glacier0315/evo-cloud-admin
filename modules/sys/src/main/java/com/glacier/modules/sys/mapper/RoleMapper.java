package com.glacier.modules.sys.mapper;

import com.glacier.common.core.mapper.BaseMapper;
import com.glacier.modules.sys.entity.pojo.Role;

import java.util.List;


/**
 * 角色 数据层
 *
 * @author glacier
 * @version 1.0
 * @date 2019-12-18 15:29
 */
public interface RoleMapper extends BaseMapper<Role, String> {
    /**
     * 根据用户id 查询角色编码
     *
     * @param userId 用户id
     * @return 角色编码
     */
    List<String> findCodeByUserId(String userId);

    /**
     * @param userId
     * @return 角色
     */
    List<Role> findByUserId(String userId);

    /**
     * @param role 角色
     * @return 编码数量（排除指定id）
     */
    Integer selectCount(Role role);

}
