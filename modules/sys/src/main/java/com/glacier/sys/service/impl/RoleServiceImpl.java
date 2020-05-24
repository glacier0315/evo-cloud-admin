package com.glacier.sys.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.glacier.common.core.entity.form.IdForm;
import com.glacier.common.core.entity.page.PageRequest;
import com.glacier.common.core.entity.page.PageResponse;
import com.glacier.sys.entity.pojo.Role;
import com.glacier.sys.entity.pojo.RoleMenu;
import com.glacier.sys.entity.pojo.UserRole;
import com.glacier.sys.mapper.RoleMapper;
import com.glacier.sys.mapper.RoleMenuMapper;
import com.glacier.sys.mapper.UserRoleMapper;
import com.glacier.sys.service.RoleService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.io.Serializable;
import java.util.List;
import java.util.stream.Collectors;

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
        if (role != null && role.getCode() != null && role.getCode().trim().length() > 0) {
            Role role1 = null;
            if (role.getId() != null && role.getId().trim().length() > 0) {
                role1 = this.roleMapper.selectById(role.getId());
                if (role1 != null && role1.getCode() != null && role1.getCode().equals(role.getCode())) {
                    return false;
                }
            }
            List<Role> list = this.roleMapper.selectList(new QueryWrapper<>(role));
            if (list != null && !list.isEmpty()) {
                return true;
            }
        }
        return false;
    }

    /**
     * 这个方法中用到了开头配置依赖的分页插件pagehelper
     * 很简单，只需要在service层传入参数，然后将参数传递给一个插件的一个静态方法即可；
     *
     * @param pageRequest
     * @return
     */
    @Override
    public PageResponse<Role> findPage(PageRequest<Role> pageRequest) {
        Page<Role> page = this.roleMapper.selectPage(new Page<>(pageRequest.getCurrent(), pageRequest.getSize()),
                new QueryWrapper<>(pageRequest.getParams()));
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
     * @param record
     * @return
     */
    @Transactional(rollbackFor = {})
    @Override
    public int save(Role record) {
        int update = 0;
        if (record.getId() != null && !record.getId().isEmpty()) {
            update = this.roleMapper.updateById(record);
        } else {
            update = this.roleMapper.insert(record);
        }
        return update;
    }

    /**
     * 根据id批量删除
     *
     * @param idForms
     * @return
     */
    @Transactional(rollbackFor = {})
    @Override
    public int batchDelete(List<IdForm> idForms) {
        int unpdate = 0;
        if (idForms != null && !idForms.isEmpty()) {
            List<String> list = idForms.stream()
                    .map(IdForm::getId)
                    .collect(Collectors.toList());
            unpdate = this.roleMapper.deleteBatchIds(list);
            for (String roleId : list) {
                // 删除用户角色关系
                this.deleteUserRoleByRoleId(roleId);
                // 删除角色资源关系
                this.deleteRoleMenuByRoleId(roleId);
            }
        }
        return unpdate;
    }

    /**
     * 保存角色菜单关系
     *
     * @param roleId
     * @param menuIds
     * @return
     */
    @Override
    public int saveRoleMenu(String roleId, List<String> menuIds) {
        int update = 0;
        if (roleId != null && !roleId.isEmpty() && menuIds != null && !menuIds.isEmpty()) {
            for (String menuId : menuIds) {
                update += this.roleMenuMapper.insert(RoleMenu.builder().roleId(roleId).menuId(menuId).build());
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
            update = this.userRoleMapper.delete(new UpdateWrapper<>(UserRole.builder().roleId(roleId).build()));
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
            update = this.roleMenuMapper.delete(new UpdateWrapper<>(RoleMenu.builder().roleId(roleId).build()));
        }
        return update;
    }
}
