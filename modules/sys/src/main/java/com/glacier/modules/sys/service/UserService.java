package com.glacier.modules.sys.service;

import com.glacier.common.core.entity.page.PageRequest;
import com.glacier.common.core.entity.page.PageResponse;
import com.glacier.common.core.entity.vo.Result;
import com.glacier.modules.sys.entity.form.user.UserAvatarForm;
import com.glacier.modules.sys.entity.form.user.UserPasswordForm;
import com.glacier.modules.sys.entity.form.user.UserProfileForm;
import com.glacier.modules.sys.entity.form.user.UserQueryForm;
import com.glacier.modules.sys.entity.pojo.User;
import com.glacier.modules.sys.entity.vo.user.UserDetails;
import com.glacier.modules.sys.entity.vo.user.UserInfo;
import com.glacier.modules.sys.entity.vo.user.UserProfile;
import com.glacier.modules.sys.entity.vo.user.UserVo;

/**
 * 用户业务层
 *
 * @author glacier
 * @version 1.0
 * @date 2019-08-04 21:50
 */
public interface UserService {

    /**
     * 根据用户id 查询
     *
     * @param id
     * @return
     */
    User findById(String id);

    /**
     * 根据用户名查询
     *
     * @param username
     * @return
     */
    User findUserByUsername(String username);

    /**
     * 根据用户名查用户
     *
     * @param username
     * @return
     */
    UserInfo findUserInfoByUsername(String username);

    /**
     * 根据用户名查用户
     *
     * @param username
     * @return
     */
    UserProfile findUserProfileByUsername(String username);

    /**
     * 根据用户名查找用户
     *
     * @param username
     * @return
     */
    UserDetails loadUserByUsername(String username);

    /**
     * 分页查询
     *
     * @param pageRequest
     * @return
     */
    PageResponse<UserVo> findPage(PageRequest<UserQueryForm> pageRequest);

    /**
     * 保存用户
     *
     * @param form 用户封装实体
     * @param <T>  用户封装类型
     * @return
     */
    <T> int save(T form);

    /**
     * 根据Id删除
     *
     * @param id
     * @return
     */
    int delete(String id);

    /**
     * 修改密码
     *
     * @param userPasswordForm
     * @return
     */
    Result<Integer> updatePassword(UserPasswordForm userPasswordForm);

    /**
     * 更新个人信息
     *
     * @param userProfileForm
     * @return
     */
    Result<Integer> updateProfile(UserProfileForm userProfileForm);

    /**
     * 更新头像
     *
     * @param userAvatarForm
     * @return
     */
    Result<Integer> updateAvatar(UserAvatarForm userAvatarForm);
}
