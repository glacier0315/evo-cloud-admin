package com.glacier.sys.controller;

import com.glacier.common.core.entity.page.PageRequest;
import com.glacier.common.core.entity.page.PageResponse;
import com.glacier.common.core.entity.vo.HttpResult;
import com.glacier.sys.common.Constant;
import com.glacier.sys.entity.form.user.*;
import com.glacier.sys.entity.vo.user.UserDetailsVo;
import com.glacier.sys.entity.vo.user.UserListVo;
import com.glacier.sys.entity.vo.user.UserProfileVo;
import com.glacier.sys.service.UserService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.oauth2.jwt.Jwt;
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

    /**
     * 获取当前用户信息
     *
     * @param authentication
     * @return
     */
    @GetMapping(value = "/profile")
    public HttpResult<UserProfileVo> getProfile(Authentication authentication) {
        log.info(">>>>>>>>>>>>>>>>>>>>>>>>");
        log.info("当前登录用户为: {}", authentication);
        log.info(">>>>>>>>>>>>>>>>>>>>>>>>");
        String username = ((Jwt) authentication.getPrincipal())
                .getClaim("user_name");
        return HttpResult.ok(
                this.userService.findUserProfileByUsername(username));
    }

    /**
     * 更新当前用户信息
     *
     * @param userProfileForm
     * @return
     */
    @PutMapping(value = "/profile")
    public HttpResult<Integer> updateProfile(
            @RequestBody UserProfileForm userProfileForm) {
        return HttpResult.ok(
                this.userService.update(userProfileForm));
    }

    /**
     * 更新当前用户头像信息
     *
     * @param userAvatarForm
     * @return
     */
    @PostMapping(value = "/avatar")
    public HttpResult<Integer> avatar(
            @RequestBody UserAvatarForm userAvatarForm) {
        return HttpResult.ok(
                this.userService.update(userAvatarForm));
    }

    /**
     * 重置用户密码
     *
     * @param userPasswordForm
     * @return
     */
    @PostMapping("/resetPwd")
    public HttpResult<Integer> resetPwd(
            @RequestBody UserPasswordForm userPasswordForm) {
        // 设置重置密码
        userPasswordForm.setNewPassword(Constant.DEFAULT_PASSWD);
        return this.userService.updatePassword(userPasswordForm);
    }

    /**
     * 修改用户密码
     *
     * @param userPasswordForm
     * @return
     */
    @PutMapping("/updatePwd")
    public HttpResult<Integer> updatePwd(
            @RequestBody UserPasswordForm userPasswordForm) {
        return this.userService.updatePassword(userPasswordForm);
    }
}
