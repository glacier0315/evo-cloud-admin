package com.glacier.authorization.server.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

/**
 * @author glacier
 * @version 1.0
 * @date 2020-07-27 09:50
 */
@Slf4j
@Controller
public class LoginController {

    @GetMapping(value = "/login")
    public String login() {
        return "login";
    }
}
