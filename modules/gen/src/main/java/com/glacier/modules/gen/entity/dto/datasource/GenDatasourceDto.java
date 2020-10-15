package com.glacier.modules.gen.entity.dto.datasource;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import java.io.Serializable;

/**
 * @author glacier
 * @version 1.0
 * @date 2020-09-20 16:04
 */
@ApiModel(description = "数据源模型")
public class GenDatasourceDto implements Serializable {
    private static final long serialVersionUID = 2368894564683166828L;
    /**
     * 名称
     */
    @ApiModelProperty(value = "名称")
    private String name;

    /**
     * jdbc url
     */
    @ApiModelProperty(value = "jdbc url")
    private String url;

    public static long getSerialVersionUID() {
        return serialVersionUID;
    }

    public String getName() {
        return this.name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getUrl() {
        return this.url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    @Override
    public String toString() {
        return "GenDatasourceDto{" +
                "name='" + this.name + '\'' +
                ", url='" + this.url + '\'' +
                '}';
    }
}
