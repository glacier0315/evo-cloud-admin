package com.glacier.modules.dfs.service.impl;

import com.glacier.common.core.constant.CommonConstant;
import com.glacier.common.core.entity.Result;
import com.glacier.common.core.utils.AppContextHolder;
import com.glacier.common.core.utils.IdGen;
import com.glacier.common.core.utils.StringUtil;
import com.glacier.modules.dfs.config.properties.MinioProperties;
import com.glacier.modules.dfs.service.FileService;
import io.minio.*;
import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.io.FilenameUtils;
import org.apache.commons.io.IOUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.MimeTypeUtils;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletResponse;
import java.io.InputStream;
import java.net.URLEncoder;
import java.time.LocalDate;
import java.util.Objects;

/**
 * @author glacier
 * @version 1.0
 * @date 2020-09-11 15:30
 */
@Slf4j
@Service("fileService")
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class FileServiceImpl implements FileService {
    private final MinioClient minioClient;
    private final MinioProperties minioProperties;
    public static final String FIlE_SEQ = "/";

    /**
     * @param filePath
     * @param response 响应
     */
    @SneakyThrows(Exception.class)
    @Override
    public void download(String filePath, HttpServletResponse response) {
        if (StringUtil.isBlank(filePath)
                || !StringUtil.contains(filePath, FIlE_SEQ)) {
            throw new IllegalAccessException("文件路径不正确！");
        }
        int index = filePath.indexOf(FIlE_SEQ);
        String bucketName = filePath.substring(0, index);
        // 文件路径
        String path = filePath.substring(index);
        // 文件名称
        String fileName = filePath.substring(filePath.lastIndexOf(FIlE_SEQ));
        InputStream in = null;
        final ObjectStat stat = this.minioClient.statObject(StatObjectArgs.builder()
                .bucket(bucketName)
                .object(path)
                .build());
        response.setContentType(stat.contentType());

        response.setHeader("Content-Disposition",
                StringUtil.join("attachment;filename=",
                        URLEncoder.encode(fileName, CommonConstant.CHARSET_UTF_8)));
        in = this.minioClient.getObject(GetObjectArgs.builder()
                .bucket(bucketName)
                .object(path)
                .build());
        IOUtils.copy(in, response.getOutputStream());
        IOUtils.closeQuietly(in, e -> {
            log.error("关闭流异常 ", e);
        });
    }

    @SneakyThrows(Exception.class)
    @Override
    public Result<String> upload(MultipartFile file) {
        LocalDate now = LocalDate.now();
        String contentType = file.getContentType();
        String extension = FilenameUtils.getExtension(file.getOriginalFilename());
        if (extension == null || extension.isEmpty()) {
            extension = MimeTypeUtils.parseMimeType(Objects.requireNonNull(file.getContentType())).getSubtype();
        }
        // 根据contentType 转换为 对应的bucketName
        String bucketName = this.minioProperties.getDefaultBucketName();
        String path = StringUtil.join(now.getYear(), FIlE_SEQ,
                now.getMonthValue(), FIlE_SEQ,
                AppContextHolder.getInstance().getUserId(), FIlE_SEQ,
                IdGen.uuid(), ".", extension);
        boolean bucketExists = this.minioClient.bucketExists(BucketExistsArgs.builder()
                .bucket(bucketName)
                .build());
        if (!bucketExists) {
            log.info("bucket不存在！");
            this.minioClient.makeBucket(MakeBucketArgs.builder()
                    .bucket(bucketName)
                    .build());
        }
        this.minioClient.putObject(PutObjectArgs.builder()
                .contentType(contentType)
                .bucket(bucketName)
                .object(path)
                .stream(file.getInputStream(), file.getSize(), -1)
                .build());
        return Result.ok(StringUtil.join(bucketName, FIlE_SEQ, path));
    }

    @SneakyThrows(Exception.class)
    @Override
    public Result<String> deleteFile(String filePath) {
        if (StringUtil.isBlank(filePath)
                || !StringUtil.contains(filePath, FIlE_SEQ)) {
            throw new IllegalAccessException("文件路径不正确！");
        }
        int index = filePath.indexOf(FIlE_SEQ);
        String bucketName = filePath.substring(0, index);
        // 文件路径
        String path = filePath.substring(index);
        this.minioClient.removeObject(RemoveObjectArgs.builder()
                .bucket(bucketName)
                .object(path)
                .build());
        return Result.ok();
    }
}
