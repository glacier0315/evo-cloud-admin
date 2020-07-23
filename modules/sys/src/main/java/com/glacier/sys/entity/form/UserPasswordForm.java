package com.glacier.sys.entity.form;

import com.glacier.common.core.entity.form.IdForm;
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
@EqualsAndHashCode(callSuper = true)
@Data
@ToString
public class UserPasswordForm extends IdForm {
    private static final long serialVersionUID = -3806037954613930772L;
    /**
     * 旧密码
     */
    private String oldPassword;

    /**
     * 新密码
     */
    private String newPassword;
}
