package com.glacier.modules.sys.entity.dto.user;

import io.swagger.annotations.ApiModelProperty;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.List;

/**
 * @author glacier
 * @version 1.0
 * date 2020-08-15 10:02
 */
public abstract class AbstractUserDto implements Serializable {
    private static final long serialVersionUID = -8546936668021702992L;
    /**
     * 用户名
     */
    @ApiModelProperty(value = "用户名")
    protected String username;
    /**
     * 昵称
     */
    @ApiModelProperty(value = "昵称")
    protected String nickname;
    /**
     * 身份证号
     */
    @ApiModelProperty(value = "身份证号")
    protected String idCard;
    /**
     * 出生日期
     */
    @ApiModelProperty(value = "出生日期，格式yyyy-MM-dd")
    protected LocalDate birthday;
    /**
     * 性别 1=男 2=女 3 其他=保密
     */
    @ApiModelProperty(value = "性别 1=男 2=女 3 其他=保密")
    protected Integer sex;
    /**
     * 邮箱
     */
    @ApiModelProperty(value = "邮箱")
    protected String email;
    /**
     * 手机号
     */
    @ApiModelProperty(value = "手机号")
    protected String mobile;
    /**
     * 岗位id
     */
    @ApiModelProperty(value = "岗位id")
    protected String postId;
    /**
     * 单位id
     */
    @ApiModelProperty(value = "单位id")
    protected String deptId;
    /**
     * 单位名称
     */
    @ApiModelProperty(value = "单位名称")
    protected String deptName;
    /**
     * 简介
     */
    @ApiModelProperty(value = "简介")
    protected String introduction;
    /**
     * 角色
     */
    @ApiModelProperty(value = "角色")
    protected List<String> roleIds;

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

    public String getDeptName() {
        return this.deptName;
    }

    public void setDeptName(String deptName) {
        this.deptName = deptName;
    }

    public String getIntroduction() {
        return this.introduction;
    }

    public void setIntroduction(String introduction) {
        this.introduction = introduction;
    }

    public List<String> getRoleIds() {
        return this.roleIds;
    }

    public void setRoleIds(List<String> roleIds) {
        this.roleIds = roleIds;
    }

    @Override
    public String toString() {
        return "AbstractUserDto{" +
                "username='" + this.username + '\'' +
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
