package com.glacier.sys.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * 测试
 *
 * @author glacier
 * @version 1.0
 * @date 2020-02-11 13:50
 */
@Slf4j
@RestController
public class HelloController {

    @GetMapping(value = "/")
    public Object index(Authentication authentication) {
        Object principal = authentication.getPrincipal();
        log.info(">>>>>>>>>>>>>>>>>>>>>>>>");
        log.info("index 当前登录用户为: {}", principal);
        log.info(">>>>>>>>>>>>>>>>>>>>>>>>");
        return principal;
    }

    @GetMapping(value = "/hello")
    public String hello(String name) {
        return "hello " + name;
    }
}
