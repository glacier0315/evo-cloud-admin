package com.glacier.sys.service;

import com.glacier.common.core.entity.dto.IdDto;
import com.glacier.common.core.entity.page.PageRequest;
import com.glacier.common.core.entity.page.PageResponse;
import com.glacier.common.core.entity.vo.HttpResult;
import com.glacier.sys.entity.dto.UserInfo;
import com.glacier.sys.entity.pojo.User;

import java.util.List;

/**
 * 用户业务层
 * @author glacier
 * @version 1.0
 * @date 2019-08-04 21:50
 */
public interface UserService {

    /**
     * 根据用户名查用户
     *
     * @param username
     * @return
     */
    HttpResult<UserInfo> loadUserInfoByUsername(String username);

    /**
     * 根据用户名查找用户
     *
     * @param username
     * @return
     */
    User loadUserByUsername(String username);

    /**
     * 分页查询
     *
     * @param pageRequest
     * @return
     */
    PageResponse<User> findPage(PageRequest<User> pageRequest);

    /**
     * 更新操作
     *
     * @param record
     * @return
     */
    int update(User record);

    /**
     * 保存操作，同时保存用户角色
     *
     * @param record
     * @return
     */
    int saveUserRole(User record);

    /**
     * 根据Id批量删除
     *
     * @param idDtos
     * @return
     */
    int batchDelete(List<IdDto> idDtos);
}
