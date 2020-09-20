package com.glacier.modules.gen.entity.dto.table;

import com.glacier.common.core.entity.dto.IdDto;
import com.glacier.modules.gen.entity.dto.column.GenTableColumnDto;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

import java.util.List;

/**
 * @author glacier
 * @version 1.0
 * @date 2020-09-20 18:45
 */
@ApiModel(description = "表模型")
@Data
@EqualsAndHashCode(callSuper = true)
@ToString
public class GenTableForm extends IdDto {
    private static final long serialVersionUID = 1273886041559067510L;
    /**
     * 表字段集合
     */
    @ApiModelProperty(value = "表字段集合")
    private List<GenTableColumnDto> tableColumnDtos;
}
