package com.glacier.common.security.handler;

import com.glacier.common.core.entity.Result;
import com.glacier.common.core.exception.BaseException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

/**
 * 异常处理
 *
 * @author glacier
 * @version 1.0
 * date 2020-05-03 09:54
 */
@RestControllerAdvice
public class GlobalExceptionHandler {
    private static final Logger log = LoggerFactory.getLogger(GlobalExceptionHandler.class);
    /**
     * 处理 认证异常
     *
     * @param baseException 异常
     * @return 异常响应
     */
    @ExceptionHandler(value = BaseException.class)
    public Result<String> handler(BaseException baseException) {
        log.error("异常：", baseException);
        return Result.error(baseException.getCode(), baseException.getMsg());
    }

    /**
     * 处理 异常
     *
     * @param usernameNotFoundException 异常
     * @return 异常响应
     */
    @ExceptionHandler(value = UsernameNotFoundException.class)
    public Result<String> handler(UsernameNotFoundException usernameNotFoundException) {
        log.error("异常：", usernameNotFoundException);
        return Result.error(usernameNotFoundException.getMessage());
    }
}
