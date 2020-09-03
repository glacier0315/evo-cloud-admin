package com.glacier.modules.gen.mapper;

import com.glacier.common.core.mapper.BaseMapper;
import com.glacier.modules.gen.entity.GenTable;
import com.glacier.modules.gen.entity.dto.GenTableQuery;

import java.util.List;


/**
 * 代码生成表 数据层
 *
 * @author glacier
 * @version 1.0
 * @date 2019-08-04 21:53
 */
public interface GenTableMapper extends BaseMapper<GenTable, String> {

    /**
     * 根据条件查询
     * @param params
     * @return
     */
    List<GenTable> selectList(GenTableQuery params);
}
