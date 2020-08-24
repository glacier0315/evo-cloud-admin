package com.glacier.modules.sys.controller;

import com.glacier.common.core.entity.Result;
import com.glacier.common.core.entity.dto.IdDto;
import com.glacier.common.security.utils.SecurityUtils;
import com.glacier.modules.sys.entity.dto.menu.MenuForm;
import com.glacier.modules.sys.entity.dto.menu.MenuVo;
import com.glacier.modules.sys.entity.dto.menu.Router;
import com.glacier.modules.sys.service.MenuService;
import com.glacier.modules.sys.utils.RouteBuildFactory;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Set;

/**
 * 菜单管理
 *
 * @author glacier
 * @version 1.0
 * @date 2019-10-09 15:59
 */
@Api(tags = "菜单管理")
@Slf4j
@RestController
@RequestMapping(value = "/menu")
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class MenuController {

    private final MenuService menuService;

    /**
     * 查询所有菜单
     *
     * @return
     */
    @ApiOperation("查询所有菜单")
    @GetMapping("/list")
    public Result<List<MenuVo>> list() {
        return Result.ok(
                this.menuService.findAllList());
    }

    /**
     * 保存菜单
     *
     * @param menuForm
     * @return
     */
    @ApiOperation("保存菜单")
    @PostMapping("/save")
    public Result<Integer> save(
            @RequestBody MenuForm menuForm) {
        return Result.ok(
                this.menuService.save(menuForm));
    }

    /**
     * 删除指定菜单
     *
     * @param idDto
     * @return
     */
    @ApiOperation("删除指定菜单")
    @PostMapping("/delete")
    public Result<Integer> delete(@RequestBody IdDto idDto) {
        return Result.ok(
                this.menuService.delete(idDto.getId()));
    }

    /**
     * 查询角色具有的菜单
     *
     * @return
     */
    @ApiOperation("查询角色具有的菜单")
    @GetMapping("/findByRole")
    public Result<List<String>> findByRole(String roleId) {
        return Result.ok(
                this.menuService.findByRole(roleId));
    }

    /**
     * 查询用户所有路由
     *
     * @return
     */
    @ApiOperation("查询用户所有路由")
    @GetMapping(value = "/getRouters")
    public Result<List<Router>> getRouters() {
        String userId = SecurityUtils.geUserId();
        log.debug("userId: {}", userId);
        return Result.ok(
                RouteBuildFactory.buildRouters(
                        this.menuService.findMenuTreeByUserId(userId)));
    }

    /**
     * 查询所有权限标识
     *
     * @return
     */
    @ApiOperation("查询所有权限标识")
    @GetMapping(value = "/getPermissions")
    public Result<Set<String>> getPermissions() {
        String userId = SecurityUtils.geUserId();
        log.debug("userId: {}" , userId);
        return Result.ok(
                this.menuService.findPermissionsByUserId(userId));
    }
}
