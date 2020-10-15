package com.glacier.common.core.exception;

import java.util.Arrays;

/**
 * 基本异常
 *
 * @author glacier
 * @version 1.0
 * @date 2020-05-18 11:19
 */
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

    public BaseException(String module, String code, String msg, Object[] args) {
        this.module = module;
        this.code = code;
        this.msg = msg;
        this.args = args;
    }

    public static long getSerialVersionUID() {
        return serialVersionUID;
    }

    public String getModule() {
        return this.module;
    }

    public void setModule(String module) {
        this.module = module;
    }

    public String getCode() {
        return this.code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getMsg() {
        return this.msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public Object[] getArgs() {
        return this.args;
    }

    public void setArgs(Object[] args) {
        this.args = args;
    }

    @Override
    public String toString() {
        return "BaseException{" +
                "module='" + this.module + '\'' +
                ", code='" + this.code + '\'' +
                ", msg='" + this.msg + '\'' +
                ", args=" + Arrays.toString(this.args) +
                '}';
    }
}
