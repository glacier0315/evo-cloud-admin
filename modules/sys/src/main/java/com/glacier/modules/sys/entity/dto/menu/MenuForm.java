package com.glacier.modules.sys.entity.dto.menu;

import com.glacier.common.core.entity.dto.TreeDto;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

/**
 * @author glacier
 * @version 1.0
 * @date 2020-07-03 17:21
 */
@ApiModel(description = "菜单模型")
@Data
@EqualsAndHashCode(callSuper = true)
@ToString
public class MenuForm extends TreeDto {
    private static final long serialVersionUID = -5671623247967325528L;

    /**
     * 端点相对路径
     */
    @ApiModelProperty(value = "端点相对路径")
    private String path;
    /**
     * 组件
     */
    @ApiModelProperty(value = "组件")
    private String component;
    /**
     * 图标
     */
    @ApiModelProperty(value = "图标")
    private String icon;
    /**
     * 权限标识
     */
    @ApiModelProperty(value = "权限标识")
    private String permission;
    /**
     * 类型 1 目录  2 端点  3 权限标识
     */
    @ApiModelProperty(value = "类型 1 目录  2 端点  3 权限标识")
    private Integer type;
    /**
     * 是否显示 1 显示  2 隐藏
     */
    @ApiModelProperty(value = "是否显示 1 显示  2 隐藏")
    private Integer visible;
    /**
     * 是否外链 1 是  2 否
     */
    @ApiModelProperty(value = "是否外链 1 是  2 否")
    private Integer isFrame;
}
