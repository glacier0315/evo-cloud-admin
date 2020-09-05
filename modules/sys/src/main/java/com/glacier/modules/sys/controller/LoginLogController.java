package com.glacier.modules.sys.controller;

import io.swagger.annotations.Api;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * 登录日志管理
 * @author glacier
 * @version 1.0
 * @date 2020-09-05 17:12
 */
@Api(tags = "登录日志管理")
@Slf4j
@RestController
@RequestMapping("/logs/login")
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class LoginLogController {
}
