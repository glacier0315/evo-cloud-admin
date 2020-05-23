package com.glacier.auth.service;

import com.glacier.auth.entity.pojo.User;

/**
 * 用户业务层
 * @author glacier
 * @version 1.0
 * @date 2019-08-04 21:50
 */
public interface UserService {

    /**
     * 根据用户唯一标识获取用户信息
     *
     * @param username
     * @return
     */
    User getByUsername(String username);
}
