package com.glacier.authorization.resource.controller;

import com.glacier.authorization.resource.service.RoleService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
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
@RestController
@RequestMapping("role")
public class RoleController {
    private static final Logger log = LoggerFactory.getLogger(RoleController.class);
    private final RoleService roleService;

    @Autowired
    public RoleController(RoleService roleService) {
        this.roleService = roleService;
    }
}
