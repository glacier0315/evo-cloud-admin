package com.glacier.modules.sys.entity.vo.user;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.*;

import java.io.Serializable;

/**
 * 查询用户信息
 *
 * @author glacier
 * @version 1.0
 * @date 2020-07-23 10:31
 */
@ApiModel(description = "用户信息模型")
@Data
@ToString
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class UserProfile implements Serializable {

    private static final long serialVersionUID = -2286892393843097739L;
    /**
     * 用户id
     */
    @ApiModelProperty(value = "主键")
    private String id;
    /**
     * 用户名
     */
    @ApiModelProperty(value = "用户名")
    private String username;
    /**
     * 昵称
     */
    @ApiModelProperty(value = "昵称")
    private String nickname;
    /**
     * 手机号
     */
    @ApiModelProperty(value = "手机号")
    private String mobile;
    /**
     * 邮箱
     */
    @ApiModelProperty(value = "邮箱")
    private String email;
    /**
     * 性别
     */
    @ApiModelProperty(value = "性别")
    private Integer sex;
    /**
     * 单位id
     */
    @ApiModelProperty(value = "单位id")
    private String deptId;
    /**
     * 单位名称
     */
    @ApiModelProperty(value = "单位名称")
    private String deptName;
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
}
