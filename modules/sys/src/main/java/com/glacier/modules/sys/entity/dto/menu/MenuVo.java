package com.glacier.modules.sys.entity.dto.menu;

import com.glacier.common.core.entity.TreeData;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

import java.util.List;

/**
 * 菜单显示模型
 *
 * @author glacier
 * @version 1.0
 * @date 2020-08-11 21:06
 */
@ApiModel(description = "菜单显示模型")
@Data
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
public class MenuVo extends MenuForm implements TreeData<MenuVo> {
    private static final long serialVersionUID = 5992007206682436283L;
    /**
     * 子类
     */
    @ApiModelProperty(value = "子类")
    protected List<MenuVo> children;
}
