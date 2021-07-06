package com.glacier.modules.sys.entity;

import com.glacier.common.core.entity.AbstractDataEntity;

/**
 * 岗位
 *
 * @author glacier
 * @version 1.0
 * date 2019-10-09 11:03
 */
public class Post extends AbstractDataEntity {
    private static final long serialVersionUID = -8716186319813001885L;
    /**
     * 名称
     */
    private String name;
    /**
     * 编码
     */
    private String code;
    /**
     * 状态 1 正常 2 禁用
     */
    private Integer status;
    /**
     * 排序号
     */
    protected Integer orderNum;
    /**
     * 描述
     */
    private String description;
    /**
     * 备注
     */
    private String remarks;

    public static long getSerialVersionUID() {
        return serialVersionUID;
    }

    public String getName() {
        return this.name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCode() {
        return this.code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public Integer getStatus() {
        return this.status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public Integer getOrderNum() {
        return this.orderNum;
    }

    public void setOrderNum(Integer orderNum) {
        this.orderNum = orderNum;
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

    @Override
    public String toString() {
        return "Post{" +
                "name='" + this.name + '\'' +
                ", code='" + this.code + '\'' +
                ", status=" + this.status +
                ", orderNum=" + this.orderNum +
                ", description='" + this.description + '\'' +
                ", remarks='" + this.remarks + '\'' +
                ", id='" + this.id + '\'' +
                '}';
    }
}
