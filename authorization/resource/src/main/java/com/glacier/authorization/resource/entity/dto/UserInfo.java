package com.glacier.authorization.resource.entity.dto;

import java.io.Serializable;
import java.util.List;

/**
 * 查询用户信息
 *
 * @author glacier
 * @version 1.0
 * date 2020-05-22 16:56
 */
public class UserInfo implements Serializable {
    private static final long serialVersionUID = 2496540352121367958L;

    /**
     * 用户id
     */
    private String id;
    /**
     * 用户名
     */
    private String name;
    /**
     * 头像地址
     */
    private String avatar;
    /**
     *
     */
    private String introduction;
    /**
     * 权限
     */
    private List<String> roles;

    public static long getSerialVersionUID() {
        return serialVersionUID;
    }

    public String getId() {
        return this.id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return this.name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAvatar() {
        return this.avatar;
    }

    public void setAvatar(String avatar) {
        this.avatar = avatar;
    }

    public String getIntroduction() {
        return this.introduction;
    }

    public void setIntroduction(String introduction) {
        this.introduction = introduction;
    }

    public List<String> getRoles() {
        return this.roles;
    }

    public void setRoles(List<String> roles) {
        this.roles = roles;
    }

    @Override
    public String toString() {
        return "UserInfo{" +
                "id='" + this.id + '\'' +
                ", name='" + this.name + '\'' +
                ", avatar='" + this.avatar + '\'' +
                ", introduction='" + this.introduction + '\'' +
                ", roles=" + this.roles +
                '}';
    }
}
