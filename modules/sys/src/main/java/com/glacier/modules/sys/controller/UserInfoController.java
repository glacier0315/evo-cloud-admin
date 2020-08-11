package com.glacier.modules.sys.controller;

import com.glacier.common.core.entity.vo.Result;
import com.glacier.modules.sys.entity.vo.user.UserInfo;
import com.glacier.modules.sys.service.UserService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.oauth2.jwt.Jwt;
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
public class UserInfoController {
    private final UserService userService;

    /**
     * 获取当前用户信息
     *
     * @param authentication
     * @return
     */
    @ApiOperation("获取当前用户信息")
    @GetMapping(value = "/userInfo")
    public Result<UserInfo> userInfo(Authentication authentication) {
        log.info(">>>>>>>>>>>>>>>>>>>>>>>>");
        log.info("当前登录用户为: {}" , authentication);
        log.info(">>>>>>>>>>>>>>>>>>>>>>>>");
        String username = ((Jwt) authentication.getPrincipal())
                .getClaim("user_name");
        return Result.ok(
                this.userService.findUserInfoByUsername(username));
    }
}
