package com.glacier.authorization.server.config;

import com.glacier.common.security.config.settings.SecuritySettings;
import com.glacier.common.security.handler.CustomAccessDeniedHandler;
import com.glacier.common.security.handler.CustomAuthenticationEntryPoint;
import com.glacier.common.security.handler.CustomLogoutSuccessHandler;
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
 * security??????
 *
 * @author glacier
 * @version 1.0
 * date 2019-08-04 10:03
 */
@Configuration
@EnableConfigurationProperties(SecuritySettings.class)
@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true, securedEnabled = true, jsr250Enabled = true)
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {

    private final SecuritySettings securitySettings;
    private final KeyPair keyPair;

    @Autowired
    public WebSecurityConfig(SecuritySettings securitySettings, KeyPair keyPair) {
        this.securitySettings = securitySettings;
        this.keyPair = keyPair;
    }

    /**
     * ????????????????????????????????????????????????
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
     * ??????????????????????????????
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
     * ????????????????????????
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
     * ???????????????
     *
     * @return ???????????????
     */
    @Bean
    PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    /**
     * ???????????????
     *
     * @return ?????????????????????
     */
    @Bean
    AuthenticationEntryPoint authenticationEntryPoint() {
        return new CustomAuthenticationEntryPoint();
    }

    /**
     * ?????????????????????????????????
     *
     * @return ?????????????????????????????????
     */
    @Bean
    AccessDeniedHandler accessDeniedHandler() {
        return new CustomAccessDeniedHandler();
    }

    /**
     * ????????????????????????
     *
     * @return ????????????????????????
     */
    @Bean
    CustomLogoutSuccessHandler logoutSuccessHandler() {
        return new CustomLogoutSuccessHandler();
    }

    /**
     * ????????? RestTemplate
     *
     * @return ????????? RestTemplate
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
