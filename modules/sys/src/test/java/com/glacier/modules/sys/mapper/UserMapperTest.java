package com.glacier.modules.sys.mapper;

import com.glacier.modules.sys.SysApplication;
import com.glacier.modules.sys.entity.pojo.User;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import javax.annotation.Resource;
import java.util.Optional;

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
        User user = this.userMapper.selectOneByUsername(username);
        System.out.println("user:\t" + user);
        assertEquals(Optional.ofNullable(user).orElse(new User()).getUsername(), username);
    }
}
