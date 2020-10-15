package com.glacier.authorization.resource.service.impl;

import com.glacier.authorization.resource.entity.User;
import com.glacier.authorization.resource.entity.dto.UserInfo;
import com.glacier.authorization.resource.mapper.RoleMapper;
import com.glacier.authorization.resource.mapper.UserMapper;
import com.glacier.authorization.resource.service.UserService;
import com.glacier.common.core.entity.Result;
import com.glacier.common.core.exception.AuthErrorType;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * 用户业务层
 *
 * @author glacier
 * @version 1.0
 * @date 2020-05-21 17:42
 */
@Transactional(readOnly = true)
@Service(value = "userService")
public class UserServiceImpl implements UserService {
    private static final Logger log = LoggerFactory.getLogger(UserServiceImpl.class);
    private final UserMapper userMapper;
    private final RoleMapper roleMapper;

    @Autowired
    public UserServiceImpl(UserMapper userMapper,
                           RoleMapper roleMapper) {
        this.userMapper = userMapper;
        this.roleMapper = roleMapper;
    }

    /**
     * 根据用户名查用户
     *
     * @param username
     * @return
     */
    @Override
    public Result<UserInfo> loadUserByUsername(String username) {
        User user = this.userMapper.selectOneByUsername(username);
        if (user != null) {
            // 查找角色
            List<String> roles = this.roleMapper.findCodeByUserId(user.getId());
            UserInfo userInfo = new UserInfo();
            userInfo.setId(user.getId());
            userInfo.setName(user.getUsername());
            userInfo.setRoles(roles);
            return Result.ok(userInfo);
        }
        return Result.error(AuthErrorType.INVALID_GRANT);
    }
}
