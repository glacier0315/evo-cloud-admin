package com.glacier.modules.sys.entity.form.user;

import com.glacier.common.core.entity.form.IdForm;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

/**
 * 用户密码修改封装类
 *
 * @author glacier
 * @version 1.0
 * @date 2020-07-23 10:12
 */
@ApiModel(description = "用户密码模型")
@EqualsAndHashCode(callSuper = true)
@Data
@ToString
public class UserPasswordForm extends IdForm {
    private static final long serialVersionUID = -3806037954613930772L;
    /**
     * 旧密码
     */
    @ApiModelProperty(value = "旧密码")
    private String oldPassword;
    /**
     * 新密码
     */
    @ApiModelProperty(value = "新密码")
    private String newPassword;
}
