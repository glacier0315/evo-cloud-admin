package com.glacier.sys.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.glacier.common.core.entity.form.IdForm;
import com.glacier.common.core.entity.page.PageRequest;
import com.glacier.common.core.entity.page.PageResponse;
import com.glacier.common.core.entity.vo.HttpResult;
import com.glacier.common.core.exception.AuthErrorType;
import com.glacier.common.core.exception.SystemErrorType;
import com.glacier.sys.entity.form.UserAddForm;
import com.glacier.sys.entity.form.UserQueryForm;
import com.glacier.sys.entity.form.UserUpdateForm;
import com.glacier.sys.entity.pojo.User;
import com.glacier.sys.entity.pojo.UserRole;
import com.glacier.sys.entity.vo.UserDetailsVo;
import com.glacier.sys.entity.vo.UserInfo;
import com.glacier.sys.entity.vo.UserListVo;
import com.glacier.sys.mapper.RoleMapper;
import com.glacier.sys.mapper.UserMapper;
import com.glacier.sys.mapper.UserRoleMapper;
import com.glacier.sys.service.UserService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.modelmapper.TypeToken;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

/**
 * 用户业务类
 *
 * @author glacier
 * @version 1.0
 * @date 2019-08-04 21:50
 */
@Slf4j
@Transactional(readOnly = true)
@Service(value = "userService")
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class UserServiceImpl implements UserService {
    private final ModelMapper modelMapper;
    private final UserMapper userMapper;
    private final RoleMapper roleMapper;
    private final UserRoleMapper userRoleMapper;
    private final PasswordEncoder passwordEncoder;

    @Override
    public HttpResult<UserInfo> loadUserInfoByUsername(String username) {
        User user = this.userMapper.selectOne(new QueryWrapper<>(User
                .builder()
                .username(username)
                .build()));
        if (user != null) {
            // 查找角色
            List<String> roles = this.roleMapper.findCodeByUserId(user.getId());
            UserInfo userInfo = UserInfo.builder()
                    .id(user.getId())
                    .name(user.getUsername())
                    .roles(roles)
                    .build();
            return HttpResult.ok(userInfo);
        } else {
            return HttpResult.error(AuthErrorType.INVALID_GRANT);
        }
    }

    @Override
    public HttpResult<UserDetailsVo> loadUserByUsername(String username) {
        User user = this.userMapper.selectOne(new QueryWrapper<>(User
                .builder()
                .username(username)
                .build()));
        if (user != null) {
            List<String> roles = this.roleMapper.findCodeByUserId(user.getId());
            return HttpResult.ok(
                    UserDetailsVo.builder()
                            .username(user.getUsername())
                            .password(user.getPassword())
                            .authorities(
                                    roles.stream()
                                            .map(SimpleGrantedAuthority::new)
                                            .collect(Collectors.toSet())
                            )
                            .build()
            );
        } else {
            return HttpResult.error(SystemErrorType.SYSTEM_ERROR, "用户不存在！");
        }
    }

    /**
     * 分页查找
     *
     * @param pageRequest
     * @return
     */
    @Override
    public PageResponse<UserListVo> findPage(PageRequest<UserQueryForm> pageRequest) {
        Page<User> page = this.userMapper.selectPage(
                new Page<>(
                        pageRequest.getCurrent(),
                        pageRequest.getSize()
                ),
                new QueryWrapper<>(
                        this.modelMapper.map(
                                pageRequest.getParams(),
                                User.class
                        )
                ));
        return PageResponse.<UserListVo>builder()
                .current(page.getCurrent())
                .size(page.getSize())
                .total(page.getTotal())
                .records(
                        this.modelMapper.map(
                                page.getRecords(),
                                new TypeToken<List<UserListVo>>() {
                                }.getType()
                        )
                )
                .build();
    }

    @Override
    public int update(UserUpdateForm userUpdateForm) {
        int update = 0;
        if (userUpdateForm != null
                && userUpdateForm.getId() != null
                && !userUpdateForm.getId().isEmpty()) {
            // 对原始密码加密
            if (userUpdateForm.getPassword() != null 
                    && !userUpdateForm.getPassword().isEmpty()) {
                // 加密密码
                userUpdateForm.setPassword(
                        this.passwordEncoder.encode(userUpdateForm.getPassword())
                );
            }
            update = this.userMapper.updateById(
                    this.modelMapper.map(userUpdateForm, User.class)
            );
        }
        return update;
    }

    /**
     * 保存用户
     *
     * @param userAddForm
     * @return
     */
    @Transactional(rollbackFor = {})
    @Override
    public int save(UserAddForm userAddForm) {
        int update = 0;
        if (userAddForm != null) {
            User user = this.modelMapper.map(userAddForm, User.class);
            // 对原始密码加密
            if (user.getPassword() != null
                    && !user.getPassword().isEmpty()) {
                // 加密密码
                user.setPassword(
                        this.passwordEncoder.encode(user.getPassword())
                );
            }
            update = this.userMapper.insert(user);
        }
        return update;
    }

    /**
     * 根据id批量删除
     *
     * @param idForms
     * @return
     */
    @Transactional(rollbackFor = {})
    @Override
    public int batchDelete(List<IdForm> idForms) {
        int update = 0;
        if (idForms != null && !idForms.isEmpty()) {
            List<String> list = idForms.stream()
                    .map(IdForm::getId)
                    .collect(Collectors.toList());
            update = this.userMapper.deleteBatchIds(list);
            // 删除用户角色关系
            for (String userId : list) {
                this.deleteUserRoleByUserId(userId);
            }
        }
        return update;
    }

    /**
     * 保存用户角色关系
     *
     * @param userId
     * @param roleIds
     * @return
     */
    public int saveUserRole(final String userId, List<String> roleIds) {
        int update = 0;
        if (userId != null && !userId.isEmpty()
                && roleIds != null && !roleIds.isEmpty()) {
            // 保存用户角色关系
            for (String roleId : roleIds) {
                update += this.userRoleMapper.insert(
                        UserRole.builder()
                                .userId(userId)
                                .roleId(roleId)
                                .build()
                );
            }
        }
        return update;
    }

    /**
     * 删除用户角色关系
     *
     * @param userId
     * @return
     */
    private int deleteUserRoleByUserId(final String userId) {
        int update = 0;
        if (userId != null && !userId.isEmpty()) {
            update = this.userRoleMapper.delete(
                    new UpdateWrapper<>(
                            UserRole.builder()
                                    .userId(userId)
                                    .build()
                    )
            );
        }
        return update;
    }
}
