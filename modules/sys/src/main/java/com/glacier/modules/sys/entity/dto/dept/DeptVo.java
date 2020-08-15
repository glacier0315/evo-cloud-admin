package com.glacier.modules.sys.entity.dto.dept;

import com.glacier.common.core.entity.TreeData;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

import java.util.List;

/**
 * 组织机构显示模型
 *
 * @author glacier
 * @version 1.0
 * @date 2020-08-11 21:19
 */
@ApiModel(description = "组织机构显示模型")
@Data
@EqualsAndHashCode(callSuper = true)
@ToString
public class DeptVo extends DeptForm implements TreeData<DeptVo> {
    private static final long serialVersionUID = 1327312988782909939L;
    /**
     * 子类
     */
    @ApiModelProperty(value = "子类")
    protected List<DeptVo> children;
}
