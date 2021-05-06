package com.glacier.modules.gen.convert;

import com.glacier.modules.gen.entity.GenTable;
import com.glacier.modules.gen.entity.dto.table.GenTableDto;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;

import java.util.List;

/**
 * date 2021-05-06 17:04
 *
 * @author glacier
 * @version 1.0
 */
@Mapper(componentModel = "spring",
        unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface GenTableConvert {
    /**
     * 转换
     * @param table
     * @return
     */
    GenTableDto toGenTableDto(GenTable table);
    
    /**
     * 转换
     * @param list
     * @return
     */
    List<GenTableDto> toGenTableDto(List<GenTable> tableList);
    
    /**
     * 转换
     * @param tableDto
     * @return
     */
    GenTable map(GenTableDto tableDto);
}
