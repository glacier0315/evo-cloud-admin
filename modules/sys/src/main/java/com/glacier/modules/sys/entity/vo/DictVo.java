package com.glacier.modules.sys.entity.vo;

import com.glacier.common.core.entity.vo.AbstractTreeVo;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

/**
 * 字典显示模型
 * @author glacier
 * @version 1.0
 * @date 2020-08-11 22:03
 */
@ApiModel(description = "字典显示模型")
@Data
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
public class DictVo extends AbstractTreeVo<DictVo> {
    private static final long serialVersionUID = 3386109145574281760L;
    /**
     * 字典编码
     */
    @ApiModelProperty(value = "字典编码")
    private String code;
    /**
     * 字典类型
     */
    @ApiModelProperty(value = "字典类型")
    private String type;
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
