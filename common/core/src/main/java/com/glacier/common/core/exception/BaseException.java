package com.glacier.common.core.exception;

/**
 * 异常
 *
 * @author glacier
 * @version 1.0
 * @date 2020-05-18 11:19
 */
public class BaseException extends RuntimeException {
    private static final long serialVersionUID = -720171527461795332L;
    /**
     * 异常对应的错误类型
     */
    private final ErrorType errorType;

    /**
     * 默认是系统异常
     */
    public BaseException() {
        this.errorType = SystemErrorType.SYSTEM_ERROR;
    }

    public BaseException(ErrorType errorType) {
        this.errorType = errorType;
    }

    public BaseException(ErrorType errorType, String message) {
        super(message);
        this.errorType = errorType;
    }

    public BaseException(ErrorType errorType, String message, Throwable cause) {
        super(message, cause);
        this.errorType = errorType;
    }

    public ErrorType getErrorType() {
        return this.errorType;
    }
}
