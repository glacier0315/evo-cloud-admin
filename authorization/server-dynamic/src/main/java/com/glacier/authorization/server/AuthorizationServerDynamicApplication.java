package com.glacier.authorization.server;

import com.alibaba.druid.spring.boot.autoconfigure.DruidDataSourceAutoConfigure;
import com.glacier.authorization.server.config.settings.SecuritySettings;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

/**
 * @author glacier
 * @version 1.0
 * @date 2019-12-23 10:31
 */
@EnableConfigurationProperties(SecuritySettings.class)
@EnableDiscoveryClient
@SpringBootApplication(exclude = DruidDataSourceAutoConfigure.class)
public class AuthorizationServerDynamicApplication {

    public static void main(String[] args) {
        SpringApplication.run(AuthorizationServerDynamicApplication.class, args);
    }

}
