package com.glacier.sys.utils;

import com.glacier.common.core.utils.StringUtils;
import com.glacier.sys.common.Constant;
import com.glacier.sys.entity.pojo.Menu;
import com.glacier.sys.entity.vo.MetaVo;
import com.glacier.sys.entity.vo.RouterVo;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.Iterator;
import java.util.List;

/**
 * 菜单构建工厂
 *
 * @author glacier
 * @version 1.0
 * @date 2020-06-16 21:08
 */
public class MenuBuildFactory {

    private MenuBuildFactory() {
    }

    /**
     * 组装路由
     *
     * @param menus
     * @return
     */
    public static List<RouterVo> buildRouters(final List<Menu> menus) {
        List<RouterVo> routerVos = new ArrayList<>(1);
        if (menus != null && !menus.isEmpty()) {
            Iterator<Menu> iterator = menus.iterator();
            while (iterator.hasNext()) {
                Menu menu = iterator.next();

                RouterVo routerVo = new RouterVo();
                routerVo.setName(menu.getName());
                routerVo.setPath(menu.getPath());
                routerVo.setComponent(StringUtils.isEmpty(menu.getComponent()) ? "Layout" : menu.getComponent());
                routerVo.setMeta(MetaVo.builder()
                        .icon(menu.getIcon())
                        .title(menu.getTitle())
                        .build());

                // 处理子菜单
                if (menu.getChildren() != null && !menu.getChildren().isEmpty()) {
                    routerVo.setAlwaysShow(true);
                    routerVo.setRedirect("noRedirect");
                    routerVo.setChildren(buildRouters(menu.getChildren()));
                }
                routerVos.add(routerVo);
            }
        }
        return routerVos;
    }

    /**
     * 组装菜单树
     *
     * @param menus
     * @return
     */
    public static List<Menu> buildMenuTree(final List<Menu> menus) {
        List<Menu> menuList = new ArrayList<>(10);
        // 分离出所有一级资源菜单
        if (menus != null && !menus.isEmpty()) {
            Iterator<Menu> iterator = menus.iterator();
            while (iterator.hasNext()) {
                Menu menu = iterator.next();
                if (menu.getParentId() == null
                        || "".equals(menu.getParentId())) {
                    menuList.add(menu);
                    // 删除
                    iterator.remove();
                }
            }
        }
        // 排序
        menuList.sort(Comparator.comparingInt(Menu::getOrderNum));
        // 组装子类菜单
        buildChildren(menuList, menus);
        return menuList;
    }

    /**
     * 递归组装菜单
     *
     * @param menuList 当前父级菜单
     * @param menus    待查询菜单
     */
    private static void buildChildren(final List<Menu> menuList, final List<Menu> menus) {
        // 为空则返回
        if (menuList == null
                || menuList.isEmpty()
                || menus == null
                || menus.isEmpty()) {
            return;
        }
        for (Menu parent : menuList) {
            // 非目录 则跳过
            if (parent.getType() != Constant.MENU_DICTORY) {
                continue;
            }
            List<Menu> children = new ArrayList<>(10);
            Iterator<Menu> iterator = menus.iterator();
            while (iterator.hasNext()) {
                Menu menu = iterator.next();
                if (menu.getType() == Constant.MENU_PERMISSION) {
                    // 如果是获取菜单类型是按钮的，直接过滤掉
                    iterator.remove();
                    continue;
                }
                if (parent.getId() != null
                        && parent.getId().equals(menu.getParentId())) {
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
            buildChildren(children, menus);
        }
    }
}
