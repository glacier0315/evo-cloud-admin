package com.glacier.authorization.server.controller;

import com.glacier.authorization.server.entity.pojo.OauthClientDetails;
import com.glacier.authorization.server.entity.pojo.User;
import com.glacier.authorization.server.entity.pojo.vo.CustomUserClientList;
import com.glacier.authorization.server.service.OauthClientDetailsService;
import com.glacier.authorization.server.service.UserService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * 测试
 *
 * @author glacier
 * @version 1.0
 * @date 2020-02-11 13:50
 */
@Slf4j
@RestController
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class HelloController {
    private final OauthClientDetailsService clientDetailsService;
    private final UserService userService;

    @GetMapping(value = "/")
    public String index() {
        return SecurityContextHolder.getContext().getAuthentication().getPrincipal().toString();
    }

    @GetMapping(value = "/hello")
    public String hello(String name) {
        return "hello " + name;
    }

    @GetMapping(value = "/list")
    public CustomUserClientList list() {
        List<User> userList = this.userService.findAll();
        List<OauthClientDetails> oauthClientDetailsList = this.clientDetailsService.findAll();
        return CustomUserClientList.builder()
                .userList(userList)
                .oauthClientDetailsList(oauthClientDetailsList)
                .build();
    }
}
