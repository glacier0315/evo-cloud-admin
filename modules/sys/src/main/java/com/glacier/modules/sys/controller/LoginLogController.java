package com.glacier.modules.sys.controller;

import io.swagger.annotations.Api;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * 登录日志管理
 * @author glacier
 * @version 1.0
 * @date 2020-09-05 17:12
 */
@Api(tags = "登录日志管理")
@RestController
@RequestMapping("/logs/login")
public class LoginLogController {
    private static final Logger log = LoggerFactory.getLogger(LoginLogController.class);
}
