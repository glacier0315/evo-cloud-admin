package com.glacier.modules.sys.controller;

import com.glacier.common.core.entity.Result;
import com.glacier.common.core.entity.dto.IdDto;
import com.glacier.common.core.entity.page.PageRequest;
import com.glacier.common.core.entity.page.PageResponse;
import com.glacier.modules.sys.entity.dto.post.PostDto;
import com.glacier.modules.sys.entity.dto.post.PostQuery;
import com.glacier.modules.sys.service.PostService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * 岗位管理
 * @author glacier
 * @version 1.0
 * date 2020-09-06 08:01
 */
@RestController
@RequestMapping(value = "/post")
public class PostController {
    private static final Logger log = LoggerFactory.getLogger(PostController.class);
    private final PostService postService;

    @Autowired
    public PostController(PostService postService) {
        this.postService = postService;
    }

    /**
     * 查找所有岗位
     *
     * @return 岗位
     */
    @GetMapping("/findAll")
    public Result<List<PostDto>> findAll() {
        return Result.ok(
                this.postService.findAllList());
    }

    /**
     * 分页查询岗位
     *
     * @param pageRequest 分页请求实体类
     * @return 分页响应
     */
    @PostMapping("/findPage")
    public Result<PageResponse<PostDto>> findPage(
            @RequestBody PageRequest<PostQuery> pageRequest) {
        return Result.ok(
                this.postService.findPage(pageRequest));
    }

    /**
     * 保存岗位 （新增或者更新）
     *
     * @param postDto 岗位封装实体类
     * @return 保存响应
     */
    @PostMapping("/save")
    public Result<Integer> save(
            @RequestBody PostDto postDto) {
        return Result.ok(
                this.postService.save(postDto));
    }

    /**
     * 删除指定岗位
     *
     * @param idDto id封装实体类
     * @return 删除响应
     */
    @PostMapping("/delete")
    public Result<Integer> delete(@RequestBody IdDto idDto) {
        return Result.ok(
                this.postService.delete(idDto.getId()));
    }

    /**
     * 检验岗位编码
     *
     * @param postDto 岗位封装实体类
     * @return 校验响应
     */
    @PostMapping("/checkCode")
    public Result<Boolean> checkCode(
            @RequestBody PostDto postDto) {
        return Result.ok(this.postService.checkCode(postDto));
    }
}
