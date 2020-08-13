package com.glacier.common.core.entity.vo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.ToString;

import java.io.Serializable;
import java.util.List;

/**
 * @author glacier
 * @version 1.0
 * @date 2020-08-11 21:11
 */
@ApiModel(description = "树形响应模型")
@Data
@ToString
public abstract class AbstractTreeVo<T extends AbstractTreeVo<T>> implements Serializable {
    private static final long serialVersionUID = -1036159069007440310L;
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
     * 排序号
     */
    @ApiModelProperty(value = "排序号")
    protected Integer orderNum;
    /**
     * 层级
     */
    @ApiModelProperty(value = "层级")
    protected Integer grade;
    /**
     * 子类
     */
    @ApiModelProperty(value = "子类")
    protected List<T> children;
}
