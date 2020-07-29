package com.glacier.authorization.server.config.feign;

import com.glacier.authorization.server.util.TokenUtils;
import com.google.common.net.HttpHeaders;
import feign.RequestInterceptor;
import feign.RequestTemplate;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

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

    private final TokenUtils tokenUtils;

    /**
     * 将token传递下去
     *
     * @param template
     */
    @Override
    public void apply(RequestTemplate template) {
        ServletRequestAttributes attributes = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
        String token = null;
        if (attributes != null) {
            token = attributes.getRequest()
                    .getHeader(HttpHeaders.AUTHORIZATION);
        }
        // 请求中没有令牌，重新获取客户端令牌
        if (token == null || token.isEmpty()) {
            token = String.format("%s %s", "Bearer",
                    this.tokenUtils.acquireAccessToken("uas"));
        }

        //添加token
        template.header(HttpHeaders.AUTHORIZATION, token);
    }
}
