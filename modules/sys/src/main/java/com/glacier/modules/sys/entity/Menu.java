package com.glacier.modules.sys.entity;

import com.glacier.common.core.entity.AbstractTreeEntity;

/**
 * 菜单
 *
 * @author glacier
 * @version 1.0
 * date 2019-10-09 11:03
 */
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
     * 状态 1 正常  2 禁用
     */
    private Integer status;
    /**
     * 是否显示 1 显示  2 隐藏
     */
    private Integer visible;

    /**
     * 是否外链 1 是  2 否
     */
    private Integer isFrame;

    public static long getSerialVersionUID() {
        return serialVersionUID;
    }

    public String getPath() {
        return this.path;
    }

    public void setPath(String path) {
        this.path = path;
    }

    public String getComponent() {
        return this.component;
    }

    public void setComponent(String component) {
        this.component = component;
    }

    public String getIcon() {
        return this.icon;
    }

    public void setIcon(String icon) {
        this.icon = icon;
    }

    public String getPermission() {
        return this.permission;
    }

    public void setPermission(String permission) {
        this.permission = permission;
    }

    public Integer getType() {
        return this.type;
    }

    public void setType(Integer type) {
        this.type = type;
    }

    public Integer getStatus() {
        return this.status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public Integer getVisible() {
        return this.visible;
    }

    public void setVisible(Integer visible) {
        this.visible = visible;
    }

    public Integer getIsFrame() {
        return this.isFrame;
    }

    public void setIsFrame(Integer isFrame) {
        this.isFrame = isFrame;
    }

    @Override
    public String toString() {
        return "Menu{" +
                "path='" + this.path + '\'' +
                ", component='" + this.component + '\'' +
                ", icon='" + this.icon + '\'' +
                ", permission='" + this.permission + '\'' +
                ", type=" + this.type +
                ", status=" + this.status +
                ", visible=" + this.visible +
                ", isFrame=" + this.isFrame +
                ", name='" + this.name + '\'' +
                ", parentId='" + this.parentId + '\'' +
                ", orderNum=" + this.orderNum +
                ", grade=" + this.grade +
                ", children=" + this.children +
                ", createBy='" + this.createBy + '\'' +
                ", createDate=" + this.createDate +
                ", updateBy='" + this.updateBy + '\'' +
                ", updateDate=" + this.updateDate +
                ", delFlag='" + this.delFlag + '\'' +
                ", id='" + this.id + '\'' +
                '}';
    }
}
