package com.glacier.modules.sys.controller;

import com.glacier.common.core.entity.page.PageRequest;
import com.glacier.common.core.entity.page.PageResponse;
import com.glacier.common.core.entity.vo.Result;
import com.glacier.modules.sys.entity.form.role.RoleForm;
import com.glacier.modules.sys.entity.form.role.RoleQueryForm;
import com.glacier.modules.sys.entity.vo.RoleVo;
import com.glacier.modules.sys.service.RoleService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
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
@Api(tags = "角色管理")
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
    @ApiOperation("查找所有角色")
    @GetMapping("/findAll")
    public Result<List<RoleVo>> findAll() {
        return Result.ok(
                this.roleService.findAllList());
    }

    /**
     * 分页查询角色
     *
     * @param pageRequest
     * @return
     */
    @ApiOperation("分页查询角色")
    @PostMapping("/pageList")
    public Result<PageResponse<RoleVo>> findPage(
            @RequestBody PageRequest<RoleQueryForm> pageRequest) {
        return Result.ok(
                this.roleService.findPage(pageRequest));
    }

    /**
     * 保存角色 （新增或者更新）
     *
     * @param roleForm
     * @return
     */
    @ApiOperation("保存角色 （新增或者更新）")
    @PostMapping("/save")
    public Result<Integer> save(
            @RequestBody RoleForm roleForm) {
        return Result.ok(
                this.roleService.save(roleForm));
    }

    /**
     * 删除指定角色
     *
     * @param id
     * @return
     */
    @ApiOperation("删除指定角色")
    @DeleteMapping("/delete")
    public Result<Integer> delete(String id) {
        return Result.ok(
                this.roleService.delete(id));
    }

    /**
     * 检验角色编码
     *
     * @param roleForm
     * @return
     */
    @ApiOperation("检验角色编码")
    @PostMapping("/checkCode")
    public Result<String> checkCode(
            @RequestBody RoleForm roleForm) {
        return Result.ok(
                String.valueOf(
                        this.roleService.checkCode(roleForm)));
    }

    /**
     * 根据用户id查询角色
     *
     * @param userId
     * @return
     */
    @ApiOperation("根据用户id查询角色")
    @GetMapping("/findByUserId")
    public Result<List<RoleVo>> findByUserId(String userId) {
        return Result.ok(
                this.roleService.findByUserId(userId));
    }
}
