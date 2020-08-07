package com.glacier.common.web.config;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

import java.io.Serializable;

/**
 * fastdfs 配置
 *
 * @author glacier
 * @version 1.0
 * @date 2020-02-10 19:33
 */
@Data
@Component
@ConfigurationProperties(prefix = "fdfs")
public class DfsResConfig implements Serializable {

    private static final long serialVersionUID = 7177348548467185269L;
    private String resHost;
    private String resPort;
}