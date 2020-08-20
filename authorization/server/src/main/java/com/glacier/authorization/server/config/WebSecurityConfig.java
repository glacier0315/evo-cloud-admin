package com.glacier.authorization.server.config;

import com.glacier.common.security.config.settings.SecuritySettings;
import com.glacier.common.security.handler.CustomAccessDeniedHandler;
import com.glacier.common.security.handler.CustomAuthenticationEntryPoint;
import com.glacier.common.security.handler.CustomLogoutSuccessHandler;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.BeanIds;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.oauth2.client.OAuth2AuthorizedClientManager;
import org.springframework.security.oauth2.client.OAuth2AuthorizedClientProvider;
import org.springframework.security.oauth2.client.OAuth2AuthorizedClientProviderBuilder;
import org.springframework.security.oauth2.client.registration.ClientRegistrationRepository;
import org.springframework.security.oauth2.client.web.DefaultOAuth2AuthorizedClientManager;
import org.springframework.security.oauth2.client.web.OAuth2AuthorizedClientRepository;
import org.springframework.security.oauth2.core.AbstractOAuth2Token;
import org.springframework.security.oauth2.jwt.JwtDecoder;
import org.springframework.security.oauth2.jwt.NimbusJwtDecoder;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.security.web.access.AccessDeniedHandler;
import org.springframework.web.client.RestTemplate;

import java.security.KeyPair;
import java.security.interfaces.RSAPublicKey;

/**
 * security配置
 *
 * @author glacier
 * @version 1.0
 * @date 2019-08-04 10:03
 */
@Configuration
@EnableConfigurationProperties(SecuritySettings.class)
@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true, securedEnabled = true, jsr250Enabled = true)
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {

    private final SecuritySettings securitySettings;
    private final KeyPair keyPair;

    /**
     * 授权中心管理器，解决依赖注入问题
     *
     * @return
     * @throws Exception
     */
    @Bean(BeanIds.AUTHENTICATION_MANAGER)
    @Override
    public AuthenticationManager authenticationManagerBean() throws Exception {
        return super.authenticationManagerBean();
    }

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
        http.csrf(AbstractHttpConfigurer::disable)
                .authorizeRequests(authorizeRequests -> {
                    authorizeRequests.antMatchers(this.securitySettings.permitAll2Array())
                            .permitAll()
                            .anyRequest()
                            .authenticated();
                })
                .formLogin(formLogin -> {
                    formLogin.loginPage("/login")
                            .permitAll();
                })
                .httpBasic(httpBasic -> {
                })
                .logout(logout -> {
                    logout.logoutSuccessHandler(this.logoutSuccessHandler());
                })
                .oauth2ResourceServer(resourceServer -> {
                    resourceServer
                            .accessDeniedHandler(this.accessDeniedHandler())
                            .authenticationEntryPoint(this.authenticationEntryPoint())
                            .jwt(jwt -> {
                                jwt.decoder(this.jwtDecoder());
                            });
                })
                .oauth2Client(oauth2Client -> {

                });
    }

    /**
     * 配置客户端管理器
     *
     * @param clientRegistrationRepository
     * @param authorizedClientRepository
     * @return
     */
    @Bean
    OAuth2AuthorizedClientManager authorizedClientManager(
            ClientRegistrationRepository clientRegistrationRepository,
            OAuth2AuthorizedClientRepository authorizedClientRepository) {

        OAuth2AuthorizedClientProvider authorizedClientProvider =
                OAuth2AuthorizedClientProviderBuilder.builder()
                        .authorizationCode()
                        .refreshToken()
                        .clientCredentials()
                        .password()
                        .build();

        DefaultOAuth2AuthorizedClientManager authorizedClientManager =
                new DefaultOAuth2AuthorizedClientManager(
                        clientRegistrationRepository,
                        authorizedClientRepository);
        authorizedClientManager.setAuthorizedClientProvider(authorizedClientProvider);
        return authorizedClientManager;
    }

    @Bean
    JwtDecoder jwtDecoder() {
        return NimbusJwtDecoder.withPublicKey(
                (RSAPublicKey) this.keyPair.getPublic())
                .build();
    }

    /**
     * 密码工具类
     *
     * @return 密码工具类
     */
    @Bean
    PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    /**
     * 自定义异常
     *
     * @return 自定义异常处理
     */
    @Bean
    AuthenticationEntryPoint authenticationEntryPoint() {
        return new CustomAuthenticationEntryPoint();
    }

    /**
     * 自定义权限不足异常处理
     *
     * @return 自定义权限不足异常处理
     */
    @Bean
    AccessDeniedHandler accessDeniedHandler() {
        return new CustomAccessDeniedHandler();
    }

    /**
     * 自定义退出处理类
     *
     * @return 自定义退出处理类
     */
    @Bean
    CustomLogoutSuccessHandler logoutSuccessHandler() {
        return new CustomLogoutSuccessHandler();
    }

    /**
     * 实例化 RestTemplate
     *
     * @return 实例化 RestTemplate
     */
    @Bean
    @LoadBalanced
    RestTemplate restTemplate() {
        RestTemplate restTemplate = new RestTemplate();
        restTemplate.getInterceptors()
                .add((request, body, execution) -> {
                    Authentication authentication = SecurityContextHolder.getContext()
                            .getAuthentication();
                    if (authentication == null) {
                        return execution.execute(request, body);
                    }
                    if (!(authentication.getCredentials() instanceof AbstractOAuth2Token)) {
                        return execution.execute(request, body);
                    }
                    AbstractOAuth2Token token = (AbstractOAuth2Token) authentication.getCredentials();
                    request.getHeaders().setBearerAuth(token.getTokenValue());
                    return execution.execute(request, body);
                });
        return restTemplate;
    }
}
