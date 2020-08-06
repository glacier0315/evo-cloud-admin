package com.glacier.modules.sys.controller;

import com.glacier.common.core.entity.page.PageRequest;
import com.glacier.common.core.entity.page.PageResponse;
import com.glacier.common.core.entity.vo.Result;
import com.glacier.modules.sys.common.Constant;
import com.glacier.modules.sys.entity.form.user.*;
import com.glacier.modules.sys.entity.vo.user.UserDetailsVo;
import com.glacier.modules.sys.entity.vo.user.UserListVo;
import com.glacier.modules.sys.entity.vo.user.UserProfileVo;
import com.glacier.modules.sys.service.UserService;
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
    public Result<UserDetailsVo> findByUsername(
            @PathVariable("username") String username) {
        return Result.ok(
                this.userService.loadUserByUsername(username));
    }

    /**
     * 分页查询用户
     *
     * @param pageRequest
     * @return
     */
    @PostMapping("/pageList")
    public Result<PageResponse<UserListVo>> findPage(
            @RequestBody PageRequest<UserQueryForm> pageRequest) {
        return Result.ok(
                this.userService.findPage(pageRequest));
    }

    /**
     * 保存用户
     *
     * @param userAddForm
     * @return
     */
    @PostMapping("/add")
    public Result<Integer> add(
            @RequestBody UserAddForm userAddForm) {
        return Result.ok(
                this.userService.add(userAddForm));
    }

    /**
     * 更新用户
     *
     * @param userUpdateForm
     * @return
     */
    @PutMapping("/update")
    public Result<Integer> update(
            @RequestBody UserUpdateForm userUpdateForm) {
        return Result.ok(
                this.userService.update(userUpdateForm));
    }

    /**
     * 删除指定用户
     *
     * @param id
     * @return
     */
    @DeleteMapping("/delete")
    public Result<Integer> delete(String id) {
        return Result.ok(
                this.userService.delete(id));
    }

    /**
     * 获取当前用户信息
     *
     * @param authentication
     * @return
     */
    @GetMapping(value = "/profile")
    public Result<UserProfileVo> getProfile(Authentication authentication) {
        log.info(">>>>>>>>>>>>>>>>>>>>>>>>");
        log.info("当前登录用户为: {}" , authentication);
        log.info(">>>>>>>>>>>>>>>>>>>>>>>>");
        String username = ((Jwt) authentication.getPrincipal())
                .getClaim("user_name");
        return Result.ok(
                this.userService.findUserProfileByUsername(username));
    }

    /**
     * 更新当前用户信息
     *
     * @param userProfileForm
     * @return
     */
    @PutMapping(value = "/profile")
    public Result<Integer> updateProfile(
            @RequestBody UserProfileForm userProfileForm) {
        return Result.ok(
                this.userService.update(userProfileForm));
    }

    /**
     * 更新当前用户头像信息
     *
     * @param userAvatarForm
     * @return
     */
    @PostMapping(value = "/avatar")
    public Result<Integer> avatar(
            @RequestBody UserAvatarForm userAvatarForm) {
        return Result.ok(
                this.userService.update(userAvatarForm));
    }

    /**
     * 重置用户密码
     *
     * @param userPasswordForm
     * @return
     */
    @PostMapping("/resetPwd")
    public Result<Integer> resetPwd(
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
    public Result<Integer> updatePwd(
            @RequestBody UserPasswordForm userPasswordForm) {
        return this.userService.updatePassword(userPasswordForm);
    }
}
