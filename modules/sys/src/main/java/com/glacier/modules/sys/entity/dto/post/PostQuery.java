package com.glacier.modules.sys.entity.dto.post;

import java.io.Serializable;

/**
 * 岗位查询条件
 * @author glacier
 * @version 1.0
 * date 2020-09-05 21:26
 */
public class PostQuery implements Serializable {
    private static final long serialVersionUID = 8688278856685935029L;
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

    @Override
    public String toString() {
        return "PostQuery{" +
                "name='" + this.name + '\'' +
                ", code='" + this.code + '\'' +
                ", status=" + this.status +
                '}';
    }
}
