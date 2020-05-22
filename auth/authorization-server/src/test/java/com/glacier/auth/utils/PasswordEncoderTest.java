package com.glacier.auth.utils;

import org.junit.Test;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.Base64;

/**
 * 加密测试
 *
 * @author glacier
 * @version 1.0
 * @date 2020-02-14 16:33
 */
public class PasswordEncoderTest {

    @Test
    public void test() {
        PasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
        String rawPassword = "auth-client";
        String encode = passwordEncoder.encode(rawPassword);
        System.out.println(rawPassword + "\t加密后密码：\t" + encode);
    }

    /**
     * 转换为  Authorization: Basic 密文
     */
    @Test
    public void testBasic() {
        String client_id = "eboot-sys";
        String client_secret = "eboot-sys";
        byte[] bytes = (client_id + ":" + client_secret).getBytes();
        String encodeToString = Base64.getEncoder().encodeToString(bytes);
        System.out.println("加密后：\tAuthorization: Basic " + encodeToString);
    }
}
