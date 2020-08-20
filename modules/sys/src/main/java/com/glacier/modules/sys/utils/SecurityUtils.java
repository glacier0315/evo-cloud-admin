package com.glacier.modules.sys.utils;

import com.glacier.common.core.constant.CommonConstant;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.oauth2.jwt.Jwt;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

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
     * @return 当前用户ID
     */
    public static String geUserId() {
        return (String) getClaims().get(CommonConstant.OAUTH_USER_ID);
    }

    /**
     * 获取当前用户名
     *
     * @return 当前用户名
     */
    public static String getUsername() {
        return (String) getClaims().get(CommonConstant.OAUTH_USERNAME);
    }

    /**
     * 获取令牌中的属性 键值对
     *
     * @return 令牌中的属性 键值对
     */
    public static Map<String, Object> getClaims() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        log.info("authentication: {}", authentication);
        return Optional.ofNullable(authentication)
                .flatMap(auth -> {
                    Jwt jwt = null;
                    if (auth.getPrincipal() instanceof Jwt) {
                        jwt = (Jwt) auth.getPrincipal();
                    }
                    log.info("jwt: {}", jwt);
                    return Optional.ofNullable(jwt)
                            .map(Jwt::getClaims);
                })
                .orElseGet(HashMap::new);
    }
}
