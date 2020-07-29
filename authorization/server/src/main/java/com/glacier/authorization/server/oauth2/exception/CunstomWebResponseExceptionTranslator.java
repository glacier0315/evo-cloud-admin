package com.glacier.authorization.server.oauth2.exception;

import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.oauth2.common.DefaultThrowableAnalyzer;
import org.springframework.security.oauth2.common.exceptions.InsufficientScopeException;
import org.springframework.security.oauth2.common.exceptions.OAuth2Exception;
import org.springframework.security.oauth2.provider.error.WebResponseExceptionTranslator;
import org.springframework.security.web.util.ThrowableAnalyzer;
import org.springframework.stereotype.Component;
import org.springframework.web.HttpRequestMethodNotSupportedException;

import java.io.IOException;

/**
 * 处理认证异常
 * @author glacier
 * @version 1.0
 * @date 2020-07-29 16:57
 */
@Slf4j
@Component
public class CunstomWebResponseExceptionTranslator implements WebResponseExceptionTranslator<OAuth2Exception> {

    private final ThrowableAnalyzer throwableAnalyzer = new DefaultThrowableAnalyzer();

    @Override
    public ResponseEntity<OAuth2Exception> translate(Exception e) throws Exception {
        Throwable[] causeChain = this.throwableAnalyzer.determineCauseChain(e);
        Exception ase = (OAuth2Exception) this.throwableAnalyzer.getFirstThrowableOfType(
                OAuth2Exception.class, causeChain);
        //异常链中有OAuth2Exception异常
        if (ase != null) {
            return this.handleOAuth2Exception((OAuth2Exception) ase);
        }
        //身份验证相关异常
        ase = (AuthenticationException) this.throwableAnalyzer.getFirstThrowableOfType(
                AuthenticationException.class, causeChain);
        if (ase != null) {
            return this.handleOAuth2Exception(new UnauthorizedException(e.getMessage(), e));
        }
        //异常链中包含拒绝访问异常
        ase = (AccessDeniedException) this.throwableAnalyzer.getFirstThrowableOfType(
                AccessDeniedException.class, causeChain);

        if (ase != null) {
            return this.handleOAuth2Exception(new ForbiddenException(ase.getMessage(), ase));
        }
        //异常链中包含Http方法请求异常
        ase = (HttpRequestMethodNotSupportedException) this.throwableAnalyzer.getFirstThrowableOfType(
                HttpRequestMethodNotSupportedException.class, causeChain);
        if (ase != null) {
            return this.handleOAuth2Exception(new MethodNotAllowed(ase.getMessage(), ase));
        }
        return this.handleOAuth2Exception(new ServerErrorException(HttpStatus.INTERNAL_SERVER_ERROR.getReasonPhrase(), e));
    }

    /**
     * 处理异常
     * @param e
     * @return
     * @throws IOException
     */
    private ResponseEntity<OAuth2Exception> handleOAuth2Exception(OAuth2Exception e) throws IOException {
        int status = e.getHttpErrorCode();
        HttpHeaders headers = new HttpHeaders();
        headers.set("Cache-Control", "no-store");
        headers.set("Pragma", "no-cache");
        if (status == HttpStatus.UNAUTHORIZED.value() || e instanceof InsufficientScopeException) {
            headers.set("WWW-Authenticate", String.format("%s %s", "Bearer", e.getSummary()));
        }
        CustomOAuth2Exception exception = new CustomOAuth2Exception(e.getMessage(), e);
        return new ResponseEntity<>(exception, headers, HttpStatus.valueOf(status));
    }


    private static class MethodNotAllowed extends OAuth2Exception {
        private static final long serialVersionUID = 6597574463987034094L;

        public MethodNotAllowed(String msg, Throwable t) {
            super(msg, t);
        }

        @Override
        public String getOAuth2ErrorCode() {
            return "method_not_allowed";
        }

        @Override
        public int getHttpErrorCode() {
            return 405;
        }
    }

    private static class UnauthorizedException extends OAuth2Exception {
        private static final long serialVersionUID = -7388506487289069605L;

        public UnauthorizedException(String msg, Throwable t) {
            super(msg, t);
        }

        @Override
        public String getOAuth2ErrorCode() {
            return "unauthorized";
        }

        @Override
        public int getHttpErrorCode() {
            return 401;
        }
    }

    private static class ServerErrorException extends OAuth2Exception {
        private static final long serialVersionUID = -1219547194925438482L;

        public ServerErrorException(String msg, Throwable t) {
            super(msg, t);
        }

        @Override
        public String getOAuth2ErrorCode() {
            return "server_error";
        }

        @Override
        public int getHttpErrorCode() {
            return 500;
        }
    }

    private static class ForbiddenException extends OAuth2Exception {
        private static final long serialVersionUID = -302836588623744811L;

        public ForbiddenException(String msg, Throwable t) {
            super(msg, t);
        }

        @Override
        public String getOAuth2ErrorCode() {
            return "access_denied";
        }

        @Override
        public int getHttpErrorCode() {
            return 403;
        }
    }
}
