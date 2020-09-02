package com.glacier.modules.sys.entity.dto.menu;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.*;

import java.io.Serializable;
import java.util.List;

/**
 * @author glacier
 * @version 1.0
 * @date 2020-09-01 22:18
 */
@ApiModel(description = "菜单查询条件模型")
@Data
@NoArgsConstructor
@AllArgsConstructor
@ToString
@Builder
public class MenuQuery implements Serializable {
    private static final long serialVersionUID = 3282253575775981590L;

    /**
     * 状态 1 正常  2 禁用
     */
    @ApiModelProperty(value = "状态 1 正常  2 禁用")
    private Integer status;
    /**
     * 用户id
     */
    @ApiModelProperty(value = "用户id")
    private String userId;
    /**
     * 角色id
     */
    @ApiModelProperty(value = "角色id")
    private String roleId;
    /**
     * 资源类型
     */
    @ApiModelProperty(value = "资源类型")
    private List<Integer> typeList;
}
