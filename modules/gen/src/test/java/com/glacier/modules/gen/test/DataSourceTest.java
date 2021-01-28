package com.glacier.modules.gen.test;

import org.junit.jupiter.api.Test;

import java.sql.*;

/**
 * @author glacier
 * @version 1.0
 * @date 2021-01-28 11:02
 */
public class DataSourceTest {

    @Test
    public void test01() throws Exception {
        String driver = "com.mysql.cj.jdbc.Driver";
        String url = "jdbc:mysql://192.168.10.130:3306?useUnicode=true&characterEncoding=utf-8&serverTimezone=UTC";//没有指定具体哪个数据库，现在获取的是整个连接
        String username = "root";
        String password = "root";

        //获取连接
        Class.forName(driver);//注册驱动
        Connection connection = DriverManager.getConnection(url, username, password);

        //获取元数据
        DatabaseMetaData metaData = connection.getMetaData();
        //获取数据库基本信息
        System.out.println(metaData.getUserName());
        System.out.println(metaData.supportsTransactions());//是否支持事务
        System.out.println(metaData.getDatabaseProductName());//数据库类型（MYSQL）
        connection.close();

        /**
         * 打印结果如下
         * root@localhost
         * true
         * MySQL
         */
    }

    @Test
    public void test2() throws Exception {
        String driver = "com.mysql.cj.jdbc.Driver";
        String url = "jdbc:mysql://192.168.10.130:3306?useUnicode=true&characterEncoding=utf-8&serverTimezone=UTC";//没有指定具体哪个数据库，现在获取的是整个连接
        String username = "root";
        String password = "root";

        //获取连接
        Class.forName(driver);//注册驱动
        Connection connection = DriverManager.getConnection(url, username, password);

        //获取元数据
        DatabaseMetaData metaData = connection.getMetaData();
        //获取数据库列表名称
        ResultSet resultSet = metaData.getCatalogs();
        while (resultSet.next()) {
            System.out.println(resultSet.getString(1));
        }
        resultSet.close();
        connection.close();
        /**
         * 打印连接中的所有数据库名称
         * activiti
         * dage
         * dk
         * dk1
         * dk_front
         * dk_front1
         * information_schema
         * laji
         * light_master
         * mysql
         * performance_schema
         * sakila
         * solr
         * sys
         * test
         * workflow
         * world
         */
    }


    @Test
    public void test3() throws Exception {
        String driver = "com.mysql.cj.jdbc.Driver";
        String url = "jdbc:mysql://192.168.10.130:3306?useUnicode=true&characterEncoding=utf-8&serverTimezone=UTC";//没有指定具体哪个数据库，现在获取的是整个连接
        String username = "root";
        String password = "root";

        //获取连接
        Class.forName(driver);//注册驱动
        Connection connection = DriverManager.getConnection(url, username, password);

        //获取元数据
        DatabaseMetaData metaData = connection.getMetaData();
        //获取数据库中表信息（mysql可以这样写，oracle会有一点区别）
        //参数1：当前操作的数据库 参数2：mysql可为空，oracle填写用户名（要大写） 参数3：null是查询所有表 非空是查询目标表 参数4：类型 table是表，view是视图
        ResultSet resultSet = metaData.getTables("eboot_sys", null, null, new String[]{"TABLE"});
        while (resultSet.next()) {
            //会打印出该数据库下的所有表名
            System.out.println(resultSet.getString("TABLE_NAME"));
        }
        resultSet.close();
        connection.close();
    }


    /**
     * 测试参数元数据
     * 通过preparedStatement获取
     * 目的：获取sql参数中的属性信息
     */
    @Test
    public void test4() throws Exception {
        String driver = "com.mysql.cj.jdbc.Driver";
        String url = "jdbc:mysql://192.168.10.130:3306/eboot_sys?useUnicode=true&characterEncoding=utf-8&serverTimezone=UTC";//没有指定具体哪个数据库，现在获取的是整个连接
        String username = "root";
        String password = "root";

        //获取连接
        Class.forName(driver);//注册驱动
        Connection connection = DriverManager.getConnection(url, username, password);

        String sql = "select * from sys_user where id=?";
        PreparedStatement preparedStatement = connection.prepareStatement(sql);
        preparedStatement.setInt(1, 1);//设第一个参数为int 1
        //获取参数元数据
        ParameterMetaData metaData = preparedStatement.getParameterMetaData();
        //得到参数的个数
        int count = metaData.getParameterCount();//打印 1 （只有一个id参数）

        System.out.println(count);
        preparedStatement.close();
        connection.close();
    }

    @Test
    public void test5() throws Exception {
        String driver = "com.mysql.cj.jdbc.Driver";
        String url = "jdbc:mysql://192.168.10.130:3306/eboot_sys?useUnicode=true&characterEncoding=utf-8&serverTimezone=UTC";//没有指定具体哪个数据库，现在获取的是整个连接
        String username = "root";
        String password = "root";

        //获取连接
        Class.forName(driver);//注册驱动
        Connection connection = DriverManager.getConnection(url, username, password);

        String sql = "select * from sys_user where id=?";
        PreparedStatement preparedStatement = connection.prepareStatement(sql);
        preparedStatement.setInt(1, 1);//设第一个参数为int 1
        //查询
        ResultSet resultSet = preparedStatement.executeQuery();

        //获取结果集元数据
        ResultSetMetaData metaData = resultSet.getMetaData();
        //获取查询字段个数
        int count = metaData.getColumnCount();
        for (int i = 1; i <= count; i++) {
            //获取列名
            String columnName = metaData.getColumnName(i);//第i个列
            //获取字段类型  sql类型 varchar
            int columnType = metaData.getColumnType(i);
            //获取java类型  String
            String columnClassName = metaData.getColumnClassName(i);
            System.out.println(columnName + "---" + columnType + "---" + columnClassName);
        }

        preparedStatement.close();
        connection.close();
    }
}
