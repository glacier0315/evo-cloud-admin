package com.glacier.auth.config;

import com.glacier.auth.oauth2.enhancer.CustomTokenEnhancer;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.security.oauth2.authserver.AuthorizationServerProperties;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.oauth2.provider.token.TokenEnhancerChain;
import org.springframework.security.oauth2.provider.token.TokenStore;
import org.springframework.security.oauth2.provider.token.store.JwtAccessTokenConverter;
import org.springframework.security.oauth2.provider.token.store.JwtTokenStore;
import org.springframework.security.oauth2.provider.token.store.KeyStoreKeyFactory;

import java.util.Arrays;
import java.util.Optional;

/**
 * tokenStore 配置
 *
 * @author glacier
 * @version 1.0
 * @date 2020-02-09 08:28
 */
@Configuration
@EnableConfigurationProperties({AuthorizationServerProperties.class})
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class TokenStoreConfig {
    private final UserDetailsService userDetailsService;
    private final AuthorizationServerProperties authorizationServerProperties;

    /**
     * 设置jwt 存储token
     *
     * @return
     */
    @Bean
    @Primary
    public TokenStore tokenStore() {
        return new JwtTokenStore(jwtAccessTokenConverter());
    }

    /**
     * jwt转换
     *
     * @return
     */
    @Bean
    public JwtAccessTokenConverter jwtAccessTokenConverter() {
        JwtAccessTokenConverter accessTokenConverter = new JwtAccessTokenConverter();
        Resource keyStore = new ClassPathResource(this.authorizationServerProperties.getJwt().getKeyStore());
        char[] keyStorePassword = this.authorizationServerProperties.getJwt().getKeyStorePassword().toCharArray();
        KeyStoreKeyFactory keyStoreKeyFactory = new KeyStoreKeyFactory(keyStore, keyStorePassword);
        String keyAlias = this.authorizationServerProperties.getJwt().getKeyAlias();
        char[] keyPassword = Optional.ofNullable(this.authorizationServerProperties.getJwt().getKeyPassword())
                .map(String::toCharArray)
                .orElse(keyStorePassword);
        // 使用非对称加密
        accessTokenConverter.setKeyPair(keyStoreKeyFactory.getKeyPair(keyAlias, keyPassword));
        return accessTokenConverter;
    }

    /**
     * jwt内容转换
     *
     * @return
     */
    @Bean
    public TokenEnhancerChain tokenEnhancerChain() {
        TokenEnhancerChain tokenEnhancerChain = new TokenEnhancerChain();
        tokenEnhancerChain.setTokenEnhancers(Arrays.asList(jwtAccessTokenConverter(), new CustomTokenEnhancer()));
        return tokenEnhancerChain;
    }
}
