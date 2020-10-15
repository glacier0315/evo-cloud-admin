package com.glacier.authorization.resource.entity;

import java.io.Serializable;

/**
 * 用户角色关系
 *
 * @author glacier
 * @version 1.0
 * date 2019-08-11 21:18
 */
public class UserRole implements Serializable {
    private static final long serialVersionUID = 8523322889225203563L;
    /**
     * 主键
     */
    private String id;
    /**
     * 用户id
     */
    private String userId;
    /**
     * 角色id
     */
    private String roleId;

    public static long getSerialVersionUID() {
        return serialVersionUID;
    }

    public String getId() {
        return this.id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getUserId() {
        return this.userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getRoleId() {
        return this.roleId;
    }

    public void setRoleId(String roleId) {
        this.roleId = roleId;
    }

    @Override
    public String toString() {
        return "UserRole{" +
                "id='" + this.id + '\'' +
                ", userId='" + this.userId + '\'' +
                ", roleId='" + this.roleId + '\'' +
                '}';
    }
}
