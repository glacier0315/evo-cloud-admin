package com.glacier.modules.sys.controller;

import com.glacier.common.core.entity.Result;
import com.glacier.modules.sys.entity.dto.role.RoleUserDto;
import com.glacier.modules.sys.entity.dto.user.UserRoleDto;
import com.glacier.modules.sys.service.UserRoleService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * 用户角色关系管理
 *
 * @author glacier
 * @version 1.0
 * date 2020-09-01 11:55
 */
@Api(tags = "用户角色关系管理")
@RestController
@RequestMapping("/userRole")
public class UserRoleController {
    private static final Logger log = LoggerFactory.getLogger(UserRoleController.class);
    private final UserRoleService userRoleService;

    @Autowired
    public UserRoleController(UserRoleService userRoleService) {
        this.userRoleService = userRoleService;
    }

    /**
     * 移除角色用户关系
     *
     * @param roleUserDto 角色用户关系
     * @return 移除角色用户关系响应
     */
    @ApiOperation("移除角色用户关系")
    @PostMapping("/deleteUser")
    public Result<Integer> deleteUser(
            @RequestBody RoleUserDto roleUserDto) {
        return Result.ok(
                this.userRoleService.deleteUser(roleUserDto));
    }

    /**
     * 保存角色用户关系
     *
     * @param roleUserDto 角色用户关系
     * @return 保存角色用户关系响应
     */
    @ApiOperation("保存角色用户关系")
    @PostMapping("/addUser")
    public Result<Integer> addUser(
            @RequestBody RoleUserDto roleUserDto) {
        return Result.ok(
                this.userRoleService.addUser(roleUserDto));
    }


    /**
     * 移除用户角色关系
     *
     * @param userRoleDto 用户角色关系
     * @return 移除用户角色关系响应
     */
    @ApiOperation("移除用户角色关系")
    @PostMapping("/deleteRole")
    public Result<Integer> deleteRole(
            @RequestBody UserRoleDto userRoleDto) {
        return Result.ok(
                this.userRoleService.deleteRole(userRoleDto));
    }

    /**
     * 保存用户角色关系
     *
     * @param userRoleDto 用户角色关系
     * @return 保存用户角色关系响应
     */
    @ApiOperation("保存用户角色关系")
    @PostMapping("/addRole")
    public Result<Integer> addRole(
            @RequestBody UserRoleDto userRoleDto) {
        return Result.ok(
                this.userRoleService.addRole(userRoleDto));
    }
}
