package com.glacier.modules.sys.entity.dto.dept;

import com.glacier.common.core.entity.TreeData;

import java.util.List;

/**
 * 组织机构显示模型
 *
 * @author glacier
 * @version 1.0
 * date 2020-08-11 21:19
 */
public class DeptVo extends DeptForm implements TreeData<DeptVo> {
    private static final long serialVersionUID = 1327312988782909939L;
    /**
     * 子类
     */
    protected List<DeptVo> children;

    public static long getSerialVersionUID() {
        return serialVersionUID;
    }

    @Override
    public List<DeptVo> getChildren() {
        return this.children;
    }

    @Override
    public void setChildren(List<DeptVo> children) {
        this.children = children;
    }

    @Override
    public String toString() {
        return "DeptVo{" +
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
