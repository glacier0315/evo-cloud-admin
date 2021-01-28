package com.glacier.modules.gen.utils;

import com.baomidou.dynamic.datasource.DynamicRoutingDataSource;
import com.glacier.modules.gen.entity.GenDatasource;
import com.glacier.modules.gen.service.DynamicDataSourceManager;
import com.glacier.modules.gen.service.GenDatasourceService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.sql.Connection;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * @author glacier
 * @version 1.0
 * @date 2020-12-09 16:58
 */
@Component
public class GenUtils {
    private static final Logger LOGGER = LoggerFactory.getLogger(GenUtils.class);
    public static final String TABLE = "TABLE";
    public static final String TABLE_NAME = "TABLE_NAME";
    private final GenDatasourceService genDatasourceService;
    private final DynamicDataSourceManager dynamicDataSourceManager;

    @Autowired
    public GenUtils(
            GenDatasourceService genDatasourceService,
            DynamicDataSourceManager dynamicDataSourceManager) {
        this.genDatasourceService = genDatasourceService;
        this.dynamicDataSourceManager = dynamicDataSourceManager;
    }


    /**
     * 获取所有表名
     *
     * @param dataSourceId
     * @return
     */
    public List<String> queryTables(String dataSourceId) {
        List<String> tableNames = null;
        GenDatasource genDatasource = genDatasourceService.findDatasourceById(dataSourceId);
        if (genDatasource == null) {
            return Collections.emptyList();
        }
        // 添加数据源
        DynamicRoutingDataSource dynamicRoutingDataSource = dynamicDataSourceManager.addDataSource(genDatasource);
        try (
                Connection connection = dynamicRoutingDataSource.getDataSource(genDatasource.getId())
                        .getConnection();
                ResultSet resultSet = connection.getMetaData()
                        .getTables(genDatasource.getUsername(),
                                genDatasource.getUsername().toUpperCase(),
                                null,
                                new String[]{TABLE});
        ) {
            tableNames = new ArrayList<>(resultSet.getRow());
            if (resultSet.next()) {
                tableNames.add(resultSet.getString(TABLE_NAME));
            }
        } catch (Exception e) {
            LOGGER.error("连接出现异常!", e);
        }
        return tableNames;
    }
}
