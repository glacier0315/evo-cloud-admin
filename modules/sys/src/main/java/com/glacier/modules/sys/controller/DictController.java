package com.glacier.modules.sys.controller;

import com.glacier.common.core.entity.Result;
import com.glacier.common.core.entity.dto.IdDto;
import com.glacier.modules.sys.entity.Dict;
import com.glacier.modules.sys.entity.dto.dict.DictVo;
import com.glacier.modules.sys.service.DictService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * 字典管理
 *
 * @author glacier
 * @version 1.0
 * date 2019-12-01 21:13
 */
@Api(tags = "字典管理")
@RestController
@RequestMapping(value = "/dict")
public class DictController {
    private static final Logger log = LoggerFactory.getLogger(DictController.class);
    private final DictService dictService;

    @Autowired
    public DictController(DictService dictService) {
        this.dictService = dictService;
    }

    /**
     * 查询字典
     *
     * @return 字典
     */
    @ApiOperation("查询字典")
    @GetMapping("/findDictTree")
    public Result<List<DictVo>> findDictTree() {
        return Result.ok(
                this.dictService.findDictTree());
    }

    /**
     * 保存字典
     *
     * @param dict 字典
     * @return 保存响应
     */
    @ApiOperation("保存字典")
    @PostMapping("/save")
    public Result<Integer> save(@RequestBody Dict dict) {
        return Result.ok(
                this.dictService.save(dict));
    }

    /**
     * 删除指定字典
     *
     * @param idDto 主键
     * @return 删除响应
     */
    @ApiOperation("删除指定字典")
    @PostMapping("/delete")
    public Result<Integer> delete(@RequestBody IdDto idDto) {
        return Result.ok(
                this.dictService.delete(idDto.getId()));
    }
}
