package com.glacier.auth.exception;

import com.glacier.common.core.entity.dto.result.HttpResult;
import com.glacier.common.core.exception.AuthErrorType;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.oauth2.common.exceptions.InvalidGrantException;
import org.springframework.web.bind.annotation.ExceptionHandler;

/**
 * 异常处理
 *
 * @author glacier
 * @version 1.0
 * @date 2020-05-03 09:54
 */
public class GlobalExceptionHandler {

    /**
     * 处理 认证异常
     *
     * @return
     */
    @ExceptionHandler(value = UsernameNotFoundException.class)
    public HttpResult<?> authenticationException(UsernameNotFoundException e) {
        return HttpResult.error(AuthErrorType.UNSUPPORTED_GRANT_TYPE);
    }

    /**
     * 处理 认证异常
     *
     * @return
     */
    @ExceptionHandler(value = InvalidGrantException.class)
    public HttpResult<?> authenticationException(InvalidGrantException e) {
        return HttpResult.error(AuthErrorType.UNSUPPORTED_GRANT_TYPE);
    }
}
