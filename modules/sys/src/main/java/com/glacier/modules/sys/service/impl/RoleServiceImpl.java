package com.glacier.modules.sys.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.glacier.common.core.entity.page.PageRequest;
import com.glacier.common.core.entity.page.PageResponse;
import com.glacier.modules.sys.entity.form.RoleForm;
import com.glacier.modules.sys.entity.form.RoleQueryForm;
import com.glacier.modules.sys.entity.pojo.Role;
import com.glacier.modules.sys.entity.pojo.RoleMenu;
import com.glacier.modules.sys.entity.pojo.UserRole;
import com.glacier.modules.sys.mapper.RoleMapper;
import com.glacier.modules.sys.mapper.RoleMenuMapper;
import com.glacier.modules.sys.mapper.UserRoleMapper;
import com.glacier.modules.sys.service.RoleService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.io.Serializable;
import java.util.Arrays;
import java.util.List;

/**
 * 角色业务类
 *
 * @author glacier
 * @version 1.0
 * @date 2019-08-11 21:21
 */
@Slf4j
@Transactional(readOnly = true)
@Service("roleService")
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class RoleServiceImpl implements RoleService {
    private final ModelMapper modelMapper;
    private final RoleMapper roleMapper;
    private final UserRoleMapper userRoleMapper;
    private final RoleMenuMapper roleMenuMapper;

    @Override
    public Role findById(Serializable id) {
        return this.roleMapper.selectById(id);
    }

    /**
     * 查询所有
     *
     * @return
     */
    @Override
    public List<Role> findAllList() {
        return this.roleMapper.selectList(null);
    }

    /**
     * 根据用户id 查询角色
     *
     * @param userId
     * @return
     */
    @Override
    public List<Role> findByUserId(String userId) {
        return this.roleMapper.findByUserId(userId);
    }

    @Override
    public boolean checkCode(Role role) {
        if (role != null && role.getCode() != null
                && role.getCode().trim().length() > 0) {
            Role role1 = null;
            if (role.getId() != null && role.getId().trim().length() > 0) {
                role1 = this.roleMapper.selectById(role.getId());
                if (role1 != null
                        && role1.getCode() != null
                        && role1.getCode().equals(role.getCode())) {
                    return false;
                }
            }
            Integer count = this.roleMapper.selectCount(
                    new QueryWrapper<>(role));
            if (count != null && count > 0) {
                return true;
            }
        }
        return false;
    }

    /**
     *
     * @param pageRequest
     * @return
     */
    @Override
    public PageResponse<Role> findPage(PageRequest<RoleQueryForm> pageRequest) {
        Page<Role> page = this.roleMapper.selectPage(
                new Page<>(
                        pageRequest.getCurrent(),
                        pageRequest.getSize()),
                new QueryWrapper<>(
                        this.modelMapper.map(
                                pageRequest.getParams(),
                                Role.class)));
        return PageResponse.<Role>builder()
                .current(page.getCurrent())
                .size(page.getSize())
                .total(page.getTotal())
                .records(page.getRecords())
                .build();
    }

    /**
     * 保存
     *
     * @param roleForm
     * @return
     */
    @Transactional(rollbackFor = {})
    @Override
    public int save(RoleForm roleForm) {
        Role role = this.modelMapper.map(roleForm, Role.class);
        int update = 0;
        if (role.getId() != null && !role.getId().isEmpty()) {
            update = this.roleMapper.updateById(role);
        } else {
            update = this.roleMapper.insert(role);
        }
        // 保存角色和菜单
        this.saveRoleMenu(
                role.getId(),
                Arrays.asList(
                        roleForm.getMenus()));
        return update;
    }

    /**
     * 根据id批量删除
     *
     * @param id
     * @return
     */
    @Transactional(rollbackFor = {})
    @Override
    public int delete(String id) {
        int unpdate = 0;
        if (id != null && !id.isEmpty()) {
            unpdate = this.roleMapper.deleteById(id);
            // 删除用户角色关系
            this.deleteUserRoleByRoleId(id);
            // 删除角色资源关系
            this.deleteRoleMenuByRoleId(id);
        }
        return unpdate;
    }

    /**
     * 保存角色菜单关系
     * 1 先清空
     * 2 保存
     * @param roleId
     * @param menuIds
     * @return
     */
    private int saveRoleMenu(String roleId, List<String> menuIds) {
        int update = 0;
        // 清空原角色和菜单关系
        this.roleMenuMapper.delete(
                new UpdateWrapper<>(
                        RoleMenu.builder()
                                .roleId(roleId)
                                .build()));
        // 保存角色菜单关系
        if (roleId != null && !roleId.isEmpty()
                && menuIds != null && !menuIds.isEmpty()) {
            for (String menuId : menuIds) {
                update += this.roleMenuMapper.insert(
                        RoleMenu.builder()
                                .roleId(roleId)
                                .menuId(menuId)
                                .build());
            }
        }
        return update;
    }

    /**
     * 删除用户角色关系
     *
     * @param roleId
     * @return
     */
    private int deleteUserRoleByRoleId(final String roleId) {
        int update = 0;
        if (roleId != null && !roleId.isEmpty()) {
            update = this.userRoleMapper.delete(
                    new UpdateWrapper<>(
                            UserRole.builder()
                                    .roleId(roleId)
                                    .build()));
        }
        return update;
    }

    /**
     * 删除角色菜单关系
     *
     * @param roleId
     * @return
     */
    private int deleteRoleMenuByRoleId(final String roleId) {
        int update = 0;
        if (roleId != null && !roleId.isEmpty()) {
            update = this.roleMenuMapper.delete(
                    new UpdateWrapper<>(
                            RoleMenu.builder()
                                    .roleId(roleId)
                                    .build()));
        }
        return update;
    }
}
