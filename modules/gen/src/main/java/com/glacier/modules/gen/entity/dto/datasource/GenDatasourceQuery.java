package com.glacier.modules.gen.entity.dto.datasource;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.ToString;

import java.io.Serializable;

/**
 * 数据源查询模型
 * @author glacier
 * @version 1.0
 * @date 2020-09-20 16:03
 */
@ApiModel(description = "数据源查询模型")
@Data
@ToString
public class GenDatasourceQuery implements Serializable {
    private static final long serialVersionUID = 691721743760935906L;

    /**
     * 名称
     */
    @ApiModelProperty(value = "名称")
    private String name;
}
