package com.glacier.common.security.config;

import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.Configuration;

/**
 * Feign配置
 *
 * @author glacier
 * @version 1.0
 * @date 2020-02-10 19:33
 */
@Configuration
@EnableFeignClients("com.glacier.**.consumer")
public class CustomFeignConfig {

}
