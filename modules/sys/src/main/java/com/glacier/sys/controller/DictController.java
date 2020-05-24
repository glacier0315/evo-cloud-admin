package com.glacier.sys.controller;

import com.glacier.common.core.entity.dto.IdDto;
import com.glacier.common.core.entity.vo.HttpResult;
import com.glacier.sys.entity.pojo.Dict;
import com.glacier.sys.service.DictService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * 字典控制层
 * @author glacier
 * @version 1.0
 * @date 2019-12-01 21:13
 */
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
    @GetMapping("/findDictTree")
    public HttpResult<List<Dict>> findDictTree() {
        return HttpResult.ok(this.dictService.findDictTree());
    }

    /**
     * 保存字典
     *
     * @param dict
     * @return
     */
    @PostMapping("/save")
    public HttpResult<Integer> save(@RequestBody Dict dict) {
        return HttpResult.ok(this.dictService.save(dict));
    }

    /**
     * 删除指定字典
     *
     * @param idDtos
     * @return
     */
    @PostMapping("/delete")
    public HttpResult<Integer> delete(@RequestBody List<IdDto> idDtos) {
        return HttpResult.ok(this.dictService.batchDelete(idDtos));
    }
}
