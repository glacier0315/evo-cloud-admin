package com.glacier.modules.sys.service;

import com.glacier.modules.sys.SysApplication;
import com.glacier.modules.sys.entity.pojo.Menu;
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
 * @date 2020-08-10 20:37
 */
@Slf4j
@RunWith(SpringRunner.class)
@SpringBootTest(classes = {SysApplication.class})
public class MenuServiceTest {
    @Resource
    private MenuService menuService;

    @Before
    public void setUp() throws Exception {
    }

    @After
    public void tearDown() throws Exception {
    }

    @Test
    public void findAllList() {
        List<Menu> allList = this.menuService.findAllList();
        Optional.ofNullable(allList)
                .ifPresent(System.out::println);
    }

    @Test
    public void findMenuTree() {
        List<Menu> menuTree = this.menuService.findMenuTree();
        Optional.ofNullable(menuTree)
                .ifPresent(System.out::println);
    }

    @Test
    public void findMenuTreeByUserId() {
        String userId = "1";
        List<Menu> menuTree = this.menuService.findMenuTreeByUserId(userId);
        Optional.ofNullable(menuTree)
                .ifPresent(System.out::println);
    }

    @Test
    public void findPermissionsByUserId() {
        String userId = "1";
        List<Menu> menuTree = this.menuService.findMenuTreeByUserId(userId);
        Optional.ofNullable(menuTree)
                .ifPresent(System.out::println);
    }

    @Test
    public void save() {
    }

    @Test
    public void delete() {
    }

    @Test
    public void findByRole() {
    }
}
