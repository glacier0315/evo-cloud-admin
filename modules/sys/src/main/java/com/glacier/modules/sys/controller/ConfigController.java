package com.glacier.modules.sys.controller;

import com.glacier.common.core.entity.Result;
import com.glacier.common.core.entity.dto.IdDto;
import com.glacier.common.core.entity.page.PageRequest;
import com.glacier.common.core.entity.page.PageResponse;
import com.glacier.modules.sys.entity.Config;
import com.glacier.modules.sys.service.ConfigService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * 配置管理
 *
 * @author glacier
 * @version 1.0
 * date 2019-12-01 20:43
 */
@RestController
@RequestMapping(value = "/config")
public class ConfigController {
    private static final Logger log = LoggerFactory.getLogger(ConfigController.class);
    private final ConfigService configService;

    @Autowired
    public ConfigController(ConfigService configService) {
        this.configService = configService;
    }

    /**
     * 分页查询用户
     *
     * @param pageRequest 分页请求
     * @return 分页响应
     */
    @PostMapping("/findPage")
    public Result<PageResponse<Config>> findPage(@RequestBody PageRequest<Config> pageRequest) {
        return Result.ok(
                this.configService.findPage(pageRequest));
    }

    /**
     * 保存配置
     *
     * @param config 记录
     * @return 保存记录数
     */
    @PostMapping("/save")
    public Result<Integer> save(@RequestBody Config config) {
        return Result.ok(
                this.configService.save(config));
    }

    /**
     * 删除指定用户
     *
     * @param idDto id封装
     * @return 删除记录数
     */
    @PostMapping("/delete")
    public Result<Integer> delete(@RequestBody IdDto idDto) {
        return Result.ok(
                this.configService.delete(idDto.getId()));
    }
}
