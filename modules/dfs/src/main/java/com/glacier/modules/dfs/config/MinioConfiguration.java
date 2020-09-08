package com.glacier.modules.dfs.config;

import com.glacier.modules.dfs.config.properties.MinioProperties;
import io.minio.MinioClient;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @author glacier
 * @version 1.0
 * @date 2020-09-08 19:55
 */
@Slf4j
@Configuration
@EnableConfigurationProperties(MinioProperties.class)
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class MinioConfiguration {

    private final MinioProperties minioProperties;

    @Bean
    public MinioClient minioClient() {
        return MinioClient.builder()
                .endpoint(this.minioProperties.getEndpoint())
                .credentials(this.minioProperties.getAccesskey(),
                        this.minioProperties.getSecretKey())
                .build();
    }

}
