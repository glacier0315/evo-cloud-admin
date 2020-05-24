package com.glacier.sys.service;

import com.glacier.common.core.entity.form.IdForm;
import com.glacier.common.core.entity.page.PageRequest;
import com.glacier.common.core.entity.page.PageResponse;
import com.glacier.sys.entity.form.UserAddForm;
import com.glacier.sys.entity.form.UserQueryForm;
import com.glacier.sys.entity.form.UserUpdateForm;
import com.glacier.sys.entity.pojo.User;
import com.glacier.sys.entity.vo.UserDetailsVo;
import com.glacier.sys.entity.vo.UserInfo;
import com.glacier.sys.entity.vo.UserListVo;

import java.util.List;

/**
 * 用户业务层
 * @author glacier
 * @version 1.0
 * @date 2019-08-04 21:50
 */
public interface UserService {

    /**
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
     * 根据用户名查找用户
     *
     * @param username
     * @return
     */
    UserDetailsVo loadUserByUsername(String username);

    /**
     * 分页查询
     *
     * @param pageRequest
     * @return
     */
    PageResponse<UserListVo> findPage(PageRequest<UserQueryForm> pageRequest);

    /**
     * 更新操作
     *
     * @param userUpdateForm
     * @return
     */
    int update(UserUpdateForm userUpdateForm);

    /**
     * 保存用户
     *
     * @param userAddForm
     * @return
     */
    int save(UserAddForm userAddForm);

    /**
     * 根据Id批量删除
     *
     * @param idForms
     * @return
     */
    int batchDelete(List<IdForm> idForms);
}
