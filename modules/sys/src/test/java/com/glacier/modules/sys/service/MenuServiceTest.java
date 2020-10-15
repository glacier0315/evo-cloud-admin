package com.glacier.modules.sys.service;

import com.glacier.modules.sys.entity.dto.menu.MenuQuery;
import com.glacier.modules.sys.entity.dto.menu.MenuVo;
import com.google.common.collect.Lists;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import javax.annotation.Resource;
import java.util.List;
import java.util.Optional;

/**
 * @author glacier
 * @version 1.0
 * date 2020-08-10 20:37
 */
@SpringBootTest
public class MenuServiceTest {
    @Resource
    private MenuService menuService;

    @Test
    public void findList() {
        MenuQuery menuQuery = new MenuQuery();
        menuQuery.setStatus(1);
        menuQuery.setUserId("1");
        menuQuery.setRoleId("1");
        menuQuery.setTypeList(Lists.newArrayList(1, 2));
        List<MenuVo> menuVos = this.menuService.findList(menuQuery);
        Optional.ofNullable(menuVos)
                .ifPresent(System.out::println);
    }

    @Test
    public void findMenuTreeByUserId() {
        String userId = "c35d5b94aca547799fbe56ddd90bcfc4";
        List<MenuVo> menuTree = this.menuService.findMenuTreeByUserId(userId);
        Optional.ofNullable(menuTree)
                .ifPresent(System.out::println);
    }

    @Test
    public void findPermissionsByUserId() {
        String userId = "1";
        List<MenuVo> menuTree = this.menuService.findMenuTreeByUserId(userId);
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
        String roleId = "1";
        List<String> list = this.menuService.findByRole(roleId);
        Optional.ofNullable(list)
                .ifPresent(System.out::println);
    }
}
