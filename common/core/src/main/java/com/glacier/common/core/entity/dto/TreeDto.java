package com.glacier.common.core.entity.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.ToString;

import java.io.Serializable;

/**
 * @author glacier
 * @version 1.0
 * @date 2020-08-11 21:25
 */
@ApiModel(description = "树形模型")
@Data
@ToString
public class TreeDto implements Serializable {
    private static final long serialVersionUID = -1422718419166727130L;
    /**
     * 主键
     */
    @ApiModelProperty(value = "主键")
    protected String id;
    /**
     * 名称
     */
    @ApiModelProperty(value = "名称")
    protected String name;
    /**
     * 父级id 顶级id默认为空
     */
    @ApiModelProperty(value = "父级id 顶级id默认为空")
    protected String parentId;
    /**
     * 父级名称
     */
    @ApiModelProperty(value = "父级名称")
    protected String parentName;
    /**
     * 排序号
     */
    @ApiModelProperty(value = "排序号")
    protected Integer orderNum;
    /**
     * 层级
     */
    @ApiModelProperty(value = "层级")
    protected Integer grade;
}
