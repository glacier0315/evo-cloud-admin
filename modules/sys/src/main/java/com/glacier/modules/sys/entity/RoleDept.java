package com.glacier.modules.sys.entity;

import java.io.Serializable;

/**
 * 角色组织机构关联模型
 *
 * @author glacier
 * @version 1.0
 * @date 2019-10-09 14:49
 */
public class RoleDept implements Serializable {
    private static final long serialVersionUID = 6012509805874207086L;
    /**
     * 角色id
     */
    private String roleId;
    /**
     * 单位id
     */
    private String deptId;

    public RoleDept() {
    }

    public RoleDept(String roleId, String deptId) {
        this.roleId = roleId;
        this.deptId = deptId;
    }

    public static long getSerialVersionUID() {
        return serialVersionUID;
    }

    public String getRoleId() {
        return this.roleId;
    }

    public void setRoleId(String roleId) {
        this.roleId = roleId;
    }

    public String getDeptId() {
        return this.deptId;
    }

    public void setDeptId(String deptId) {
        this.deptId = deptId;
    }

    @Override
    public String toString() {
        return "RoleDept{" +
                "roleId='" + this.roleId + '\'' +
                ", deptId='" + this.deptId + '\'' +
                '}';
    }
}
