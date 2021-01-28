package com.glacier.modules.gen.service.impl;

import com.baomidou.dynamic.datasource.DynamicRoutingDataSource;
import com.baomidou.dynamic.datasource.creator.DruidDataSourceCreator;
import com.baomidou.dynamic.datasource.spring.boot.autoconfigure.DataSourceProperty;
import com.baomidou.dynamic.datasource.spring.boot.autoconfigure.druid.DruidConfig;
import com.glacier.modules.gen.entity.GenDatasource;
import com.glacier.modules.gen.service.DynamicDataSourceManager;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.sql.DataSource;

/**
 * @author glacier
 * @version 1.0
 * @date 2021-01-28 16:24
 */
@Service
public class DynamicDataSourceManagerImpl implements DynamicDataSourceManager {
    private static final Logger log = LoggerFactory.getLogger(DynamicDataSourceManagerImpl.class);
    private final DynamicRoutingDataSource ds;
    private final DruidDataSourceCreator druidDataSourceCreator;

    @Autowired
    public DynamicDataSourceManagerImpl(
            DynamicRoutingDataSource ds,
            DruidDataSourceCreator druidDataSourceCreator) {
        this.ds = ds;
        this.druidDataSourceCreator = druidDataSourceCreator;
    }

    @Override
    public boolean checkDataSourceExists(String poolname) {
        return ds.getDataSource(poolname) != null;
    }

    @Override
    public DynamicRoutingDataSource addDataSource(GenDatasource genDatasource) {
        if (genDatasource == null
                || StringUtils.isBlank(genDatasource.getId())
                || StringUtils.isBlank(genDatasource.getDriverClassName())
                || StringUtils.isBlank(genDatasource.getUrl())
                || StringUtils.isBlank(genDatasource.getUsername())
                || StringUtils.isBlank(genDatasource.getPassword())
                || StringUtils.isBlank(genDatasource.getValidationQuery())) {
            log.error("数据源参数 {} 不合法！", genDatasource);
            throw new IllegalArgumentException("数据源参数不合法！");
        }
        // 数据源不存在时
        if (ds.getDataSource(genDatasource.getId()) == null) {
            DataSourceProperty dataSourceProperty = new DataSourceProperty();
            dataSourceProperty.setPoolName(genDatasource.getId());
            dataSourceProperty.setUrl(genDatasource.getUrl());
            dataSourceProperty.setUsername(genDatasource.getUsername());
            dataSourceProperty.setPassword(genDatasource.getPassword());
            dataSourceProperty.setDriverClassName(genDatasource.getDriverClassName());
            DruidConfig druidConfig = new DruidConfig();
            druidConfig.setValidationQuery(genDatasource.getValidationQuery());
            dataSourceProperty.setDruid(druidConfig);
            DataSource dataSource = druidDataSourceCreator.createDataSource(dataSourceProperty);
            ds.addDataSource(dataSourceProperty.getPoolName(), dataSource);
        }
        return ds;
    }


    @Override
    public void removeDataSource(String poolname) {
        ds.removeDataSource(poolname);
    }
}
