package com.glacier.modules.sys.controller;

import com.glacier.common.core.entity.page.PageRequest;
import com.glacier.common.core.entity.page.PageResponse;
import com.glacier.common.core.entity.vo.HttpResult;
import com.glacier.modules.sys.entity.form.RoleForm;
import com.glacier.modules.sys.entity.form.RoleQueryForm;
import com.glacier.modules.sys.entity.pojo.Role;
import com.glacier.modules.sys.service.RoleService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * 角色管理
 *
 * @author glacier
 * @version 1.0
 * @date 2019-08-11 21:24
 */
@RestController
@RequestMapping(value = "/role")
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class RoleController {

    private final RoleService roleService;

    /**
     * 查找所有角色
     *
     * @return
     */
    @GetMapping("/findAll")
    public HttpResult<List<Role>> findAll() {
        return HttpResult.ok(
                this.roleService.findAllList());
    }

    /**
     * 分页查询角色
     *
     * @param pageRequest
     * @return
     */
    @PostMapping("/pageList")
    public HttpResult<PageResponse<Role>> findPage(
            @RequestBody PageRequest<RoleQueryForm> pageRequest) {
        return HttpResult.ok(
                this.roleService.findPage(pageRequest));
    }

    /**
     * 新增角色
     *
     * @param roleForm
     * @return
     */
    @PostMapping("/add")
    public HttpResult<Integer> add(
            @RequestBody RoleForm roleForm) {
        return HttpResult.ok(
                this.roleService.save(roleForm));
    }

    /**
     * 更新角色
     *
     * @param roleForm
     * @return
     */
    @PutMapping("/update")
    public HttpResult<Integer> update(
            @RequestBody RoleForm roleForm) {
        return HttpResult.ok(
                this.roleService.save(roleForm));
    }

    /**
     * 删除指定角色
     *
     * @param id
     * @return
     */
    @DeleteMapping("/delete")
    public HttpResult<Integer> delete(String id) {
        return HttpResult.ok(
                this.roleService.delete(id));
    }

    /**
     * 检验角色编码
     *
     * @param role
     * @return
     */
    @PostMapping("/checkCode")
    public HttpResult<String> checkCode(
            @RequestBody Role role) {
        HttpResult<String> httpResult = HttpResult.ok();
        httpResult.setData(String.valueOf(this.roleService.checkCode(role)));
        return httpResult;
    }

    /**
     * 根据用户id查询角色
     *
     * @param userId
     * @return
     */
    @GetMapping("/findByUserId")
    public HttpResult<List<Role>> findByUserId(String userId) {
        return HttpResult.ok(this.roleService.findByUserId(userId));
    }
}
