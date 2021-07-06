package com.glacier.modules.sys.entity;

import com.glacier.common.core.entity.AbstractTreeEntity;

/**
 * 组织机构
 *
 * @author glacier
 * @version 1.0
 * date 2019-10-14 17:06
 */
public class Dept extends AbstractTreeEntity<Dept> {
    private static final long serialVersionUID = 7605652474322748904L;
    /**
     * 单位编码
     */
    private String code;
    /**
     * 类型
     */
    private Integer type;
    /**
     * 状态
     */
    private Integer status;
    /**
     * 父级名称
     */
    private String parentName;
    /**
     * 父级Id,格式：,id1,id2,
     */
    private String parentIds;

    public static long getSerialVersionUID() {
        return serialVersionUID;
    }

    public String getCode() {
        return this.code;
    }

    public void setCode(String code) {
        this.code = code;
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

    public String getParentName() {
        return this.parentName;
    }

    public void setParentName(String parentName) {
        this.parentName = parentName;
    }

    public String getParentIds() {
        return this.parentIds;
    }

    public void setParentIds(String parentIds) {
        this.parentIds = parentIds;
    }

    @Override
    public String toString() {
        return "Dept{" +
                "code='" + this.code + '\'' +
                ", type=" + this.type +
                ", status=" + this.status +
                ", parentName='" + this.parentName + '\'' +
                ", parentIds='" + this.parentIds + '\'' +
                ", name='" + this.name + '\'' +
                ", parentId='" + this.parentId + '\'' +
                ", orderNum=" + this.orderNum +
                ", grade=" + this.grade +
                ", children=" + this.children +
                ", id='" + this.id + '\'' +
                '}';
    }
}
