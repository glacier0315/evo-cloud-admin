package com.glacier.modules.sys.controller;

import com.glacier.common.core.entity.Result;
import com.glacier.common.core.entity.dto.vo.UserDetailsDto;
import com.glacier.modules.sys.entity.dto.user.UserInfo;
import com.glacier.modules.sys.service.UserService;
import com.glacier.modules.sys.utils.SecurityUtils;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * 用户信息 端点
 * @author glacier
 * @version 1.0
 * @date 2020-05-24 18:27
 */
@Api(tags = "用户信息管理")
@Slf4j
@RestController
@RequestMapping(value = "/oauth")
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class OauthController {
    private final UserService userService;

    /**
     * 获取当前用户信息
     *
     * @return 当前用户信息
     */
    @ApiOperation("获取当前用户信息")
    @GetMapping(value = "/userInfo")
    public Result<UserInfo> userInfo() {
        return Result.ok(
                this.userService.findUserInfoByUsername(SecurityUtils.getUsername()));
    }

    /**
     * 根据用户名获取用户信息
     * 涉及到认证服务器登录调用，需排除权限
     *
     * @param username 用户名
     * @return 用户详情响应
     */
    @ApiOperation("根据用户名查询用户")
    @GetMapping(value = "/user")
    public Result<UserDetailsDto> findByUsername(
            String username) {
        return this.userService.loadUserByUsername(username);
    }
}
