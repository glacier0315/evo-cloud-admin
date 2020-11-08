package com.glacier.modules.dfs.service;

import com.glacier.common.core.entity.Result;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * @author glacier
 * @version 1.0
 * date 2020-09-08 21:51
 */
public interface FileService {

    /**
     * 文件下载
     * @param fileName 文件路径
     * @param response 响应
     * @param request 请求
     */
    void download(String fileName, HttpServletRequest request, HttpServletResponse response);

    /**
     * 单文件上传
     *
     * @param file 文件
     * @return 文件上传响应
     */
    Result<String> upload(MultipartFile file);

    /**
     * 文件删除
     * @param url 文件url
     * @return 文件删除响应
     */
    Result<String> deleteFile(String url);
}
