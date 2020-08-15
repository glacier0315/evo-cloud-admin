package com.glacier.authorization.resource.service;

import com.glacier.authorization.resource.entity.dto.UserInfo;
import com.glacier.common.core.entity.Result;

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
    Result<UserInfo> loadUserByUsername(String username);
}
