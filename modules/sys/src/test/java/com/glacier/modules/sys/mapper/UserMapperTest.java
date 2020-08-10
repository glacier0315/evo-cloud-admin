package com.glacier.modules.sys.mapper;

import com.glacier.modules.sys.SysApplication;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import javax.annotation.Resource;

import static org.junit.Assert.assertEquals;

/**
 * @author glacier
 * @version 1.0
 * @date 2020-08-07 17:25
 */
@Slf4j
@RunWith(SpringRunner.class)
@SpringBootTest(classes = {SysApplication.class})
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
