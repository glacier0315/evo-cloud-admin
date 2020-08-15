package com.glacier.modules.sys.entity;

import lombok.Data;
import lombok.ToString;

import java.io.Serializable;

/**
 * 角色组织机构关联模型
 *
 * @author glacier
 * @version 1.0
 * @date 2019-10-09 14:49
 */
@Data
@ToString
public class RoleDept implements Serializable {
    private static final long serialVersionUID = 6012509805874207086L;
    /**
     * 主键
     */
    private String id;
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

    public RoleDept(String id, String roleId, String deptId) {
        this.id = id;
        this.roleId = roleId;
        this.deptId = deptId;
    }
}
