package com.glacier.modules.sys.entity;

import com.glacier.common.core.entity.AbstractTreeEntity;

/**
 * 字典
 *
 * @author glacier
 * @version 1.0
 * date 2019-12-01 21:16
 */
public class Dict extends AbstractTreeEntity<Dict> {
    private static final long serialVersionUID = -8004367732541881835L;
    /**
     * 字典编码
     */
    private String code;
    /**
     * 字典类型
     */
    private String type;
    /**
     * 描述
     */
    private String description;
    /**
     * 备注
     */
    private String remarks;
    /**
     * 父级名称
     */
    private String parentName;

    public static long getSerialVersionUID() {
        return serialVersionUID;
    }

    public String getCode() {
        return this.code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getType() {
        return this.type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getDescription() {
        return this.description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getRemarks() {
        return this.remarks;
    }

    public void setRemarks(String remarks) {
        this.remarks = remarks;
    }

    public String getParentName() {
        return this.parentName;
    }

    public void setParentName(String parentName) {
        this.parentName = parentName;
    }

    @Override
    public String toString() {
        return "Dict{" +
                "code='" + this.code + '\'' +
                ", type='" + this.type + '\'' +
                ", description='" + this.description + '\'' +
                ", remarks='" + this.remarks + '\'' +
                ", parentName='" + this.parentName + '\'' +
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
