package com.glacier.common.core.exception;

import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * 系统错误代码
 *
 * @author glacier
 * @version 1.0
 * @date 2020-05-18 11:20
 */
@Getter
@AllArgsConstructor
public enum SystemErrorType implements ErrorType {
    /**
     * 系统异常
     */
    SYSTEM_ERROR("-1", "系统异常"),
    /**
     * 系统繁忙,请稍候再试
     */
    SYSTEM_BUSY("000001", "系统繁忙,请稍候再试"),
    /**
     * 服务未找到
     */
    GATEWAY_NOT_FOUND_SERVICE("010404", "服务未找到"),
    /**
     * 网关异常
     */
    GATEWAY_ERROR("010500", "网关异常"),
    /**
     * 网关超时
     */
    GATEWAY_CONNECT_TIME_OUT("010002", "网关超时"),
    /**
     * 请求参数校验不通过
     */
    ARGUMENT_NOT_VALID("020000", "请求参数校验不通过"),
    /**
     * 无效token
     */
    INVALID_TOKEN("020001", "无效token"),
    /**
     * 上传文件大小超过限制
     */
    UPLOAD_FILE_SIZE_LIMIT("020010", "上传文件大小超过限制"),
    /**
     * 唯一键冲突
     */
    DUPLICATE_PRIMARY_KEY("030000", "唯一键冲突");

    /**
     * 错误类型码
     */
    private final String code;
    /**
     * 错误类型描述信息
     */
    private final String msg;

    @Override
    public String getCode() {
        return code;
    }

    @Override
    public String getMsg() {
        return msg;
    }
}
