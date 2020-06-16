package com.glacier.sys.controller;

import com.glacier.common.core.entity.vo.HttpResult;
import com.glacier.sys.entity.vo.UserInfo;
import com.glacier.sys.service.UserService;
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
    @GetMapping(value = "/userInfo")
    public HttpResult<UserInfo> userInfo(Authentication authentication) {
        log.info(">>>>>>>>>>>>>>>>>>>>>>>>");
        log.info("当前登录用户为: {}", authentication);
        log.info(">>>>>>>>>>>>>>>>>>>>>>>>");
        String username = ((Jwt) authentication.getPrincipal()).getClaim("user_name");
        return HttpResult.ok(this.userService.findUserInfoByUsername(username));
    }
}