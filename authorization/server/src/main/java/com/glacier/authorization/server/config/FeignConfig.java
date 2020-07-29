package com.glacier.authorization.server.config;

import com.glacier.authorization.server.config.feign.TokenFeignClientInterceptor;
import feign.Logger;
import feign.RequestInterceptor;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * Feign配置
 *
 * @author glacier
 * @version 1.0
 * @date 2020-02-10 19:33
 */
@Configuration
@EnableFeignClients("com.glacier.authorization.server.consumer")
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
    public RequestInterceptor tokenFeignClientInterceptor() {
        return new TokenFeignClientInterceptor();
    }
}
