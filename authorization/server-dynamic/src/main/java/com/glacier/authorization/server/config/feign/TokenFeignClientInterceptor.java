package com.glacier.authorization.server.config.feign;

import com.google.common.net.HttpHeaders;
import feign.RequestInterceptor;
import feign.RequestTemplate;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

/**
 * feign拦截器
 *
 * @author glacier
 * @version 1.0
 * @date 2020-07-28 21:40
 */
public class TokenFeignClientInterceptor implements RequestInterceptor {
    /**
     * 将token传递下去
     *
     * @param template
     */
    @Override
    public void apply(RequestTemplate template) {
        ServletRequestAttributes attributes = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
        if (attributes != null) {
            //添加token
            template.header(HttpHeaders.AUTHORIZATION,
                    attributes.getRequest()
                            .getHeader(HttpHeaders.AUTHORIZATION));
        }
    }
}
