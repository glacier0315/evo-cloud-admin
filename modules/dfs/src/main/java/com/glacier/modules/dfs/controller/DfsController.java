package com.glacier.modules.dfs.controller;

import com.glacier.common.core.entity.Result;
import com.glacier.modules.dfs.service.FileService;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import springfox.documentation.annotations.ApiIgnore;

import javax.servlet.http.HttpServletResponse;

/**
 * @author glacier
 * @version 1.0
 * @date 2020-09-11 15:11
 */
@Slf4j
@RestController
@RequestMapping("/dfs")
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class DfsController {

    private final FileService fileService;

    /**
     * 下载文件
     * @param path
     * @param response
     */
    @ApiOperation(value = "下载文件")
    @GetMapping(value = "/download")
    @SneakyThrows(Exception.class)
    public void download(@RequestParam("path") String path,
                         @ApiIgnore HttpServletResponse response) {
        this.fileService.download(path, response);
    }

    /**
     * 单文件上传
     *
     * @param file
     * @return
     */
    @PostMapping("/upload")
    public Result<String> upload(@RequestParam("file") MultipartFile file) {
        return this.fileService.upload(file);
    }

    /**
     * 单文件删除
     *
     * @param url
     * @return
     */
    @PostMapping("/delete")
    public Result<String> deleteFile(String url) {
        return this.fileService.deleteFile(url);
    }
}
