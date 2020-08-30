package com.glacier.modules.sys.service;

import com.glacier.modules.sys.entity.dto.role.UserRoleForm;

/**
 * @author glacier
 * @version 1.0
 * @date 2020-08-30 12:46
 */
public interface UserRoleService {
    /**
     * 保存操作
     *
     * @param userRoleForm 用户角色关系
     * @return
     */
    int save(UserRoleForm userRoleForm);

    /**
     * 根据删除
     *
     * @param userRoleForm 用户角色关系
     * @return
     */
    int delete(UserRoleForm userRoleForm);
}
