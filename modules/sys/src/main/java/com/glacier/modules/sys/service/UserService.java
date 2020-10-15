package com.glacier.modules.sys.service;

import com.glacier.common.core.entity.Result;
import com.glacier.common.core.entity.dto.vo.UserDetailsDto;
import com.glacier.common.core.entity.page.PageRequest;
import com.glacier.common.core.entity.page.PageResponse;
import com.glacier.modules.sys.entity.User;
import com.glacier.modules.sys.entity.dto.user.*;

import java.util.List;

/**
 * 用户业务层
 *
 * @author glacier
 * @version 1.0
 * date 2019-08-04 21:50
 */
public interface UserService {

    /**
     * 根据用户id 查询
     *
     * @param id 主键
     * @return 用户
     */
    User findById(String id);

    /**
     * 根据用户名查询
     *
     * @param username 用户名
     * @return 用户
     */
    User findUserByUsername(String username);

    /**
     * 根据用户名查用户
     *
     * @param username 用户名
     * @return 用户信息
     */
    UserInfo findUserInfoByUsername(String username);

    /**
     * 根据用户名查用户
     *
     * @param username 用户名
     * @return 用户
     */
    UserProfile findUserProfileByUsername(String username);

    /**
     * 根据用户名查找用户
     *
     * @param username 用户名
     * @return 用户
     */
    Result<UserDetailsDto> loadUserByUsername(String username);

    /**
     * 分页查询
     *
     * @param pageRequest 分页请求
     * @return 分页响应
     */
    PageResponse<UserDto> findPage(PageRequest<UserQuery> pageRequest);

    /**
     * 分页查询
     *
     * @param userQuery 用户查询
     * @return 用户集合
     */
    List<UserDto> findList(UserQuery userQuery);

    /**
     * 保存用户，同时处理用户角色关系
     *
     * @param form 用户封装实体
     * @param <T>  用户封装类型
     * @return 保存记录数
     */
    <T extends AbstractUserDto> int save(T form);

    /**
     * 根据Id删除
     *
     * @param id 主键
     * @return 删除记录数
     */
    int delete(String id);

    /**
     * 重置密码
     *
     * @param passwordResetForm 密码修改实体
     * @return 响应
     */
    Result<Integer> resetPassword(UserPasswordResetForm passwordResetForm);

    /**
     * 修改密码
     *
     * @param userPasswordForm 密码修改实体
     * @return 响应
     */
    Result<Integer> updatePassword(UserPasswordForm userPasswordForm);

    /**
     * 更新个人信息
     *
     * @param userProfileForm 个信信息实体
     * @return 响应
     */
    Result<Integer> updateProfile(UserProfileForm userProfileForm);

    /**
     * 更新头像
     *
     * @param userAvatarForm 头像实体
     * @return 响应
     */
    Result<Integer> updateAvatar(UserAvatarForm userAvatarForm);

    /**
     * 检验用户名
     *
     * @param userDto 用户名
     * @return 检验结果
     */
    boolean checkUsername(UserDto userDto);

    /**
     * 检验身份证号
     * @param userDto 身份证号
     * @return 检验结果
     */
    boolean checkIdCard(UserDto userDto);
}
