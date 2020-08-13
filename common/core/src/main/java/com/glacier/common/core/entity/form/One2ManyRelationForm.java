package com.glacier.common.core.entity.form;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.ToString;

import java.io.Serializable;
import java.util.List;

/**
 * @author glacier
 * @version 1.0
 * @date 2020-05-18 16:02
 */
@ApiModel(description = "一对多模型")
@Data
@ToString
public class One2ManyRelationForm implements Serializable {
    private static final long serialVersionUID = 2858625583098650664L;
    /**
     * 父级id
     */
    @ApiModelProperty(value = "父级id")
    private String pid;
    /**
     * 子级id集合
     */
    @ApiModelProperty(value = "子级id集合")
    private List<String> ids;
}
