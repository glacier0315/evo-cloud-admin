package com.glacier.modules.sys.entity;

import com.glacier.common.core.entity.DataEntity;

import java.time.LocalDate;

/**
 * 用户
 *
 * @author glacier
 * @version 1.0
 * date 2019-08-04 13:45
 */
public class User extends DataEntity {
    private static final long serialVersionUID = -3083387263445135811L;
    /**
     * 用户名
     */
    private String username;
    /**
     * 密码
     */
    private String password;
    /**
     * 昵称
     */
    private String nickname;
    /**
     * 身份证号
     */
    private String idCard;
    /**
     * 出生日期
     */
    private LocalDate birthday;
    /**
     * 性别 1=男 2=女 其他=保密
     */
    private Integer sex;
    /**
     * 状态  1 正常  2 锁定
     */
    private String status;
    /**
     * 邮箱
     */
    private String email;
    /**
     * 手机号
     */
    private String mobile;
    /**
     * 头像地址
     */
    private String avatar;
    /**
     * 岗位id
     */
    private String postId;
    /**
     * 单位id
     */
    private String deptId;
    /**
     * 单位名称
     */
    private String deptName;

    public static long getSerialVersionUID() {
        return serialVersionUID;
    }

    public String getUsername() {
        return this.username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return this.password;
    }

    public void setPassword(String password) {
        this.password = password;
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

    public String getAvatar() {
        return this.avatar;
    }

    public void setAvatar(String avatar) {
        this.avatar = avatar;
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

    @Override
    public String toString() {
        return "User{" +
                "username='" + this.username + '\'' +
                ", password='" + this.password + '\'' +
                ", nickname='" + this.nickname + '\'' +
                ", idCard='" + this.idCard + '\'' +
                ", birthday=" + this.birthday +
                ", sex=" + this.sex +
                ", status='" + this.status + '\'' +
                ", email='" + this.email + '\'' +
                ", mobile='" + this.mobile + '\'' +
                ", avatar='" + this.avatar + '\'' +
                ", postId='" + this.postId + '\'' +
                ", deptId='" + this.deptId + '\'' +
                ", deptName='" + this.deptName + '\'' +
                ", id='" + this.id + '\'' +
                '}';
    }
}
