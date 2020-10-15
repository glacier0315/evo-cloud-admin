package com.glacier.authorization.server.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
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
@RestController
public class HelloController {
    private static final Logger log = LoggerFactory.getLogger(HelloController.class);
    @GetMapping(value = "/")
    public String index() {
        return SecurityContextHolder.getContext().getAuthentication().getPrincipal().toString();
    }

    @GetMapping(value = "/hello")
    public String hello(String name) {
        return "hello " + name;
    }

}
