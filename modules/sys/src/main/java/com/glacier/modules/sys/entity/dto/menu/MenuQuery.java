package com.glacier.modules.sys.entity.dto.menu;


import java.io.Serializable;
import java.util.List;

/**
 * @author glacier
 * @version 1.0
 * date 2020-09-01 22:18
 */
public class MenuQuery implements Serializable {
    private static final long serialVersionUID = 3282253575775981590L;

    /**
     * 状态 1 正常  2 禁用
     */
    private Integer status;
    /**
     * 用户id
     */
    private String userId;
    /**
     * 角色id
     */
    private String roleId;
    /**
     * 资源类型
     */
    private List<Integer> typeList;

    public static long getSerialVersionUID() {
        return serialVersionUID;
    }

    public Integer getStatus() {
        return this.status;
    }

    public void setStatus(Integer status) {
        this.status = status;
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

    public List<Integer> getTypeList() {
        return this.typeList;
    }

    public void setTypeList(List<Integer> typeList) {
        this.typeList = typeList;
    }

    @Override
    public String toString() {
        return "MenuQuery{" +
                "status=" + this.status +
                ", userId='" + this.userId + '\'' +
                ", roleId='" + this.roleId + '\'' +
                ", typeList=" + this.typeList +
                '}';
    }
}
