package com.glacier.modules.sys.entity.dto.role;

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
@Data
@EqualsAndHashCode(callSuper = true)
@ToString
public class RoleForm extends RoleVo {
    private static final long serialVersionUID = -1193819369875966276L;
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
