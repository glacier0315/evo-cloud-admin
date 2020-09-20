package com.glacier.modules.gen.mapper;

import com.glacier.common.core.mapper.BaseMapper;
import com.glacier.modules.gen.entity.GenDatasource;
import com.glacier.modules.gen.entity.dto.datasource.GenDatasourceQuery;

import java.util.List;

/**
 * 数据源表  持久层
 * @author glacier
 * @version 1.0
 * @date 2020-09-20 16:02
 */
public interface GenDatasourceMapper extends BaseMapper<GenDatasource, String> {

    /**
     * 根据条件查询
     * @param params
     * @return
     */
    List<GenDatasource> selectList(GenDatasourceQuery params);
}
