package com.glacier.modules.sys.mapper;

import com.glacier.common.core.entity.dto.IdDto;
import com.glacier.common.core.mapper.BaseMapper;
import com.glacier.modules.sys.entity.Dict;

import java.util.List;


/**
 * 字典 数据层
 *
 * @author glacier
 * @version 1.0
 * @date 2019-08-04 21:53
 */
public interface DictMapper extends BaseMapper<Dict, String> {

    /**
     * 批量删除
     *
     * @param list 待删除id集合
     * @return 删除记录数
     */
    int deleteBatchIds(List<IdDto> list);
}
