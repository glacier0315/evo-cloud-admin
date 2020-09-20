package com.glacier.modules.fdfs.controller;

import com.glacier.common.core.entity.Result;
import com.glacier.common.core.utils.StringUtil;
import com.glacier.modules.fdfs.config.DfsResConfig;
import com.glacier.modules.fdfs.util.FileDfsUtil;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

/**
 * fastdfs 控制层
 *
 * @author glacier
 * @version 1.0
 * @date 2020-02-10 19:33
 */
@Slf4j
@RestController
@RequestMapping("/dfs")
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class FileDfsController {

    private final FileDfsUtil fastDfsClientUtil;
    private final DfsResConfig dfsResConfig;

    /**
     * 单文件上传
     *
     * @param file
     * @return
     */
    @PostMapping("/upload")
    public Result<String> upload(@RequestParam("file") MultipartFile file) {
        Result<String> result = this.fastDfsClientUtil.uploadFile(file);
        if (StringUtil.equals(result.getCode(), Result.SUCCUSS)) {
            String url = String.format("%s://%s:%s/%s", "http",
                    this.dfsResConfig.getResHost(),
                    this.dfsResConfig.getResPort(),
                    result.getData());
            return Result.ok(url);
        }
        return result;
    }

    /**
     * 单文件删除
     *
     * @param url
     * @return
     */
    @PostMapping("/delete")
    public Result<String> deleteFile(String url) {
        return this.fastDfsClientUtil.deleteFile(url);
    }
}
