package com.glacier.authorization.resource.service.impl;

import com.glacier.authorization.resource.entity.pojo.User;
import com.glacier.authorization.resource.entity.vo.UserInfo;
import com.glacier.authorization.resource.mapper.RoleMapper;
import com.glacier.authorization.resource.mapper.UserMapper;
import com.glacier.authorization.resource.service.UserService;
import com.glacier.common.core.entity.vo.Result;
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
    public Result<UserInfo> loadUserByUsername(String username) {
        User user = this.userMapper.selectOneByUsername(username);
        if (user != null) {
            // 查找角色
            List<String> roles = this.roleMapper.findCodeByUserId(user.getId());
            UserInfo userInfo = UserInfo.builder()
                    .id(user.getId())
                    .name(user.getUsername())
                    .roles(roles)
                    .build();
            return Result.ok(userInfo);
        } else {
            return Result.error(AuthErrorType.INVALID_GRANT);
        }
    }
}
