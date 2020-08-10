package com.glacier.modules.sys.service;

import com.glacier.modules.sys.entity.pojo.Menu;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import javax.annotation.Resource;
import java.util.List;
import java.util.Optional;

/**
 * @author glacier
 * @version 1.0
 * @date 2020-08-10 20:37
 */
@SpringBootTest
public class MenuServiceTest {
    @Resource
    private MenuService menuService;

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
