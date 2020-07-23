package com.glacier.auth.oauth2;

import com.glacier.common.core.entity.vo.HttpResult;
import com.glacier.common.core.exception.SystemErrorType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.oauth2.common.exceptions.OAuth2Exception;
import org.springframework.security.oauth2.provider.error.WebResponseExceptionTranslator;

/**
 * 自定义异常响应
 *
 * @author glacier
 * @version 1.0
 * @date 2020-07-23 17:49
 */
public class MyWebResponseExceptionTranslator implements WebResponseExceptionTranslator<OAuth2Exception> {
    /**
     * token 无效
     */
    private static final String INVALID_TOKEN_ERROR_DESCRIPTION = "Token was not recognised";
    /**
     * 授权码无效
     */
    private static final String INVALID_AUTHORIZATION_CODE = "Invalid authorization code";
    /**
     * 无效用户
     */
    private static final String INVALID_USER = "无效用户";
    /**
     * 密码有误
     */
    private static final String BAD_CREDENTIALS = "Bad credentials";

    @Override
    public ResponseEntity translate(Exception e) throws Exception {
        if (e.getMessage().startsWith(INVALID_AUTHORIZATION_CODE)) {
            // 无效授权码
            return ResponseEntity.ok().body(HttpResult.error(e.getMessage()));
        } else if (INVALID_USER.equals(e.getMessage())) {
            // 无效用户
            return ResponseEntity.ok().body(HttpResult.error("用户名或者密码错误！"));
        } else if (BAD_CREDENTIALS.equals(e.getMessage())) {
            // 密码错误
            return ResponseEntity.ok().body(HttpResult.error("用户名或者密码错误！"));
        } else if (INVALID_TOKEN_ERROR_DESCRIPTION.equals(e.getMessage())) {
            return ResponseEntity.ok().body(HttpResult.error(SystemErrorType.INVALID_TOKEN));
        } else {
            return ResponseEntity.ok().body(HttpResult.error(e.getMessage()));
        }
    }
}
