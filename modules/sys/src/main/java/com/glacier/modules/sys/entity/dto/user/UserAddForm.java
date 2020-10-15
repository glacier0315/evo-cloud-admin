package com.glacier.modules.sys.entity.dto.user;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

/**
 * 用户新增
 *
 * @author glacier
 * @version 1.0
 * @date 2020-05-24 08:56
 */
@ApiModel(description = "用户新增模型")
public class UserAddForm extends AbstractUserDto {
    private static final long serialVersionUID = 5710986214973433730L;

    /**
     * 密码
     */
    @ApiModelProperty(value = "密码")
    private String password;
    /**
     * 状态  1 正常  0 锁定
     */
    @ApiModelProperty(value = "状态  1 正常  0 锁定")
    private String status;

    public static long getSerialVersionUID() {
        return serialVersionUID;
    }

    public String getPassword() {
        return this.password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getStatus() {
        return this.status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    @Override
    public String toString() {
        return "UserAddForm{" +
                "password='" + this.password + '\'' +
                ", status='" + this.status + '\'' +
                ", username='" + this.username + '\'' +
                ", nickname='" + this.nickname + '\'' +
                ", idCard='" + this.idCard + '\'' +
                ", birthday=" + this.birthday +
                ", sex=" + this.sex +
                ", email='" + this.email + '\'' +
                ", mobile='" + this.mobile + '\'' +
                ", postId='" + this.postId + '\'' +
                ", deptId='" + this.deptId + '\'' +
                ", deptName='" + this.deptName + '\'' +
                ", introduction='" + this.introduction + '\'' +
                ", roleIds=" + this.roleIds +
                '}';
    }
}
