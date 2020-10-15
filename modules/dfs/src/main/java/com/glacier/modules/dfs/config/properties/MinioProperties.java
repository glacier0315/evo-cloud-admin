package com.glacier.modules.dfs.config.properties;

import org.springframework.boot.context.properties.ConfigurationProperties;

/**
 * @author glacier
 * @version 1.0
 * @date 2020-09-08 19:57
 */
@ConfigurationProperties("minio")
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
     * 默认BucketName
     */
    private String defaultBucketName;

    public String getEndpoint() {
        return this.endpoint;
    }

    public void setEndpoint(String endpoint) {
        this.endpoint = endpoint;
    }

    public String getAccesskey() {
        return this.accesskey;
    }

    public void setAccesskey(String accesskey) {
        this.accesskey = accesskey;
    }

    public String getSecretKey() {
        return this.secretKey;
    }

    public void setSecretKey(String secretKey) {
        this.secretKey = secretKey;
    }

    public String getDefaultBucketName() {
        return this.defaultBucketName;
    }

    public void setDefaultBucketName(String defaultBucketName) {
        this.defaultBucketName = defaultBucketName;
    }

    @Override
    public String toString() {
        return "MinioProperties{" +
                "endpoint='" + this.endpoint + '\'' +
                ", accesskey='" + this.accesskey + '\'' +
                ", secretKey='" + this.secretKey + '\'' +
                ", defaultBucketName='" + this.defaultBucketName + '\'' +
                '}';
    }
}
