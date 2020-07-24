package com.glacier.auth.oauth2;

import com.alibaba.fastjson.JSONWriter;
import com.glacier.common.core.constant.CommonConstant;
import com.glacier.common.core.entity.vo.HttpResult;
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
    public void commence(HttpServletRequest request, HttpServletResponse response,
                         AuthenticationException authException) throws IOException, ServletException {
        response.setStatus(HttpStatus.OK.value());
        response.setContentType(CommonConstant.MEDIA_TYPE);
        response.setCharacterEncoding(CommonConstant.CHARSET_UTF_8);
        JSONWriter jsonWriter = new JSONWriter(response.getWriter());
        jsonWriter.writeObject(HttpResult.<String>error(authException.getMessage()));
        jsonWriter.flush();
        jsonWriter.close();
    }
}
