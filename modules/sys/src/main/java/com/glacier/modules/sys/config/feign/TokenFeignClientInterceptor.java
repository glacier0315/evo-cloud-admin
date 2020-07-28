package com.glacier.modules.sys.config.feign;

import com.google.common.net.HttpHeaders;
import feign.RequestInterceptor;
import feign.RequestTemplate;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

/**
 * feign 拦截器 传递token
 *
 * @author glacier
 * @version 1.0
 * @date 2020-05-19 12:49
 */
public class TokenFeignClientInterceptor implements RequestInterceptor {

    public static final String BEARER = "Bearer";
    public static final String AUTHORIZATION = "Authorization";

    @Override
    public void apply(RequestTemplate template) {
//        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
//        if (authentication != null && authentication.getCredentials() instanceof AbstractOAuth2Token) {
//            AbstractOAuth2Token token = (AbstractOAuth2Token) authentication.getCredentials();
//            // Clear out the header
//            template.header(AUTHORIZATION);
//            template.header(AUTHORIZATION, String.format("%s %s", BEARER, token.getTokenValue()));
//        }

        ServletRequestAttributes attributes = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
        if (attributes != null) {
            //添加token
            template.header(HttpHeaders.AUTHORIZATION,
                    attributes.getRequest()
                            .getHeader(HttpHeaders.AUTHORIZATION));
        }
    }
}
