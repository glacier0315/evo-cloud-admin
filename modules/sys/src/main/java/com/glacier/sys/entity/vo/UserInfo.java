package com.glacier.sys.entity.vo;

import lombok.*;

import java.io.Serializable;
import java.util.List;

/**
 * 查询用户信息
 *
 * @author glacier
 * @version 1.0
 * @date 2020-05-22 16:56
 */
@Data
@ToString
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class UserInfo implements Serializable {
    private static final long serialVersionUID = 2496540352121367958L;

    /**
     * 用户id
     */
    private String id;
    /**
     * 用户名
     */
    private String name;
    /**
     * 头像地址
     */
    private String avatar;
    /**
     *
     */
    private String introduction;
    /**
     * 权限
     */
    private List<String> roles;
}
