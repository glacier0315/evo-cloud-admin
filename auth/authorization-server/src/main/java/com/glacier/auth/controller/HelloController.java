package com.glacier.auth.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.context.SecurityContextHolder;
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
    public String index() {
        return SecurityContextHolder.getContext().getAuthentication().getPrincipal().toString();
    }

    @GetMapping(value = "/hello")
    public String hello(String name) {
        return "hello " + name;
    }
}
