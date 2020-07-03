package com.glacier.sys.controller;

import com.glacier.common.core.entity.page.PageRequest;
import com.glacier.common.core.entity.page.PageResponse;
import com.glacier.common.core.entity.vo.HttpResult;
import com.glacier.sys.entity.form.UserAddForm;
import com.glacier.sys.entity.form.UserQueryForm;
import com.glacier.sys.entity.form.UserUpdateForm;
import com.glacier.sys.entity.vo.UserDetailsVo;
import com.glacier.sys.entity.vo.UserListVo;
import com.glacier.sys.service.UserService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * 用户管理
 *
 * @author glacier
 * @version 1.0
 * @date 2019-08-04 22:13
 */
@Slf4j
@RestController
@RequestMapping(value = "/user")
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class UserController {
    private final UserService userService;

    /**
     * 根据用户名获取用户信息
     *
     * @param username
     * @return
     */
    @GetMapping(value = "/{username}")
    public HttpResult<UserDetailsVo> findByUsername(
            @PathVariable("username") String username) {
        return HttpResult.ok(
                this.userService.loadUserByUsername(username));
    }

    /**
     * 分页查询用户
     *
     * @param pageRequest
     * @return
     */
    @PostMapping("/pageList")
    public HttpResult<PageResponse<UserListVo>> findPage(
            @RequestBody PageRequest<UserQueryForm> pageRequest) {
        return HttpResult.ok(
                this.userService.findPage(pageRequest));
    }

    /**
     * 保存用户
     *
     * @param userAddForm
     * @return
     */
    @PostMapping("/add")
    public HttpResult<Integer> add(
            @RequestBody UserAddForm userAddForm) {
        return HttpResult.ok(
                this.userService.add(userAddForm));
    }

    /**
     * 更新用户
     *
     * @param userUpdateForm
     * @return
     */
    @PutMapping("/update")
    public HttpResult<Integer> update(
            @RequestBody UserUpdateForm userUpdateForm) {
        return HttpResult.ok(
                this.userService.update(userUpdateForm));
    }

    /**
     * 删除指定用户
     *
     * @param id
     * @return
     */
    @DeleteMapping("/delete")
    public HttpResult<Integer> delete(String id) {
        return HttpResult.ok(
                this.userService.delete(id));
    }
}
