package com.glacier.modules.sys.entity.vo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.ToString;

import java.io.Serializable;

/**
 * @author glacier
 * @version 1.0
 * @date 2020-08-12 11:22
 */
@ApiModel(description = "角色显示模型")
@Data
@ToString
public class RoleVo implements Serializable {
    /**
     * 主键
     */
    @ApiModelProperty(value = "主键")
    private String id;
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
     * 描述
     */
    @ApiModelProperty(value = "描述")
    private String description;
    /**
     * 数据权限 1 所有权限  2 一级单位及以下  3 二级单位及以下  4 所属单位部门及以下  5 自己
     */
    @ApiModelProperty(value = "数据权限")
    private String dataScope;
}
