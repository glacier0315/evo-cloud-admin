package com.glacier.modules.gen.entity.dto.datasource;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.ToString;

import java.io.Serializable;

/**
 * @author glacier
 * @version 1.0
 * @date 2020-09-20 16:04
 */
@ApiModel(description = "数据源模型")
@Data
@ToString
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
}
