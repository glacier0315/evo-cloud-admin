package com.glacier.modules.sys.entity;

import com.glacier.common.core.entity.AbstractTreeEntity;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

/**
 * 菜单
 *
 * @author glacier
 * @version 1.0
 * @date 2019-10-09 11:03
 */
@Data
@ToString(callSuper = true)
@EqualsAndHashCode(callSuper = false)
public class Menu extends AbstractTreeEntity<Menu> {

    private static final long serialVersionUID = 1207728347319595982L;
    /**
     * 端点相对路径
     */
    private String path;
    /**
     * 组件
     */
    private String component;
    /**
     * 图标
     */
    private String icon;
    /**
     * 权限标识
     */
    private String permission;
    /**
     * 类型 1 目录  2 端点  3 权限标识
     */
    private Integer type;
    /**
     * 是否显示 1 显示  2 隐藏
     */
    private Integer visible;

    /**
     * 是否外链 1 是  2 否
     */
    private Integer isFrame;
}
