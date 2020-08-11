package com.glacier.modules.sys.controller;

import com.glacier.common.core.entity.form.IdForm;
import com.glacier.common.core.entity.page.PageRequest;
import com.glacier.common.core.entity.page.PageResponse;
import com.glacier.common.core.entity.vo.Result;
import com.glacier.modules.sys.entity.pojo.Config;
import com.glacier.modules.sys.service.ConfigService;
import io.swagger.annotations.Api;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * 配置管理
 *
 * @author glacier
 * @version 1.0
 * @date 2019-12-01 20:43
 */
@Api(tags = "配置管理")
@Slf4j
@RestController
@RequestMapping(value = "/config")
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class ConfigController {

    private final ConfigService configService;

    /**
     * 分页查询用户
     *
     * @param pageRequest
     * @return
     */
    @PostMapping("/findPage")
    public Result<PageResponse<Config>> findPage(@RequestBody PageRequest<Config> pageRequest) {
        return Result.ok(this.configService.findPage(pageRequest));
    }

    /**
     * 保存配置
     *
     * @param config
     * @return
     */
    @PostMapping("/save")
    public Result<Integer> save(@RequestBody Config config) {
        return Result.ok(this.configService.save(config));
    }

    /**
     * 删除指定用户
     *
     * @param idForms
     * @return
     */
    @PostMapping("/delete")
    public Result<Integer> delete(@RequestBody List<IdForm> idForms) {
        return Result.ok(this.configService.batchDelete(idForms));
    }
}
