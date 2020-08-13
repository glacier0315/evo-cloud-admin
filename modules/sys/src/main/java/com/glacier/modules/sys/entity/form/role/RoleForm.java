package com.glacier.modules.sys.entity.form.role;

import com.glacier.common.core.entity.form.IdForm;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

import java.util.List;

/**
 * 角色模型
 *
 * @author glacier
 * @version 1.0
 * @date 2020-07-03 21:52
 */
@ApiModel(description = "角色模型")
@EqualsAndHashCode(callSuper = true)
@Data
@ToString
public class RoleForm extends IdForm {
    private static final long serialVersionUID = -1193819369875966276L;
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
     * 数据权限 1 所有单位  2 所属一级单位及以下  3 所属二级单位及以下  4 所属单位部门及以下  5 自定义 6 自己
     */
    @ApiModelProperty(value = "数据权限 1 所有单位  2 所属一级单位及以下  3 所属二级单位及以下  4 所属单位部门及以下  5 自定义 6 自己")
    private String dataScope;
    /**
     * 描述
     */
    @ApiModelProperty(value = "描述")
    private String description;
    /**
     * 菜单ID 集合
     */
    @ApiModelProperty(value = "菜单ID 集合")
    private List<String> menus;
    /**
     * 单位ID 集合
     */
    @ApiModelProperty(value = "菜单ID 集合")
    private List<String> depts;
}
