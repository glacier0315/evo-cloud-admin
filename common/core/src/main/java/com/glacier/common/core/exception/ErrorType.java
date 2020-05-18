package com.glacier.common.core.exception;

/**
 * @author glacier
 * @version 1.0
 * @date 2020-05-18 11:18
 */
public interface ErrorType {
    /**
     * 返回code
     *
     * @return
     */
    String getCode();

    /**
     * 返回mesg
     *
     * @return
     */
    String getMsg();
}
