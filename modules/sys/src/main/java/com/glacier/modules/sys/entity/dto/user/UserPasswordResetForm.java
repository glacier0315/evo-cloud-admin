package com.glacier.modules.sys.entity.dto.user;

import com.glacier.common.core.entity.dto.IdDto;
/**
 * 用户密码重置模型
 *
 * @author glacier
 * @version 1.0
 * date 2020-08-27 16:50
 */
public class UserPasswordResetForm extends IdDto {
    private static final long serialVersionUID = -3806037954613930772L;
    /**
     * 新密码
     */
    private String newPassword;

    public static long getSerialVersionUID() {
        return serialVersionUID;
    }

    public String getNewPassword() {
        return this.newPassword;
    }

    public void setNewPassword(String newPassword) {
        this.newPassword = newPassword;
    }

    @Override
    public String toString() {
        return "UserPasswordResetForm{" +
                "newPassword='" + this.newPassword + '\'' +
                ", id='" + this.id + '\'' +
                '}';
    }
}
