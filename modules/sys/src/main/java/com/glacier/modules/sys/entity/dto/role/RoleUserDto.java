package com.glacier.modules.sys.entity.dto.role;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.io.Serializable;
import java.util.List;

/**
 * 用户角色模型
 * @author glacier
 * @version 1.0
 * @date 2020-08-30 12:42
 */
@ApiModel(description = "用户角色模型")
@Data
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class RoleUserDto implements Serializable {
    private static final long serialVersionUID = -8860417900882239422L;
    /**
     * 角色id
     */
    @ApiModelProperty(value = "角色id")
    private String roleId;
    /**
     * 用户id集合
     */
    @ApiModelProperty(value = "用户id集合")
    private List<String> userIds;
}
