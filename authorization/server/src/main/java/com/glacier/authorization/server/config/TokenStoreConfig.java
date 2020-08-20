package com.glacier.authorization.server.config;

import com.glacier.authorization.server.oauth2.CustomTokenEnhancer;
import com.glacier.authorization.server.oauth2.SubjectAttributeUserTokenConverter;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.security.oauth2.authserver.AuthorizationServerProperties;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;
import org.springframework.security.oauth2.provider.token.DefaultAccessTokenConverter;
import org.springframework.security.oauth2.provider.token.TokenEnhancerChain;
import org.springframework.security.oauth2.provider.token.TokenStore;
import org.springframework.security.oauth2.provider.token.store.JwtAccessTokenConverter;
import org.springframework.security.oauth2.provider.token.store.JwtTokenStore;
import org.springframework.security.oauth2.provider.token.store.KeyStoreKeyFactory;

import java.security.KeyPair;
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
    private final AuthorizationServerProperties authorizationServerProperties;

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
}
