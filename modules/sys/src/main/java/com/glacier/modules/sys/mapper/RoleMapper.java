package com.glacier.modules.sys.mapper;

import com.glacier.common.core.mapper.BaseMapper;
import com.glacier.modules.sys.entity.Role;
import com.glacier.modules.sys.entity.dto.role.RoleQuery;

import java.util.List;


/**
 * 角色 数据层
 *
 * @author glacier
 * @version 1.0
 * date 2019-12-18 15:29
 */
public interface RoleMapper extends BaseMapper<Role, String> {

    /**
     * 根据用户id 查询角色
     *
     * @param userId 用户id
     * @return 角色
     */
    List<Role> findByUserId(String userId);

    /**
     * 查询角色编码是否已使用
     * id 不为空时 排除该id的角色
     *
     * @param role 角色
     * @return 编码数量（排除指定id）
     */
    int checkCodeExist(Role role);

    /**
     * 根据条件查询
     *
     * @param params 查询参数
     * @return 角色集合
     */
    List<Role> selectList(RoleQuery params);
}
