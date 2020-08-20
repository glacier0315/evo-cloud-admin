package com.glacier.authorization.server.oauth2;

import com.glacier.common.core.entity.Result;
import com.google.common.collect.Maps;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.oauth2.common.DefaultOAuth2AccessToken;
import org.springframework.security.oauth2.common.OAuth2AccessToken;
import org.springframework.security.oauth2.provider.OAuth2Authentication;
import org.springframework.security.oauth2.provider.token.TokenEnhancer;

import java.util.Map;

/**
 * 自定义token携带内容
 *
 * @author glacier
 * @version 1.0
 * @date 2020-05-21 17:15
 */
@Slf4j
public class CustomTokenEnhancer implements TokenEnhancer {

    @Override
    public OAuth2AccessToken enhance(OAuth2AccessToken accessToken, OAuth2Authentication authentication) {
        Map<String, Object> additionalInfo = Maps.newHashMap();
        Authentication userAuthentication = authentication.getUserAuthentication();
        if (userAuthentication instanceof UsernamePasswordAuthenticationToken) {
            log.info("userAuthentication: {}", userAuthentication);
        }
        // 自定义token内容，加入状态编码
        additionalInfo.put("code" , Result.SUCCUSS);
        ((DefaultOAuth2AccessToken) accessToken).setAdditionalInformation(additionalInfo);
        return accessToken;
    }
}