package com.glacier.gateway.common;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

/**
 * @author glacier
 * @version 1.0
 * date 2019-12-08 09:30
 */
@EnableDiscoveryClient
@SpringBootApplication
public class GatewayCommonApplication {

    public static void main(String[] args) {
        SpringApplication.run(GatewayCommonApplication.class, args);
    }
}
