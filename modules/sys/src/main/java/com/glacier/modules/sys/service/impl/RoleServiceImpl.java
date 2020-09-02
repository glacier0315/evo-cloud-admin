package com.glacier.modules.sys.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.glacier.common.core.constant.SysConstants;
import com.glacier.common.core.entity.page.PageRequest;
import com.glacier.common.core.entity.page.PageResponse;
import com.glacier.modules.sys.entity.Role;
import com.glacier.modules.sys.entity.RoleDept;
import com.glacier.modules.sys.entity.RoleMenu;
import com.glacier.modules.sys.entity.dto.role.RoleForm;
import com.glacier.modules.sys.entity.dto.role.RoleQueryForm;
import com.glacier.modules.sys.entity.dto.role.RoleVo;
import com.glacier.modules.sys.mapper.RoleDeptMapper;
import com.glacier.modules.sys.mapper.RoleMapper;
import com.glacier.modules.sys.mapper.RoleMenuMapper;
import com.glacier.modules.sys.mapper.UserRoleMapper;
import com.glacier.modules.sys.service.RoleService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.modelmapper.ModelMapper;
import org.modelmapper.TypeToken;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

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
    private final RoleDeptMapper roleDeptMapper;

    @Override
    public RoleVo findById(String id) {
        return this.modelMapper.map(
                this.roleMapper.selectByPrimaryKey(id), RoleVo.class);
    }

    /**
     * 查询所有
     *
     * @return
     */
    @Override
    public List<RoleVo> findAllList() {
        return this.modelMapper.map(
                this.roleMapper.selectAll(),
                new TypeToken<List<RoleVo>>() {
                }.getType());
    }

    /**
     * 根据用户id 查询角色
     *
     * @param userId
     * @return
     */
    @Override
    public List<RoleVo> findByUserId(String userId) {
        return this.modelMapper.map(
                this.roleMapper.findByUserId(userId),
                new TypeToken<List<RoleVo>>() {
                }.getType());
    }

    @Override
    public boolean checkCode(final RoleForm roleForm) {
        if (roleForm != null
                && StringUtils.isNotEmpty(roleForm.getCode())) {
            Role role = new Role();
            role.setId(roleForm.getId());
            role.setCode(roleForm.getCode());
            return this.roleMapper.selectCount(role) > 0;
        }
        return false;
    }

    /**
     * @param pageRequest
     * @return
     */
    @Override
    public PageResponse<RoleVo> findPage(PageRequest<RoleQueryForm> pageRequest) {
        PageHelper.startPage(pageRequest.getPageNum(), pageRequest.getPageSize());
        List<Role> roleList = this.roleMapper.selectList(pageRequest.getParams());
        PageInfo<Role> pageInfo = PageInfo.of(roleList);
        return new PageResponse<>(
                pageInfo.getPageNum(),
                pageInfo.getPages(),
                pageInfo.getTotal(),
                this.modelMapper.map(
                        pageInfo.getList(),
                        new TypeToken<List<RoleVo>>() {
                        }.getType()));
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
        if (!role.isNewRecord()) {
            role.preUpdate();
            update = this.roleMapper.updateByPrimaryKey(role);
        } else {
            role.preInsert();
            update = this.roleMapper.insert(role);
        }
        // 保存角色和菜单
        this.saveRoleMenu(role.getId(), roleForm.getMenus());
        // 保存角色和单位
        this.saveRoleDept(role.getId(), role.getDataScope(), roleForm.getDepts());
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
        if (StringUtils.isNotEmpty(id)) {
            unpdate = this.roleMapper.deleteByPrimaryKey(id);
            // 删除用户角色关系
            this.userRoleMapper.deleteByRoleId(id);
            // 删除角色资源关系
            this.roleMenuMapper.deleteByRoleId(id);
            // 删除角色单位关系
            this.roleDeptMapper.deleteByRoleId(id);
        }
        return unpdate;
    }

    /**
     * 保存角色菜单关系
     * 1 先清空
     * 2 保存
     *
     * @param roleId
     * @param menuIds
     * @return
     */
    private int saveRoleMenu(String roleId, List<String> menuIds) {
        int update = 0;
        if (StringUtils.isNotEmpty(roleId)) {
            // 清空原角色和菜单关系
            this.roleMenuMapper.deleteByRoleId(roleId);
            // 保存角色菜单关系
            if (menuIds != null
                    && !menuIds.isEmpty()) {
                for (String menuId : menuIds) {
                    update += this.roleMenuMapper.insert(new RoleMenu(roleId, menuId));
                }
            }
        }
        return update;
    }

    /**
     * 保存角色单位关系
     * 1 先清空
     * 2 保存 仅自定义时保存
     *
     * @param roleId
     * @param dataScope
     * @param deptIds
     * @return
     */
    private int saveRoleDept(String roleId, String dataScope, List<String> deptIds) {
        int update = 0;
        if (StringUtils.isNotEmpty(roleId)) {
            // 清空原角色和单位关系
            this.roleDeptMapper.deleteByRoleId(roleId);
            // 保存角单位单关系
            if (SysConstants.DATASCOPE_CUSTOMIZE_DEPT.equals(dataScope)
                    && deptIds != null
                    && !deptIds.isEmpty()) {
                for (String deptId : deptIds) {
                    update += this.roleDeptMapper.insert(new RoleDept(roleId, deptId));
                }
            }
        }
        return update;
    }
}
