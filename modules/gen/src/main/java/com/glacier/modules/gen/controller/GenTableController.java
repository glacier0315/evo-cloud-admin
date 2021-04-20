package com.glacier.modules.gen.controller;

import com.glacier.common.core.entity.Result;
import com.glacier.common.core.entity.page.PageRequest;
import com.glacier.common.core.entity.page.PageResponse;
import com.glacier.modules.gen.entity.dto.table.GenTableDto;
import com.glacier.modules.gen.entity.dto.table.GenTableQuery;
import com.glacier.modules.gen.service.GenTableService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * 代码生成管理
 * @author glacier
 * @version 1.0
 * date 2020-08-26 16:31
 */
@RestController
@RequestMapping(value = "/table")
public class GenTableController {
    private static final Logger log = LoggerFactory.getLogger(GenTableController.class);
    private final GenTableService genTableService;

    @Autowired
    public GenTableController(GenTableService genTableService) {
        this.genTableService = genTableService;
    }

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
