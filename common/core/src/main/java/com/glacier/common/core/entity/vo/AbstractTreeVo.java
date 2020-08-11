package com.glacier.common.core.entity.vo;

import io.swagger.annotations.ApiModelProperty;

import java.io.Serializable;
import java.util.List;

/**
 * @author glacier
 * @version 1.0
 * @date 2020-08-11 21:11
 */
public abstract class AbstractTreeVo<T extends AbstractTreeVo<T>> implements Serializable {
    private static final long serialVersionUID = -1036159069007440310L;
    /**
     * 主键
     */
    @ApiModelProperty(value = "主键")
    private String id;
    /**
     * 名称
     */
    @ApiModelProperty(value = "名称")
    private String name;
    /**
     * 父级id 顶级id默认为空
     */
    @ApiModelProperty(value = "父级id 顶级id默认为空")
    private String parentId;
    /**
     * 排序号
     */
    @ApiModelProperty(value = "排序号")
    private Integer orderNum;
    /**
     * 层级
     */
    @ApiModelProperty(value = "层级")
    private Integer grade;
    /**
     * 子类
     */
    @ApiModelProperty(value = "子类")
    private List<T> children;

    public static long getSerialVersionUID() {
        return serialVersionUID;
    }

    public String getId() {
        return this.id;
    }

    public void setId(String id) {
        this.id = id;
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
        return "AbstractTreeVo{" +
                "id='" + this.id + '\'' +
                ", name='" + this.name + '\'' +
                ", parentId='" + this.parentId + '\'' +
                ", orderNum=" + this.orderNum +
                ", grade=" + this.grade +
                ", children=" + this.children +
                '}';
    }
}
