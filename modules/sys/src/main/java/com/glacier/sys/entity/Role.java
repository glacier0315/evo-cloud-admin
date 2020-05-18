package com.glacier.sys.entity;

import lombok.*;

import java.io.Serializable;
import java.util.Date;

/**
 * 角色
 *
 * @author glacier
 * @version 1.0
 * @date 2019-08-04 13:45
 */
@Data
@ToString
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Role implements Serializable {
    private static final long serialVersionUID = -3318599726827564559L;
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
     * 创建人
     */
    private String createBy;
    /**
     * 创建时间
     */
    private Date createDate;
    /**
     * 更新人
     */
    private String updateBy;
    /**
     * 更新时间
     */
    private Date updateDate;
    /**
     * 删除标记
     */
    private String delFlag;
}