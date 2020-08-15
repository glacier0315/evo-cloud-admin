package com.glacier.modules.sys.entity.dto.user;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

/**
 * @author glacier
 * @version 1.0
 * @date 2020-05-24 09:32
 */
@ApiModel(description = "用户显示和更新模型")
@Data
@EqualsAndHashCode(callSuper = true)
@ToString
public class UserDto extends AbstractUserDto {
    private static final long serialVersionUID = 3725415789818769982L;
    /**
     * 主键
     */
    @ApiModelProperty(value = "主键")
    private String id;
    /**
     * 状态  1 正常  0 锁定
     */
    @ApiModelProperty(value = "状态  1 正常  0 锁定")
    private String status;
}
