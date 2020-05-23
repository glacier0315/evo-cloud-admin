package com.glacier.auth.service;

import com.glacier.auth.entity.vo.UserInfo;
import com.glacier.common.core.entity.dto.result.HttpResult;

/**
 * 用户业务层
 *
 * @author glacier
 * @version 1.0
 * @date 2020-05-21 17:41
 */
public interface UserService {

    /**
     * 根据用户名查用户
     *
     * @param username
     * @return
     */
    HttpResult<UserInfo> loadUserByUsername(String username);
}
