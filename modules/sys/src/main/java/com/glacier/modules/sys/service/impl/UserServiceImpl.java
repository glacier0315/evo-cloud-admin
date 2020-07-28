package com.glacier.modules.sys.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.glacier.common.core.entity.form.IdForm;
import com.glacier.common.core.entity.page.PageRequest;
import com.glacier.common.core.entity.page.PageResponse;
import com.glacier.common.core.entity.vo.HttpResult;
import com.glacier.common.core.exception.SystemErrorType;
import com.glacier.modules.sys.common.Constant;
import com.glacier.modules.sys.entity.form.user.UserAddForm;
import com.glacier.modules.sys.entity.form.user.UserPasswordForm;
import com.glacier.modules.sys.entity.form.user.UserQueryForm;
import com.glacier.modules.sys.entity.pojo.Dept;
import com.glacier.modules.sys.entity.pojo.User;
import com.glacier.modules.sys.entity.pojo.UserRole;
import com.glacier.modules.sys.entity.vo.user.UserDetailsVo;
import com.glacier.modules.sys.entity.vo.user.UserInfo;
import com.glacier.modules.sys.entity.vo.user.UserListVo;
import com.glacier.modules.sys.entity.vo.user.UserProfileVo;
import com.glacier.modules.sys.mapper.DeptMapper;
import com.glacier.modules.sys.mapper.RoleMapper;
import com.glacier.modules.sys.mapper.UserMapper;
import com.glacier.modules.sys.mapper.UserRoleMapper;
import com.glacier.modules.sys.service.UserService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.ObjectUtils;
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
    private final DeptMapper deptMapper;
    private final UserRoleMapper userRoleMapper;
    private final PasswordEncoder passwordEncoder;

    @Override
    public User findUserByUsername(String username) {
        return this.userMapper.selectOne(new QueryWrapper<>(User
                .builder()
                .username(username)
                .build()));
    }

    @Override
    public UserInfo findUserInfoByUsername(String username) {
        User user = this.findUserByUsername(username);
        UserInfo userInfo = null;
        if (user != null) {
            // 查找角色
            List<String> roles = this.roleMapper.findCodeByUserId(user.getId());
            userInfo = UserInfo.builder()
                    .id(user.getId())
                    .name(user.getUsername())
                    .avatar(user.getAvatar())
                    .roles(roles)
                    .build();
        }
        return userInfo;
    }

    @Override
    public UserProfileVo findUserProfileByUsername(String username) {
        User user = this.findUserByUsername(username);
        UserProfileVo userProfile = null;
        if (user != null) {
            userProfile = this.modelMapper.map(user, UserProfileVo.class);
            userProfile.setDeptName(
                    ObjectUtils.defaultIfNull(
                            this.deptMapper.selectById(user.getDeptId()), new Dept())
                            .getName());
        }
        return userProfile;
    }

    @Override
    public UserDetailsVo loadUserByUsername(String username) {
        User user = this.findUserByUsername(username);
        UserDetailsVo userDetailsVo = null;
        if (user != null) {
            List<String> roles = this.roleMapper.findCodeByUserId(user.getId());
            userDetailsVo = new UserDetailsVo(
                    user.getId(),
                    user.getUsername(),
                    user.getPassword(),
                    roles.stream()
                            .map(SimpleGrantedAuthority::new)
                            .collect(Collectors.toSet()),
                    true, true, true,
                    Constant.USER_ENABLED.equals(user.getStatus())
            );
        }
        return userDetailsVo;
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
                        pageRequest.getSize()),
                new QueryWrapper<>(
                        this.modelMapper.map(
                                pageRequest.getParams(), User.class)
                ));
        List<UserListVo> userListVos = this.modelMapper.map(
                page.getRecords(),
                new TypeToken<List<UserListVo>>() {
                }.getType());
        // 处理工作单位
        userListVos.forEach(userListVo -> {
            if (userListVo.getDeptId() != null
                    && !userListVo.getDeptId().isEmpty()) {
                userListVo.setDeptName(
                        ObjectUtils.defaultIfNull(
                                this.deptMapper.selectById(userListVo.getDeptId()), new Dept())
                                .getName());
            }
        });
        return PageResponse.<UserListVo>builder()
                .current(page.getCurrent())
                .size(page.getSize())
                .total(page.getTotal())
                .records(userListVos)
                .build();
    }

    /**
     * 修改用户
     *
     * @param form 更新封装类
     * @param <T>
     * @return
     */
    @Transactional(rollbackFor = {})
    @Override
    public <T extends IdForm> int update(T form) {
        int update = 0;
        if (form != null
                && form.getId() != null
                && !form.getId().isEmpty()) {
            update = this.userMapper.updateById(
                    this.modelMapper.map(form, User.class)
            );
        }
        return update;
    }

    /**
     * 修改密码
     *
     * @param userPasswordForm
     * @return
     */
    @Transactional(rollbackFor = {})
    @Override
    public HttpResult<Integer> updatePassword(UserPasswordForm userPasswordForm) {
        int update = 0;
        if (userPasswordForm != null
                && userPasswordForm.getId() != null
                && !userPasswordForm.getId().isEmpty()) {
            // 判断原始密码是否一致
            User user = this.userMapper.selectById(userPasswordForm.getId());
            if (user != null) {
                if (this.passwordEncoder.matches(
                        userPasswordForm.getOldPassword(),
                        user.getPassword())) {
                    // 加密密码
                    user.setPassword(
                            this.passwordEncoder.encode(userPasswordForm.getNewPassword())
                    );
                    update = this.userMapper.updateById(user);
                } else {
                    return HttpResult.error("原始密码不正确！");
                }
            } else {
                return HttpResult.error("未找到该用户！");
            }
        } else {
            return HttpResult.error(SystemErrorType.ARGUMENT_NOT_VALID);
        }
        return HttpResult.ok("修改成功！", update);
    }

    /**
     * 保存用户
     *
     * @param userAddForm
     * @return
     */
    @Transactional(rollbackFor = {})
    @Override
    public int add(UserAddForm userAddForm) {
        int update = 0;
        if (userAddForm != null) {
            User user = this.modelMapper.map(userAddForm, User.class);
            if (user.getPassword() == null
                    || user.getPassword().isEmpty()) {
                user.setPassword(Constant.DEFAULT_PASSWD);
            }
            // 对原始密码加密
            user.setPassword(
                    this.passwordEncoder.encode(user.getPassword())
            );
            update = this.userMapper.insert(user);
        }
        return update;
    }

    /**
     * 根据id删除
     *
     * @param id
     * @return
     */
    @Transactional(rollbackFor = {})
    @Override
    public int delete(String id) {
        int update = 0;
        if (id != null && !id.isEmpty()) {
            update = this.userMapper.deleteById(id);
            // 删除用户角色关系
            this.deleteUserRoleByUserId(id);
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
