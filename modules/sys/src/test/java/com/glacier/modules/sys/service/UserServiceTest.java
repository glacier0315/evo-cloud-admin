package com.glacier.modules.sys.service;

import com.glacier.common.core.entity.page.PageRequest;
import com.glacier.common.core.entity.page.PageResponse;
import com.glacier.modules.sys.SysApplication;
import com.glacier.modules.sys.entity.form.user.UserQueryForm;
import com.glacier.modules.sys.entity.pojo.User;
import com.glacier.modules.sys.entity.vo.user.UserDetails;
import com.glacier.modules.sys.entity.vo.user.UserInfo;
import com.glacier.modules.sys.entity.vo.user.UserProfile;
import com.glacier.modules.sys.entity.vo.user.UserVo;
import lombok.extern.slf4j.Slf4j;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import javax.annotation.Resource;
import java.util.Optional;

/**
 * @author glacier
 * @version 1.0
 * @date 2020-08-08 10:21
 */
@Slf4j
@RunWith(SpringRunner.class)
@SpringBootTest(classes = {SysApplication.class})
public class UserServiceTest {
    @Resource
    private UserService userService;

    @Before
    public void setUp() throws Exception {

    }

    @After
    public void tearDown() throws Exception {
    }

    @Test
    public void findUserByUsername() {
        String username = "admin";
        User user = this.userService.findUserByUsername(username);
        Assert.assertEquals(user.getUsername(),
                username);
    }

    @Test
    public void findUserInfoByUsername() {
        String username = "admin";
        UserInfo user = this.userService.findUserInfoByUsername(username);
        Assert.assertEquals(Optional.ofNullable(user)
                        .orElse(new UserInfo())
                        .getName(),
                username);
    }

    @Test
    public void findUserProfileByUsername() {
        String username = "admin";
        UserProfile user = this.userService.findUserProfileByUsername(username);
        Assert.assertEquals(Optional.ofNullable(user)
                        .orElse(new UserProfile())
                        .getUsername(),
                username);
    }

    @Test
    public void loadUserByUsername() {
        String username = "admin";
        UserDetails user = this.userService.loadUserByUsername(username);
        Assert.assertEquals(Optional.ofNullable(user)
                        .orElse(new UserDetails())
                        .getUsername(),
                username);
    }

    @Test
    public void findPage() {
        String username = "admin";
        UserQueryForm userQueryForm = new UserQueryForm();
        userQueryForm.setUsername(username);
        PageResponse<UserVo> page = this.userService.findPage(new PageRequest<>(1, 10, userQueryForm));
        System.out.println(page);
        Assert.assertNotNull(page);
        Assert.assertTrue(page.getList() != null
                && !page.getList().isEmpty());
        Assert.assertEquals(page.getList()
                .get(0)
                .getUsername(), username);
    }

    @Test
    public void update() {
    }

    @Test
    public void add() {
    }

    @Test
    public void delete() {
    }

    @Test
    public void updatePassword() {
    }
}
