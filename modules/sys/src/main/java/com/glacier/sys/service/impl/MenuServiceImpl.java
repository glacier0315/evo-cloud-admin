package com.glacier.sys.service.impl;

import com.glacier.common.core.entity.form.IdForm;
import com.glacier.sys.common.Constant;
import com.glacier.sys.entity.pojo.Menu;
import com.glacier.sys.entity.vo.RouterVo;
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

    /**
     * 根据用户id 查询菜单树
     *
     * @param userId
     * @return
     */
    @Override
    public List<Menu> findMenusByUserId(String userId) {
        List<Menu> menuList = new ArrayList<>(1);
        if (userId == null || userId.isEmpty()) {
            return menuList;
        }
        if (Constant.ADMIN_ID.equals(userId)) {
            menuList = this.menuMapper.selectList(null);
        } else {
            menuList = this.menuMapper.findMenusByUserId(userId);
        }
        if (menuList == null) {
            menuList = new ArrayList<>(1);
        }
        return menuList;
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

    /**
     * 组装路由
     * @param menus
     * @return
     */
    @Override
    public List<RouterVo> buildRouters(List<Menu> menus) {
        List<RouterVo> routerVos = new ArrayList<>(1);
        if (menus != null && !menus.isEmpty()) {

        }
        return routerVos;
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
        this.findChildren(menuList, menus, isContainButton);
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
                    menu.setLevel(parent.getLevel() + 1);
                    children.add(menu);
                    iterator.remove();
                }
            }
            parent.setChildren(children);
            children.sort(Comparator.comparingInt(Menu::getOrderNum));
            this.findChildren(children, menus, isContainButton);
        }
    }
}
