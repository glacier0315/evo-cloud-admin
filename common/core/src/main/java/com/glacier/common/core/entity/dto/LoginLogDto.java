package com.glacier.common.core.entity.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.ToString;

import java.io.Serializable;
import java.time.LocalDate;

/**
 * 登录日志模型
 * @author glacier
 * @version 1.0
 * @date 2020-09-03 21:59
 */
@ApiModel(description = "登录日志模型")
@Data
@ToString
public class LoginLogDto implements Serializable {
    private static final long serialVersionUID = -527241343480166982L;
    /**
     * 用户名
     */
    @ApiModelProperty(value = "用户名")
    private String username;

    /**
     * ip地址
     */
    @ApiModelProperty(value = "ip地址")
    private String ipAddr;

    /**
     * 登录状态 1 登录成功 2 登陆失败
     */
    @ApiModelProperty(value = "登录状态 1 登录成功 2 登陆失败")
    private Integer status;

    /**
     * 登录客户端
     */
    @ApiModelProperty(value = "登录客户端")
    private String userAgent;

    /**
     * 浏览器
     */
    @ApiModelProperty(value = "浏览器")
    private String browser;

    /**
     * 操作系统
     */
    @ApiModelProperty(value = "操作系统")
    private String os;

    /**
     * 登陆时间
     */
    @ApiModelProperty(value = "登陆时间")
    private LocalDate loginTime;
}
