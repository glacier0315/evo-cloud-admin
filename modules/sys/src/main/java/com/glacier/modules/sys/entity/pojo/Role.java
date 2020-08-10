package com.glacier.modules.sys.entity.pojo;

import com.glacier.common.core.entity.pojo.AbstractDataPojo;
import lombok.*;

/**
 * 角色
 *
 * @author glacier
 * @version 1.0
 * @date 2019-08-04 13:45
 */
@Data
@ToString(callSuper = true)
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(callSuper = false)
@Builder
public class Role extends AbstractDataPojo {
    private static final long serialVersionUID = -3318599726827564559L;
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
