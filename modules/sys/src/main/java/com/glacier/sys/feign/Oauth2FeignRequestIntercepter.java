package com.glacier.sys.feign;

import feign.RequestInterceptor;
import feign.RequestTemplate;

/**
 * feign 拦截器 传递token
 *
 * @author glacier
 * @version 1.0
 * @date 2020-05-19 12:49
 */
public class Oauth2FeignRequestIntercepter implements RequestInterceptor {

    public static final String BEARER = "Bearer";
    public static final String AUTHORIZATION = "Authorization";

    @Override
    public void apply(RequestTemplate template) {
        String token = null;
        // Clear out the header
        template.header(AUTHORIZATION);
        template.header(AUTHORIZATION, String.format("%s %s", BEARER, token));
    }
}
