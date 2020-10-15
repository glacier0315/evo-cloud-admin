package com.glacier.modules.gen;

import com.alibaba.druid.spring.boot.autoconfigure.DruidDataSourceAutoConfigure;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * @author glacier
 * @version 1.0
 * date 2020-08-07 11:14
 */
@SpringBootApplication(exclude = DruidDataSourceAutoConfigure.class)
public class GenApplication {

    public static void main(String[] args) {
        SpringApplication.run(GenApplication.class, args);
    }
}
