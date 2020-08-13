package com.glacier.common.core.constant;

/**
 * 通用常量
 *
 * @author glacier
 * @version 1.0
 * @date 2020-07-23 17:08
 */
public interface CommonConstant {
    /**
     * http 响应类型
     */
    String MEDIA_TYPE = "application/json;charset=utf-8";
    /**
     * 编码
     */
    String CHARSET_UTF_8 = "UTF-8";


    /**
     * 数据权限 1 所有单位
     */
    String DATASCOPE_ALL_DEPT = "1";
    /**
     * 数据权限 2 所属一级单位及以下
     */
    String DATASCOPE_LEVEL_ONE_DEPT = "2";
    /**
     * 数据权限 3 所属二级单位及以下
     */
    String DATASCOPE_LEVEL_TWO_DEPT = "3";
    /**
     * 数据权限 4 所属单位部门及以下
     */
    String DATASCOPE_DEPARTMENT = "4";
    /**
     * 数据权限 5 自定义
     */
    String DATASCOPE_CUSTOMIZE_DEPT = "5";
    /**
     * 数据权限 6 自己
     */
    String DATASCOPE_JUST_MYSELF = "6";
}
