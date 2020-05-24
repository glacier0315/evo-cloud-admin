package com.glacier.sys.config.oauth2;

import com.alibaba.fastjson.JSONWriter;
import com.glacier.common.core.entity.vo.HttpResult;
import com.glacier.common.core.exception.AuthErrorType;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
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
    public void commence(HttpServletRequest request, HttpServletResponse response, AuthenticationException authException) throws IOException, ServletException {
        response.setStatus(HttpStatus.OK.value());
        response.setContentType("application/json;charset=utf-8");
        response.setCharacterEncoding("UTF-8");
        JSONWriter jsonWriter = new JSONWriter(response.getWriter());
        jsonWriter.writeObject(HttpResult.<String>error(AuthErrorType.INVALID_GRANT));
        jsonWriter.flush();
        jsonWriter.close();
    }
}
