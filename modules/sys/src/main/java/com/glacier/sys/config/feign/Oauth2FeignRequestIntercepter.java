package com.glacier.sys.config.feign;

import feign.RequestInterceptor;
import feign.RequestTemplate;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.oauth2.core.AbstractOAuth2Token;

/**
 * feign 拦截器 传递token
 *
 * @author glacier
 * @version 1.0
 * @date 2020-05-19 12:49
 */
public class Oauth2FeignRequestIntercepter implements RequestInterceptor {

    public static final String BEARER = "Bearer";
    public static final String AUTHORIZATION = "Authorization";

    @Override
    public void apply(RequestTemplate template) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (authentication != null && authentication.getCredentials() instanceof AbstractOAuth2Token) {
            AbstractOAuth2Token token = (AbstractOAuth2Token) authentication.getCredentials();
            // Clear out the header
            template.header(AUTHORIZATION);
            template.header(AUTHORIZATION, String.format("%s %s", BEARER, token.getTokenValue()));
        }
    }
}
