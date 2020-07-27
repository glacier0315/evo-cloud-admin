package com.glacier.modules.sys.entity.form;

import lombok.Data;
import lombok.ToString;

import java.io.Serializable;

/**
 * @author glacier
 * @version 1.0
 * @date 2020-07-03 21:51
 */
@Data
@ToString
public class RoleQueryForm implements Serializable {
    private static final long serialVersionUID = 4596028730042166514L;
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
}
