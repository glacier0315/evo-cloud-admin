package com.glacier.modules.gen.service;

import com.glacier.modules.gen.entity.dto.column.GenTableColumnDto;

import java.util.List;

/**
 * 生成表字段  业务层
 *
 * @author glacier
 * @version 1.0
 * @date 2020-08-26 16:32
 */
public interface GenTableColumnService {
    /**
     * 根据表id 查询
     *
     * @param tableId 表id
     * @return 生成表字段集合
     */
    List<GenTableColumnDto> selectListByTableId(String tableId);
}
