package com.glacier.modules.gen.entity.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.ToString;

import java.io.Serializable;

/**
 * @author glacier
 * @version 1.0
 * @date 2020-08-26 16:56
 */
@ApiModel(description = "生成表查询模型")
@Data
@ToString
public class GenTableQueryForm implements Serializable {
    private static final long serialVersionUID = 8039085389833762809L;
    /**
     * 表名称
     */
    @ApiModelProperty(value = "表名称")
    private String tableName;
    /**
     * 表描述
     */
    @ApiModelProperty(value = "表描述")
    private String tableComment;
}
