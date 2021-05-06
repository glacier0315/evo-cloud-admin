package com.glacier.modules.gen.convert;

import com.glacier.modules.gen.entity.GenDatasource;
import com.glacier.modules.gen.entity.dto.datasource.GenDatasourceDto;
import com.glacier.modules.gen.entity.dto.datasource.GenDatasourceForm;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;

import java.util.List;

/**
 * date 2021-05-06 16:56
 *
 * @author glacier
 * @version 1.0
 */
@Mapper(componentModel = "spring",
        unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface GenDatasourceConvert {
    /**
     * 转换
     * @param genDatasource
     * @return
     */
    GenDatasourceDto toGenDatasourceDto(GenDatasource genDatasource);
    
    /**
     * 转换
     * @param list
     * @return
     */
    List<GenDatasourceDto> GenDatasourceDto(List<GenDatasource> list);
    
    /**
     * 转换
     * @param form
     * @return
     */
    GenDatasource map(GenDatasourceForm form);
}
