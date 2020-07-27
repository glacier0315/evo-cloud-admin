package com.glacier.authorization.resource.entity.pojo;

import com.glacier.common.core.entity.pojo.BasePojo;
import lombok.*;

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
@EqualsAndHashCode(callSuper = false)
@Builder
public class Role extends BasePojo {
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
     * 删除标记
     */
    private String delFlag;
}
