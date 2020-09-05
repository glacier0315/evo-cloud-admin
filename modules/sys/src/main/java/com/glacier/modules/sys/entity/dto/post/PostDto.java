package com.glacier.modules.sys.entity.dto.post;

import com.glacier.common.core.entity.dto.IdDto;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

/**
 * 岗位查询条件
 * @author glacier
 * @version 1.0
 * @date 2020-09-05 21:26
 */
@ApiModel(description = "岗位查询条件模型")
@Data
@ToString
@EqualsAndHashCode(callSuper = true)
public class PostDto extends IdDto {
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
    /**
     * 排序号
     */
    @ApiModelProperty(value = "排序号")
    protected Integer orderNum;
    /**
     * 描述
     */
    @ApiModelProperty(value = "描述")
    private String description;
    /**
     * 备注
     */
    @ApiModelProperty(value = "备注")
    private String remarks;
}
