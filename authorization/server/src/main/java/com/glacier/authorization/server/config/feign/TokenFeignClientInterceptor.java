package com.glacier.authorization.server.config.feign;

import feign.RequestInterceptor;
import feign.RequestTemplate;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
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
import java.util.Optional;

/**
 * feign拦截器
 *
 * @author glacier
 * @version 1.0
 * @date 2020-07-28 21:40
 */
@Slf4j
@Component
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class TokenFeignClientInterceptor implements RequestInterceptor {


    private final OAuth2AuthorizedClientService authorizedClientService;
    private final OAuth2AuthorizedClientManager oAuth2AuthorizedClientManager;
    /**
     * 认证服务器客户端reg id
     */
    private final String feignClientId = "uas_client";
    /**
     * 消息头 令牌前缀
     */
    public static final String BEARER = "Bearer";

    /**
     * 将token传递下去
     *
     * @param template
     */
    @Override
    public void apply(RequestTemplate template) {
        String token = Optional.ofNullable((ServletRequestAttributes) RequestContextHolder.getRequestAttributes())
                .map(attributes ->
                        attributes.getRequest().getHeader(HttpHeaders.AUTHORIZATION))
                .orElse("");
        // 请求中没有令牌，重新获取客户端令牌
        if (!token.contains(BEARER)) {
            token = Optional.ofNullable(this.acquireAccessToken(feignClientId))
                    .map(accessToken ->
                            String.format("%s %s", BEARER,
                                    accessToken.getTokenValue()))
                    .orElse("");
        }

        //添加token
        template.header(HttpHeaders.AUTHORIZATION, token);
    }

    /**
     * 获取token
     *
     * @param clientId
     * @return
     */
    public OAuth2AccessToken acquireAccessToken(final String clientId) {
        OAuth2AccessToken oAuth2AccessToken = null;
        final Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (authentication != null) {
            OAuth2AuthorizedClient oAuth2AuthorizedClient =
                    this.authorizedClientService.loadAuthorizedClient(clientId, authentication.getName());
            if (oAuth2AuthorizedClient != null) {
                oAuth2AccessToken = oAuth2AuthorizedClient.getAccessToken();
            }
            log.info("从本地获取令牌: {}", oAuth2AccessToken);

            if (oAuth2AccessToken == null || oAuth2AccessToken.getTokenValue().isEmpty()) {
                final ServletRequestAttributes servletRequestAttributes =
                        (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
                assert servletRequestAttributes != null;
                OAuth2AuthorizeRequest authorizeRequest =
                        OAuth2AuthorizeRequest
                                .withClientRegistrationId(clientId)
                                .principal(authentication)
                                .attributes(attrs -> {
                                    attrs.put(HttpServletRequest.class.getName(), servletRequestAttributes.getRequest());
                                    attrs.put(HttpServletResponse.class.getName(), servletRequestAttributes.getResponse());
                                })
                                .build();
                OAuth2AuthorizedClient authorizedClient = this.oAuth2AuthorizedClientManager.authorize(authorizeRequest);
                assert authorizedClient != null;
                oAuth2AccessToken = authorizedClient.getAccessToken();
                log.debug("获取客户端令牌是: {}", oAuth2AccessToken);
            }
        }
        return oAuth2AccessToken;
    }
}
