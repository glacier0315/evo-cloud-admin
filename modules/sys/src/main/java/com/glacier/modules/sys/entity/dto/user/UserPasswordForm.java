package com.glacier.modules.sys.entity.dto.user;

import com.glacier.common.core.entity.dto.IdDto;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

/**
 * 用户密码修改封装类
 *
 * @author glacier
 * @version 1.0
 * @date 2020-07-23 10:12
 */
@ApiModel(description = "用户密码模型")
public class UserPasswordForm extends IdDto {
    private static final long serialVersionUID = -3806037954613930772L;
    /**
     * 旧密码
     */
    @ApiModelProperty(value = "旧密码")
    private String oldPassword;
    /**
     * 新密码
     */
    @ApiModelProperty(value = "新密码")
    private String newPassword;

    public static long getSerialVersionUID() {
        return serialVersionUID;
    }

    public String getOldPassword() {
        return this.oldPassword;
    }

    public void setOldPassword(String oldPassword) {
        this.oldPassword = oldPassword;
    }

    public String getNewPassword() {
        return this.newPassword;
    }

    public void setNewPassword(String newPassword) {
        this.newPassword = newPassword;
    }

    @Override
    public String toString() {
        return "UserPasswordForm{" +
                "oldPassword='" + this.oldPassword + '\'' +
                ", newPassword='" + this.newPassword + '\'' +
                ", id='" + this.id + '\'' +
                '}';
    }
}
