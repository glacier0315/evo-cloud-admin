package com.glacier.modules.dfs.controller;

import com.glacier.common.core.entity.Result;
import com.glacier.modules.dfs.service.FileService;
import io.swagger.annotations.ApiOperation;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import springfox.documentation.annotations.ApiIgnore;

import javax.servlet.http.HttpServletResponse;

/**
 * @author glacier
 * @version 1.0
 * date 2020-09-11 15:11
 */
@RestController
@RequestMapping("/dfs")
public class DfsController {
    private static final Logger log = LoggerFactory.getLogger(DfsController.class);
    private final FileService fileService;

    @Autowired
    public DfsController(FileService fileService) {
        this.fileService = fileService;
    }

    /**
     * 下载文件
     *
     * @param path     文件路径
     * @param response 响应
     */
    @ApiOperation(value = "下载文件")
    @GetMapping(value = "/download")
    public void download(@RequestParam("path") String path,
                         @ApiIgnore HttpServletResponse response) {
        this.fileService.download(path, response);
    }

    /**
     * 单文件上传
     *
     * @param file 文件
     * @return 响应结果
     */
    @PostMapping("/upload")
    public Result<String> upload(@RequestParam("file") MultipartFile file) {
        return this.fileService.upload(file);
    }

    /**
     * 单文件删除
     *
     * @param url 文件url
     * @return 响应
     */
    @PostMapping("/delete")
    public Result<String> deleteFile(String url) {
        return this.fileService.deleteFile(url);
    }
}
