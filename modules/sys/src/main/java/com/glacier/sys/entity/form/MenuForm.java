package com.glacier.sys.entity.form;

import lombok.Data;
import lombok.ToString;

import java.io.Serializable;

/**
 * @author glacier
 * @version 1.0
 * @date 2020-07-03 17:21
 */
@Data
@ToString
public class MenuForm implements Serializable {
    private static final long serialVersionUID = -5671623247967325528L;

    /**
     * 主键
     */
    private String id;
    /**
     * 资源名称
     */
    private String name;
    /**
     * 端点相对路径
     */
    private String path;
    /**
     * 组件
     */
    private String component;
    /**
     * 标题 支持国际化
     */
    private String title;
    /**
     * 图标
     */
    private String icon;
    /**
     * 权限标识
     */
    private String permission;
    /**
     * 1 目录  2 端点  3 权限标识
     */
    private Integer type;
    /**
     * 1 显示  2 隐藏
     */
    private Integer visible;

    /**
     * 是否外链 1 是  2 否
     */
    private Integer isFrame;

    /**
     * 父级id 顶级id默认为0
     */
    private String parentId;
    /**
     * 排序号
     */
    private Integer orderNum;
}
