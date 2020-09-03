package com.glacier.modules.sys.entity;

import lombok.Data;
import lombok.ToString;

import java.io.Serializable;
import java.time.LocalDate;

/**
 * 登录日志
 *
 * @author glacier
 * @version 1.0
 * @date 2019-10-09 11:03
 */
@Data
@ToString
public class LoginLog implements Serializable {
    private static final long serialVersionUID = -6057481206987109088L;
    /**
     * 主键
     */
    private String id;

    /**
     * 用户名
     */
    private String username;

    /**
     * ip地址
     */
    private String ipAddr;
    /**
     * 登录状态 1 登录成功 2 登陆失败
     */
    private Integer status;

    /**
     * 登录客户端
     */
    private String userAgent;

    /**
     * 浏览器
     */
    private String browser;

    /**
     * 操作系统
     */
    private String os;

    /**
     * 登陆地址
     */
    private LocalDate loginTime;
}
