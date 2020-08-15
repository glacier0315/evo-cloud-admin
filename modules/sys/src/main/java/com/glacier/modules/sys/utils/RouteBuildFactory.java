package com.glacier.modules.sys.utils;

import com.glacier.modules.sys.common.Constant;
import com.glacier.modules.sys.entity.dto.menu.MenuVo;
import com.glacier.modules.sys.entity.dto.menu.Meta;
import com.glacier.modules.sys.entity.dto.menu.Router;

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
                .map(menuVos -> menuVos.stream()
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
                            if (menu.getChildren() != null
                                    && !menu.getChildren().isEmpty()) {
                                router.setAlwaysShow(true);
                                router.setRedirect("noRedirect");
                                router.setChildren(buildRouters(menu.getChildren()));
                            }
                            return router;
                        })
                        .collect(Collectors.toList()))
                .orElseGet(ArrayList::new);
    }
}
