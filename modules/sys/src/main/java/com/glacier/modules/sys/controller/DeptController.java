package com.glacier.modules.sys.controller;

import com.glacier.common.core.entity.Result;
import com.glacier.common.core.entity.dto.IdDto;
import com.glacier.common.security.utils.SecurityUtils;
import com.glacier.modules.sys.entity.dto.dept.DeptForm;
import com.glacier.modules.sys.entity.dto.dept.DeptVo;
import com.glacier.modules.sys.service.DeptService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * 组织机构管理
 *
 * @author glacier
 * @version 1.0
 * date 2019-10-24 17:15
 */
@Api(tags = "组织机构管理")
@RestController
@RequestMapping(value = "/dept")
public class DeptController {
    private static final Logger log = LoggerFactory.getLogger(DeptController.class);
    private final DeptService deptService;

    @Autowired
    public DeptController(DeptService deptService) {
        this.deptService = deptService;
    }

    /**
     * 查询所有组织机构
     *
     * @return 组织机构
     */
    @ApiOperation("查询所有组织机构")
    @GetMapping("/list")
    public Result<List<DeptVo>> list() {
        return Result.ok(
                this.deptService.findAllList());
    }

    /**
     * 保存组织机构
     *
     * @param deptForm 组织机构表单
     * @return 响应
     */
    @ApiOperation("保存组织机构")
    @PostMapping("/save")
    public Result<Integer> save(@RequestBody DeptForm deptForm) {
        return Result.ok(
                this.deptService.save(deptForm));
    }

    /**
     * 删除指定组织机构
     *
     * @param idDto 主键封装
     * @return 响应
     */
    @ApiOperation("删除指定组织机构")
    @PostMapping("/delete")
    public Result<Integer> delete(@RequestBody IdDto idDto) {
        return Result.ok(
                this.deptService.delete(idDto.getId()));
    }

    /**
     * 查询所有组织机构树
     *
     * @return 组织机构树响应
     */
    @ApiOperation("查询所有组织机构树")
    @GetMapping("/findTree")
    public Result<List<DeptVo>> findTree() {
        String userId = SecurityUtils.geUserId();
        log.debug("userId: {}", userId);
        return Result.ok(
                this.deptService.findTree(userId));
    }

    /**
     * 查询角色具有的单位
     *
     * @param roleId 角色id
     * @return 单位集合
     */
    @ApiOperation("查询角色具有的单位")
    @GetMapping("/findByRole")
    public Result<List<String>> findByRole(String roleId) {
        return Result.ok(
                this.deptService.findByRole(roleId));
    }
}
