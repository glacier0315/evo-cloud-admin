package com.glacier.authorization.server.exception;

import com.glacier.common.core.entity.Result;
import lombok.extern.slf4j.Slf4j;
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
     * @param exception 异常
     * @return 异常响应
     */
    @ExceptionHandler(value = Exception.class)
    public Result<String> exceptionHandler(Exception exception) {
        log.error("异常：" , exception);
        return Result.error(exception.getMessage());
    }
}
