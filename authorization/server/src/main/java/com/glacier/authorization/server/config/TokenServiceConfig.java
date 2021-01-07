package com.glacier.authorization.server.config;

import com.glacier.authorization.server.oauth2.CustomTokenEnhancer;
import com.glacier.authorization.server.oauth2.SubjectAttributeUserTokenConverter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.security.oauth2.authserver.AuthorizationServerProperties;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;
import org.springframework.security.oauth2.provider.token.*;
import org.springframework.security.oauth2.provider.token.store.JwtAccessTokenConverter;
import org.springframework.security.oauth2.provider.token.store.JwtTokenStore;
import org.springframework.security.oauth2.provider.token.store.KeyStoreKeyFactory;

import java.security.KeyPair;
import java.util.Arrays;
import java.util.Optional;
import java.util.concurrent.TimeUnit;

/**
 * tokenStore 配置
 *
 * @author glacier
 * @version 1.0
 * date 2020-02-09 08:28
 */
@Configuration
@EnableConfigurationProperties({AuthorizationServerProperties.class})
public class TokenServiceConfig {
    private final AuthorizationServerProperties authorizationServerProperties;

    @Autowired
    public TokenServiceConfig(AuthorizationServerProperties authorizationServerProperties) {
        this.authorizationServerProperties = authorizationServerProperties;
    }

    /**
     * 设置jwt 存储token
     *
     * @return
     */
    @Bean
    @Primary
    public TokenStore tokenStore() {
        return new JwtTokenStore(this.jwtAccessTokenConverter());
    }

    /**
     * 配置令牌加密
     * @return
     */
    @Bean
    public KeyPair keyPair() {
        Resource keyStore = new ClassPathResource(this.authorizationServerProperties.getJwt().getKeyStore());
        char[] keyStorePassword = this.authorizationServerProperties.getJwt().getKeyStorePassword().toCharArray();
        KeyStoreKeyFactory keyStoreKeyFactory = new KeyStoreKeyFactory(keyStore, keyStorePassword);
        String keyAlias = this.authorizationServerProperties.getJwt().getKeyAlias();
        char[] keyPassword = Optional.ofNullable(this.authorizationServerProperties.getJwt().getKeyPassword())
                .map(String::toCharArray)
                .orElse(keyStorePassword);
        return keyStoreKeyFactory.getKeyPair(keyAlias, keyPassword);
    }

    /**
     * jwt转换
     *
     * @return
     */
    @Bean
    public JwtAccessTokenConverter jwtAccessTokenConverter() {
        JwtAccessTokenConverter converter = new JwtAccessTokenConverter();
        // 使用非对称加密
        converter.setKeyPair(this.keyPair());

        DefaultAccessTokenConverter accessTokenConverter = new DefaultAccessTokenConverter();
        accessTokenConverter.setUserTokenConverter(new SubjectAttributeUserTokenConverter());
        converter.setAccessTokenConverter(accessTokenConverter);
        return converter;
    }

    /**
     * jwt内容转换
     *
     * @return
     */
    @Bean
    public TokenEnhancerChain tokenEnhancerChain() {
        TokenEnhancerChain tokenEnhancerChain = new TokenEnhancerChain();
        tokenEnhancerChain.setTokenEnhancers(Arrays.asList(this.jwtAccessTokenConverter(), new CustomTokenEnhancer()));
        return tokenEnhancerChain;
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
        defaultTokenServices.setTokenStore(this.tokenStore());
        // 配置jwt 转换
        defaultTokenServices.setTokenEnhancer(this.tokenEnhancerChain());
        // 是否产生刷新令牌
        defaultTokenServices.setSupportRefreshToken(true);
        // 设置令牌有效期2分钟
        defaultTokenServices.setAccessTokenValiditySeconds((int) TimeUnit.DAYS.toSeconds(5));
        // 设置刷新令牌有效期3天  默认3天
        defaultTokenServices.setRefreshTokenValiditySeconds((int) TimeUnit.DAYS.toSeconds(3));
        return defaultTokenServices;
    }
}
