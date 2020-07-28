package com.glacier.modules.sys.common;

/**
 * 常量表
 *
 * @author glacier
 * @version 1.0
 * @date 2019-08-04 13:52
 */
public interface Constant {
    /**
     * 默认密码
     */
    String DEFAULT_PASSWD = "12345678";
    /**
     * 系统管理员用户名
     */
    String ADMIN = "admin";
    String ADMIN_ID = "1";

    /**
     * 用户状态
     */
    String USER_ENABLED = "1";
    String USER_DISABLED = "2";
    /**
     * 性别 1 男  2 女 3 未知
     */
    int SEX_MAN = 1;
    int SEX_WO_MAN = 2;
    int SEX_UNKNOW = 3;

    /**
     * 目录
     */
    int MENU_DICTORY = 1;
    /**
     * 端点
     */
    int MENU_ENDPOINT = 2;
    /**
     * 权限
     */
    int MENU_PERMISSION = 3;
}
