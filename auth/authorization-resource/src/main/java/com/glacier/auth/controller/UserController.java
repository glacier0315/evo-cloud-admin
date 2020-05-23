package com.glacier.auth.controller;

import com.glacier.auth.entity.vo.UserInfo;
import com.glacier.auth.service.UserService;
import com.glacier.common.core.entity.dto.result.HttpResult;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

/**
 * 用户控制层
 *
 * @author glacier
 * @version 1.0
 * @date 2020-05-21 17:45
 */
@Slf4j
@RestController
@RequestMapping("user")
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class UserController {
    private final UserService userService;

    /**
     * 查看当前用户信息
     *
     * @return
     */
    @RequestMapping(value = "/info", method = {RequestMethod.GET, RequestMethod.POST})
    public HttpResult<UserInfo> userInfo() {
        Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        log.info(">>>>>>>>>>>>>>>>>>>>>>>>");
        log.info("当前登录用户为: {}", principal);
        log.info(">>>>>>>>>>>>>>>>>>>>>>>>");
        return this.userService.loadUserByUsername((String) principal);
    }
}
