package com.glacier.modules.dfs.service.impl;

import com.glacier.common.core.constant.CommonConstant;
import com.glacier.common.core.constant.ServiceNameConstants;
import com.glacier.common.core.entity.Result;
import com.glacier.common.core.exception.BaseException;
import com.glacier.common.core.exception.SystemErrorType;
import com.glacier.common.core.utils.AppContextHolder;
import com.glacier.common.core.utils.IdGen;
import com.glacier.common.core.utils.StringUtil;
import com.glacier.modules.dfs.config.properties.MinioProperties;
import com.glacier.modules.dfs.controller.DfsController;
import com.glacier.modules.dfs.service.FileService;
import io.minio.*;
import org.apache.commons.io.FilenameUtils;
import org.apache.commons.io.IOUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
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
 * date 2020-09-11 15:30
 */
@Service("fileService")
public class FileServiceImpl implements FileService {
    private static final Logger log = LoggerFactory.getLogger(DfsController.class);
    private final MinioClient minioClient;
    private final MinioProperties minioProperties;
    /**
     * 路径分割线
     */
    public static final String FILE_SEQ = "/";

    @Autowired
    public FileServiceImpl(MinioClient minioClient, MinioProperties minioProperties) {
        this.minioClient = minioClient;
        this.minioProperties = minioProperties;
    }

    /**
     * 文件下载
     *
     * @param filePath 文件路径
     * @param response 响应
     */
    @Override
    public void download(String filePath, HttpServletResponse response) {
        this.checkFilePath(filePath);
        int index = filePath.indexOf(FILE_SEQ);
        String bucketName = filePath.substring(0, index);
        // 文件路径
        String path = filePath.substring(index);
        // 文件名称
        String fileName = filePath.substring(filePath.lastIndexOf(FILE_SEQ));
        InputStream in = null;
        final ObjectStat stat;
        try {
            stat = this.minioClient.statObject(StatObjectArgs.builder()
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
        } catch (Exception e) {
            log.error("出现异常", e);
            throw new BaseException(ServiceNameConstants.DFS_SERVICE,
                    SystemErrorType.BUSINESS_ERROR.getCode(),
                    "文件下载出现异常！",
                    new Object[]{filePath});
        } finally {
            IOUtils.closeQuietly(in, e -> {
                log.error("关闭流异常 ", e);
            });
        }
    }

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
        String path = StringUtil.join(now.getYear(), FILE_SEQ,
                now.getMonthValue(), FILE_SEQ,
                AppContextHolder.getInstance().getUserId(), FILE_SEQ,
                IdGen.uuid(), ".", extension);
        boolean bucketExists = false;
        InputStream in = null;
        try {
            bucketExists = this.minioClient.bucketExists(BucketExistsArgs.builder()
                    .bucket(bucketName)
                    .build());
            if (!bucketExists) {
                log.info("bucket不存在！");
                this.minioClient.makeBucket(MakeBucketArgs.builder()
                        .bucket(bucketName)
                        .build());
            }
            in = file.getInputStream();
            this.minioClient.putObject(PutObjectArgs.builder()
                    .contentType(contentType)
                    .bucket(bucketName)
                    .object(path)
                    .stream(in, file.getSize(), -1)
                    .build());
        } catch (Exception e) {
            log.error("出现异常", e);
            throw new BaseException(ServiceNameConstants.DFS_SERVICE,
                    SystemErrorType.BUSINESS_ERROR.getCode(),
                    "文件上传出现异常！",
                    new Object[]{file});
        } finally {
            IOUtils.closeQuietly(in, e -> {
                log.error("关闭流异常 ", e);
            });
        }
        return Result.ok(StringUtil.join(bucketName, FILE_SEQ, path));
    }

    @Override
    public Result<String> deleteFile(String filePath) {
        this.checkFilePath(filePath);
        int index = filePath.indexOf(FILE_SEQ);
        String bucketName = filePath.substring(0, index);
        // 文件路径
        String path = filePath.substring(index);
        try {
            this.minioClient.removeObject(RemoveObjectArgs.builder()
                    .bucket(bucketName)
                    .object(path)
                    .build());
        } catch (Exception e) {
            log.error("出现异常", e);
            throw new BaseException(ServiceNameConstants.DFS_SERVICE,
                    SystemErrorType.BUSINESS_ERROR.getCode(),
                    "文件删除出现异常！",
                    new Object[]{filePath});
        } finally {
        }
        return Result.ok();
    }

    /**
     * 检查文件路径
     *
     * @param filePath
     */
    private void checkFilePath(String filePath) {
        if (StringUtil.isBlank(filePath)
                || !StringUtil.contains(filePath, FILE_SEQ)) {
            log.error("文件路径 {} 不正确！", filePath);
            throw new BaseException(ServiceNameConstants.DFS_SERVICE,
                    SystemErrorType.BUSINESS_ERROR.getCode(),
                    "文件路径不正确！",
                    new Object[]{filePath});
        }
    }
}
