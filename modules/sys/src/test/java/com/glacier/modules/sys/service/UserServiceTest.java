package com.glacier.modules.sys.service;

import com.glacier.common.core.entity.dto.vo.UserDetailsDto;
import com.glacier.common.core.entity.page.PageRequest;
import com.glacier.common.core.entity.page.PageResponse;
import com.glacier.modules.sys.entity.User;
import com.glacier.modules.sys.entity.dto.user.UserDto;
import com.glacier.modules.sys.entity.dto.user.UserInfo;
import com.glacier.modules.sys.entity.dto.user.UserProfile;
import com.glacier.modules.sys.entity.dto.user.UserQueryForm;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import javax.annotation.Resource;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

/**
 * @author glacier
 * @version 1.0
 * @date 2020-08-08 10:21
 */
@SpringBootTest
public class UserServiceTest {
    @Resource
    private UserService userService;


    @Test
    public void findUserByUsername() {
        String username = "admin";
        User user = this.userService.findUserByUsername(username);
        assertEquals(user.getUsername(),
                username);
    }

    @Test
    public void findUserInfoByUsername() {
        String username = "admin";
        UserInfo user = this.userService.findUserInfoByUsername(username);
        assertEquals(Optional.ofNullable(user)
                        .orElse(new UserInfo())
                        .getName(),
                username);
    }

    @Test
    public void findUserProfileByUsername() {
        String username = "admin";
        UserProfile user = this.userService.findUserProfileByUsername(username);
        assertEquals(Optional.ofNullable(user)
                        .orElse(new UserProfile())
                        .getUsername(),
                username);
    }

    @Test
    public void loadUserByUsername() {
        String username = "admin";
        UserDetailsDto user = this.userService.loadUserByUsername(username);
        assertEquals(Optional.ofNullable(user)
                        .orElse(new UserDetailsDto())
                        .getUsername(),
                username);
    }

    @Test
    public void findPage() {
        String username = "admin";
        UserQueryForm userQueryForm = new UserQueryForm();
        userQueryForm.setUsername(username);
        PageResponse<UserDto> page = this.userService.findPage(new PageRequest<>(1, 10, userQueryForm));
        System.out.println(page);
        assertNotNull(page);
        assertTrue(page.getList() != null
                && !page.getList().isEmpty());
        assertEquals(page.getList()
                .get(0)
                .getUsername(), username);
    }

    @Test
    public void save() {
    }

    @Test
    public void delete() {
    }

    @Test
    public void updatePassword() {
    }

    @Test
    public void updateProfile() {
    }

    @Test
    public void updateAvatar() {
    }
}
