package com.glacier.modules.sys.controller;

import com.glacier.common.core.entity.form.IdForm;
import com.glacier.common.core.entity.vo.Result;
import com.glacier.modules.sys.entity.pojo.Dict;
import com.glacier.modules.sys.entity.vo.DictVo;
import com.glacier.modules.sys.service.DictService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * 字典管理
 *
 * @author glacier
 * @version 1.0
 * @date 2019-12-01 21:13
 */
@Api(tags = "字典管理")
@Slf4j
@RestController
@RequestMapping(value = "/dict")
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class DictController {
    private final DictService dictService;

    /**
     * 查询字典
     *
     * @return
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
     * @param dict
     * @return
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
     * @param idForms
     * @return
     */
    @ApiOperation("删除指定字典")
    @PostMapping("/delete")
    public Result<Integer> delete(@RequestBody List<IdForm> idForms) {
        return Result.ok(
                this.dictService.batchDelete(idForms));
    }
}
