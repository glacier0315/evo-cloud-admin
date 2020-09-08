package com.glacier.modules.dfs.config.properties;

import lombok.Data;
import lombok.ToString;
import org.springframework.boot.context.properties.ConfigurationProperties;

/**
 * @author glacier
 * @version 1.0
 * @date 2020-09-08 19:57
 */
@ConfigurationProperties("minio")
@Data
@ToString
public class MinioProperties {
    /**
     *
     */
    private String endpoint;
    /**
     * key
     */
    private String accesskey;
    /**
     * 密钥
     */
    private String secretKey;
    /**
     * 默认文件夹
     */
    private String defaultFolder;
}
