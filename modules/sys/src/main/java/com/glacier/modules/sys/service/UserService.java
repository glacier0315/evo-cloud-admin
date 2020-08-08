package com.glacier.modules.sys.service;

import com.glacier.common.core.entity.form.IdForm;
import com.glacier.common.core.entity.page.PageRequest;
import com.glacier.common.core.entity.page.PageResponse;
import com.glacier.common.core.entity.vo.Result;
import com.glacier.modules.sys.entity.form.user.UserAddForm;
import com.glacier.modules.sys.entity.form.user.UserPasswordForm;
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
     * 更新操作
     *
     * @param form 更新封装类
     * @param <T>
     * @return
     */
    <T extends IdForm> int update(T form);

    /**
     * 保存用户
     *
     * @param userAddForm
     * @return
     */
    int add(UserAddForm userAddForm);

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
}
