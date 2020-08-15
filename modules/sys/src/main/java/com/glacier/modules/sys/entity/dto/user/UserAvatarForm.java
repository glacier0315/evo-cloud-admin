package com.glacier.modules.sys.entity.dto.user;

import com.glacier.common.core.entity.dto.IdDto;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
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
@ApiModel(description = "用户头像模型")
@Data
@EqualsAndHashCode(callSuper = true)
@ToString
public class UserAvatarForm extends IdDto {

    private static final long serialVersionUID = -7808828383978219364L;
    /**
     * 头像地址
     */
    @ApiModelProperty(value = "头像地址")
    private String avatar;
}
