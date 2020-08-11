package com.glacier.common.core.entity.pojo;

import java.util.List;

/**
 * 树形基类
 *
 * @author glacier
 * @version 1.0
 * @date 2020-08-10 17:40
 */
public abstract class AbstractTreePojo<T extends AbstractTreePojo<T>> extends AbstractDataPojo {
    private static final long serialVersionUID = 5789176651104516887L;
    /**
     * 名称
     */
    private String name;
    /**
     * 父级id 顶级id默认为空
     */
    private String parentId;
    /**
     * 排序号
     */
    private Integer orderNum;
    /**
     * 层级
     */
    private Integer grade;
    /**
     * 子类
     */
    private List<T> children;

    public static long getSerialVersionUID() {
        return serialVersionUID;
    }

    public String getName() {
        return this.name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getParentId() {
        return this.parentId;
    }

    public void setParentId(String parentId) {
        this.parentId = parentId;
    }

    public Integer getOrderNum() {
        return this.orderNum;
    }

    public void setOrderNum(Integer orderNum) {
        this.orderNum = orderNum;
    }

    public Integer getGrade() {
        return this.grade;
    }

    public void setGrade(Integer grade) {
        this.grade = grade;
    }

    public List<T> getChildren() {
        return this.children;
    }

    public void setChildren(List<T> children) {
        this.children = children;
    }

    @Override
    public String toString() {
        return "AbstractTreePojo{" +
                "name='" + this.name + '\'' +
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
