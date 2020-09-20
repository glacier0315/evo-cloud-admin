package com.glacier.modules.sys.service.impl;

import com.glacier.common.core.utils.StringUtil;
import com.glacier.modules.sys.entity.dto.role.RoleUserDto;
import com.glacier.modules.sys.entity.dto.user.UserRoleDto;
import com.glacier.modules.sys.mapper.UserRoleMapper;
import com.glacier.modules.sys.service.UserRoleService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
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
    public int addUser(RoleUserDto roleUserDto) {
        if (roleUserDto != null
                && StringUtil.isNotBlank(roleUserDto.getRoleId())
                && roleUserDto.getUserIds() != null
                && !roleUserDto.getUserIds().isEmpty()) {
            return this.userRoleMapper.insertBatchUser(roleUserDto);
        }
        return 0;
    }

    @Override
    public int deleteUser(RoleUserDto roleUserDto) {
        if (roleUserDto != null
                && StringUtil.isNotBlank(roleUserDto.getRoleId())
                && roleUserDto.getUserIds() != null
                && !roleUserDto.getUserIds().isEmpty()) {
            return this.userRoleMapper.deleteBatchUser(roleUserDto);
        }
        return 0;
    }

    @Override
    public int addRole(UserRoleDto userRoleDto) {
        if (userRoleDto != null
                && StringUtil.isNotBlank(userRoleDto.getUserId())
                && userRoleDto.getRoleIds() != null
                && !userRoleDto.getRoleIds().isEmpty()) {
            return this.userRoleMapper.insertBatchRole(userRoleDto);
        }
        return 0;
    }

    @Override
    public int deleteRole(UserRoleDto userRoleDto) {
        if (userRoleDto != null
                && StringUtil.isNotBlank(userRoleDto.getUserId())
                && userRoleDto.getRoleIds() != null
                && !userRoleDto.getRoleIds().isEmpty()) {
            return this.userRoleMapper.deleteBatchRole(userRoleDto);
        }
        return 0;
    }
}
