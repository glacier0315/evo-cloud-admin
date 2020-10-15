package com.glacier.common.core.exception;

/**
 * 认证错误代码
 *
 * @author glacier
 * @version 1.0
 * date 2020-05-18 11:20
 */
public enum AuthErrorType implements ErrorType {
    /**
     * 无效请求
     */
    INVALID_REQUEST("040001", "无效请求"),
    /**
     * 无效client_id
     */
    INVALID_CLIENT("040002", "无效client_id或client_secret"),
    /**
     * 无效授权
     */
    INVALID_GRANT("040003", "无效授权"),
    /**
     * 无效scope
     */
    INVALID_SCOPE("040004", "无效scope"),
    /**
     * 无效token
     */
    INVALID_TOKEN("040005", "无效token"),
    /**
     * 授权不足
     */
    INSUFFICIENT_SCOPE("040010", "授权不足"),
    /**
     * redirect url不匹配
     */
    REDIRECT_URI_MISMATCH("040020", "redirect url不匹配"),
    /**
     * 拒绝访问
     */
    ACCESS_DENIED("040030", "拒绝访问"),
    /**
     * 不支持该方法
     */
    METHOD_NOT_ALLOWED("040040", "不支持该方法"),
    /**
     * 权限服务错误
     */
    SERVER_ERROR("040050", "权限服务错误"),
    /**
     * 未授权客户端
     */
    UNAUTHORIZED_CLIENT("040060", "未授权客户端"),
    /**
     * 未授权
     */
    UNAUTHORIZED("040061", "未授权"),
    /**
     * 不支持的授权类型
     */
    UNSUPPORTED_RESPONSE_TYPE("040070", "支持的响应类型"),
    /**
     * 不支持的授权类型
     */
    UNSUPPORTED_GRANT_TYPE("040071", "不支持的授权类型");

    /**
     * 错误类型码
     */
    private final String code;
    /**
     * 错误类型描述信息
     */
    private final String msg;

    AuthErrorType(String code, String msg) {
        this.code = code;
        this.msg = msg;
    }

    @Override
    public String getCode() {
        return this.code;
    }

    @Override
    public String getMsg() {
        return this.msg;
    }
}
