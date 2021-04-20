package com.glacier.modules.sys.entity.dto.dept;

import com.glacier.common.core.entity.dto.TreeDto;

/**
 * 组织机构模型
 *
 * @author glacier
 * @version 1.0
 * date 2020-08-11 21:18
 */
public class DeptForm extends TreeDto {
    private static final long serialVersionUID = 179906087824726888L;
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

    @Override
    public String toString() {
        return "DeptForm{" +
                "code='" + this.code + '\'' +
                ", type=" + this.type +
                ", status=" + this.status +
                ", id='" + this.id + '\'' +
                ", name='" + this.name + '\'' +
                ", parentId='" + this.parentId + '\'' +
                ", parentName='" + this.parentName + '\'' +
                ", orderNum=" + this.orderNum +
                ", grade=" + this.grade +
                '}';
    }
}
