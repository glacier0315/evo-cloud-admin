package com.glacier.authorization.server.config;

import com.glacier.authorization.server.oauth2.exception.CunstomWebResponseExceptionTranslator;
import com.glacier.authorization.server.oauth2.filter.CustomClientCredentialsTokenEndpointFilter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.oauth2.config.annotation.configurers.ClientDetailsServiceConfigurer;
import org.springframework.security.oauth2.config.annotation.web.configuration.AuthorizationServerConfigurerAdapter;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableAuthorizationServer;
import org.springframework.security.oauth2.config.annotation.web.configurers.AuthorizationServerEndpointsConfigurer;
import org.springframework.security.oauth2.config.annotation.web.configurers.AuthorizationServerSecurityConfigurer;
import org.springframework.security.oauth2.provider.client.JdbcClientDetailsService;
import org.springframework.security.oauth2.provider.code.AuthorizationCodeServices;
import org.springframework.security.oauth2.provider.code.JdbcAuthorizationCodeServices;
import org.springframework.security.oauth2.provider.token.AuthorizationServerTokenServices;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.security.web.access.AccessDeniedHandler;

import javax.sql.DataSource;

/**
 * oauth2服务端配置
 *
 * @author glacier
 * @version 1.0
 * date 2020-05-18 11:05
 */
@Configuration
@EnableAuthorizationServer
public class AuthorizationServerConfig extends AuthorizationServerConfigurerAdapter {
    @Autowired
    private AuthenticationManager authenticationManager;
    @Autowired
    private UserDetailsService userDetailsService;
    @Autowired
    private JdbcClientDetailsService jdbcClientDetailsService;
    @Autowired
    private AuthorizationCodeServices jdbcAuthorizationCodeServices;
    @Autowired
    private AuthorizationServerTokenServices tokenService;
    @Autowired
    private CunstomWebResponseExceptionTranslator webResponseExceptionTranslator;
    @Autowired
    private AuthenticationEntryPoint authenticationEntryPoint;
    @Autowired
    private AccessDeniedHandler accessDeniedHandler;

    @Override
    public void configure(AuthorizationServerSecurityConfigurer security) throws Exception {
        CustomClientCredentialsTokenEndpointFilter endpointFilter = new CustomClientCredentialsTokenEndpointFilter();
        endpointFilter.setConfigurer(security);
        endpointFilter.setAuthenticationEntryPoint(this.authenticationEntryPoint);
        endpointFilter.afterPropertiesSet();
        // 使用此方式，处理客户端异常
        // 注意不能使用 allowFormAuthenticationForClients()，否则不生效
        security.addTokenEndpointAuthenticationFilter(endpointFilter);

        security.tokenKeyAccess("permitAll()")
                .checkTokenAccess("permitAll()")
                // 允许表单验证
                // .allowFormAuthenticationForClients()
                // 权限不足处理
                .accessDeniedHandler(this.accessDeniedHandler)
                // 处理异常
                .authenticationEntryPoint(this.authenticationEntryPoint);
    }

    @Override
    public void configure(ClientDetailsServiceConfigurer clients) throws Exception {
        clients.withClientDetails(jdbcClientDetailsService);
    }

    /**
     * 使用密码模式需要配置
     *
     * @param endpoints
     * @throws Exception
     */
    @Override
    public void configure(AuthorizationServerEndpointsConfigurer endpoints) throws Exception {
        endpoints
                // 处理异常
                .exceptionTranslator(this.webResponseExceptionTranslator)
                .authorizationCodeServices(this.jdbcAuthorizationCodeServices)
                // 配置密码模式
                .authenticationManager(this.authenticationManager)
                .userDetailsService(this.userDetailsService)
                // 配置令牌服务
                .tokenServices(this.tokenService);
    }

    /**
     * 配置客户端服务
     *
     * @return
     */
    @Bean
    public JdbcClientDetailsService jdbcClientDetailsService(
            DataSource dataSource, PasswordEncoder passwordEncoder) {
        JdbcClientDetailsService detailsService = new JdbcClientDetailsService(dataSource);
        detailsService.setPasswordEncoder(passwordEncoder);
        return detailsService;
    }

    /**
     * 配置授权码服务
     *
     * @return
     */
    @Bean
    public AuthorizationCodeServices jdbcAuthorizationCodeServices(DataSource dataSource) {
        return new JdbcAuthorizationCodeServices(dataSource);
    }
}
