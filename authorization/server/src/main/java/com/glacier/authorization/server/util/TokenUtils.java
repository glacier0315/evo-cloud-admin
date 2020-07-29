package com.glacier.authorization.server.util;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.oauth2.client.OAuth2AuthorizeRequest;
import org.springframework.security.oauth2.client.OAuth2AuthorizedClient;
import org.springframework.security.oauth2.client.OAuth2AuthorizedClientManager;
import org.springframework.security.oauth2.client.OAuth2AuthorizedClientService;
import org.springframework.security.oauth2.core.OAuth2AccessToken;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * @author glacier
 * @version 1.0
 * @date 2020-07-29 16:42
 */
@Slf4j
@Component
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class TokenUtils {

    private final OAuth2AuthorizedClientService authorizedClientService;
    private final OAuth2AuthorizedClientManager oAuth2AuthorizedClientManager;


    /**
     * 获取token
     *
     * @param clientId
     * @return
     */
    public OAuth2AccessToken acquireAccessToken(String clientId) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        OAuth2AuthorizedClient oAuth2AuthorizedClient = this.authorizedClientService.loadAuthorizedClient("uas", authentication.getName());
        OAuth2AccessToken accessToken = oAuth2AuthorizedClient.getAccessToken();
        if (accessToken == null
                || accessToken.getTokenValue() == null
                || accessToken.getTokenValue().isEmpty()) {
            ServletRequestAttributes servletRequestAttributes = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
            assert servletRequestAttributes != null;
            HttpServletRequest request = servletRequestAttributes.getRequest();
            HttpServletResponse response = servletRequestAttributes.getResponse();
            OAuth2AuthorizeRequest authorizeRequest = OAuth2AuthorizeRequest.withClientRegistrationId(clientId)
                    .principal(authentication)
                    .attributes(attrs -> {
                        attrs.put(HttpServletRequest.class.getName(), request);
                        attrs.put(HttpServletResponse.class.getName(), response);
                    })
                    .build();
            OAuth2AuthorizedClient authorizedClient = this.oAuth2AuthorizedClientManager.authorize(authorizeRequest);
            assert authorizedClient != null;
            accessToken = authorizedClient.getAccessToken();
        }
        return accessToken;
    }
}
