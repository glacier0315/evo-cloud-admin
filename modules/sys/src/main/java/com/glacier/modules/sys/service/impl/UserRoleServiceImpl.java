package com.glacier.modules.sys.service.impl;

import com.glacier.modules.sys.entity.UserRole;
import com.glacier.modules.sys.entity.dto.role.UserRoleForm;
import com.glacier.modules.sys.mapper.UserRoleMapper;
import com.glacier.modules.sys.service.UserRoleService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * 用户角色关联关系业务层
 *
 * @author glacier
 * @version 1.0
 * @date 2020-08-30 12:47
 */
@Slf4j
@Transactional(rollbackFor = {})
@Service(value = "userRoleService")
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class UserRoleServiceImpl implements UserRoleService {
    private final UserRoleMapper userRoleMapper;

    @Override
    public int save(UserRoleForm userRoleForm) {
        if (userRoleForm != null
                && StringUtils.isNotBlank(userRoleForm.getRoleId())
                && userRoleForm.getUserIds() != null
                && !userRoleForm.getUserIds().isEmpty()) {
            return this.userRoleMapper.insertBatchUser(userRoleForm.getRoleId(),
                    userRoleForm.getUserIds());
        }
        return 0;
    }

    @Override
    public int delete(UserRoleForm userRoleForm) {
        if (userRoleForm != null
                && StringUtils.isNotBlank(userRoleForm.getRoleId())
                && userRoleForm.getUserIds() != null
                && !userRoleForm.getUserIds().isEmpty()) {
            return userRoleForm.getUserIds()
                    .stream()
                    .mapToInt(userId ->
                            this.userRoleMapper.delete(
                                    new UserRole(userId, userRoleForm.getRoleId())))
                    .sum();
        }
        return 0;
    }
}
