package com.glacier.modules.gen.test;

import com.baomidou.dynamic.datasource.DynamicRoutingDataSource;
import com.baomidou.dynamic.datasource.creator.DruidDataSourceCreator;
import com.baomidou.dynamic.datasource.spring.boot.autoconfigure.DataSourceProperty;
import com.baomidou.dynamic.datasource.spring.boot.autoconfigure.druid.DruidConfig;
import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.DatabaseMetaData;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * @author glacier
 * @version 1.0
 * date 2021-01-28 14:47
 */
@SpringBootTest
public class DynamicDataSourceTest {
    private static final Logger log = LoggerFactory.getLogger(DynamicDataSourceTest.class);
    @Autowired
    private DynamicRoutingDataSource ds;
    @Autowired
    private DruidDataSourceCreator druidDataSourceCreator;
    @Test
    void test01() {

        log.info("数据源: {}", ds);
        log.info("数据源: {}", ds.getCurrentDataSources().keySet());
        log.info("druidDataSourceCreator: {}", druidDataSourceCreator);
    }

    @Test
    void test02() throws SQLException {
        String poolName = "eboot_sys";
        String driver = "com.mysql.cj.jdbc.Driver";
        String url = "jdbc:mysql://192.168.10.130:3306/eboot_sys?useUnicode=true&characterEncoding=utf-8&serverTimezone=UTC";
        String username = "eboot_sys";
        String password = "eboot_sys";


        DataSourceProperty dataSourceProperty = new DataSourceProperty();
        dataSourceProperty.setPoolName(poolName);
        dataSourceProperty.setUrl(url);
        dataSourceProperty.setUsername(username);
        dataSourceProperty.setPassword(password);
        dataSourceProperty.setDriverClassName(driver);
        DataSource dataSource = druidDataSourceCreator.createDataSource(dataSourceProperty);
        ds.addDataSource(dataSourceProperty.getPoolName(), dataSource);
        log.info("数据源: {}", ds.getCurrentDataSources().keySet());

        // 获取连接
        Connection connection = ds.getDataSource(poolName).getConnection();
        log.info("connection: {}", connection);
        //获取元数据
        DatabaseMetaData metaData = connection.getMetaData();
        log.info("metaData: {}", metaData);
        //获取数据库中表信息（mysql可以这样写，oracle会有一点区别）
        //参数1：当前操作的数据库 参数2：mysql可为空，oracle填写用户名（要大写） 参数3：null是查询所有表 非空是查询目标表 参数4：类型 table是表，view是视图
        ResultSet resultSet = metaData.getTables(username, username.toUpperCase(), null, new String[]{"TABLE"});
        while (resultSet.next()) {
            //会打印出该数据库下的所有表名
            log.info("TABLE_NAME: {}", resultSet.getString("TABLE_NAME"));
        }
        resultSet.close();
        connection.close();
    }

    @Test
    void test03() throws SQLException {
        String poolName = "gaedu";
        String driver = "oracle.jdbc.driver.OracleDriver";
        String url = "jdbc:oracle:thin:@192.168.10.128:1521:XE?characterEncoding=utf8&useSSL=false";
        String username = "gaedu";
        String password = "gaedu";
        String validationQuery = "select 1 from dual";

        DataSourceProperty dataSourceProperty = new DataSourceProperty();
        dataSourceProperty.setPoolName(poolName);
        dataSourceProperty.setUrl(url);
        dataSourceProperty.setUsername(username);
        dataSourceProperty.setPassword(password);
        dataSourceProperty.setDriverClassName(driver);
        // oracle 需要重写 连接测试
        DruidConfig druidConfig = new DruidConfig();
        druidConfig.setValidationQuery(validationQuery);
        dataSourceProperty.setDruid(druidConfig);
        DataSource dataSource = druidDataSourceCreator.createDataSource(dataSourceProperty);
        ds.addDataSource(dataSourceProperty.getPoolName(), dataSource);
        log.info("数据源: {}", ds.getCurrentDataSources().keySet());

        // 获取连接
        Connection connection = ds.getDataSource(poolName).getConnection();
        log.info("connection: {}", connection);
        //获取元数据
        DatabaseMetaData metaData = connection.getMetaData();
        log.info("metaData: {}", metaData);
        //获取数据库中表信息（mysql可以这样写，oracle会有一点区别）
        //参数1：当前操作的数据库 参数2：mysql可为空，oracle填写用户名（要大写） 参数3：null是查询所有表 非空是查询目标表 参数4：类型 table是表，view是视图
        ResultSet resultSet = metaData.getTables(username, username.toUpperCase(), null, new String[]{"TABLE"});
        while (resultSet.next()) {
            //会打印出该数据库下的所有表名
            log.info("TABLE_NAME: {}", resultSet.getString("TABLE_NAME"));
        }
        resultSet.close();
        connection.close();
    }

    @Test
    void test04() throws SQLException {
        String poolName = "eboot";
        String driver = "com.mysql.cj.jdbc.Driver";
        String url = "jdbc:mysql://192.168.10.130:3306/eboot_sys?useUnicode=true&characterEncoding=utf-8&serverTimezone=UTC";
        String username = "eboot_sys";
        String password = "eboot_sys";


        DataSourceProperty dataSourceProperty = new DataSourceProperty();
        dataSourceProperty.setPoolName(poolName);
        dataSourceProperty.setUrl(url);
        dataSourceProperty.setUsername(username);
        dataSourceProperty.setPassword(password);
        dataSourceProperty.setDriverClassName(driver);
        DataSource dataSource = druidDataSourceCreator.createDataSource(dataSourceProperty);
        ds.addDataSource(poolName, dataSource);
        log.info("数据源: {}", ds.getCurrentDataSources().keySet());
        log.info("数据源: {}", ds.getDataSource(poolName));
        log.info("数据源: {}", ds.getDataSource(poolName).getConnection().getMetaData());

        driver = "oracle.jdbc.driver.OracleDriver";
        url = "jdbc:oracle:thin:@192.168.10.128:1521:XE?characterEncoding=utf8&useSSL=false";
        username = "gaedu";
        password = "gaedu";


        dataSourceProperty = new DataSourceProperty();
        dataSourceProperty.setPoolName(poolName);
        dataSourceProperty.setUrl(url);
        dataSourceProperty.setUsername(username);
        dataSourceProperty.setPassword(password);
        dataSourceProperty.setDriverClassName(driver);
        // oracle 需要重写 连接测试
        DruidConfig druidConfig = new DruidConfig();
        druidConfig.setValidationQuery("select 1 from dual");
        dataSourceProperty.setDruid(druidConfig);
        dataSource = druidDataSourceCreator.createDataSource(dataSourceProperty);
        ds.removeDataSource(poolName);
        ds.addDataSource(poolName, dataSource);
        log.info("数据源: {}", ds.getCurrentDataSources().keySet());
        log.info("数据源: {}", ds.getDataSource(poolName));
        log.info("数据源: {}", ds.getDataSource(poolName).getConnection().getMetaData());
    }
}
