package com.glacier.modules.sys.config;

import com.baomidou.mybatisplus.extension.plugins.PaginationInterceptor;
import com.glacier.modules.sys.config.mybatis.PoMetaObjectHandler;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.transaction.annotation.EnableTransactionManagement;

/**
 * mybatis 配置
 *
 * @author glacier
 * @version 1.0
 * @date 2020-02-08 21:07
 */
@Configuration
@EnableTransactionManagement
@MapperScan("com.glacier.modules.sys.mapper")
public class MybatisConfig {

    /**
     * 通用处理
     * @return
     */
    @Bean
    public PoMetaObjectHandler poMetaObjectHandler() {
        return new PoMetaObjectHandler();
    }

    /**
     * mybatis-plus 分页插件
     *
     * @return
     */
    @Bean
    public PaginationInterceptor paginationInterceptor() {
        return new PaginationInterceptor();
    }
}
