package com.glacier.modules.gen.controller;

import com.glacier.common.core.entity.Result;
import com.glacier.common.core.entity.page.PageRequest;
import com.glacier.common.core.entity.page.PageResponse;
import com.glacier.modules.gen.entity.dto.table.GenTableDto;
import com.glacier.modules.gen.entity.dto.table.GenTableQuery;
import com.glacier.modules.gen.service.GenTableService;
import io.swagger.annotations.Api;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author glacier
 * @version 1.0
 * @date 2020-08-26 16:31
 */
@Api(tags = "代码生成管理")
@Slf4j
@RestController
@RequestMapping(value = "/table")
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class GenTableController {
    private final GenTableService genTableService;

    /**
     * 分页查询用户
     *
     * @param pageRequest
     * @return
     */
    @PostMapping("/findPage")
    public Result<PageResponse<GenTableDto>> findPage(@RequestBody PageRequest<GenTableQuery> pageRequest) {
        return Result.ok(
                this.genTableService.findPage(pageRequest));
    }
}
