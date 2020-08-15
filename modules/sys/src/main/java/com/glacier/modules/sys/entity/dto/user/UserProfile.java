package com.glacier.modules.sys.entity.dto.user;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

/**
 * 查询用户信息
 *
 * @author glacier
 * @version 1.0
 * @date 2020-07-23 10:31
 */
@ApiModel(description = "用户信息模型")
@Data
@EqualsAndHashCode(callSuper = true)
@ToString
public class UserProfile extends AbstractUserDto {

    private static final long serialVersionUID = -2286892393843097739L;
    /**
     * 用户id
     */
    @ApiModelProperty(value = "主键")
    private String id;
    /**
     * 头像地址
     */
    @ApiModelProperty(value = "头像地址")
    private String avatar;
}
