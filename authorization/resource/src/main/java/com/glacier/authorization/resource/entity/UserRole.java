package com.glacier.authorization.resource.entity;

import lombok.*;

import java.io.Serializable;

/**
 * 用户角色关系
 *
 * @author glacier
 * @version 1.0
 * @date 2019-08-11 21:18
 */
@Data
@ToString
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class UserRole implements Serializable {
    private static final long serialVersionUID = 8523322889225203563L;
    /**
     * 主键
     */
    private String id;
    /**
     * 用户id
     */
    private String userId;
    /**
     * 角色id
     */
    private String roleId;
}
