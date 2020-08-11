package com.glacier.modules.sys.entity.form.dept;

import com.glacier.common.core.entity.form.TreeForm;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

/**
 * 组织机构模型
 * @author glacier
 * @version 1.0
 * @date 2020-08-11 21:18
 */
@ApiModel(description = "组织机构模型")
@Data
@EqualsAndHashCode(callSuper = true)
@ToString
public class DeptForm extends TreeForm {
    private static final long serialVersionUID = 179906087824726888L;
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
}
