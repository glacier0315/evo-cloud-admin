package com.glacier.common.core.entity.dto;

import io.swagger.annotations.ApiModel;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.io.Serializable;
import java.util.List;

/**
 * @author glacier
 * @version 1.0
 * @date 2020-05-18 16:02
 */
@ApiModel(description = "一对多或者多对一，关联关系处理类")
@Data
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class One2ManyRelationDto implements Serializable {
    private static final long serialVersionUID = 2858625583098650664L;
    /**
     * 父级id
     */
    private String pid;
    /**
     * 子级id集合
     */
    private List<String> ids;
}
