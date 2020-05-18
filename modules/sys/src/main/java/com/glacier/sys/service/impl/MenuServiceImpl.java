package com.glacier.sys.service.impl;

import com.glacier.common.core.entity.dto.IdDto;
import com.glacier.sys.common.Constant;
import com.glacier.sys.entity.Menu;
import com.glacier.sys.mapper.MenuMapper;
import com.glacier.sys.service.MenuService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;
import java.util.stream.Collectors;

/**
 * @author glacier
 * @version 1.0
 * @description 菜单业务层
 * @date 2019-10-09 15:45
 */
@Slf4j
@Transactional(readOnly = true)
@Service("MenuService")
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
            update = menuMapper.updateById(menu);
        } else {
            update = menuMapper.insert(menu);
        }
        return update;
    }


    /**
     * 根据id批量删除
     *
     * @param idDtos
     * @return
     */
    @Transactional(rollbackFor = {})
    @Override
    public int batchDelete(List<IdDto> idDtos) {
        if (idDtos != null && !idDtos.isEmpty()) {
            List<String> list = idDtos.stream()
                    .map(IdDto::getId)
                    .collect(Collectors.toList());
            return menuMapper.deleteBatchIds(list);
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
        return menuMapper.findMenusByRoleId(roleId);
    }

    /**
     * 查询菜单树
     *
     * @return
     */
    @Override
    public List<Menu> findMenuTree() {
        List<Menu> menus = menuMapper.selectList(null);
        return this.findMenuTree(menus, true);
    }

    /**
     * 根据用户id 查询菜单树
     *
     * @param userId
     * @return
     */
    @Override
    public List<Menu> findTree(String userId) {
        List<Menu> menus = this.findMenusByUserId(userId);
        return this.findMenuTree(menus);
    }

    /**
     * 根据用户ID 查询权限标识
     *
     * @param userId
     * @return
     */
    @Override
    public Set<String> findPermissions(String userId) {
        Set<String> permissions = new HashSet<>(10);
        if (userId == null) {
            return permissions;
        }
        if (Constant.ADMIN_ID.equals(userId)) {
            permissions = menuMapper.findAllPermissions();
        } else {
            permissions = menuMapper.findPermissionsByUserId(userId);
        }
        if (permissions == null) {
            permissions = new HashSet<>(1);
        }
        return permissions;
    }

    /**
     * 组装菜单树
     *
     * @param menus
     * @return
     */
    private List<Menu> findMenuTree(List<Menu> menus) {
        return this.findMenuTree(menus, false);
    }

    /**
     * 组装菜单树
     *
     * @param menus
     * @return
     */
    private List<Menu> findMenuTree(List<Menu> menus, boolean isContainButton) {
        List<Menu> menuList = new ArrayList<>(10);
        //
        if (menus != null && !menus.isEmpty()) {
            Iterator<Menu> iterator = menus.iterator();
            while (iterator.hasNext()) {
                Menu menu = iterator.next();
                if (menu.getParentId() == null || "".equals(menu.getParentId().trim())
                        || "0".equals(menu.getParentId())) {
                    menuList.add(menu);
                    // 删除
                    iterator.remove();
                }
            }
        }
        // 排序
        menuList.sort(Comparator.comparingInt(Menu::getOrderNum));
        // 组装子类菜单
        findChildren(menuList, menus, isContainButton);
        return menuList;
    }

    /**
     * 递归组装菜单
     *
     * @param menuList 当前父级菜单
     * @param menus    待查询菜单
     */
    private void findChildren(List<Menu> menuList, List<Menu> menus, boolean isContainButton) {
        // 为空则返回
        if (menuList == null || menuList.isEmpty() || menus == null || menus.isEmpty()) {
            return;
        }
        for (Menu parent : menuList) {
            // 非目录 则跳过
            if (!isContainButton && parent.getType() != 1) {
                continue;
            }
            List<Menu> children = new ArrayList<>(10);
            Iterator<Menu> iterator = menus.iterator();
            while (iterator.hasNext()) {
                Menu menu = iterator.next();
                if (!isContainButton && menu.getType() == 3) {
                    // 如果是获取菜单类型是按钮的，直接过滤掉
                    iterator.remove();
                    continue;
                }
                if (parent.getId() != null && parent.getId().equals(menu.getParentId())) {
                    // 处理层级
                    if (parent.getLevel() == null) {
                        parent.setLevel(0);
                    }
                    menu.setParentName(parent.getName());
                    menu.setLevel(parent.getLevel() + 1);
                    children.add(menu);
                    iterator.remove();
                }
            }
            parent.setChildren(children);
            children.sort(Comparator.comparingInt(Menu::getOrderNum));
            findChildren(children, menus, isContainButton);
        }
    }

    /**
     * 根据用户ID查找所有可见菜单
     *
     * @param userId
     * @return
     */
    private List<Menu> findMenusByUserId(String userId) {
        List<Menu> menuList = new ArrayList<>(10);
        if (userId == null) {
            return menuList;
        }
        if (Constant.ADMIN_ID.equals(userId)) {
            menuList = menuMapper.selectList(null);
        } else {
            menuList = menuMapper.findMenusByUserId(userId);
        }
        return menuList;
    }
}
