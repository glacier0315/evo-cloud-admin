package com.glacier.auth.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.glacier.auth.entity.pojo.User;
import com.glacier.auth.entity.vo.UserInfo;
import com.glacier.auth.mapper.RoleMapper;
import com.glacier.auth.mapper.UserMapper;
import com.glacier.auth.service.UserService;
import com.glacier.common.core.entity.vo.HttpResult;
import com.glacier.common.core.exception.AuthErrorType;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
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
@Slf4j
@Transactional(readOnly = true)
@Service(value = "userService")
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class UserServiceImpl implements UserService {

    private final UserMapper userMapper;
    private final RoleMapper roleMapper;

    /**
     * 根据用户名查用户
     *
     * @param username
     * @return
     */
    @Override
    public HttpResult<UserInfo> loadUserByUsername(String username) {
        User user = userMapper.selectOne(new QueryWrapper<>(User
                .builder()
                .username(username)
                .build()));
        if (user != null) {
            // 查找角色
            List<String> roles = roleMapper.findCodeByUserId(user.getId());
            UserInfo userInfo = UserInfo.builder()
                    .id(user.getId())
                    .name(user.getUsername())
                    .roles(roles)
                    .build();
            return HttpResult.ok(userInfo);
        } else {
            return HttpResult.error(AuthErrorType.INVALID_GRANT);
        }
    }
}
