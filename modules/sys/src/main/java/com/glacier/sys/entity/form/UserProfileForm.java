package com.glacier.sys.entity.form;

import com.glacier.common.core.entity.form.IdForm;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

/**
 * 用户信息修改封装类
 *
 * @author glacier
 * @version 1.0
 * @date 2020-07-23 10:31
 */
@EqualsAndHashCode(callSuper = true)
@Data
@ToString
public class UserProfileForm extends IdForm {

    private static final long serialVersionUID = -4572380926802939142L;
    /**
     * 昵称
     */
    private String nickname;
    /**
     * 手机号
     */
    private String mobile;
    /**
     * 邮箱
     */
    private String email;
    /**
     * 性别
     */
    private Integer sex;
}

