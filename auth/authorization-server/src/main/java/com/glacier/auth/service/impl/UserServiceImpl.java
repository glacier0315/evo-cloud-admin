package com.glacier.auth.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.glacier.auth.entity.User;
import com.glacier.auth.mapper.UserMapper;
import com.glacier.auth.service.UserService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * @author glacier
 * @version 1.0
 * @description 用户业务类
 * @date 2019-08-04 21:50
 */
@Slf4j
@Transactional(readOnly = true)
@Service(value = "userService")
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class UserServiceImpl implements UserService {
    private final UserMapper userMapper;

    @Override
    public User getByUsername(String username) {
        return userMapper.selectOne(new QueryWrapper<>(User
                .builder()
                .username(username)
                .build()));
    }
}
