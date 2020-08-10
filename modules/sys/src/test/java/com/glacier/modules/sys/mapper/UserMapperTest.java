package com.glacier.modules.sys.mapper;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import javax.annotation.Resource;

import static org.junit.jupiter.api.Assertions.assertEquals;


/**
 * @author glacier
 * @version 1.0
 * @date 2020-08-07 17:25
 */
@SpringBootTest
public class UserMapperTest {
    @Resource
    private UserMapper userMapper;

    @Test
    public void selectOneByUsername() {
        String username = "admin";
        this.userMapper.selectOneByUsername(username)
                .ifPresent(user -> {
                    System.out.println("user:\t" + user);
                    assertEquals(user.getUsername(), username);
                });

    }
}
