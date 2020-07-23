package com.glacier.common.core.entity.vo;

import com.glacier.common.core.exception.ErrorType;
import com.glacier.common.core.exception.SystemErrorType;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.*;

import java.io.Serializable;
import java.time.Instant;

/**
 * HTTP结果封装
 *
 * @author glacier
 * @date 2019-10-14 15:53
 */
@ApiModel(description = "rest请求的返回模型，所有rest正常都返回该类的对象")
@Data
@ToString
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class HttpResult<T> implements Serializable {

    private static final long serialVersionUID = -8984794300938868661L;
    /**
     * 默认成功 状态码
     */
    public static final String SUCCUSS = "20000";
    /**
     * 响应编码
     */
    @ApiModelProperty(value = "响应编码")
    private String code;
    /**
     * 响应信息
     */
    @ApiModelProperty(value = "响应错误信息")
    private String msg;
    /**
     * 请求生成时间戳
     */
    @ApiModelProperty(value = "请求结果生成时间戳")
    private Instant time;
    /**
     * 响应返回数据
     */
    @ApiModelProperty(value = "响应返回数据")
    private T data;

    /**
     * 返回默认 "-1" 错误
     *
     * @param <T>
     * @return
     */
    public static <T> HttpResult<T> error() {
        return error(SystemErrorType.SYSTEM_ERROR);
    }

    /**
     * 返回默认 "-1" 错误
     *
     * @param <T>
     * @return
     */
    public static <T> HttpResult<T> error(String msg) {
        return error(SystemErrorType.SYSTEM_ERROR, msg);
    }

    /**
     * 返回指定编码错误
     *
     * @param errorType
     * @param <T>
     * @return
     */
    public static <T> HttpResult<T> error(ErrorType errorType) {
        return error(errorType, errorType.getMsg());
    }

    /**
     * 返回指定编码错误
     *
     * @param errorType
     * @param msg
     * @param <T>
     * @return
     */
    public static <T> HttpResult<T> error(ErrorType errorType, String msg) {
        return HttpResult.<T>builder()
                .code(errorType.getCode())
                .msg(msg)
                .build();
    }

    /**
     * 返回默认成功状态
     *
     * @param <T>
     * @return
     */
    public static <T> HttpResult<T> ok() {
        return ok(null, null);
    }

    /**
     * 返回成功状态 携带指定数据
     *
     * @param data
     * @param <T>
     * @return
     */
    public static <T> HttpResult<T> ok(T data) {
        return ok(null, data);
    }

    /**
     * 返回成功状态 携带指定数据和消息
     *
     * @param msg
     * @param data
     * @param <T>
     * @return
     */
    public static <T> HttpResult<T> ok(String msg, T data) {
        return HttpResult.<T>builder()
                .code(SUCCUSS)
                .msg(msg)
                .data(data)
                .build();
    }
}
