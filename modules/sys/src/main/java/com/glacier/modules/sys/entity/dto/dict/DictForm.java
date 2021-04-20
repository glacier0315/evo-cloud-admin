package com.glacier.modules.sys.entity.dto.dict;

import com.glacier.common.core.entity.dto.TreeDto;

/**
 * 字典模型
 *
 * @author glacier
 * @version 1.0
 * date 2020-08-11 21:17
 */
public class DictForm extends TreeDto {
    private static final long serialVersionUID = 4192376189821797520L;
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

    @Override
    public String toString() {
        return "DictForm{" +
                "code='" + this.code + '\'' +
                ", type='" + this.type + '\'' +
                ", description='" + this.description + '\'' +
                ", remarks='" + this.remarks + '\'' +
                ", id='" + this.id + '\'' +
                ", name='" + this.name + '\'' +
                ", parentId='" + this.parentId + '\'' +
                ", parentName='" + this.parentName + '\'' +
                ", orderNum=" + this.orderNum +
                ", grade=" + this.grade +
                '}';
    }
}
