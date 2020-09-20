package com.glacier.modules.gen.controller;

import com.glacier.common.core.entity.Result;
import com.glacier.common.core.entity.dto.IdDto;
import com.glacier.common.core.entity.page.PageRequest;
import com.glacier.common.core.entity.page.PageResponse;
import com.glacier.modules.gen.entity.dto.datasource.GenDatasourceDto;
import com.glacier.modules.gen.entity.dto.datasource.GenDatasourceForm;
import com.glacier.modules.gen.entity.dto.datasource.GenDatasourceQuery;
import com.glacier.modules.gen.service.GenDatasourceService;
import io.swagger.annotations.Api;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * 数据源管理
 * @author glacier
 * @version 1.0
 * @date 2020-08-26 16:31
 */
@Api(tags = "数据源管理")
@Slf4j
@RestController
@RequestMapping(value = "/datasource")
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class GenDatasourceController {
    private final GenDatasourceService genDatasourceService;

    /**
     * 分页查询
     *
     * @param pageRequest
     * @return
     */
    @PostMapping("/findPage")
    public Result<PageResponse<GenDatasourceDto>> findPage(@RequestBody PageRequest<GenDatasourceQuery> pageRequest) {
        return Result.ok(
                this.genDatasourceService.findPage(pageRequest));
    }

    /**
     * 保存
     *
     * @param form
     * @return
     */
    @PostMapping("/save")
    public Result<Integer> save(@RequestBody GenDatasourceForm form) {
        return Result.ok(
                this.genDatasourceService.save(form));
    }

    /**
     * 删除
     *
     * @param idDto
     * @return
     */
    @PostMapping("/delete")
    public Result<Integer> delete(@RequestBody IdDto idDto) {
        return Result.ok(
                this.genDatasourceService.delete(idDto.getId()));
    }
}
