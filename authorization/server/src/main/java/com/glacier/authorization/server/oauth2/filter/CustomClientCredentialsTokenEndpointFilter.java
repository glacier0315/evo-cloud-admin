package com.glacier.authorization.server.oauth2.filter;

import lombok.extern.slf4j.Slf4j;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.oauth2.config.annotation.web.configurers.AuthorizationServerSecurityConfigurer;
import org.springframework.security.oauth2.provider.client.ClientCredentialsTokenEndpointFilter;
import org.springframework.security.web.AuthenticationEntryPoint;

/**
 * @author glacier
 * @version 1.0
 * @date 2020-07-25 12:19
 */
@Slf4j
public class CustomClientCredentialsTokenEndpointFilter extends ClientCredentialsTokenEndpointFilter {

    private AuthorizationServerSecurityConfigurer configurer;
    private AuthenticationEntryPoint authenticationEntryPoint;

    @Override
    public void setAuthenticationEntryPoint(AuthenticationEntryPoint authenticationEntryPoint) {
        this.authenticationEntryPoint = authenticationEntryPoint;
    }

    public void setConfigurer(AuthorizationServerSecurityConfigurer configurer) {
        this.configurer = configurer;
    }

    @Override
    protected AuthenticationManager getAuthenticationManager() {
        return this.configurer.and().getSharedObject(AuthenticationManager.class);
    }

    @Override
    public void afterPropertiesSet() {
        this.setAuthenticationFailureHandler((httpServletRequest, httpServletResponse, authenticationException) -> {
            this.authenticationEntryPoint.commence(httpServletRequest, httpServletResponse, authenticationException);
        });
        this.setAuthenticationSuccessHandler((httpServletRequest, httpServletResponse, authentication) -> {
            // 无操作-仅允许过滤器链继续到令牌端点
        });
    }
}
