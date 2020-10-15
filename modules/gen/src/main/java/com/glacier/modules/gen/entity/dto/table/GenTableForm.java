package com.glacier.modules.gen.entity.dto.table;

import com.glacier.common.core.entity.dto.IdDto;
import com.glacier.modules.gen.entity.dto.column.GenTableColumnDto;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import java.util.List;

/**
 * @author glacier
 * @version 1.0
 * @date 2020-09-20 18:45
 */
@ApiModel(description = "表模型")
public class GenTableForm extends IdDto {
    private static final long serialVersionUID = 1273886041559067510L;
    /**
     * 表字段集合
     */
    @ApiModelProperty(value = "表字段集合")
    private List<GenTableColumnDto> tableColumnDtos;

    public static long getSerialVersionUID() {
        return serialVersionUID;
    }

    public List<GenTableColumnDto> getTableColumnDtos() {
        return this.tableColumnDtos;
    }

    public void setTableColumnDtos(List<GenTableColumnDto> tableColumnDtos) {
        this.tableColumnDtos = tableColumnDtos;
    }

    @Override
    public String toString() {
        return "GenTableForm{" +
                "tableColumnDtos=" + this.tableColumnDtos +
                ", id='" + this.id + '\'' +
                '}';
    }
}
