package com.glacier.sys.controller;

import com.glacier.common.core.entity.dto.IdDto;
import com.glacier.common.core.entity.dto.ParentChildrenDto;
import com.glacier.common.core.http.HttpResult;
import com.glacier.common.core.page.PageRequest;
import com.glacier.common.core.page.PageResponse;
import com.glacier.sys.common.Constant;
import com.glacier.sys.entity.Role;
import com.glacier.sys.service.RoleService;
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
        return HttpResult.ok(roleService.findAllList());
    }

    /**
     * 分页查询角色
     *
     * @param pageRequest
     * @return
     */
    @PostMapping("/findPage")
    public HttpResult<PageResponse<Role>> findPage(@RequestBody PageRequest<Role> pageRequest) {
        return HttpResult.ok(roleService.findPage(pageRequest));
    }

    /**
     * 保存角色
     *
     * @param role
     * @return
     */
    @PostMapping("/save")
    public HttpResult<Integer> save(@RequestBody Role role) {
        return HttpResult.ok(roleService.save(role));
    }

    /**
     * 删除指定角色
     *
     * @param idDtos
     * @return
     */
    @PostMapping("/delete")
    public HttpResult<Integer> delete(@RequestBody List<IdDto> idDtos) {
        return HttpResult.ok(roleService.batchDelete(idDtos));
    }

    /**
     * 检验角色编码
     *
     * @param role
     * @return
     */
    @PostMapping("/checkCode")
    public HttpResult<String> checkCode(@RequestBody Role role) {
        HttpResult<String> httpResult = HttpResult.ok();
        httpResult.setData(String.valueOf(roleService.checkCode(role)));
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
        return HttpResult.ok(roleService.findByUserId(userId));
    }

    /**
     * 保存角色菜单
     *
     * @param parentChildrenDto
     * @return
     */
    @PostMapping("/saveRoleMenus")
    public HttpResult<Integer> saveRoleMenus(@RequestBody ParentChildrenDto parentChildrenDto) {
        if (parentChildrenDto != null && parentChildrenDto.getParentId() != null
                && parentChildrenDto.getParentId().trim().length() > 0) {
            // 判断超级管理员
            Role role = roleService.findById(parentChildrenDto.getParentId());
            if (Constant.ADMIN.equals(role.getCode())) {
                // 如果是超级管理员，不允许修改
                return HttpResult.error("超级管理员拥有所有菜单权限，不允许修改！");
            }
        }
        assert parentChildrenDto != null;
        return HttpResult.ok(roleService.saveRoleMenu(parentChildrenDto.getParentId(), parentChildrenDto.getChildrenIds()));
    }
}
