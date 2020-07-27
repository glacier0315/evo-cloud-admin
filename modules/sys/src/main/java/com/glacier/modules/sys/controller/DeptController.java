package com.glacier.modules.sys.controller;

import com.glacier.common.core.entity.form.IdForm;
import com.glacier.common.core.entity.vo.HttpResult;
import com.glacier.modules.sys.entity.pojo.Dept;
import com.glacier.modules.sys.service.DeptService;
import com.glacier.modules.sys.utils.SecurityUtils;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * 组织机构控制层
 * @author glacier
 * @version 1.0
 * @date 2019-10-24 17:15
 */
@Slf4j
@RestController
@RequestMapping(value = "/dept")
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class DeptController {

    private final DeptService deptService;

    /**
     * 查询所有组织机构 树
     *
     * @return
     */
    @GetMapping("/list")
    public HttpResult<List<Dept>> list() {
        return HttpResult.ok(
                this.deptService.findList()
        );
    }

    /**
     * 保存组织机构
     *
     * @param dept
     * @return
     */
    @PostMapping("/save")
    public HttpResult<Integer> save(@RequestBody Dept dept) {
        return HttpResult.ok(this.deptService.save(dept));
    }

    /**
     * 删除指定组织机构
     *
     * @param idForms
     * @return
     */
    @PostMapping("/delete")
    public HttpResult<Integer> delete(@RequestBody List<IdForm> idForms) {
        return HttpResult.ok(this.deptService.batchDelete(idForms));
    }

    /**
     * 查询所有组织机构 树
     *
     * @return
     */
    @GetMapping("/findTree")
    public HttpResult<List<Dept>> findTree() {
        String userId = SecurityUtils.geUserId();
        log.debug("userId: {}", userId);
        List<Dept> tree = this.deptService.findTree(userId);
        return HttpResult.ok(tree);
    }
}
