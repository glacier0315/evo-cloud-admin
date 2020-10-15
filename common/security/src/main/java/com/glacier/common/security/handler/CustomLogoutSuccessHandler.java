package com.glacier.common.security.handler;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.glacier.common.core.constant.CommonConstant;
import com.glacier.common.core.constant.MediaConstants;
import com.glacier.common.core.entity.Result;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.logout.LogoutSuccessHandler;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * 登出成功后处理
 *
 * @author glacier
 * @version 1.0
 * date 2020-05-22 21:20
 */
public class CustomLogoutSuccessHandler implements LogoutSuccessHandler {
    private static final Logger log = LoggerFactory.getLogger(CustomLogoutSuccessHandler.class);
    @Override
    public void onLogoutSuccess(HttpServletRequest request, HttpServletResponse response, Authentication authentication) throws IOException, ServletException {
        response.setStatus(HttpStatus.OK.value());
        response.setContentType(MediaConstants.APPLICATION_JSON_CHARSET_UTF_8);
        response.setCharacterEncoding(CommonConstant.CHARSET_UTF_8);
        ObjectMapper objectMapper = new ObjectMapper();
        objectMapper.writeValue(
                response.getOutputStream(),
                Result.ok());
    }
}
