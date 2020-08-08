package com.glacier.modules.sys.entity.vo.user;

import lombok.*;

import java.io.Serializable;

/**
 * 查询用户信息
 *
 * @author glacier
 * @version 1.0
 * @date 2020-07-23 10:31
 */
@Data
@ToString
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class UserProfile implements Serializable {

    private static final long serialVersionUID = -2286892393843097739L;
    /**
     * 用户id
     */
    private String id;
    /**
     * 用户名
     */
    private String username;
    /**
     * 昵称
     */
    private String nickname;
    /**
     * 手机号
     */
    private String mobile;
    /**
     * 邮箱
     */
    private String email;
    /**
     * 性别
     */
    private Integer sex;
    /**
     * 单位id
     */
    private String deptId;
    /**
     * 单位名称
     */
    private String deptName;
    /**
     * 头像地址
     */
    private String avatar;
    /**
     * 简介
     */
    private String introduction;
}
