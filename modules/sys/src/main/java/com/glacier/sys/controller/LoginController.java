package com.glacier.sys.controller;

import com.glacier.common.core.http.HttpResult;
import com.glacier.sys.entity.dto.LoginUserDto;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.oauth2.client.OAuth2AuthorizationContext;
import org.springframework.security.oauth2.client.OAuth2AuthorizeRequest;
import org.springframework.security.oauth2.client.OAuth2AuthorizedClient;
import org.springframework.security.oauth2.client.OAuth2AuthorizedClientManager;
import org.springframework.security.oauth2.core.OAuth2AccessToken;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author glacier
 * @version 1.0
 * @date 2020-05-03 17:45
 */
@Slf4j
@RestController
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class LoginController {

    private final OAuth2AuthorizedClientManager authorizedClientManager;

    @PostMapping("login")
    public HttpResult<Object> login(@RequestBody LoginUserDto loginUserDto) {
        OAuth2AuthorizeRequest authorizeRequest = OAuth2AuthorizeRequest.withClientRegistrationId("uas_password")
                .attributes(attrs -> {
                    attrs.put(OAuth2AuthorizationContext.USERNAME_ATTRIBUTE_NAME, loginUserDto.getUsername());
                    attrs.put(OAuth2AuthorizationContext.PASSWORD_ATTRIBUTE_NAME, loginUserDto.getPassword());
                })
                .build();
        OAuth2AuthorizedClient authorizedClient = this.authorizedClientManager.authorize(authorizeRequest);
        assert authorizedClient != null;
        OAuth2AccessToken accessToken = authorizedClient.getAccessToken();
        return HttpResult.ok(accessToken.getTokenValue());
    }
}
