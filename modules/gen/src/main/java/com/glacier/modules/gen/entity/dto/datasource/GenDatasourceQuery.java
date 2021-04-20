package com.glacier.modules.gen.entity.dto.datasource;


import java.io.Serializable;

/**
 * 数据源查询模型
 * @author glacier
 * @version 1.0
 * date 2020-09-20 16:03
 */
public class GenDatasourceQuery implements Serializable {
    private static final long serialVersionUID = 691721743760935906L;

    /**
     * 名称
     */
    private String name;

    public static long getSerialVersionUID() {
        return serialVersionUID;
    }

    public String getName() {
        return this.name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return "GenDatasourceQuery{" +
                "name='" + this.name + '\'' +
                '}';
    }
}
