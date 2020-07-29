package com.glacier.authorization.server.exception;

import com.glacier.common.core.entity.vo.HttpResult;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.oauth2.common.exceptions.InvalidScopeException;
import org.springframework.security.oauth2.common.exceptions.OAuth2Exception;
import org.springframework.security.oauth2.common.exceptions.UnsupportedGrantTypeException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

/**
 * 异常处理
 *
 * @author glacier
 * @version 1.0
 * @date 2020-05-03 09:54
 */
@Slf4j
@RestControllerAdvice
public class GlobalExceptionHandler {

    /**
     * 处理 认证异常
     *
     * @param exception
     * @return
     */
    @ExceptionHandler(value = OAuth2Exception.class)
    public HttpResult<String> exceptionHandler(OAuth2Exception exception) {
        log.error("认证异常：", exception);
        if (exception instanceof UnsupportedGrantTypeException) {
            return HttpResult.error(
                    exception.getMessage().replace("Unsupported", "不支持"));
        } else if (exception instanceof InvalidScopeException) {
            return HttpResult.error(
                    exception.getMessage().replace("Invalid", "无效"));
        }
        return HttpResult.error(exception.getMessage());
    }
}
