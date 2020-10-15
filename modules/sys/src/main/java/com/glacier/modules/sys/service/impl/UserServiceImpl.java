package com.glacier.modules.sys.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.glacier.common.core.constant.SysConstants;
import com.glacier.common.core.entity.Result;
import com.glacier.common.core.entity.dto.vo.RoleDetailsDto;
import com.glacier.common.core.entity.dto.vo.UserDetailsDto;
import com.glacier.common.core.entity.page.PageRequest;
import com.glacier.common.core.entity.page.PageResponse;
import com.glacier.common.core.exception.SystemErrorType;
import com.glacier.common.core.utils.StringUtil;
import com.glacier.modules.sys.entity.Role;
import com.glacier.modules.sys.entity.User;
import com.glacier.modules.sys.entity.dto.user.*;
import com.glacier.modules.sys.mapper.RoleMapper;
import com.glacier.modules.sys.mapper.UserMapper;
import com.glacier.modules.sys.mapper.UserRoleMapper;
import com.glacier.modules.sys.service.UserService;
import org.modelmapper.ModelMapper;
import org.modelmapper.TypeToken;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Collections;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

/**
 * 用户业务类
 *
 * @author glacier
 * @version 1.0
 * @date 2019-08-04 21:50
 */
@Transactional(readOnly = true)
@Service(value = "userService")
public class UserServiceImpl implements UserService {
    private static final Logger log = LoggerFactory.getLogger(UserServiceImpl.class);
    private final ModelMapper modelMapper;
    private final UserMapper userMapper;
    private final RoleMapper roleMapper;
    private final UserRoleMapper userRoleMapper;
    private final PasswordEncoder passwordEncoder;

    @Autowired
    public UserServiceImpl(
            ModelMapper modelMapper,
            UserMapper userMapper,
            RoleMapper roleMapper,
            UserRoleMapper userRoleMapper,
            PasswordEncoder passwordEncoder) {
        this.modelMapper = modelMapper;
        this.userMapper = userMapper;
        this.roleMapper = roleMapper;
        this.userRoleMapper = userRoleMapper;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    public User findById(final String id) {
        return this.userMapper.selectByPrimaryKey(id);
    }

    @Override
    public User findUserByUsername(final String username) {
        return this.userMapper.selectOneByUsername(username);
    }

    @Override
    public UserInfo findUserInfoByUsername(String username) {
        User user = this.findUserByUsername(username);
        // 查找角色
        List<Role> roles = this.roleMapper.findByUserId(user.getId());
        UserInfo userInfo = new UserInfo();
        userInfo.setId(user.getId());
        userInfo.setName(user.getUsername());
        userInfo.setAvatar(user.getAvatar());
        userInfo.setRoles(Optional.ofNullable(roles)
                .orElse(Collections.emptyList())
                .stream()
                .map(Role::getCode)
                .collect(Collectors.toList()));
        return userInfo;
    }

    @Override
    public UserProfile findUserProfileByUsername(String username) {
        User user = this.findUserByUsername(username);
        return this.modelMapper.map(user, UserProfile.class);
    }

    @Override
    public Result<UserDetailsDto> loadUserByUsername(String username) {
        if (StringUtil.isEmpty(username)) {
            return Result.error("用户名为空！");
        }
        User user = this.findUserByUsername(username);
        if (user == null || StringUtil.isEmpty(user.getId())) {
            return Result.error("用户不存在！");
        }
        UserDetailsDto userDetailsDto = this.modelMapper.map(user, UserDetailsDto.class);
        userDetailsDto.setRoleDetailDtos(
                this.modelMapper.map(
                        this.roleMapper.findByUserId(user.getId()),
                        new TypeToken<List<RoleDetailsDto>>() {
                        }.getType()));
        return Result.ok(userDetailsDto);
    }

    /**
     * 分页查找
     *
     * @param pageRequest
     * @return
     */
    @Override
    public PageResponse<UserDto> findPage(PageRequest<UserQuery> pageRequest) {
        PageHelper.startPage(pageRequest.getPageNum(), pageRequest.getPageSize());
        List<User> userList = this.userMapper.selectList(pageRequest.getParams());
        PageInfo<User> pageInfo = PageInfo.of(userList);
        return new PageResponse<>(
                pageInfo.getPageNum(),
                pageInfo.getPages(),
                pageInfo.getTotal(),
                this.modelMapper.map(
                        pageInfo.getList(),
                        new TypeToken<List<UserDto>>() {
                        }.getType()));
    }

    @Override
    public List<UserDto> findList(UserQuery userQuery) {
        List<User> userList = this.userMapper.selectList(userQuery);
        return this.modelMapper.map(
                userList,
                new TypeToken<List<UserDto>>() {
                }.getType());
    }

    @Transactional(rollbackFor = {})
    @Override
    public Result<Integer> resetPassword(UserPasswordResetForm passwordResetForm) {
        int update = 0;
        if (passwordResetForm == null
                || StringUtil.isEmpty(passwordResetForm.getId())
                || StringUtil.isEmpty(passwordResetForm.getNewPassword())) {
            return Result.error(SystemErrorType.ARGUMENT_NOT_VALID);
        }
        User user = new User();
        user.setId(passwordResetForm.getId());
        // 加密密码
        user.setPassword(
                this.passwordEncoder.encode(passwordResetForm.getNewPassword()));

        user.preUpdate();
        update = this.userMapper.updatePasswordByPrimaryKey(user);
        return Result.ok("修改成功！", update);
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
                || StringUtil.isEmpty(userPasswordForm.getId())
                || StringUtil.isEmpty(userPasswordForm.getOldPassword())
                || StringUtil.isEmpty(userPasswordForm.getNewPassword())) {
            return Result.error(SystemErrorType.ARGUMENT_NOT_VALID);
        }
        // 判断原始密码是否一致
        User user = this.findById(userPasswordForm.getId());
        if (!this.passwordEncoder.matches(
                userPasswordForm.getOldPassword(),
                user.getPassword())) {
            return Result.error("原始密码不正确！");
        }
        user.preUpdate();
        // 加密密码
        user.setPassword(
                this.passwordEncoder.encode(userPasswordForm.getNewPassword()));
        update = this.userMapper.updatePasswordByPrimaryKey(user);
        return Result.ok("修改成功！", update);
    }

    @Transactional(rollbackFor = {})
    @Override
    public Result<Integer> updateProfile(UserProfileForm userProfileForm) {
        if (userProfileForm == null
                || StringUtil.isEmpty(userProfileForm.getId())) {
            return Result.error(SystemErrorType.ARGUMENT_NOT_VALID);
        }
        User user = this.modelMapper.map(userProfileForm, User.class);
        user.preUpdate();
        return Result.ok("修改成功！",
                this.userMapper.updateProfileByPrimaryKey(user));
    }

    @Transactional(rollbackFor = {})
    @Override
    public Result<Integer> updateAvatar(UserAvatarForm userAvatarForm) {
        if (userAvatarForm == null
                || StringUtil.isEmpty(userAvatarForm.getId())) {
            return Result.error(SystemErrorType.ARGUMENT_NOT_VALID);
        }
        User user = this.modelMapper.map(userAvatarForm, User.class);
        user.preUpdate();
        return Result.ok("修改成功！",
                this.userMapper.updateAvatarByPrimaryKey(user));
    }

    @Override
    public boolean checkUsername(UserDto userDto) {
        if (userDto != null
                && StringUtil.isNotEmpty(userDto.getUsername())) {
            return this.userMapper.checkUsernameExist(userDto) > 0;
        }
        return false;
    }

    @Override
    public boolean checkIdCard(UserDto userDto) {
        if (userDto != null
                && StringUtil.isNotEmpty(userDto.getIdCard())) {
            return this.userMapper.checkIdCardExist(userDto) > 0;
        }
        return false;
    }

    /**
     * 保存用户
     *
     * @param form 用户封装实体
     * @param <T>
     * @return
     */
    @Transactional(rollbackFor = {})
    @Override
    public <T extends AbstractUserDto> int save(T form) {
        if (form == null) {
            return 0;
        }
        User user = this.modelMapper.map(form, User.class);
        if (!user.isNewRecord()) {
            user.preUpdate();
            return this.userMapper.updateByPrimaryKey(user);
        }
        // 对原始密码加密
        user.setPassword(
                this.passwordEncoder.encode(
                        Optional.of(user)
                                .map(User::getPassword)
                                .orElse(SysConstants.DEFAULT_PASSWD)));
        user.preInsert();
        // 处理用户角色
        this.saveUserRole(user.getId(), form.getRoleIds());
        return this.userMapper.insert(user);
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
        if (StringUtil.isBlank(id)) {
            return 0;
        }
        // 删除用户角色关系
        this.userRoleMapper.deleteByUserId(id);
        return this.userMapper.deleteByPrimaryKey(id);
    }

    /**
     * 保存用户角色关系
     *
     * @param userId
     * @param roleIds
     * @return
     */
    @Transactional(rollbackFor = {})
    public int saveUserRole(final String userId, List<String> roleIds) {
        int update = 0;
        // 清空用户角色关系
        this.userRoleMapper.deleteByUserId(userId);
        if (StringUtil.isNotEmpty(userId)
                && roleIds != null
                && !roleIds.isEmpty()) {
            // 保存用户角色关系
            return this.userRoleMapper.insertBatchRole(new UserRoleDto(userId, roleIds));
        }
        return update;
    }
}
