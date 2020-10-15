package com.glacier.modules.sys.entity.dto.user;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import java.io.Serializable;
import java.util.List;

/**
 * 用户角色关系模型
 *
 * @author glacier
 * @version 1.0
 * date 2020-09-01 11:51
 */
@ApiModel(description = "用户角色关系模型")
public class UserRoleDto implements Serializable {

    private static final long serialVersionUID = 4166908160897610687L;
    /**
     * 用户ID
     */
    @ApiModelProperty(value = "用户Id")
    private String userId;
    /**
     * 角色ID集合
     */
    @ApiModelProperty(value = "角色ID集合")
    private List<String> roleIds;

    public UserRoleDto() {
    }

    public UserRoleDto(String userId, List<String> roleIds) {
        this.userId = userId;
        this.roleIds = roleIds;
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

    public List<String> getRoleIds() {
        return this.roleIds;
    }

    public void setRoleIds(List<String> roleIds) {
        this.roleIds = roleIds;
    }

    @Override
    public String toString() {
        return "UserRoleDto{" +
                "userId='" + this.userId + '\'' +
                ", roleIds=" + this.roleIds +
                '}';
    }
}
