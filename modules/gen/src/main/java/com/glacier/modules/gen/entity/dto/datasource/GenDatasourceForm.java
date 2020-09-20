package com.glacier.modules.gen.entity.dto.datasource;

import com.glacier.common.core.entity.dto.IdDto;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

/**
 * 数据源表单模型
 * @author glacier
 * @version 1.0
 * @date 2020-09-20 16:15
 */
@ApiModel(description = "数据源表单模型")
@Data
@EqualsAndHashCode(callSuper = true)
@ToString
public class GenDatasourceForm extends IdDto {
    private static final long serialVersionUID = 492314054272106718L;
    /**
     * 名称
     */
    @ApiModelProperty(value = "名称")
    private String name;

    /**
     * jdbc url
     */
    @ApiModelProperty(value = "jdbc url")
    private String url;

    /**
     * 用户名
     */
    @ApiModelProperty(value = "用户名")
    private String username;

    /**
     * 密码
     */
    @ApiModelProperty(value = "密码")
    private String password;
}
