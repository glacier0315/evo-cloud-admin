package com.glacier.authorization.server.oauth2.exception;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import org.springframework.security.oauth2.common.exceptions.OAuth2Exception;

/**
 * 认证异常
 * @author glacier
 * @version 1.0
 * date 2020-07-29 17:01
 */
@JsonSerialize(using = CustomOAuth2ExceptionSerializer.class)
public class CustomOAuth2Exception extends OAuth2Exception {

    private static final long serialVersionUID = -2252443956730840817L;

    public CustomOAuth2Exception(String msg, Throwable t) {
        super(msg, t);
    }

    public CustomOAuth2Exception(String msg) {
        super(msg);
    }

    public static long getSerialVersionUID() {
        return serialVersionUID;
    }
}
