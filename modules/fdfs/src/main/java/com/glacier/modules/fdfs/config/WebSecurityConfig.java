package com.glacier.modules.fdfs.config;

import com.alibaba.fastjson.JSONWriter;
import com.glacier.common.core.entity.Result;
import com.glacier.common.core.exception.AuthErrorType;
import com.glacier.modules.fdfs.config.settings.SecuritySettings;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.http.HttpStatus;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.oauth2.jwt.JwtDecoder;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.security.web.access.AccessDeniedHandler;

/**
 * security配置
 *
 * @author glacier
 * @version 1.0
 * @date 2019-08-04 10:03
 */
@EnableWebSecurity
@EnableConfigurationProperties(SecuritySettings.class)
@EnableGlobalMethodSecurity(prePostEnabled = true, securedEnabled = true, jsr250Enabled = true)
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {

    private final SecuritySettings securitySettings;
    private final JwtDecoder jwtDecoder;

    /**
     * 配置静态资源拦截问题
     *
     * @param web
     * @throws Exception
     */
    @Override
    public void configure(WebSecurity web) throws Exception {
        web.ignoring()
                .antMatchers("/favicon.ico", "/error", "/static/**", "/webjars/**", "/css/**", "/js/**", "/fonts/**");
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
                .csrf(AbstractHttpConfigurer::disable)
                .authorizeRequests(authorize -> {
                    authorize
                            .antMatchers(this.securitySettings.permitAll2Array())
                            .permitAll()
                            .anyRequest()
                            .authenticated();
                })
                .sessionManagement((sessionManagement) -> {
                    sessionManagement.sessionCreationPolicy(SessionCreationPolicy.STATELESS);
                })
                .oauth2ResourceServer(resourceServer -> {
                    resourceServer
                            .accessDeniedHandler(this.accessDeniedHandler())
                            .authenticationEntryPoint(this.authenticationEntryPoint())
                            .jwt(jwt -> {
                                jwt.decoder(this.jwtDecoder);
                            });
                });
    }

    /**
     * 自定义权限不足异常
     *
     * @return
     */
    @Bean
    public AccessDeniedHandler accessDeniedHandler() {
        return (request, response, accessDeniedException) -> {
            response.setStatus(HttpStatus.OK.value());
            response.setContentType("application/json;charset=utf-8");
            response.setCharacterEncoding("UTF-8");
            JSONWriter jsonWriter = new JSONWriter(response.getWriter());
            jsonWriter.writeObject(Result.<String>error(AuthErrorType.ACCESS_DENIED));
            jsonWriter.flush();
            jsonWriter.close();
        };
    }

    /**
     * 自定义需要授权异常
     *
     * @return
     */
    @Bean
    public AuthenticationEntryPoint authenticationEntryPoint() {
        return (request, response, authException) -> {
            response.setStatus(HttpStatus.OK.value());
            response.setContentType("application/json;charset=utf-8");
            response.setCharacterEncoding("UTF-8");
            JSONWriter jsonWriter = new JSONWriter(response.getWriter());
            jsonWriter.writeObject(Result.<String>error(AuthErrorType.INVALID_GRANT));
            jsonWriter.flush();
            jsonWriter.close();
        };
    }
}
