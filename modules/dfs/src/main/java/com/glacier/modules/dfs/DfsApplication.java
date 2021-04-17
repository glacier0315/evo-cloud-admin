package com.glacier.modules.dfs;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

/**
 * @author glacier
 * @version 1.0
 * date 2020-07-22 15:19
 */
@EnableDiscoveryClient
@SpringBootApplication
public class DfsApplication {

    public static void main(String[] args) {
        SpringApplication.run(DfsApplication.class, args);
    }
}
