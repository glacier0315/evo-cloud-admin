package com.glacier.authorization.server.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

/**
 * 登录
 *
 * @author glacier
 * @version 1.0
 * @date 2020-07-27 09:50
 */
@Controller
public class LoginController {
    private static final Logger log = LoggerFactory.getLogger(LoginController.class);
    /**
     * 登录页跳转
     *
     * @return
     */
    @GetMapping(value = "/login")
    public String login() {
        return "login";
    }
}
