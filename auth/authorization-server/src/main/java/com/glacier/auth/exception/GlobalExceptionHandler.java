package com.glacier.auth.exception;

import com.glacier.auth.oauth2.exception.AuthErrorType;
import com.glacier.common.core.entity.vo.HttpResult;
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
    @ExceptionHandler(value = InvalidGrantException.class)
    public HttpResult<?> authenticationException(InvalidGrantException e) {
        return HttpResult.error(AuthErrorType.UNSUPPORTED_GRANT_TYPE);
    }
}
