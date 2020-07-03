package com.glacier.sys.entity.form;

import lombok.Data;
import lombok.ToString;

import java.io.Serializable;

/**
 * @author glacier
 * @version 1.0
 * @date 2020-07-03 21:52
 */
@Data
@ToString
public class RoleForm implements Serializable {
    private static final long serialVersionUID = -1193819369875966276L;
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
     * 菜单ID 集合
     */
    private String[] menus;
}
