package com.glacier.auth.oauth2.exception;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import org.springframework.security.oauth2.common.exceptions.OAuth2Exception;

/**
 * 自定义异常
 *
 * @author glacier
 * @version 1.0
 * @date 2020-07-23 21:53
 */
@JsonSerialize(using = CustomOauthExceptionSerializer.class)
public class CustomOauthException extends OAuth2Exception {
    private static final long serialVersionUID = 7493542518733194375L;

    public CustomOauthException(String msg, Throwable t) {
        super(msg, t);
    }

    public CustomOauthException(String msg) {
        super(msg);
    }
}
