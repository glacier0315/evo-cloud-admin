package com.glacier.auth.controller;

import com.glacier.auth.service.RoleService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * 角色控制层
 *
 * @author glacier
 * @version 1.0
 * @date 2020-05-21 17:45
 */
@Slf4j
@RestController
@RequestMapping("role")
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class RoleController {
    private final RoleService roleService;
}
