package com.glacier.sys.service.impl;

import com.glacier.common.core.entity.form.IdForm;
import com.glacier.sys.common.Constant;
import com.glacier.sys.entity.pojo.Menu;
import com.glacier.sys.mapper.MenuMapper;
import com.glacier.sys.service.MenuService;
import com.glacier.sys.utils.MenuBuildFactory;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

/**
 * 菜单业务层
 *
 * @author glacier
 * @version 1.0
 * @date 2019-10-09 15:45
 */
@Slf4j
@Transactional(readOnly = true)
@Service("menuService")
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class MenuServiceImpl implements MenuService {

    private final MenuMapper menuMapper;

    /**
     * 保存
     *
     * @param menu
     * @return
     */
    @Transactional(rollbackFor = {})
    @Override
    public int save(Menu menu) {
        int update = 0;
        if (menu.getId() != null && !menu.getId().isEmpty()) {
            update = this.menuMapper.updateById(menu);
        } else {
            update = this.menuMapper.insert(menu);
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
        if (idForms != null && !idForms.isEmpty()) {
            List<String> list = idForms.stream()
                    .map(IdForm::getId)
                    .collect(Collectors.toList());
            return this.menuMapper.deleteBatchIds(list);
        }
        return 0;
    }

    /**
     * 根据角色id查询菜单
     *
     * @param roleId
     * @return
     */
    @Override
    public List<Menu> findMenusByRoleId(String roleId) {
        return this.menuMapper.findMenusByRoleId(roleId);
    }

    @Override
    public List<Menu> findMenuTree() {
        List<Menu> menuList = this.menuMapper.selectList(null);
        return MenuBuildFactory.buildMenuTree(menuList);
    }

    /**
     * 根据用户id 查询菜单树
     *
     * @param userId
     * @return
     */
    @Override
    public List<Menu> findMenuTreeByUserId(String userId) {
        List<Menu> menuList = new ArrayList<>(1);
        if (userId == null || userId.isEmpty()) {
            return menuList;
        }
        if (Constant.ADMIN_ID.equals(userId)) {
            menuList = this.menuMapper.selectList(null);
        } else {
            menuList = this.menuMapper.findMenusByUserId(userId);
        }
        return MenuBuildFactory.buildMenuTree(menuList);
    }

    /**
     * 根据用户ID 查询权限标识
     *
     * @param userId
     * @return
     */
    @Override
    public Set<String> findPermissionsByUserId(String userId) {
        Set<String> permissions = new HashSet<>(1);
        if (userId == null || userId.isEmpty()) {
            return permissions;
        }
        if (Constant.ADMIN_ID.equals(userId)) {
            permissions = this.menuMapper.findAllPermissions();
        } else {
            permissions = this.menuMapper.findPermissionsByUserId(userId);
        }
        if (permissions == null) {
            permissions = new HashSet<>(1);
        }
        return permissions;
    }


}
