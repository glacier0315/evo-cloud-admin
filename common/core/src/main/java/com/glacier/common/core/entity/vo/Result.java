package com.glacier.common.core.entity.vo;

import com.glacier.common.core.exception.ErrorType;
import com.glacier.common.core.exception.SystemErrorType;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.*;

import java.io.Serializable;

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
public class Result<T> implements Serializable {

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
    private Long time;
    /**
     * 响应返回数据
     */
    @ApiModelProperty(value = "响应返回数据")
    private T data;

    /**
     * 返回默认 "500" 错误
     *
     * @param <T>
     * @return
     */
    public static <T> Result<T> error() {
        return error(SystemErrorType.BUSINESS_ERROR);
    }

    /**
     * 返回默认 "-1" 错误
     *
     * @param <T>
     * @return
     */
    public static <T> Result<T> error(String msg) {
        return error(SystemErrorType.BUSINESS_ERROR.getCode(), msg);
    }

    /**
     * 返回指定编码错误
     *
     * @param errorType
     * @param <T>
     * @return
     */
    public static <T> Result<T> error(ErrorType errorType) {
        return error(errorType.getCode(), errorType.getMsg());
    }

    /**
     * 返回指定编码错误
     *
     * @param code
     * @param msg
     * @param <T>
     * @return
     */
    public static <T> Result<T> error(String code, String msg) {
        return Result.<T>builder()
                .code(code)
                .msg(msg)
                .time(System.currentTimeMillis())
                .build();
    }

    /**
     * 返回默认成功状态
     *
     * @param <T>
     * @return
     */
    public static <T> Result<T> ok() {
        return ok("成功！" , null);
    }

    /**
     * 返回成功状态 携带指定数据
     *
     * @param data
     * @param <T>
     * @return
     */
    public static <T> Result<T> ok(T data) {
        return ok("成功！" , data);
    }

    /**
     * 返回成功状态 携带指定数据和消息
     *
     * @param msg
     * @param data
     * @param <T>
     * @return
     */
    public static <T> Result<T> ok(String msg, T data) {
        return Result.<T>builder()
                .code(SUCCUSS)
                .msg(msg)
                .data(data)
                .time(System.currentTimeMillis())
                .build();
    }
}
