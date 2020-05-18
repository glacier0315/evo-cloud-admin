package com.glacier.sys.controller;

import com.glacier.common.core.entity.dto.IdDto;
import com.glacier.common.core.http.HttpResult;
import com.glacier.sys.entity.Dept;
import com.glacier.sys.service.DeptService;
import com.glacier.sys.utils.SecurityUtils;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @author glacier
 * @version 1.0
 * @description 组织机构控制层
 * @date 2019-10-24 17:15
 */
@Slf4j
@RestController
@RequestMapping(value = "/dept")
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class DeptController {

    private final DeptService deptService;

    /**
     * 保存组织机构
     *
     * @param dept
     * @return
     */
    @PostMapping("/save")
    public HttpResult<Integer> save(@RequestBody Dept dept) {
        return HttpResult.ok(deptService.save(dept));
    }

    /**
     * 删除指定组织机构
     *
     * @param idDtos
     * @return
     */
    @PostMapping("/delete")
    public HttpResult<Integer> delete(@RequestBody List<IdDto> idDtos) {
        return HttpResult.ok(deptService.batchDelete(idDtos));
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
        List<Dept> tree = deptService.findTree(userId);
        return HttpResult.ok(tree);
    }
}
