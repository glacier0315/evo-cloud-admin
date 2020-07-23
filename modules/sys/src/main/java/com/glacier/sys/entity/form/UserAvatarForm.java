package com.glacier.sys.entity.form;

import com.glacier.common.core.entity.form.IdForm;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

/**
 * 用户头像修改封装类
 *
 * @author glacier
 * @version 1.0
 * @date 2020-07-23 10:31
 */
@EqualsAndHashCode(callSuper = true)
@Data
@ToString
public class UserAvatarForm extends IdForm {

    private static final long serialVersionUID = -7808828383978219364L;
    /**
     * 头像地址
     */
    private String avatar;
}
