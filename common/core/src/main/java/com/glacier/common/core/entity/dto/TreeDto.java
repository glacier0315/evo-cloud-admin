package com.glacier.common.core.entity.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import java.io.Serializable;

/**
 * @author glacier
 * @version 1.0
 * @date 2020-08-11 21:25
 */
@ApiModel(description = "树形模型")
public class TreeDto implements Serializable {
    private static final long serialVersionUID = -1422718419166727130L;
    /**
     * 主键
     */
    @ApiModelProperty(value = "主键")
    protected String id;
    /**
     * 名称
     */
    @ApiModelProperty(value = "名称")
    protected String name;
    /**
     * 父级id 顶级id默认为空
     */
    @ApiModelProperty(value = "父级id 顶级id默认为空")
    protected String parentId;
    /**
     * 父级名称
     */
    @ApiModelProperty(value = "父级名称")
    protected String parentName;
    /**
     * 排序号
     */
    @ApiModelProperty(value = "排序号")
    protected Integer orderNum;
    /**
     * 层级
     */
    @ApiModelProperty(value = "层级")
    protected Integer grade;

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

    public String getParentName() {
        return this.parentName;
    }

    public void setParentName(String parentName) {
        this.parentName = parentName;
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

    @Override
    public String toString() {
        return "TreeDto{" +
                "id='" + this.id + '\'' +
                ", name='" + this.name + '\'' +
                ", parentId='" + this.parentId + '\'' +
                ", parentName='" + this.parentName + '\'' +
                ", orderNum=" + this.orderNum +
                ", grade=" + this.grade +
                '}';
    }
}
