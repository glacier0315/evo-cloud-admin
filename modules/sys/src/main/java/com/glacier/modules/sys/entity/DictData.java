package com.glacier.modules.sys.entity;

import com.glacier.common.core.entity.DataEntity;

/**
 * 字典数据
 *
 * @author glacier
 * @version 1.0
 * date 2019-10-09 11:03
 */
public class DictData extends DataEntity {
    private static final long serialVersionUID = 2538417218158807473L;
    /**
     * 字典标签
     */
    private String label;

    /**
     * 字典键值
     */
    private String value;

    /**
     * 字典类型
     */
    private String type;

    /**
     * 是否默认（1是 2否）
     */
    private String defaultFlag;

    /**
     * 字典类型
     */
    private String dictType;

    /**
     * 状态（1正常 2停用）
     */
    private Integer status;

    /**
     * 排序号
     */
    private Integer orderNum;

    /**
     * 备注
     */
    private String remarks;

    public static long getSerialVersionUID() {
        return serialVersionUID;
    }

    public String getLabel() {
        return this.label;
    }

    public void setLabel(String label) {
        this.label = label;
    }

    public String getValue() {
        return this.value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    public String getType() {
        return this.type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getDefaultFlag() {
        return this.defaultFlag;
    }

    public void setDefaultFlag(String defaultFlag) {
        this.defaultFlag = defaultFlag;
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

    public Integer getOrderNum() {
        return this.orderNum;
    }

    public void setOrderNum(Integer orderNum) {
        this.orderNum = orderNum;
    }

    public String getRemarks() {
        return this.remarks;
    }

    public void setRemarks(String remarks) {
        this.remarks = remarks;
    }

    @Override
    public String toString() {
        return "DictData{" +
                "label='" + this.label + '\'' +
                ", value='" + this.value + '\'' +
                ", type='" + this.type + '\'' +
                ", defaultFlag='" + this.defaultFlag + '\'' +
                ", dictType='" + this.dictType + '\'' +
                ", status=" + this.status +
                ", orderNum=" + this.orderNum +
                ", remarks='" + this.remarks + '\'' +
                ", id='" + this.id + '\'' +
                '}';
    }
}
