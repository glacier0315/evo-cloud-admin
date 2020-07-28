package com.glacier.modules.sys.controller;

import com.glacier.common.core.entity.vo.HttpResult;
import com.glacier.modules.sys.entity.form.MenuForm;
import com.glacier.modules.sys.entity.pojo.Menu;
import com.glacier.modules.sys.entity.vo.RouterVo;
import com.glacier.modules.sys.service.MenuService;
import com.glacier.modules.sys.utils.MenuBuildFactory;
import com.glacier.modules.sys.utils.SecurityUtils;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Set;

/**
 * 菜单控制层
 *
 * @author glacier
 * @version 1.0
 * @date 2019-10-09 15:59
 */
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
    @GetMapping("/list")
    public HttpResult<List<Menu>> list() {
        return HttpResult.ok(
                this.menuService.findAllList());
    }

    /**
     * 添加菜单
     *
     * @param menuForm
     * @return
     */
    @PostMapping("/add")
    public HttpResult<Integer> add(
            @RequestBody MenuForm menuForm) {
        return HttpResult.ok(
                this.menuService.save(menuForm));
    }

    /**
     * 更新菜单
     *
     * @param menuForm
     * @return
     */
    @PutMapping("/update")
    public HttpResult<Integer> update(
            @RequestBody MenuForm menuForm) {
        return HttpResult.ok(
                this.menuService.save(menuForm));
    }

    /**
     * 删除指定菜单
     *
     * @param id
     * @return
     */
    @DeleteMapping("/delete")
    public HttpResult<Integer> delete(String id) {
        return HttpResult.ok(
                this.menuService.delete(id));
    }

    /**
     * 查询角色具有的菜单
     *
     * @return
     */
    @GetMapping("/findByRole")
    public HttpResult<List<String>> findByRole(String roleId) {
        return HttpResult.ok(
                this.menuService.findByRole(roleId));
    }

    /**
     * 查询用户所有路由
     *
     * @return
     */
    @GetMapping(value = "/getRouters")
    public HttpResult<List<RouterVo>> getRouters() {
        String userId = SecurityUtils.geUserId();
        log.debug("userId: {}", userId);
        return HttpResult.ok(
                MenuBuildFactory.buildRouters(
                        this.menuService.findMenuTreeByUserId(userId)));
    }

    /**
     * 查询所有权限标识
     *
     * @return
     */
    @GetMapping(value = "/getPermissions")
    public HttpResult<Set<String>> getPermissions() {
        String userId = SecurityUtils.geUserId();
        log.debug("userId: {}", userId);
        return HttpResult.ok(
                this.menuService.findPermissionsByUserId(userId));
    }
}