package com.glacier.auth.oauth2;

import com.glacier.auth.oauth2.exception.CustomOauthException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.oauth2.common.DefaultThrowableAnalyzer;
import org.springframework.security.oauth2.common.exceptions.OAuth2Exception;
import org.springframework.security.oauth2.provider.error.WebResponseExceptionTranslator;
import org.springframework.security.web.util.ThrowableAnalyzer;

/**
 * 自定义异常响应
 *
 * @author glacier
 * @version 1.0
 * @date 2020-07-23 17:49
 */
@Slf4j
public class MyWebResponseExceptionTranslator implements WebResponseExceptionTranslator<OAuth2Exception> {

    private ThrowableAnalyzer throwableAnalyzer = new DefaultThrowableAnalyzer();

    @Override
    public ResponseEntity<OAuth2Exception> translate(Exception exception) throws Exception {
        Throwable[] throwables = this.throwableAnalyzer.determineCauseChain(exception);
        Exception ase = (OAuth2Exception) this.throwableAnalyzer.getFirstThrowableOfType(OAuth2Exception.class, throwables);
        log.info("认证异常， ", exception);
        log.info("认证异常， ", ase);
        if (exception instanceof OAuth2Exception) {
            OAuth2Exception oAuth2Exception = (OAuth2Exception) exception;
            return ResponseEntity
                    .status(oAuth2Exception.getHttpErrorCode())
                    .body(new CustomOauthException(oAuth2Exception.getMessage()));
        } else if (exception instanceof AuthenticationException) {
            AuthenticationException authenticationException = (AuthenticationException) exception;
            return ResponseEntity
                    .status(HttpStatus.UNAUTHORIZED)
                    .body(new CustomOauthException(authenticationException.getMessage()));
        }
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(new CustomOauthException(exception.getMessage()));
    }
}
