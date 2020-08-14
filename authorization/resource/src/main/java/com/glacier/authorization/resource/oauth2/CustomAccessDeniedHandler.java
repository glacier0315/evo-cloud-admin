package com.glacier.authorization.resource.oauth2;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.glacier.common.core.constant.CommonConstant;
import com.glacier.common.core.constant.MediaConstants;
import com.glacier.common.core.entity.vo.Result;
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
 * @author glacier
 * @version 1.0
 * @date 2020-07-24 16:38
 */
@Slf4j
public class CustomAccessDeniedHandler implements AccessDeniedHandler {

    @Override
    public void handle(HttpServletRequest request, HttpServletResponse response, AccessDeniedException accessDeniedException) throws IOException, ServletException {
        log.error("异常：", accessDeniedException);
        response.setStatus(HttpStatus.OK.value());
        response.setContentType(MediaConstants.MEDIA_TYPE);
        response.setCharacterEncoding(CommonConstant.CHARSET_UTF_8);
        ObjectMapper objectMapper = new ObjectMapper();
        objectMapper.writeValue(
                response.getOutputStream(),
                Result.error(AuthErrorType.ACCESS_DENIED));
    }
}
