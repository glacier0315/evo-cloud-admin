package com.glacier.modules.gen.entity.dto.datasource;

import com.glacier.common.core.entity.dto.IdDto;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

/**
 * 数据源表单模型
 * @author glacier
 * @version 1.0
 * @date 2020-09-20 16:15
 */
@ApiModel(description = "数据源表单模型")
public class GenDatasourceForm extends IdDto {
    private static final long serialVersionUID = 492314054272106718L;
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

    /**
     * 用户名
     */
    @ApiModelProperty(value = "用户名")
    private String username;

    /**
     * 密码
     */
    @ApiModelProperty(value = "密码")
    private String password;

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

    public String getUsername() {
        return this.username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return this.password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    @Override
    public String toString() {
        return "GenDatasourceForm{" +
                "name='" + this.name + '\'' +
                ", url='" + this.url + '\'' +
                ", username='" + this.username + '\'' +
                ", password='" + this.password + '\'' +
                ", id='" + this.id + '\'' +
                '}';
    }
}
