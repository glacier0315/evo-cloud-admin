package com.glacier.modules.sys.entity;


import com.glacier.common.core.entity.AbstractDataEntity;

/**
 * 配置
 *
 * @author glacier
 * @version 1.0
 * date 2019-11-06 11:11
 */
public class Config extends AbstractDataEntity {

    private static final long serialVersionUID = 3535850238275216235L;
    /**
     * 值
     */
    private String value;
    /**
     * 显示名称
     */
    private String label;
    /**
     * 类型
     */
    private String type;
    /**
     * 描述
     */
    private String description;
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

    public String getValue() {
        return this.value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    public String getLabel() {
        return this.label;
    }

    public void setLabel(String label) {
        this.label = label;
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
        return "Config{" +
                "value='" + this.value + '\'' +
                ", label='" + this.label + '\'' +
                ", type='" + this.type + '\'' +
                ", description='" + this.description + '\'' +
                ", orderNum=" + this.orderNum +
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
