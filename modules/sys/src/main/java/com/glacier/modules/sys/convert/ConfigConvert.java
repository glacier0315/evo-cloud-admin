package com.glacier.modules.sys.convert;

import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;

/**
 * date 2021-05-06 16:43
 * 设置类型转换
 * @author glacier
 * @version 1.0
 */
@Mapper(componentModel = "spring",
        unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface ConfigConvert {
}
