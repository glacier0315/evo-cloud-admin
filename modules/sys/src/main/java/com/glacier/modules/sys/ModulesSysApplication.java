package com.glacier.modules.sys;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.openfeign.EnableFeignClients;

/**
 * @author glacier
 * @version 1.0
 * @date 2019-12-23 10:31
 */
@EnableFeignClients
@EnableDiscoveryClient
@SpringBootApplication
public class ModulesSysApplication {

    public static void main(String[] args) {
        SpringApplication.run(ModulesSysApplication.class, args);
    }

}
