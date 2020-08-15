package com.glacier.modules.sys.entity;

import lombok.Data;
import lombok.ToString;

import java.io.Serializable;

/**
 * 用户角色关系
 *
 * @author glacier
 * @version 1.0
 * @date 2019-08-11 21:18
 */
@Data
@ToString
public class UserRole implements Serializable {

    private static final long serialVersionUID = -7193974752669679122L;
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

    public UserRole() {
    }

    public UserRole(String id, String userId, String roleId) {
        this.id = id;
        this.userId = userId;
        this.roleId = roleId;
    }
}
