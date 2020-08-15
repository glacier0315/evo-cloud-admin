package com.glacier.modules.sys.utils;

import com.glacier.modules.sys.entity.dto.menu.MenuVo;
import com.glacier.modules.sys.service.MenuService;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import javax.annotation.Resource;
import java.util.List;
import java.util.Optional;

/**
 * @author glacier
 * @version 1.0
 * @date 2020-08-10 20:40
 */
@SpringBootTest
public class RouteBuildFactoryTest {

    @Resource
    private MenuService menuService;


    @Test
    public void buildRouters() {
        List<MenuVo> allList = this.menuService.findMenuTree();
        Optional.ofNullable(allList)
                .map(RouteBuildFactory::buildRouters)
                .ifPresent(System.out::println);
    }
}
