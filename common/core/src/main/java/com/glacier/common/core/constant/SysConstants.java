package com.glacier.common.core.constant;

/**
 * 系统常量
 *
 * @author glacier
 * @version 1.0
 * date 2020-08-14 10:37
 */
public interface SysConstants {

    /**
     * 平台内系统用户的唯一标志
     */
    String SYS_USER = "admin";
    String SYS_USER_ID = "1";

    /**
     * 默认密码
     */
    String DEFAULT_PASSWD = "12345678";

    /* 性别 start */
    /**
     * 性别 男性
     */
    int SEX_MAN = 1;
    /**
     * 性别 女性
     */
    int SEX_FEMALE = 2;
    /**
     * 性别 其他
     */
    int SEX_OTHER = 3;
    /**
     * 性别 保密
     */
    int SEX_SECURITY = 4;
    /* 性别 end */


    /* 状态 start */
    /**
     * 状态 正常
     */
    int STATUS_ENABLED = 1;
    /**
     * 状态 禁用
     */
    int STATUS_DISABLED = 2;
    /* 状态 end */


    /* 菜单 start */
    /**
     * Layout组件标识
     */
    String LAYOUT = "Layout";
    /**
     * 无跳转
     */
    String NO_REDIRECT = "noRedirect";
    /**
     * 菜单类型（目录）
     */
    int TYPE_DIR = 1;
    /**
     * 菜单类型（菜单）
     */
    int TYPE_MENU = 2;
    /**
     * 菜单类型（权限）
     */
    int TYPE_BUTTON = 3;
    /* 菜单 end */


    /* 数据权限 start */
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
    /* 数据权限 end   */


}
