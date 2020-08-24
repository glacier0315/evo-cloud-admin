package com.glacier.modules.sys.handler;

import com.glacier.common.core.entity.Result;
import com.glacier.common.core.exception.BaseException;
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
     * @param baseException 异常
     * @return 异常响应
     */
    @ExceptionHandler(value = BaseException.class)
    public Result<String> handler(BaseException baseException) {
        log.error("异常：", baseException);
        return Result.error(baseException.getCode(), baseException.getMsg());
    }
}
