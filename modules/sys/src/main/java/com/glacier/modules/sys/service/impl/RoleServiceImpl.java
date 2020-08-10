package com.glacier.modules.sys.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.glacier.common.core.entity.page.PageRequest;
import com.glacier.common.core.entity.page.PageResponse;
import com.glacier.common.core.utils.IdGen;
import com.glacier.modules.sys.entity.form.RoleForm;
import com.glacier.modules.sys.entity.form.RoleQueryForm;
import com.glacier.modules.sys.entity.pojo.Role;
import com.glacier.modules.sys.entity.pojo.RoleMenu;
import com.glacier.modules.sys.mapper.RoleMapper;
import com.glacier.modules.sys.mapper.RoleMenuMapper;
import com.glacier.modules.sys.mapper.UserRoleMapper;
import com.glacier.modules.sys.service.RoleService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.concurrent.atomic.AtomicReference;

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
    public Role findById(String id) {
        return this.roleMapper.selectByPrimaryKey(id)
                .orElseThrow(() -> new IllegalArgumentException("角色不存在!"));
    }

    /**
     * 查询所有
     *
     * @return
     */
    @Override
    public List<Role> findAllList() {
        return this.roleMapper.selectAll();
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
        AtomicReference<Boolean> ifExists = new AtomicReference<>(false);
        Optional.ofNullable(role)
                .map(Role::getCode)
                .ifPresent(s -> {
                    ifExists.set(
                            this.roleMapper.selectCount(role) > 0);
                });
        return ifExists.get();
    }

    /**
     *
     * @param pageRequest
     * @return
     */
    @Override
    public PageResponse<Role> findPage(PageRequest<RoleQueryForm> pageRequest) {
        PageHelper.startPage(pageRequest.getPageNum(), pageRequest.getPageSize());
        List<Role> roleList = this.roleMapper.selectList(this.modelMapper.map(
                pageRequest.getParams(), Role.class));
        PageInfo<Role> pageInfo = PageInfo.of(roleList);
        return new PageResponse<>(
                pageInfo.getPageNum(),
                pageInfo.getPages(),
                pageInfo.getTotal(),
                pageInfo.getList());
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
        if (StringUtils.isNotEmpty(role.getId())) {
            update = this.roleMapper.updateByPrimaryKey(role);
        } else {
            role.setId(IdGen.uuid());
            update = this.roleMapper.insert(role);
        }
        // 保存角色和菜单
        this.saveRoleMenu(role.getId(),
                Arrays.asList(roleForm.getMenus()));
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
        if (StringUtils.isNotEmpty(roleId)) {
            // 清空原角色和菜单关系
            this.roleMenuMapper.deleteByRoleId(roleId);
            // 保存角色菜单关系
            if (menuIds != null && !menuIds.isEmpty()) {
                for (String menuId : menuIds) {
                    update += this.roleMenuMapper.insert(
                            RoleMenu.builder()
                                    .id(IdGen.uuid())
                                    .roleId(roleId)
                                    .menuId(menuId)
                                    .build());
                }
            }
        }
        return update;
    }
}
