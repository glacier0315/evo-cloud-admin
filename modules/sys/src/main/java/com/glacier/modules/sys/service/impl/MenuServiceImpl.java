package com.glacier.modules.sys.service.impl;

import com.glacier.common.core.utils.TreeBuildFactory;
import com.glacier.modules.sys.common.Constant;
import com.glacier.modules.sys.entity.form.MenuForm;
import com.glacier.modules.sys.entity.pojo.Menu;
import com.glacier.modules.sys.mapper.MenuMapper;
import com.glacier.modules.sys.service.MenuService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;

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
    private final ModelMapper modelMapper;

    /**
     * 保存
     *
     * @param menuForm
     * @return
     */
    @Transactional(rollbackFor = {})
    @Override
    public int save(MenuForm menuForm) {
        Menu menu = this.modelMapper.map(menuForm, Menu.class);
        if (menu.isNewRecord()) {
            menu.preInsert();
            return this.menuMapper.insert(menu);
        }
        menu.preUpdate();
        return this.menuMapper.updateByPrimaryKey(menu);
    }


    /**
     * 根据id删除
     *
     * @param id
     * @return
     */
    @Transactional(rollbackFor = {})
    @Override
    public int delete(String id) {
        if (StringUtils.isNotEmpty(id)) {
            return this.menuMapper.deleteByPrimaryKey(id);
        }
        return 0;
    }

    @Override
    public List<String> findByRole(String roleId) {
        return this.menuMapper.findMenuIdsByRoleId(roleId);
    }

    @Override
    public List<Menu> findAllList() {
        return this.menuMapper.selectAll();
    }

    @Override
    public List<Menu> findMenuTree() {
        List<Menu> menuList = this.findAllList();
        return TreeBuildFactory.buildMenuTree(menuList);
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
            menuList = this.menuMapper.selectAll();
        } else {
            menuList = this.menuMapper.findByUserId(userId);
        }
        return TreeBuildFactory.buildMenuTree(menuList);
    }

    /**
     * 根据用户ID 查询权限标识
     *
     * @param userId
     * @return
     */
    @Override
    public Set<String> findPermissionsByUserId(String userId) {
        if (userId == null || userId.isEmpty()) {
            return new HashSet<>(1);
        }
        Set<String> permissions = null;
        if (Constant.ADMIN_ID.equals(userId)) {
            permissions = this.menuMapper.findAllPermissions();
        } else {
            permissions = this.menuMapper.findPermissionsByUserId(userId);
        }
        return Optional.ofNullable(permissions)
                .orElseGet(HashSet::new);
    }


}
