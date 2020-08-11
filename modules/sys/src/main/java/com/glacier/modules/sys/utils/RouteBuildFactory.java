package com.glacier.modules.sys.utils;

import com.glacier.modules.sys.common.Constant;
import com.glacier.modules.sys.entity.vo.MenuVo;
import com.glacier.modules.sys.entity.vo.Meta;
import com.glacier.modules.sys.entity.vo.Router;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

/**
 * 菜单构建工厂
 *
 * @author glacier
 * @version 1.0
 * @date 2020-06-16 21:08
 */
public class RouteBuildFactory {

    private RouteBuildFactory() {
    }

    /**
     * 组装路由
     *
     * @param menus
     * @return
     */
    public static List<Router> buildRouters(final List<MenuVo> menus) {
        return Optional.ofNullable(menus)
                .orElseGet(ArrayList::new)
                .stream()
                .filter(menu ->
                        !(menu.getType() != null
                                && menu.getType() == Constant.MENU_PERMISSION))
                .map(menu -> {
                    Router router = new Router();
                    router.setName(menu.getName());
                    router.setPath(menu.getPath());
                    router.setComponent(
                            Optional.ofNullable(menu.getComponent())
                                    .orElseGet(() -> "Layout"));
                    router.setMeta(
                            Meta.builder()
                                    .icon(menu.getIcon())
                                    .title(menu.getName())
                                    .build());

                    // 处理子菜单
                    Optional.ofNullable(menu.getChildren())
                            .ifPresent(menuList -> {
                                router.setAlwaysShow(true);
                                router.setRedirect("noRedirect");
                                router.setChildren(buildRouters(menuList));
                            });
                    return router;
                })
                .collect(Collectors.toList());
    }
}
