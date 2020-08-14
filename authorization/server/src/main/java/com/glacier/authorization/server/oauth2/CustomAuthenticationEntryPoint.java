package com.glacier.authorization.server.oauth2;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.glacier.common.core.constant.CommonConstant;
import com.glacier.common.core.constant.MediaConstants;
import com.glacier.common.core.entity.vo.Result;
import com.glacier.common.core.exception.AuthErrorType;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.AuthenticationEntryPoint;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * 自定义授权异常类
 *
 * @author glacier
 * @version 1.0
 * @date 2020-05-22 17:11
 */
@Slf4j
public class CustomAuthenticationEntryPoint implements AuthenticationEntryPoint {

    @Override
    public void commence(HttpServletRequest request, HttpServletResponse response,
                         AuthenticationException authException) throws IOException, ServletException {
        log.error("异常：", authException);
        response.setStatus(HttpStatus.OK.value());
        response.setContentType(MediaConstants.MEDIA_TYPE);
        response.setCharacterEncoding(CommonConstant.CHARSET_UTF_8);
        ObjectMapper objectMapper = new ObjectMapper();
        if (authException instanceof BadCredentialsException) {
            objectMapper.writeValue(
                    response.getOutputStream(),
                    Result.error(AuthErrorType.INVALID_CLIENT));
        } else {
            objectMapper.writeValue(
                    response.getOutputStream(),
                    Result.error(authException.getMessage()));
        }
    }
}
