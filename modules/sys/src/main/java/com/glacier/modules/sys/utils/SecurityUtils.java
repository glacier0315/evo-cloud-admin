package com.glacier.modules.sys.utils;

import com.glacier.modules.sys.entity.User;
import com.glacier.modules.sys.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.oauth2.jwt.Jwt;

import java.util.Map;
import java.util.Objects;

/**
 * security 工具类
 *
 * @author glacier
 * @version 1.0
 * @date 2019-10-28 09:08
 */
@Slf4j
public class SecurityUtils {

    private SecurityUtils() {
    }

    /**
     * 获取当前用户ID
     *
     * @return
     */
    public static String geUserId() {
        String userId = null;
        User user = geUser();
        if (user != null) {
            userId = user.getId();
        }
        return userId;
    }

    /**
     * 获取当前用户
     *
     * @return
     */
    public static User geUser() {
        User user = null;
        String username = getUsername();
        if (username != null) {
            UserService userService = SpringContextUtil.getBean(UserService.class);
            user = userService.findUserByUsername(username);
        }
        return user;
    }

    /**
     * 获取当前用户名
     *
     * @return
     */
    public static String getUsername() {
        return (String) getClaims().get("user_name");
    }

    /**
     * 获取当前用户名
     *
     * @return
     */
    public static Map<String, Object> getClaims() {
        return  ((Jwt) Objects.requireNonNull(getAuthentication()).getPrincipal()).getClaims();
    }

    /**
     * 获取当前登录信息
     *
     * @return
     */
    public static Authentication getAuthentication() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        log.info("authentication: {}", authentication);
        return authentication;
    }
}
