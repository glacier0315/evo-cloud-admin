package com.glacier.modules.dfs.service;

import io.minio.*;
import org.apache.commons.io.IOUtils;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.io.File;
import java.io.FileInputStream;

/**
 * @author glacier
 * @version 1.0
 * date 2020-09-08 21:52
 */
@SpringBootTest
public class FileServiceTest {

    @Autowired
    private MinioClient minioClient;

    @BeforeEach
    void setUp() {
    }

    @AfterEach
    void tearDown() {
    }

    @Test
    void testUpload() throws Exception {
        File file = new File("D:\\12.jpg");
        String bucket = "files";
        FileInputStream fileInputStream = null;
        boolean bucketExists = this.minioClient.bucketExists(
                BucketExistsArgs.builder()
                        .bucket(bucket)
                        .build());
        System.out.println(bucketExists);
        if (!bucketExists) {
            this.minioClient.makeBucket(MakeBucketArgs.builder()
                    .bucket(bucket)
                    .build());
        }
        fileInputStream = new FileInputStream(file);
        this.minioClient.putObject(PutObjectArgs.builder()
                .bucket(bucket)
                .contentType("image/jpeg")
                .object("2020/09/11/14.jpg")
                .stream(fileInputStream, file.length(), -1)
                .build());
        IOUtils.closeQuietly(fileInputStream, e -> {
        });
    }

    @Test
    void testRemove() throws Exception {
        String bucket = "files";
        this.minioClient.removeObject(RemoveObjectArgs.builder()
                .bucket(bucket)
                .object("2020/09/11/15.jpg")
                .build());
    }
}
