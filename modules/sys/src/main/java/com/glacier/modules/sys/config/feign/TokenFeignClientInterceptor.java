package com.glacier.modules.sys.config.feign;

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
    private final String feignClientId = "uas";
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
        ServletRequestAttributes attributes = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
        String token = Optional.ofNullable(attributes)
                .map(servletRequestAttributes -> {
                    String header = servletRequestAttributes.getRequest()
                            .getHeader(HttpHeaders.AUTHORIZATION);
                    log.info("从请求头获取令牌： {}", header);
                    return header;
                })
                .orElseGet(() -> {
                    OAuth2AccessToken accessToken = this.acquireAccessToken(this.feignClientId);
                    String token1 = Optional.ofNullable(accessToken)
                            .map(oAuth2AccessToken ->
                                    String.format("%s %s", BEARER, accessToken.getTokenValue()))
                            .orElse("");
                    log.info("从认证服务器获取令牌： {}", token1);
                    return token1;
                });

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
        final Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        OAuth2AuthorizedClient oAuth2AuthorizedClient =
                this.authorizedClientService.loadAuthorizedClient(
                        clientId,
                        Optional.ofNullable(authentication)
                                .map(Authentication::getName)
                                .orElse("")
                );
        log.debug("获取客户端是: {}", oAuth2AuthorizedClient);
        OAuth2AccessToken accessToken = Optional.ofNullable(oAuth2AuthorizedClient)
                .map(OAuth2AuthorizedClient::getAccessToken)
                .orElseGet(() -> {
                    final ServletRequestAttributes servletRequestAttributes =
                            (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
                    assert servletRequestAttributes != null;
                    OAuth2AuthorizedClient authorizedClient = this.oAuth2AuthorizedClientManager.authorize(
                            OAuth2AuthorizeRequest
                                    .withClientRegistrationId(clientId)
                                    .principal(authentication)
                                    .attributes(attrs -> {
                                        attrs.put(HttpServletRequest.class.getName(),
                                                servletRequestAttributes.getRequest());
                                        attrs.put(HttpServletResponse.class.getName(),
                                                servletRequestAttributes.getResponse());
                                    })
                                    .build()
                    );
                    assert authorizedClient != null;
                    return authorizedClient.getAccessToken();
                });
        log.debug("获取客户端令牌是: {}", accessToken);
        return accessToken;
    }
}
