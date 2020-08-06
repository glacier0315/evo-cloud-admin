package com.glacier.authorization.server.controller;

import com.glacier.authorization.server.entity.pojo.User;
import com.glacier.authorization.server.service.UserService;
import com.glacier.common.core.entity.vo.Result;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * @author glacier
 * @version 1.0
 * @date 2020-07-26 19:26
 */
@Slf4j
@RestController
@RequestMapping("/user")
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class UserController {
    private final UserService userService;

    @GetMapping(value = "/list")
    public Result<List<User>> list() {
        return Result.ok(
                this.userService.findAll());
    }
}
