package com.glacier.modules.gen.service;

import com.baomidou.dynamic.datasource.DynamicRoutingDataSource;
import com.glacier.modules.gen.entity.GenDatasource;

/**
 * @author glacier
 * @version 1.0
 * date 2021-01-28 16:23
 */
public interface DynamicDataSourceManager {

    /**
     * 校验数据源是否已存在
     * @param poolname 数据源名称
     * @return
     */
    boolean checkDataSourceExists(String poolname);

    /**
     * 添加数据源
     * @param genDatasource 数据源
     * @return 数据源
     */
    DynamicRoutingDataSource addDataSource(GenDatasource genDatasource);

    /**
     * 移除数据源
     * @param poolname 数据源名称
     */
    void removeDataSource(String poolname);
}
