package com.glacier.modules.sys.entity.dto.user;

import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import java.io.Serializable;
import java.time.LocalDate;

/**
 * 用户查询封装类
 *
 * @author glacier
 * @version 1.0
 * @date 2020-05-24 09:37
 */
@ApiModel(description = "用户查询条件模型")
public class UserQuery implements Serializable {
    private static final long serialVersionUID = 7302567686429075050L;
    /**
     * 用户名
     */
    @ApiModelProperty(value = "用户名")
    private String username;
    /**
     * 昵称
     */
    @ApiModelProperty(value = "昵称")
    private String nickname;
    /**
     * 身份证号
     */
    @ApiModelProperty(value = "身份证号")
    private String idCard;
    /**
     * 出生日期
     */
    @ApiModelProperty(value = "出生日期")
    @JsonFormat(pattern = "yyyy-MM-dd", timezone = "GMT+8")
    private LocalDate birthday;
    /**
     * 性别 1=男 2=女 3 其他=保密
     */
    @ApiModelProperty(value = "性别 1=男 2=女 3 其他=保密")
    private Integer sex;
    /**
     * 状态  1 正常  0 锁定
     */
    @ApiModelProperty(value = "状态  1 正常  0 锁定")
    private String status;
    /**
     * 邮箱
     */
    @ApiModelProperty(value = "邮箱")
    private String email;
    /**
     * 手机号
     */
    @ApiModelProperty(value = "手机号")
    private String mobile;
    /**
     * 岗位id
     */
    @ApiModelProperty(value = "岗位id")
    private String postId;
    /**
     * 单位id
     */
    @ApiModelProperty(value = "单位id")
    private String deptId;
    /**
     * 角色id， 用于查询具有该角色的用户
     */
    @ApiModelProperty(value = "角色id， 用于查询具有该角色的用户")
    private String roleId;
    /**
     * 角色id， 用于查询不具有该角色的用户
     */
    @ApiModelProperty(value = "角色id， 用于查询不具有该角色的用户")
    private String notEqRoleId;

    public static long getSerialVersionUID() {
        return serialVersionUID;
    }

    public String getUsername() {
        return this.username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getNickname() {
        return this.nickname;
    }

    public void setNickname(String nickname) {
        this.nickname = nickname;
    }

    public String getIdCard() {
        return this.idCard;
    }

    public void setIdCard(String idCard) {
        this.idCard = idCard;
    }

    public LocalDate getBirthday() {
        return this.birthday;
    }

    public void setBirthday(LocalDate birthday) {
        this.birthday = birthday;
    }

    public Integer getSex() {
        return this.sex;
    }

    public void setSex(Integer sex) {
        this.sex = sex;
    }

    public String getStatus() {
        return this.status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getEmail() {
        return this.email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getMobile() {
        return this.mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }

    public String getPostId() {
        return this.postId;
    }

    public void setPostId(String postId) {
        this.postId = postId;
    }

    public String getDeptId() {
        return this.deptId;
    }

    public void setDeptId(String deptId) {
        this.deptId = deptId;
    }

    public String getRoleId() {
        return this.roleId;
    }

    public void setRoleId(String roleId) {
        this.roleId = roleId;
    }

    public String getNotEqRoleId() {
        return this.notEqRoleId;
    }

    public void setNotEqRoleId(String notEqRoleId) {
        this.notEqRoleId = notEqRoleId;
    }

    @Override
    public String toString() {
        return "UserQuery{" +
                "username='" + this.username + '\'' +
                ", nickname='" + this.nickname + '\'' +
                ", idCard='" + this.idCard + '\'' +
                ", birthday=" + this.birthday +
                ", sex=" + this.sex +
                ", status='" + this.status + '\'' +
                ", email='" + this.email + '\'' +
                ", mobile='" + this.mobile + '\'' +
                ", postId='" + this.postId + '\'' +
                ", deptId='" + this.deptId + '\'' +
                ", roleId='" + this.roleId + '\'' +
                ", notEqRoleId='" + this.notEqRoleId + '\'' +
                '}';
    }
}
