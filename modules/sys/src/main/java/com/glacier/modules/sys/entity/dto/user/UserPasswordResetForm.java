package com.glacier.modules.sys.entity.dto.user;

import com.glacier.common.core.entity.dto.IdDto;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

/**
 * 用户密码重置模型
 *
 * @author glacier
 * @version 1.0
 * @date 2020-08-27 16:50
 */
@ApiModel(description = "用户密码重置模型")
@Data
@EqualsAndHashCode(callSuper = true)
@ToString
public class UserPasswordResetForm extends IdDto {
    private static final long serialVersionUID = -3806037954613930772L;
    /**
     * 新密码
     */
    @ApiModelProperty(value = "新密码")
    private String newPassword;
}
