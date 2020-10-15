package com.glacier.modules.sys.entity.dto.role;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import java.io.Serializable;

/**
 * 角色查询条件模型
 * @author glacier
 * @version 1.0
 * date 2020-07-03 21:51
 */
@ApiModel(description = "角色查询条件模型")
public class RoleQuery implements Serializable {
    private static final long serialVersionUID = 4596028730042166514L;
    /**
     * 角色名称
     */
    @ApiModelProperty(value = "角色名称")
    private String name;
    /**
     * 角色编码
     */
    @ApiModelProperty(value = "角色编码")
    private String code;
    /**
     * 状态 1 正常  2 禁用
     */
    @ApiModelProperty(value = "状态 1 正常  2 禁用")
    private String status;
    /**
     * 用户id， 用于查询用户具有角色
     */
    @ApiModelProperty(value = "用户id， 用于查询用户具有角色")
    private String userId;
    /**
     * 用户id， 用于查询用户不具有角色
     */
    @ApiModelProperty(value = "用户id， 用于查询用户不具有角色")
    private String notEqUserId;

    public static long getSerialVersionUID() {
        return serialVersionUID;
    }

    public String getName() {
        return this.name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCode() {
        return this.code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getStatus() {
        return this.status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getUserId() {
        return this.userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getNotEqUserId() {
        return this.notEqUserId;
    }

    public void setNotEqUserId(String notEqUserId) {
        this.notEqUserId = notEqUserId;
    }

    @Override
    public String toString() {
        return "RoleQuery{" +
                "name='" + this.name + '\'' +
                ", code='" + this.code + '\'' +
                ", status='" + this.status + '\'' +
                ", userId='" + this.userId + '\'' +
                ", notEqUserId='" + this.notEqUserId + '\'' +
                '}';
    }
}
