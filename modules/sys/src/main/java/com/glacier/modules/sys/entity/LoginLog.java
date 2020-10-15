package com.glacier.modules.sys.entity;

import java.io.Serializable;
import java.time.LocalDate;

/**
 * 登录日志
 *
 * @author glacier
 * @version 1.0
 * date 2019-10-09 11:03
 */
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

    public static long getSerialVersionUID() {
        return serialVersionUID;
    }

    public String getId() {
        return this.id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getUsername() {
        return this.username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getIpAddr() {
        return this.ipAddr;
    }

    public void setIpAddr(String ipAddr) {
        this.ipAddr = ipAddr;
    }

    public Integer getStatus() {
        return this.status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public String getUserAgent() {
        return this.userAgent;
    }

    public void setUserAgent(String userAgent) {
        this.userAgent = userAgent;
    }

    public String getBrowser() {
        return this.browser;
    }

    public void setBrowser(String browser) {
        this.browser = browser;
    }

    public String getOs() {
        return this.os;
    }

    public void setOs(String os) {
        this.os = os;
    }

    public LocalDate getLoginTime() {
        return this.loginTime;
    }

    public void setLoginTime(LocalDate loginTime) {
        this.loginTime = loginTime;
    }

    @Override
    public String toString() {
        return "LoginLog{" +
                "id='" + this.id + '\'' +
                ", username='" + this.username + '\'' +
                ", ipAddr='" + this.ipAddr + '\'' +
                ", status=" + this.status +
                ", userAgent='" + this.userAgent + '\'' +
                ", browser='" + this.browser + '\'' +
                ", os='" + this.os + '\'' +
                ", loginTime=" + this.loginTime +
                '}';
    }
}
