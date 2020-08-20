package com.glacier.modules.sys.controller;

import com.glacier.common.core.entity.Result;
import com.glacier.common.core.entity.dto.IdDto;
import com.glacier.common.core.entity.page.PageRequest;
import com.glacier.common.core.entity.page.PageResponse;
import com.glacier.modules.sys.common.Constant;
import com.glacier.modules.sys.entity.dto.user.*;
import com.glacier.modules.sys.service.UserService;
import com.glacier.modules.sys.utils.SecurityUtils;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
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
@Api(tags = "用户管理")
@Slf4j
@RestController
@RequestMapping(value = "/user")
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class UserController {
    private final UserService userService;

    /**
     * 分页查询用户
     *
     * @param pageRequest
     * @return
     */
    @ApiOperation("分页查询用户")
    @PostMapping("/pageList")
    public Result<PageResponse<UserDto>> findPage(
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
    @ApiOperation("添加用户")
    @PostMapping("/add")
    public Result<Integer> add(
            @RequestBody UserAddForm userAddForm) {
        return Result.ok(
                this.userService.save(userAddForm));
    }

    /**
     * 更新用户
     *
     * @param userDto 用户更新实体
     * @return 更新结果
     */
    @ApiOperation("更新用户")
    @PostMapping("/update")
    public Result<Integer> update(
            @RequestBody UserDto userDto) {
        return Result.ok(
                this.userService.save(userDto));
    }

    /**
     * 删除指定用户
     *
     * @param idDto
     * @return
     */
    @ApiOperation("删除指定用户")
    @PostMapping("/delete")
    public Result<Integer> delete(@RequestBody IdDto idDto) {
        return Result.ok(
                this.userService.delete(idDto.getId()));
    }

    /**
     * 获取当前用户信息
     *
     * @return
     */
    @ApiOperation("获取当前用户信息")
    @GetMapping(value = "/profile")
    public Result<UserProfile> getProfile() {
        return Result.ok(
                this.userService.findUserProfileByUsername(SecurityUtils.getUsername()));
    }

    /**
     * 更新当前用户信息
     *
     * @param userProfileForm
     * @return
     */
    @ApiOperation("更新当前用户信息")
    @PostMapping(value = "/profile")
    public Result<Integer> updateProfile(
            @RequestBody UserProfileForm userProfileForm) {
        return this.userService.updateProfile(userProfileForm);
    }

    /**
     * 更新当前用户头像信息
     *
     * @param userAvatarForm
     * @return
     */
    @ApiOperation("更新当前用户头像信息")
    @PostMapping(value = "/avatar")
    public Result<Integer> avatar(
            @RequestBody UserAvatarForm userAvatarForm) {
        return this.userService.updateAvatar(userAvatarForm);
    }

    /**
     * 重置用户密码
     *
     * @param userPasswordForm
     * @return
     */
    @ApiOperation("重置用户密码")
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
    @ApiOperation("修改用户密码")
    @PostMapping("/updatePwd")
    public Result<Integer> updatePwd(
            @RequestBody UserPasswordForm userPasswordForm) {
        return this.userService.updatePassword(userPasswordForm);
    }
}
