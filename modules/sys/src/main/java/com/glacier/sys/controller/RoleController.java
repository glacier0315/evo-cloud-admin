package com.glacier.sys.controller;

import com.glacier.common.core.entity.form.IdDto;
import com.glacier.common.core.entity.form.One2ManyRelationDto;
import com.glacier.common.core.entity.page.PageRequest;
import com.glacier.common.core.entity.page.PageResponse;
import com.glacier.common.core.entity.vo.HttpResult;
import com.glacier.common.core.exception.SystemErrorType;
import com.glacier.sys.common.Constant;
import com.glacier.sys.entity.pojo.Role;
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
        return HttpResult.ok(this.roleService.findAllList());
    }

    /**
     * 分页查询角色
     *
     * @param pageRequest
     * @return
     */
    @PostMapping("/findPage")
    public HttpResult<PageResponse<Role>> findPage(@RequestBody PageRequest<Role> pageRequest) {
        return HttpResult.ok(this.roleService.findPage(pageRequest));
    }

    /**
     * 保存角色
     *
     * @param role
     * @return
     */
    @PostMapping("/save")
    public HttpResult<Integer> save(@RequestBody Role role) {
        return HttpResult.ok(this.roleService.save(role));
    }

    /**
     * 删除指定角色
     *
     * @param idDtos
     * @return
     */
    @PostMapping("/delete")
    public HttpResult<Integer> delete(@RequestBody List<IdDto> idDtos) {
        return HttpResult.ok(this.roleService.batchDelete(idDtos));
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

    /**
     * 保存角色菜单
     *
     * @param one2ManyRelationDto
     * @return
     */
    @PostMapping("/saveRoleMenus")
    public HttpResult<Integer> saveRoleMenus(@RequestBody One2ManyRelationDto one2ManyRelationDto) {
        assert one2ManyRelationDto != null;
        if (one2ManyRelationDto.getPid() != null
                && one2ManyRelationDto.getPid().trim().length() > 0) {
            // 判断超级管理员
            Role role = this.roleService.findById(one2ManyRelationDto.getPid());
            if (Constant.ADMIN.equals(role.getCode())) {
                // 如果是超级管理员，不允许修改
                // todo 如果是超级管理员，不允许修改
                return HttpResult.error(SystemErrorType.SYSTEM_ERROR);
            }
        }
        return HttpResult.ok(this.roleService.saveRoleMenu(one2ManyRelationDto.getPid(), one2ManyRelationDto.getIds()));
    }
}
