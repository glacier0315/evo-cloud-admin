package com.glacier.modules.sys.utils;

import com.glacier.modules.sys.SysApplication;
import com.glacier.modules.sys.entity.pojo.Menu;
import com.glacier.modules.sys.service.MenuService;
import lombok.extern.slf4j.Slf4j;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import javax.annotation.Resource;
import java.util.List;
import java.util.Optional;

/**
 * @author glacier
 * @version 1.0
 * @date 2020-08-10 20:40
 */
@Slf4j
@RunWith(SpringRunner.class)
@SpringBootTest(classes = {SysApplication.class})
public class RouteBuildFactoryTest {

    @Resource
    private MenuService menuService;

    @Before
    public void setUp() throws Exception {
    }

    @After
    public void tearDown() throws Exception {
    }

    @Test
    public void buildRouters() {
        List<Menu> allList = this.menuService.findMenuTree();
        Optional.ofNullable(allList)
                .map(RouteBuildFactory::buildRouters)
                .ifPresent(System.out::println);
    }
}
