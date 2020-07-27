package com.glacier.authorization.server.utils;

import com.glacier.authorization.server.AuthorizationServerApplication;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.test.context.junit4.SpringRunner;

import javax.annotation.Resource;
import java.util.Base64;

/**
 * 加密测试
 *
 * @author glacier
 * @version 1.0
 * @date 2020-02-14 16:33
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = {AuthorizationServerApplication.class})
public class PasswordEncoderTest {
    @Resource
    private PasswordEncoder passwordEncoder;

    @Test
    public void test() {
        String rawPassword = "auth-client";
        String encode = this.passwordEncoder.encode(rawPassword);
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
