package com.glacier.modules.sys.utils;

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
        OAuth2AccessToken accessToken = null;
        Authentication authentication =
                SecurityContextHolder.getContext()
                        .getAuthentication();
        OAuth2AuthorizedClient oAuth2AuthorizedClient =
                this.authorizedClientService.loadAuthorizedClient(clientId, authentication.getName());
        log.debug("获取客户端是: {}", oAuth2AuthorizedClient);
        if (oAuth2AuthorizedClient != null) {
            accessToken = oAuth2AuthorizedClient.getAccessToken();
            log.debug("客户端获取token: {}", accessToken);
        }
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
            log.debug("通过客户端管理重新获取token: {}", accessToken);
            assert authorizedClient != null;
            accessToken = authorizedClient.getAccessToken();
        }
        return accessToken;
    }
}
