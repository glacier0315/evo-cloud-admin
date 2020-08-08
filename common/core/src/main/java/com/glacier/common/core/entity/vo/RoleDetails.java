package com.glacier.common.core.entity.vo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.io.Serializable;

/**
 * @author glacier
 * @version 1.0
 * @date 2020-08-08 11:07
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class RoleDetails implements Serializable {
    private static final long serialVersionUID = 2730122991817147481L;
    /**
     * 主键
     */
    private String id;
    /**
     * 角色名称
     */
    private String name;
    /**
     * 角色编码
     */
    private String code;
    /**
     * 状态 1 正常  2 禁用
     */
    private String status;
    /**
     * 描述
     */
    private String description;
    /**
     * 数据权限
     */
    private String dataScope;
}
