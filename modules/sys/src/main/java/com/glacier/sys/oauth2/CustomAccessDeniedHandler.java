package com.glacier.sys.oauth2;

import com.alibaba.fastjson.JSONWriter;
import com.glacier.common.core.entity.dto.result.HttpResult;
import com.glacier.common.core.exception.AuthErrorType;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.web.access.AccessDeniedHandler;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * 自定义拒绝操作类
 *
 * @author glacier
 * @version 1.0
 * @date 2020-05-22 17:10
 */
@Slf4j
public class CustomAccessDeniedHandler implements AccessDeniedHandler {

    @Override
    public void handle(HttpServletRequest request, HttpServletResponse response, AccessDeniedException accessDeniedException) throws IOException, ServletException {
        response.setStatus(HttpStatus.OK.value());
        response.setContentType("application/json;charset=utf-8");
        response.setCharacterEncoding("UTF-8");
        JSONWriter jsonWriter = new JSONWriter(response.getWriter());
        jsonWriter.writeObject(HttpResult.<String>error(AuthErrorType.ACCESS_DENIED));
        jsonWriter.flush();
        jsonWriter.close();
    }
}
