package com.glacier.common.core.exception;

import lombok.*;

/**
 * 基本异常
 *
 * @author glacier
 * @version 1.0
 * @date 2020-05-18 11:19
 */
@EqualsAndHashCode(callSuper = true)
@Data
@ToString
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class BaseException extends RuntimeException {
    private static final long serialVersionUID = -720171527461795332L;
    /**
     * 错误模块
     */
    private String module;

    /**
     * 错误代码
     */
    private String code;
    /**
     * 错误信息
     */
    private String msg;
    /**
     * 错误码对应的参数
     */
    private Object[] args;

}
