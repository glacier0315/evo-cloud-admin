package com.glacier.authorization.resource.config;

import com.glacier.authorization.resource.config.settings.SecuritySettings;
import com.glacier.authorization.resource.oauth2.CustomAccessDeniedHandler;
import com.glacier.authorization.resource.oauth2.CustomAuthenticationEntryPoint;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableResourceServer;
import org.springframework.security.oauth2.config.annotation.web.configuration.ResourceServerConfigurerAdapter;
import org.springframework.security.oauth2.config.annotation.web.configurers.ResourceServerSecurityConfigurer;
import org.springframework.security.oauth2.provider.token.TokenStore;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.security.web.access.AccessDeniedHandler;

/**
 * 资源段、端配置
 *
 * @author glacier
 * @version 1.0
 * date 2020-05-22 17:08
 */
@Configuration
@EnableResourceServer
@EnableGlobalMethodSecurity(prePostEnabled = true, securedEnabled = true, jsr250Enabled = true)
public class ResourceServerConfig extends ResourceServerConfigurerAdapter {
    public static final String AUTHORIZATION_RESOURCE = "authorization-resource";
    private final SecuritySettings securitySettings;
    private final TokenStore tokenStore;

    @Autowired
    public ResourceServerConfig(SecuritySettings securitySettings, TokenStore tokenStore) {
        this.securitySettings = securitySettings;
        this.tokenStore = tokenStore;
    }

    @Override
    public void configure(ResourceServerSecurityConfigurer resources) throws Exception {
        resources.resourceId(AUTHORIZATION_RESOURCE)
                .tokenStore(this.tokenStore);
    }

    @Override
    public void configure(HttpSecurity http) throws Exception {
        http.authorizeRequests()
                .antMatchers(this.securitySettings.permitAll2Array())
                .permitAll()
                .anyRequest()
                .authenticated()
                .and()
                .exceptionHandling()
                .accessDeniedHandler(this.customAccessDeniedHandler())
                .authenticationEntryPoint(this.customAuthenticationEntryPoint());
    }

    /**
     * 自定义
     *
     * @return
     */
    @Bean
    public AccessDeniedHandler customAccessDeniedHandler() {
        return new CustomAccessDeniedHandler();
    }

    /**
     * 自定义需要授权异常
     *
     * @return
     */
    @Bean
    public AuthenticationEntryPoint customAuthenticationEntryPoint() {
        return new CustomAuthenticationEntryPoint();
    }
}
