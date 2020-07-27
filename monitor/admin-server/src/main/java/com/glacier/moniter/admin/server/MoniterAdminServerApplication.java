package com.glacier.moniter.admin.server;

import de.codecentric.boot.admin.server.config.EnableAdminServer;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

/**
 * spring boot admin 服务端
 *
 * @author glacier
 * @version 1.0
 * @date 2020-05-24 11:43
 */
@EnableDiscoveryClient
@EnableAdminServer
@SpringBootApplication
public class MoniterAdminServerApplication {

    public static void main(String[] args) {
        SpringApplication.run(MoniterAdminServerApplication.class, args);
    }
}
