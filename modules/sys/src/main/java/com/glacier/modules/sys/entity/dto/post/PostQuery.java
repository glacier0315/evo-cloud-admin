package com.glacier.modules.sys.entity.dto.post;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.ToString;

import java.io.Serializable;

/**
 * 岗位查询条件
 * @author glacier
 * @version 1.0
 * @date 2020-09-05 21:26
 */
@ApiModel(description = "岗位查询条件模型")
@Data
@ToString
public class PostQuery implements Serializable {
    private static final long serialVersionUID = 8688278856685935029L;
    /**
     * 名称
     */
    @ApiModelProperty(value = "名称")
    private String name;
    /**
     * 编码
     */
    @ApiModelProperty(value = "编码")
    private String code;
    /**
     * 状态 1 正常 2 禁用
     */
    @ApiModelProperty(value = "状态 1 正常 2 禁用")
    private Integer status;
}
