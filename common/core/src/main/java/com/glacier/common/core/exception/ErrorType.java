package com.glacier.common.core.exception;

/**
 * @author glacier
 * @version 1.0
 * date 2020-05-18 11:18
 */
public interface ErrorType {
    /**
     * 返回code
     *
     * @return 编码
     */
    String getCode();

    /**
     * 返回msg
     *
     * @return 信息
     */
    String getMsg();
}
