package com.glacier.sys.common;

/**
 * 常量表
 *
 * @author glacier
 * @version 1.0
 * @date 2019-08-04 13:52
 */
public interface Constant {
    /**
     * 系统管理员用户名
     */
    public static final String ADMIN = "admin";
    public static final String ADMIN_ID = "1";
    /**
     * 性别 1 男  2 女 3 未知
     */
    public static final int SEX_MAN = 1;
    public static final int SEX_WO_MAN = 2;
    public static final int SEX_UNKNOW = 3;

    /**
     * 目录
     */
    public static final int MENU_DICTORY = 1;
    /**
     * 端点
     */
    public static final int MENU_ENDPOINT = 2;
    /**
     * 权限
     */
    public static final int MENU_PERMISSION = 3;
}
