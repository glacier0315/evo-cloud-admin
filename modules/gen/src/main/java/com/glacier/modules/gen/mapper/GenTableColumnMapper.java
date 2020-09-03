package com.glacier.modules.gen.mapper;

import com.glacier.common.core.mapper.BaseMapper;
import com.glacier.modules.gen.entity.GenTableColumn;

import java.util.List;

/**
 * 代码生成表字段 数据层
 *
 * @author glacier
 * @version 1.0
 * @date 2019-08-04 21:53
 */
public interface GenTableColumnMapper extends BaseMapper<GenTableColumn, String> {

    /**
     * 根据条件查询
     * @param params
     * @return
     */
    List<GenTableColumn> selectList(GenTableColumn params);

    /**
     * 根据表id 查询
     *
     * @param tableId 表id
     * @return 生成表字段集合
     */
    List<GenTableColumn> selectListByTableId(String tableId);
}
