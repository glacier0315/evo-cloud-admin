package com.glacier.common.core.entity.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.ToString;

import java.io.Serializable;

/**
 * @author glacier
 * @version 1.0
 * @date 2020-05-23 21:53
 */
@ApiModel(description = "ID请求模型")
@Data
@ToString
public class IdDto implements Serializable {
    private static final long serialVersionUID = -5618778600490092506L;
    /**
     * 主键
     */
    @ApiModelProperty(value = "主键")
    protected String id;
}
