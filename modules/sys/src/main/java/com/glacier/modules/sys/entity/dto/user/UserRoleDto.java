package com.glacier.modules.sys.entity.dto.user;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.io.Serializable;
import java.util.List;

/**
 * 用户角色关系模型
 *
 * @author glacier
 * @version 1.0
 * @date 2020-09-01 11:51
 */
@ApiModel(description = "用户角色关系模型")
@Data
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class UserRoleDto implements Serializable {

    private static final long serialVersionUID = 4166908160897610687L;
    /**
     * 用户ID
     */
    @ApiModelProperty(value = "用户Id")
    private String userId;
    /**
     * 角色ID集合
     */
    @ApiModelProperty(value = "角色ID集合")
    private List<String> roleIds;
}
