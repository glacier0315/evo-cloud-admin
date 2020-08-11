package com.glacier.modules.sys.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.glacier.common.core.entity.page.PageRequest;
import com.glacier.common.core.entity.page.PageResponse;
import com.glacier.common.core.entity.vo.Result;
import com.glacier.common.core.entity.vo.RoleDetails;
import com.glacier.common.core.exception.SystemErrorType;
import com.glacier.common.core.utils.IdGen;
import com.glacier.modules.sys.common.Constant;
import com.glacier.modules.sys.entity.form.user.UserAvatarForm;
import com.glacier.modules.sys.entity.form.user.UserPasswordForm;
import com.glacier.modules.sys.entity.form.user.UserProfileForm;
import com.glacier.modules.sys.entity.form.user.UserQueryForm;
import com.glacier.modules.sys.entity.pojo.Role;
import com.glacier.modules.sys.entity.pojo.User;
import com.glacier.modules.sys.entity.pojo.UserRole;
import com.glacier.modules.sys.entity.vo.user.UserDetails;
import com.glacier.modules.sys.entity.vo.user.UserInfo;
import com.glacier.modules.sys.entity.vo.user.UserProfile;
import com.glacier.modules.sys.entity.vo.user.UserVo;
import com.glacier.modules.sys.mapper.RoleMapper;
import com.glacier.modules.sys.mapper.UserMapper;
import com.glacier.modules.sys.mapper.UserRoleMapper;
import com.glacier.modules.sys.service.UserService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang.StringUtils;
import org.modelmapper.ModelMapper;
import org.modelmapper.TypeToken;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Collections;
import java.util.List;
import java.util.Optional;
import java.util.concurrent.atomic.AtomicInteger;
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
    public User findById(String id) {
        return this.userMapper.selectByPrimaryKey(id)
                .orElseThrow(() -> new IllegalArgumentException("该用户不存在!"));
    }

    @Override
    public User findUserByUsername(String username) {
        return this.userMapper.selectOneByUsername(username)
                .orElseThrow(() -> new IllegalArgumentException("该用户不存在!"));
    }

    @Override
    public UserInfo findUserInfoByUsername(String username) {
        User user = this.findUserByUsername(username);
        // 查找角色
        List<Role> roles = this.roleMapper.findByUserId(user.getId());
        return UserInfo.builder()
                .id(user.getId())
                .name(user.getUsername())
                .avatar(user.getAvatar())
                .roles(
                        Optional.ofNullable(roles)
                                .orElse(Collections.emptyList())
                                .stream()
                                .map(Role::getCode)
                                .collect(Collectors.toList())
                )
                .build();
    }

    @Override
    public UserProfile findUserProfileByUsername(String username) {
        User user = this.findUserByUsername(username);
        return this.modelMapper.map(user, UserProfile.class);
    }

    @Override
    public UserDetails loadUserByUsername(String username) {
        User user = this.findUserByUsername(username);
        UserDetails userDetails = this.modelMapper.map(user, UserDetails.class);
        userDetails.setRoleDetails(
                this.modelMapper.map(
                        this.roleMapper.findByUserId(user.getId()),
                        new TypeToken<List<RoleDetails>>() {
                        }.getType()));
        return userDetails;
    }

    /**
     * 分页查找
     *
     * @param pageRequest
     * @return
     */
    @Override
    public PageResponse<UserVo> findPage(PageRequest<UserQueryForm> pageRequest) {
        PageHelper.startPage(pageRequest.getPageNum(), pageRequest.getPageSize());
        List<User> userList = this.userMapper.selectList(this.modelMapper.map(
                pageRequest.getParams(), User.class));
        PageInfo<User> pageInfo = PageInfo.of(userList);
        return new PageResponse<>(
                pageInfo.getPageNum(),
                pageInfo.getPages(),
                pageInfo.getTotal(),
                this.modelMapper.map(
                        pageInfo.getList(),
                        new TypeToken<List<UserVo>>() {
                        }.getType()));
    }

    /**
     * 修改密码
     *
     * @param userPasswordForm
     * @return
     */
    @Transactional(rollbackFor = {})
    @Override
    public Result<Integer> updatePassword(UserPasswordForm userPasswordForm) {
        int update = 0;
        if (userPasswordForm == null
                || StringUtils.isEmpty(userPasswordForm.getId())) {
            return Result.error(SystemErrorType.ARGUMENT_NOT_VALID);
        }
        // 判断原始密码是否一致
        User user = this.findById(userPasswordForm.getId());
        if (!this.passwordEncoder.matches(
                userPasswordForm.getOldPassword(),
                user.getPassword())) {
            return Result.error("原始密码不正确！");
        }
        // 加密密码
        user.setPassword(
                this.passwordEncoder.encode(userPasswordForm.getNewPassword()));
        update = this.userMapper.updateByPrimaryKey(user);
        return Result.ok("修改成功！", update);
    }

    @Override
    public Result<Integer> updateProfile(UserProfileForm userProfileForm) {
        if (userProfileForm == null
                || StringUtils.isEmpty(userProfileForm.getId())) {
            return Result.error(SystemErrorType.ARGUMENT_NOT_VALID);
        }
        return Result.ok("修改成功！",
                this.userMapper.updateProfileByPrimaryKey(
                        this.modelMapper.map(userProfileForm, User.class)));
    }

    @Override
    public Result<Integer> updateAvatar(UserAvatarForm UserAvatarForm) {
        if (UserAvatarForm == null
                || StringUtils.isEmpty(UserAvatarForm.getId())) {
            return Result.error(SystemErrorType.ARGUMENT_NOT_VALID);
        }
        return Result.ok("修改成功！",
                this.userMapper.updateAvatarByPrimaryKey(
                        this.modelMapper.map(UserAvatarForm, User.class)));
    }

    /**
     * 保存用户
     *
     * @param form 用户封装实体
     * @param <T>
     * @return
     */
    @Override
    public <T> int save(T form) {
        AtomicInteger update = new AtomicInteger(0);
        Optional.ofNullable(form).ifPresent(t -> {
            User user = this.modelMapper.map(t, User.class);
            if (!user.isNewRecord()) {
                user.preUpdate();
                update.set(this.userMapper.updateByPrimaryKey(user));
            } else {
                // 对原始密码加密
                user.setPassword(
                        this.passwordEncoder.encode(
                                Optional.of(user)
                                        .map(User::getPassword)
                                        .orElse(Constant.DEFAULT_PASSWD)));
                user.preInsert();
                update.set(this.userMapper.insert(user));
            }
        });
        return update.get();
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
        AtomicInteger update = new AtomicInteger(0);
        Optional.ofNullable(id).ifPresent(s -> {
            update.set(this.userMapper.deleteByPrimaryKey(id));
            // 删除用户角色关系
            this.userRoleMapper.deleteByUserId(id);
        });
        return update.get();
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
        if (StringUtils.isNotEmpty(userId)
                && roleIds != null && !roleIds.isEmpty()) {
            // 保存用户角色关系
            for (String roleId : roleIds) {
                update += this.userRoleMapper.insert(
                        UserRole.builder()
                                .id(IdGen.uuid())
                                .userId(userId)
                                .roleId(roleId)
                                .build()
                );
            }
        }
        return update;
    }
}
