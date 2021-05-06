package com.glacier.modules.gen.convert;

import com.glacier.modules.gen.entity.GenTableColumn;
import com.glacier.modules.gen.entity.dto.column.GenTableColumnDto;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;

import java.util.List;

/**
 * date 2021-05-06 17:01
 *
 * @author glacier
 * @version 1.0
 */
@Mapper(componentModel = "spring",
        unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface GenTableColumnConvert {
    /**
     * 转换
     *
     * @param tableColumn
     * @return
     */
    GenTableColumnDto toGenTableColumnDto(GenTableColumn tableColumn);
    
    /**
     * 转换
     *
     * @param tableColumnList
     * @return
     */
    List<GenTableColumnDto> toGenTableColumnDto(List<GenTableColumn> tableColumnList);
}
