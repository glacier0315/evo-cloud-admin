package com.glacier.authorization.resource.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.core.io.ClassPathResource;
import org.springframework.security.oauth2.provider.token.TokenStore;
import org.springframework.security.oauth2.provider.token.store.JwtAccessTokenConverter;
import org.springframework.security.oauth2.provider.token.store.JwtTokenStore;
import org.springframework.util.FileCopyUtils;

import java.io.IOException;

/**
 * jwt 存储令牌
 *
 * @author glacier
 * @version 1.0
 * @date 2020-02-09 08:28
 */
@Configuration
public class JwtTokenStoreConfig {
    /**
     * 公钥
     */
    public static final String PUBKEY_PUB = "pubkey.pub";

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
     * jwt转换
     *
     * @return
     */
    @Bean
    public JwtAccessTokenConverter jwtAccessTokenConverter() {
        JwtAccessTokenConverter accessTokenConverter = new JwtAccessTokenConverter();
        String publicKey;
        try {
            publicKey = new String(FileCopyUtils.copyToByteArray(new ClassPathResource(PUBKEY_PUB).getInputStream()));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        accessTokenConverter.setVerifierKey(publicKey);
        return accessTokenConverter;
    }
}
