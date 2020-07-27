package com.glacier.modules.sys.exception;

import com.glacier.common.core.entity.vo.HttpResult;
import com.glacier.common.core.exception.BaseException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

/**
 * 异常处理
 * @author glacier
 * @version 1.0
 * @date 2020-05-24 10:37
 */
@RestControllerAdvice
public class GlobalExceptionHandler {
    /**
     * 处理 认证异常
     *
     * @return
     */
    @ExceptionHandler(value = BaseException.class)
    public HttpResult<String> authenticationException(BaseException e) {
        return HttpResult.error(e.getErrorType().getCode(), e.getMessage());
    }
}
