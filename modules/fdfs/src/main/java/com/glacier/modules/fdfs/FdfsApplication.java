package com.glacier.modules.fdfs;

import com.alibaba.druid.spring.boot.autoconfigure.DruidDataSourceAutoConfigure;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

/**
 * @author glacier
 * @version 1.0
 * @date 2020-07-22 15:19
 */
@EnableDiscoveryClient
@SpringBootApplication(exclude = {DruidDataSourceAutoConfigure.class})
public class FdfsApplication {

    public static void main(String[] args) {
        SpringApplication.run(FdfsApplication.class, args);
    }
}