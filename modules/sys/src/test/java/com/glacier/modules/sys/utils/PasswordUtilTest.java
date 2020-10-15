package com.glacier.modules.sys.utils;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.crypto.password.PasswordEncoder;

import javax.annotation.Resource;

/**
 * @author glacier
 * @version 1.0
 * date 2020-07-23 19:30
 */
@SpringBootTest
public class PasswordUtilTest {
    @Resource
    private PasswordEncoder passwordEncoder;

    @Test
    public void test() {
        String rawPassword = "admin";
        String encode = this.passwordEncoder.encode(rawPassword);
        System.out.println(rawPassword + "\t加密后密码：\t" + encode);
    }
}
