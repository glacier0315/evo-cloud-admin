package com.glacier.sys.config;

import com.glacier.sys.config.feign.Oauth2FeignRequestIntercepter;
import feign.Logger;
import feign.RequestInterceptor;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.oauth2.core.AbstractOAuth2Token;
import org.springframework.web.client.RestTemplate;

/**
 * Feign配置
 *
 * @author glacier
 * @version 1.0
 * @date 2020-02-10 19:33
 */
@Configuration
@EnableFeignClients("com.glacier.sys.consumer")
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class FeignConfig {

    /**
     * 修改Feign日志输出级别
     *
     * @return
     */
    @Bean
    Logger.Level feignLevel() {
        return Logger.Level.FULL;
    }

    /**
     * 在feign调用的时候，也加入认证信息
     *
     * @return
     */
    @Bean
    public RequestInterceptor oauth2FeignRequestlnterceptor() {
        return new Oauth2FeignRequestIntercepter();
    }

    @Bean
    public RestTemplate rest() {
        RestTemplate rest = new RestTemplate();
        rest.getInterceptors().add((request, body, execution) -> {
            Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
            if (authentication == null) {
                return execution.execute(request, body);
            }

            if (!(authentication.getCredentials() instanceof AbstractOAuth2Token)) {
                return execution.execute(request, body);
            }

            AbstractOAuth2Token token = (AbstractOAuth2Token) authentication.getCredentials();
            request.getHeaders().setBearerAuth(token.getTokenValue());
            return execution.execute(request, body);
        });
        return rest;
    }
}
