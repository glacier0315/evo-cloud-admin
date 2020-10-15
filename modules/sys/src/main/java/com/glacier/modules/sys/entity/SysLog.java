package com.glacier.modules.sys.entity;

import java.io.Serializable;
import java.util.Date;

/**
 * @author glacier
 * @version 1.0
 * @date 2019-12-18 15:29
 */
public class SysLog implements Serializable {
    private static final long serialVersionUID = 2402303891367907111L;
    /**
     * 主键
     */
    private String id;
    /**
     * 登录id
     */
    private String userId;
    /**
     * url
     */
    private String url;
    /**
     * 登录ip
     */
    private String ip;
    /**
     * 请求方法
     */
    private String method;
    /**
     * 请求参数
     */
    private String params;
    /**
     * 登录客户端
     */
    private String userAgent;
    /**
     * 耗时
     */
    private Long useTime;
    /**
     * 创建时间
     */
    private Date createDate;
    /**
     * 登录用户名
     */
    private String username;

    public static long getSerialVersionUID() {
        return serialVersionUID;
    }

    public String getId() {
        return this.id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getUserId() {
        return this.userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getUrl() {
        return this.url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getIp() {
        return this.ip;
    }

    public void setIp(String ip) {
        this.ip = ip;
    }

    public String getMethod() {
        return this.method;
    }

    public void setMethod(String method) {
        this.method = method;
    }

    public String getParams() {
        return this.params;
    }

    public void setParams(String params) {
        this.params = params;
    }

    public String getUserAgent() {
        return this.userAgent;
    }

    public void setUserAgent(String userAgent) {
        this.userAgent = userAgent;
    }

    public Long getUseTime() {
        return this.useTime;
    }

    public void setUseTime(Long useTime) {
        this.useTime = useTime;
    }

    public Date getCreateDate() {
        return this.createDate;
    }

    public void setCreateDate(Date createDate) {
        this.createDate = createDate;
    }

    public String getUsername() {
        return this.username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    @Override
    public String toString() {
        return "SysLog{" +
                "id='" + this.id + '\'' +
                ", userId='" + this.userId + '\'' +
                ", url='" + this.url + '\'' +
                ", ip='" + this.ip + '\'' +
                ", method='" + this.method + '\'' +
                ", params='" + this.params + '\'' +
                ", userAgent='" + this.userAgent + '\'' +
                ", useTime=" + this.useTime +
                ", createDate=" + this.createDate +
                ", username='" + this.username + '\'' +
                '}';
    }
}
