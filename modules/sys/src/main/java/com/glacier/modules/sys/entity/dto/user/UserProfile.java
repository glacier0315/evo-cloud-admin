package com.glacier.modules.sys.entity.dto.user;

/**
 * 查询用户信息
 *
 * @author glacier
 * @version 1.0
 * date 2020-07-23 10:31
 */
public class UserProfile extends AbstractUserDto {

    private static final long serialVersionUID = -2286892393843097739L;
    /**
     * 用户id
     */
    private String id;
    /**
     * 头像地址
     */
    private String avatar;

    public static long getSerialVersionUID() {
        return serialVersionUID;
    }

    public String getId() {
        return this.id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getAvatar() {
        return this.avatar;
    }

    public void setAvatar(String avatar) {
        this.avatar = avatar;
    }

    @Override
    public String toString() {
        return "UserProfile{" +
                "id='" + this.id + '\'' +
                ", avatar='" + this.avatar + '\'' +
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
