package com.glacier.modules.gen.config;

import com.glacier.common.security.config.CustomFeignConfig;
import feign.Logger;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;

/**
 * Feign配置
 *
 * @author glacier
 * @version 1.0
 * @date 2020-02-10 19:33
 */
@Configuration
@Import(value = {CustomFeignConfig.class})
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
}
