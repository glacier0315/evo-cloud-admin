package com.glacier.modules.dfs.config;

import com.glacier.modules.dfs.config.properties.MinioProperties;
import io.minio.MinioClient;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @author glacier
 * @version 1.0
 * date 2020-09-08 19:55
 */
@Configuration
@EnableConfigurationProperties(MinioProperties.class)
public class MinioConfiguration {
    private static final Logger log = LoggerFactory.getLogger(MinioConfiguration.class);
    private final MinioProperties minioProperties;

    @Autowired
    public MinioConfiguration(MinioProperties minioProperties) {
        this.minioProperties = minioProperties;
    }

    @Bean
    public MinioClient minioClient() {
        return MinioClient.builder()
                .endpoint(this.minioProperties.getEndpoint())
                .credentials(this.minioProperties.getAccesskey(),
                        this.minioProperties.getSecretKey())
                .build();
    }

}
