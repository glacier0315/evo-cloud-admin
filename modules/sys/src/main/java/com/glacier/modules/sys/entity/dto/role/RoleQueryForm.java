package com.glacier.modules.sys.entity.dto.role;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.ToString;

import java.io.Serializable;

/**
 * 角色查询条件模型
 * @author glacier
 * @version 1.0
 * @date 2020-07-03 21:51
 */
@ApiModel(description = "角色查询条件模型")
@Data
@ToString
public class RoleQueryForm implements Serializable {
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
}
