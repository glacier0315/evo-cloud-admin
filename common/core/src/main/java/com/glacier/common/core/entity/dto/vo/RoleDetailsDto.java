package com.glacier.common.core.entity.dto.vo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import java.io.Serializable;
import java.util.List;

/**
 * @author glacier
 * @version 1.0
 * @date 2020-08-08 11:07
 */
@ApiModel(description = "角色详情模型")
public class RoleDetailsDto implements Serializable {
    private static final long serialVersionUID = 2730122991817147481L;
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
     * 数据权限 1 所有单位  2 所属一级单位及以下  3 所属二级单位及以下  4 所属单位部门及以下  5 自定义 6 自己
     */
    @ApiModelProperty(value = "数据权限 1 所有单位  2 所属一级单位及以下  3 所属二级单位及以下  4 所属单位部门及以下  5 自定义 6 自己")
    private String dataScope;
    /**
     * 组织机构集合
     */
    @ApiModelProperty(value = "组织机构集合")
    private List<String> deptIds;

    public static long getSerialVersionUID() {
        return serialVersionUID;
    }

    public String getId() {
        return this.id;
    }

    public void setId(String id) {
        this.id = id;
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

    public String getDescription() {
        return this.description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getDataScope() {
        return this.dataScope;
    }

    public void setDataScope(String dataScope) {
        this.dataScope = dataScope;
    }

    public List<String> getDeptIds() {
        return this.deptIds;
    }

    public void setDeptIds(List<String> deptIds) {
        this.deptIds = deptIds;
    }

    @Override
    public String toString() {
        return "RoleDetailsDto{" +
                "id='" + this.id + '\'' +
                ", name='" + this.name + '\'' +
                ", code='" + this.code + '\'' +
                ", status='" + this.status + '\'' +
                ", description='" + this.description + '\'' +
                ", dataScope='" + this.dataScope + '\'' +
                ", deptIds=" + this.deptIds +
                '}';
    }
}
