package com.glacier.modules.sys.controller;

import com.glacier.common.core.entity.form.IdForm;
import com.glacier.common.core.entity.vo.Result;
import com.glacier.modules.sys.entity.pojo.Dept;
import com.glacier.modules.sys.service.DeptService;
import com.glacier.modules.sys.utils.SecurityUtils;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * 组织机构管理
 *
 * @author glacier
 * @version 1.0
 * @date 2019-10-24 17:15
 */
@Api(tags = "组织机构管理")
@Slf4j
@RestController
@RequestMapping(value = "/dept")
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class DeptController {

    private final DeptService deptService;

    /**
     * 查询所有组织机构
     *
     * @return
     */
    @ApiOperation("查询所有组织机构")
    @GetMapping("/list")
    public Result<List<Dept>> list() {
        return Result.ok(
                this.deptService.findList());
    }

    /**
     * 保存组织机构
     *
     * @param dept
     * @return
     */
    @ApiOperation("保存组织机构")
    @PostMapping("/save")
    public Result<Integer> save(@RequestBody Dept dept) {
        return Result.ok(
                this.deptService.save(dept));
    }

    /**
     * 删除指定组织机构
     *
     * @param idForms
     * @return
     */
    @ApiOperation("删除指定组织机构")
    @PostMapping("/delete")
    public Result<Integer> delete(@RequestBody List<IdForm> idForms) {
        return Result.ok(
                this.deptService.batchDelete(idForms));
    }

    /**
     * 查询所有组织机构树
     *
     * @return
     */
    @ApiOperation("查询所有组织机构树")
    @GetMapping("/findTree")
    public Result<List<Dept>> findTree() {
        String userId = SecurityUtils.geUserId();
        log.debug("userId: {}", userId);
        return Result.ok(
                this.deptService.findTree(userId));
    }
}
