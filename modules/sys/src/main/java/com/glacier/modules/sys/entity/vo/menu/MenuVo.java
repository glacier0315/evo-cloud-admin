package com.glacier.modules.sys.entity.vo.menu;

import com.glacier.common.core.entity.vo.AbstractTreeVo;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

/**
 * 菜单显示模型
 * @author glacier
 * @version 1.0
 * @date 2020-08-11 21:06
 */
@ApiModel(description = "菜单显示模型")
@Data
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
public class MenuVo extends AbstractTreeVo<MenuVo> {
    private static final long serialVersionUID = 5992007206682436283L;
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
