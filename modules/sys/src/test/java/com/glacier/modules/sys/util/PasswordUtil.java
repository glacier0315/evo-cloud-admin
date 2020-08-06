package com.glacier.modules.sys.util;

import com.glacier.modules.sys.SysApplication;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.test.context.junit4.SpringRunner;

import javax.annotation.Resource;

/**
 * @author glacier
 * @version 1.0
 * @date 2020-07-23 19:30
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = {SysApplication.class})
public class PasswordUtil {
    @Resource
    private PasswordEncoder passwordEncoder;

    @Test
    public void test() {
        String rawPassword = "admin";
        String encode = this.passwordEncoder.encode(rawPassword);
        System.out.println(rawPassword + "\t加密后密码：\t" + encode);
    }
}
