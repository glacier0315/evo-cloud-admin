package com.glacier.sys.config;

import feign.RequestInterceptor;
import feign.RequestTemplate;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.oauth2.client.OAuth2AuthorizedClientService;
import org.springframework.stereotype.Component;


/**
 * swagger集成配置
 *
 * @author glacier
 * @version 1.0
 * @date 2019-08-04 09:26
 */
@Slf4j
@Component
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class RequestInterceptorConfig implements RequestInterceptor {

    private OAuth2AuthorizedClientService authorizedClientService;

    @Override
    public void apply(RequestTemplate requestTemplate) {
        Object details = SecurityContextHolder.getContext().getAuthentication().getDetails();
        requestTemplate.header("Authorization", "Bearer ");
    }
}
