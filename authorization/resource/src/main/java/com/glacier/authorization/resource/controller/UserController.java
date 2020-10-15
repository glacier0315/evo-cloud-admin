package com.glacier.authorization.resource.controller;

import com.glacier.authorization.resource.entity.dto.UserInfo;
import com.glacier.authorization.resource.service.UserService;
import com.glacier.common.core.entity.Result;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
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
@RestController
@RequestMapping("user")
public class UserController {
    private static final Logger log = LoggerFactory.getLogger(UserController.class);
    private final UserService userService;

    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }

    /**
     * 查看当前用户信息
     *
     * @return
     */
    @RequestMapping(value = "/info", method = {RequestMethod.GET, RequestMethod.POST})
    public Result<UserInfo> userInfo() {
        Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        log.info(">>>>>>>>>>>>>>>>>>>>>>>>");
        log.info("当前登录用户为: {}" , principal);
        log.info(">>>>>>>>>>>>>>>>>>>>>>>>");
        return this.userService.loadUserByUsername((String) principal);
    }
}
