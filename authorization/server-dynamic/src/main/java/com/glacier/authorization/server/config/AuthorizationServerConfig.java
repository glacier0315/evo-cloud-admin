package com.glacier.authorization.server.config;

import com.baomidou.dynamic.datasource.DynamicRoutingDataSource;
import com.glacier.authorization.server.constant.Constant;
import com.glacier.authorization.server.oauth2.filter.CustomClientCredentialsTokenEndpointFilter;
import lombok.RequiredArgsConstructor;
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
import org.springframework.security.oauth2.provider.token.DefaultTokenServices;
import org.springframework.security.oauth2.provider.token.TokenEnhancerChain;
import org.springframework.security.oauth2.provider.token.TokenStore;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.security.web.access.AccessDeniedHandler;

import javax.sql.DataSource;
import java.util.concurrent.TimeUnit;

/**
 * oauth2服务端配置
 *
 * @author glacier
 * @version 1.0
 * @date 2020-05-18 11:05
 */
@Configuration
@EnableAuthorizationServer
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class AuthorizationServerConfig extends AuthorizationServerConfigurerAdapter {
    private final AuthenticationManager authenticationManager;
    private final UserDetailsService userDetailsService;
    private final PasswordEncoder passwordEncoder;
    private final DataSource dataSource;
    private final TokenStore tokenStore;
    private final TokenEnhancerChain tokenEnhancerChain;
    private final AuthenticationEntryPoint authenticationEntryPoint;
    private final AccessDeniedHandler accessDeniedHandler;

    @Override
    public void configure(AuthorizationServerSecurityConfigurer security) throws Exception {
        CustomClientCredentialsTokenEndpointFilter endpointFilter = new CustomClientCredentialsTokenEndpointFilter();
        endpointFilter.setConfigurer(security);
        endpointFilter.setAuthenticationEntryPoint(this.authenticationEntryPoint);
        endpointFilter.afterPropertiesSet();
        security.addTokenEndpointAuthenticationFilter(endpointFilter);

        security.tokenKeyAccess("permitAll()")
                .checkTokenAccess("permitAll()")
                // 允许表单验证
//                .allowFormAuthenticationForClients()
                // 权限不足处理
                .accessDeniedHandler(this.accessDeniedHandler)
                // 处理异常
                .authenticationEntryPoint(this.authenticationEntryPoint);
    }

    @Override
    public void configure(ClientDetailsServiceConfigurer clients) throws Exception {
        // 方式1
            /*clients.jdbc(dataSource)
                .passwordEncoder(passwordEncoder);*/
        // 方式2
        clients.withClientDetails(this.jdbcClientDetailsService());
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
                .authorizationCodeServices(this.jdbcAuthorizationCodeServices())
                // 配置密码模式
                .authenticationManager(this.authenticationManager)
                .userDetailsService(this.userDetailsService)
                // 配置令牌服务
                .tokenServices(this.tokenService());
    }

    /**
     * 配置客户端服务
     *
     * @return
     */
    @Bean
    public JdbcClientDetailsService jdbcClientDetailsService() {
        DynamicRoutingDataSource dynamicRoutingDataSource = (DynamicRoutingDataSource) this.dataSource;
        DataSource dataSource = dynamicRoutingDataSource.getCurrentDataSources()
                .get(Constant.DATASOURCE_AUTH);
        JdbcClientDetailsService jdbcClientDetailsService = new JdbcClientDetailsService(dataSource);
        jdbcClientDetailsService.setPasswordEncoder(this.passwordEncoder);
        return jdbcClientDetailsService;
    }

    /**
     * 配置授权码服务
     *
     * @return
     */
    @Bean
    public AuthorizationCodeServices jdbcAuthorizationCodeServices() {
        DynamicRoutingDataSource dynamicRoutingDataSource = (DynamicRoutingDataSource) this.dataSource;
        DataSource dataSource = dynamicRoutingDataSource.getCurrentDataSources()
                .get(Constant.DATASOURCE_AUTH);
        return new JdbcAuthorizationCodeServices(dataSource);
    }

    /**
     * 配置令牌服务
     *
     * @return
     */
    @Bean
    public AuthorizationServerTokenServices tokenService() {
        DefaultTokenServices defaultTokenServices = new DefaultTokenServices();
        // 设置令牌存储策略
        defaultTokenServices.setTokenStore(this.tokenStore);
        // 配置jwt 转换
        defaultTokenServices.setTokenEnhancer(this.tokenEnhancerChain);
        // 是否产生刷新令牌
        defaultTokenServices.setSupportRefreshToken(true);
        // 设置令牌有效期2分钟
        defaultTokenServices.setAccessTokenValiditySeconds((int) TimeUnit.DAYS.toSeconds(5));
        // 设置刷新令牌有效期3天  默认3天
        defaultTokenServices.setRefreshTokenValiditySeconds((int) TimeUnit.DAYS.toSeconds(3));
        return defaultTokenServices;
    }

}
