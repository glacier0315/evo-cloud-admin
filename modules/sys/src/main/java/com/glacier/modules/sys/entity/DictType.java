package com.glacier.modules.sys.entity;

import com.glacier.common.core.entity.AbstractDataEntity;

/**
 * 字典类别
 *
 * @author glacier
 * @version 1.0
 * @date 2019-10-09 11:03
 */
public class DictType extends AbstractDataEntity {

    private static final long serialVersionUID = 2884044409649620265L;
    /**
     * 字典名称
     */
    private String dictName;

    /**
     * 字典类型
     */
    private String dictType;

    /**
     * 状态（1正常 2停用）
     */
    private Integer status;

    /**
     * 备注
     */
    private String remarks;

    public static long getSerialVersionUID() {
        return serialVersionUID;
    }

    public String getDictName() {
        return this.dictName;
    }

    public void setDictName(String dictName) {
        this.dictName = dictName;
    }

    public String getDictType() {
        return this.dictType;
    }

    public void setDictType(String dictType) {
        this.dictType = dictType;
    }

    public Integer getStatus() {
        return this.status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public String getRemarks() {
        return this.remarks;
    }

    public void setRemarks(String remarks) {
        this.remarks = remarks;
    }

    @Override
    public String toString() {
        return "DictType{" +
                "dictName='" + this.dictName + '\'' +
                ", dictType='" + this.dictType + '\'' +
                ", status=" + this.status +
                ", remarks='" + this.remarks + '\'' +
                ", createBy='" + this.createBy + '\'' +
                ", createDate=" + this.createDate +
                ", updateBy='" + this.updateBy + '\'' +
                ", updateDate=" + this.updateDate +
                ", delFlag='" + this.delFlag + '\'' +
                ", id='" + this.id + '\'' +
                '}';
    }
}
