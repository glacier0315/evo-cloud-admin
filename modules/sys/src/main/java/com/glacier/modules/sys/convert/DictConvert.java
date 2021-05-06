package com.glacier.modules.sys.convert;

import com.glacier.modules.sys.entity.Dict;
import com.glacier.modules.sys.entity.dto.dict.DictVo;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;

import java.util.List;

/**
 * date 2021-05-06 16:43
 * 字典类型转换
 *
 * @author glacier
 * @version 1.0
 */
@Mapper(componentModel = "spring",
        unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface DictConvert {
    
    /**
     * 转换
     *
     * @param dict
     * @return
     */
    DictVo toDictVo(Dict dict);
    
    /**
     * 转换
     *
     * @param dictList
     * @return
     */
    List<DictVo> toDictVo(List<Dict> dictList);
}
