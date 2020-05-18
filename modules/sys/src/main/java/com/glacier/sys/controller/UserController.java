package com.glacier.sys.controller;

import com.glacier.common.core.entity.dto.IdDto;
import com.glacier.common.core.http.HttpResult;
import com.glacier.common.core.page.PageRequest;
import com.glacier.common.core.page.PageResponse;
import com.glacier.sys.entity.User;
import com.glacier.sys.service.UserService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import java.util.List;

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

    @GetMapping(value = "/me")
    public Object current(Authentication authentication) {
        Object principal = authentication.getPrincipal();
        log.info(">>>>>>>>>>>>>>>>>>>>>>>>");
        log.info("当前登录用户为: {}", principal);
        log.info(">>>>>>>>>>>>>>>>>>>>>>>>");
        return principal;
    }

    /**
     * 分页查询用户
     *
     * @param pageRequest
     * @return
     */
    @PostMapping("/findPage")
    public HttpResult<PageResponse<User>> findPage(@RequestBody PageRequest<User> pageRequest) {
        return HttpResult.ok(userService.findPage(pageRequest));
    }

    /**
     * 保存用户
     *
     * @param user
     * @return
     */
    @PostMapping("/save")
    public HttpResult<Integer> save(@RequestBody User user) {
        return HttpResult.ok(userService.saveUserRole(user));
    }

    /**
     * 删除指定用户
     *
     * @param idDtos
     * @return
     */
    @PostMapping("/delete")
    public HttpResult<Integer> delete(@RequestBody List<IdDto> idDtos) {
        return HttpResult.ok(userService.batchDelete(idDtos));
    }
}
