package com.glacier.modules.sys.entity.dto.user;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

/**
 * 用户新增
 *
 * @author glacier
 * @version 1.0
 * @date 2020-05-24 08:56
 */
@ApiModel(description = "用户新增模型")
@Data
@EqualsAndHashCode(callSuper = true)
@ToString
public class UserAddForm extends AbstractUserDto {
    private static final long serialVersionUID = 5710986214973433730L;

    /**
     * 密码
     */
    @ApiModelProperty(value = "密码")
    private String password;
    /**
     * 状态  1 正常  0 锁定
     */
    @ApiModelProperty(value = "状态  1 正常  0 锁定")
    private String status;
}
