package com.glacier.common.security.utils;

import com.glacier.common.core.entity.Result;
import com.glacier.common.core.utils.StringUtils;
import com.glacier.common.security.consumer.UserService;
import com.glacier.common.security.entity.dto.UserDetailsDto;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.oauth2.jwt.Jwt;

import java.util.Map;
import java.util.Objects;
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
     * @return
     */
    public static String geUserId() {
        return Optional.ofNullable(geUser())
                .map(UserDetailsDto::getId)
                .orElseGet(null);
    }

    /**
     * 获取当前用户
     *
     * @return
     */
    public static UserDetailsDto geUser() {
        Result<UserDetailsDto> result = null;
        String username = getUsername();
        if (StringUtils.isEmpty(username)) {
            UserService userService = SpringContextHolder.getBean(UserService.class);
            result = userService.findByUsername(username);
            return result.getData();
        }
        return null;
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
        Authentication authentication = SecurityContextHolder.getContext()
                .getAuthentication();
        log.info("authentication: {}", authentication);
        return authentication;
    }
}
