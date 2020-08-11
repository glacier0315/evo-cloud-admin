package com.glacier.modules.sys.entity.vo;

import com.glacier.common.core.entity.vo.AbstractTreeVo;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

/**
 * 组织机构显示模型
 * @author glacier
 * @version 1.0
 * @date 2020-08-11 21:19
 */
@ApiModel(description = "组织机构显示模型")
@Data
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
public class DeptVo extends AbstractTreeVo<DeptVo> {
    private static final long serialVersionUID = 1327312988782909939L;
    /**
     * 单位编码
     */
    @ApiModelProperty(value = "单位编码")
    private String code;
    /**
     * 类型
     */
    @ApiModelProperty(value = "类型")
    private Integer type;
    /**
     * 状态
     */
    @ApiModelProperty(value = "状态")
    private Integer status;
    /**
     * 父级名称
     */
    @ApiModelProperty(value = "父级名称")
    private String parentName;
}
