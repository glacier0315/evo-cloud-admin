package com.glacier.common.web.controller;

import com.glacier.common.core.entity.vo.HttpResult;
import com.glacier.common.web.config.DfsResConfig;
import com.glacier.common.web.util.FileDfsUtil;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
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
public class FileDfsController {

    @Autowired
    private FileDfsUtil fastDfsClientUtil;

    @Autowired
    private DfsResConfig dfsResConfig;

    /**
     * 单文件上传
     *
     * @param file
     * @return
     */
    @PostMapping("/upload")
    public HttpResult<String> upload(@RequestParam("file") MultipartFile file) {
        HttpResult<String> result = this.fastDfsClientUtil.uploadFile(file);
        if (StringUtils.equals(result.getCode(), HttpResult.SUCCUSS)) {
            String url = String.format("%s:%s/%s", this.dfsResConfig.getResHost(),
                    this.dfsResConfig.getResPort(),
                    result.getData());
            return HttpResult.ok(url);
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
    public HttpResult<String> deleteFile(String url) {
        return this.fastDfsClientUtil.deleteFile(url);
    }
}