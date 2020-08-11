package com.glacier.modules.sys.entity.vo.user;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.*;

import java.io.Serializable;
import java.util.List;

/**
 * 查询用户信息
 *
 * @author glacier
 * @version 1.0
 * @date 2020-05-22 16:56
 */
@ApiModel(description = "用户信息模型")
@Data
@ToString
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class UserInfo implements Serializable {
    private static final long serialVersionUID = 2496540352121367958L;

    /**
     * 用户id
     */
    @ApiModelProperty(value = "主键")
    private String id;
    /**
     * 用户名
     */
    @ApiModelProperty(value = "用户名")
    private String name;
    /**
     * 头像地址
     */
    @ApiModelProperty(value = "头像地址")
    private String avatar;
    /**
     * 简介
     */
    @ApiModelProperty(value = "简介")
    private String introduction;
    /**
     * 角色编码集合
     */
    @ApiModelProperty(value = "角色编码集合")
    private List<String> roles;
}
