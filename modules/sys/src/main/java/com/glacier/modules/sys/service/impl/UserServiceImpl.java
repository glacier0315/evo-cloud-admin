package com.glacier.modules.sys.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.glacier.common.core.constant.SysConstants;
import com.glacier.common.core.entity.Result;
import com.glacier.common.core.entity.dto.UserDetailsDto;
import com.glacier.common.core.entity.page.PageRequest;
import com.glacier.common.core.entity.page.PageResponse;
import com.glacier.common.core.exception.SystemErrorType;
import com.glacier.modules.sys.convert.RoleConvert;
import com.glacier.modules.sys.convert.UserConvert;
import com.glacier.modules.sys.entity.Role;
import com.glacier.modules.sys.entity.User;
import com.glacier.modules.sys.entity.dto.user.*;
import com.glacier.modules.sys.mapper.RoleMapper;
import com.glacier.modules.sys.mapper.UserMapper;
import com.glacier.modules.sys.mapper.UserRoleMapper;
import com.glacier.modules.sys.service.UserService;
import org.apache.commons.lang3.StringUtils;
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
 * date 2019-08-04 21:50
 */
@Transactional(readOnly = true)
@Service(value = "userService")
public class UserServiceImpl implements UserService {
    private static final Logger log = LoggerFactory.getLogger(UserServiceImpl.class);
    @Autowired
    private UserConvert userConvert;
    @Autowired
    private RoleConvert roleConvert;
    @Autowired
    private UserMapper userMapper;
    @Autowired
    private RoleMapper roleMapper;
    @Autowired
    private UserRoleMapper userRoleMapper;
    @Autowired
    private PasswordEncoder passwordEncoder;

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
        return this.userConvert.toUserProfile(
                this.findUserByUsername(username));
    }

    @Override
    public Result<UserDetailsDto> loadUserByUsername(String username) {
        if (StringUtils.isEmpty(username)) {
            return Result.error("用户名为空！");
        }
        User user = this.findUserByUsername(username);
        if (user == null || StringUtils.isEmpty(user.getId())) {
            return Result.error("用户不存在！");
        }
        UserDetailsDto userDetailsDto = this.userConvert.toUserDetailsDto(user);
        userDetailsDto.setRoleDetailDtos(
                this.roleConvert.toRoleDetailsDtos(
                        this.roleMapper.findByUserId(user.getId())));
        return Result.ok(userDetailsDto);
    }

    /**
     * 分页查找
     *
     * @param pageRequest 分页请求
     * @return 分页响应
     */
    @Override
    public PageResponse<UserDto> findPage(PageRequest<UserQuery> pageRequest) {
        PageHelper.startPage(pageRequest.getPageNum(), pageRequest.getPageSize());
        PageInfo<User> pageInfo = PageInfo.of(
                this.userMapper.selectList(pageRequest.getParams()));
        return new PageResponse<>(
                pageInfo.getPageNum(),
                pageInfo.getPages(),
                pageInfo.getTotal(),
                this.userConvert.toUserDto(pageInfo.getList()));
    }

    @Override
    public List<UserDto> findList(UserQuery userQuery) {
        return this.userConvert.toUserDto(
                this.userMapper.selectList(userQuery));
    }
    
    @Transactional(rollbackFor = {})
    @Override
    public Result<Integer> resetPassword(UserPasswordResetForm passwordResetForm) {
        int update = 0;
        if (passwordResetForm == null
                || StringUtils.isEmpty(passwordResetForm.getId())
                || StringUtils.isEmpty(passwordResetForm.getNewPassword())) {
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
     * @param userPasswordForm 记录
     * @return 响应
     */
    @Transactional(rollbackFor = {})
    @Override
    public Result<Integer> updatePassword(UserPasswordForm userPasswordForm) {
        int update = 0;
        if (userPasswordForm == null
                || StringUtils.isEmpty(userPasswordForm.getId())
                || StringUtils.isEmpty(userPasswordForm.getOldPassword())
                || StringUtils.isEmpty(userPasswordForm.getNewPassword())) {
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
        User user = this.userConvert.map(userProfileForm);
        if (user == null
                || StringUtils.isEmpty(user.getId())) {
            return Result.error(SystemErrorType.ARGUMENT_NOT_VALID);
        }
        user.preUpdate();
        return Result.ok("修改成功！",
                this.userMapper.updateProfileByPrimaryKey(user));
    }

    @Transactional(rollbackFor = {})
    @Override
    public Result<Integer> updateAvatar(UserAvatarForm userAvatarForm) {
        User user = this.userConvert.map(userAvatarForm);
        if (user == null
                || StringUtils.isEmpty(user.getId())) {
            return Result.error(SystemErrorType.ARGUMENT_NOT_VALID);
        }
        user.preUpdate();
        return Result.ok("修改成功！",
                this.userMapper.updateAvatarByPrimaryKey(user));
    }

    @Override
    public boolean checkUsername(UserDto userDto) {
        if (userDto != null
                && StringUtils.isNotEmpty(userDto.getUsername())) {
            return this.userMapper.checkUsernameExist(userDto) > 0;
        }
        return false;
    }

    @Override
    public boolean checkIdCard(UserDto userDto) {
        if (userDto != null
                && StringUtils.isNotEmpty(userDto.getIdCard())) {
            return this.userMapper.checkIdCardExist(userDto) > 0;
        }
        return false;
    }
    
    /**
     * 保存用户，同时处理用户角色关系
     *
     * @param userAddForm 用户封装实体
     * @return 保存记录数
     */
    @Transactional(rollbackFor = {})
    @Override
    public int save(UserAddForm userAddForm) {
        if (userAddForm == null) {
            return 0;
        }
        return this.save(
                this.userConvert.map(userAddForm),
                userAddForm.getRoleIds());
    }
    
    /**
     * 保存用户，同时处理用户角色关系
     *
     * @param userDto 用户封装实体
     * @return 保存记录数
     */
    @Transactional(rollbackFor = {})
    @Override
    public int save(UserDto userDto) {
        if (userDto == null) {
            return 0;
        }
        return this.save(
                this.userConvert.map(userDto),
                userDto.getRoleIds());
    }

    /**
     * 保存用户
     *
     * @param user 用户
     * @param roleIds 角色ids
     * @return 响应
     */
    protected int save(User user, List<String> roleIds) {
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
        this.saveUserRole(user.getId(), roleIds);
        return this.userMapper.insert(user);
    }

    /**
     * 根据id删除
     *
     * @param id 主键
     * @return 删除记录数
     */
    @Transactional(rollbackFor = {})
    @Override
    public int delete(String id) {
        if (StringUtils.isBlank(id)) {
            return 0;
        }
        // 删除用户角色关系
        this.userRoleMapper.deleteByUserId(id);
        return this.userMapper.deleteByPrimaryKey(id);
    }

    /**
     * 保存用户角色关系
     *
     * @param userId 用户id
     * @param roleIds 角色ids
     * @return 保存记录数
     */
    @Transactional(rollbackFor = {})
    public int saveUserRole(final String userId, List<String> roleIds) {
        int update = 0;
        // 清空用户角色关系
        this.userRoleMapper.deleteByUserId(userId);
        if (StringUtils.isNotEmpty(userId)
                && roleIds != null
                && !roleIds.isEmpty()) {
            // 保存用户角色关系
            return this.userRoleMapper.insertBatchRole(new UserRoleDto(userId, roleIds));
        }
        return update;
    }
}
