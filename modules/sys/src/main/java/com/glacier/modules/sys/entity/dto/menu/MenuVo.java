package com.glacier.modules.sys.entity.dto.menu;

import com.glacier.common.core.entity.TreeData;

import java.util.List;

/**
 * 菜单显示模型
 *
 * @author glacier
 * @version 1.0
 * date 2020-08-11 21:06
 */
public class MenuVo extends MenuForm implements TreeData<MenuVo> {
    private static final long serialVersionUID = 5992007206682436283L;
    /**
     * 子类
     */
    protected List<MenuVo> children;

    public static long getSerialVersionUID() {
        return serialVersionUID;
    }

    @Override
    public List<MenuVo> getChildren() {
        return this.children;
    }

    @Override
    public void setChildren(List<MenuVo> children) {
        this.children = children;
    }

    @Override
    public String toString() {
        return "MenuVo{" +
                "children=" + this.children +
                ", id='" + this.id + '\'' +
                ", name='" + this.name + '\'' +
                ", parentId='" + this.parentId + '\'' +
                ", parentName='" + this.parentName + '\'' +
                ", orderNum=" + this.orderNum +
                ", grade=" + this.grade +
                '}';
    }
}
