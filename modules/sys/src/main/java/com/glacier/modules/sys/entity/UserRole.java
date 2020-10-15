package com.glacier.modules.sys.entity;

import java.io.Serializable;

/**
 * 用户角色关系
 *
 * @author glacier
 * @version 1.0
 * @date 2019-08-11 21:18
 */
public class UserRole implements Serializable {

    private static final long serialVersionUID = -7193974752669679122L;
    /**
     * 用户id
     */
    private String userId;
    /**
     * 角色id
     */
    private String roleId;

    public UserRole() {
    }

    public UserRole(String userId, String roleId) {
        this.userId = userId;
        this.roleId = roleId;
    }

    public static long getSerialVersionUID() {
        return serialVersionUID;
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
                "userId='" + this.userId + '\'' +
                ", roleId='" + this.roleId + '\'' +
                '}';
    }
}
