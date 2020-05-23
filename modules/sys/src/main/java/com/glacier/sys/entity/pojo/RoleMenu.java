package com.glacier.sys.entity.pojo;

import com.glacier.common.core.entity.pojo.BasePojo;
import lombok.*;

/**
 * 角色菜单关联表
 *
 * @author glacier
 * @version 1.0
 * @date 2019-10-09 14:49
 */
@Data
@ToString
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(callSuper=false)
@Builder
public class RoleMenu extends BasePojo {

    private static final long serialVersionUID = -234428421997899712L;
    /**
     * 角色id
     */
    private String roleId;
    /**
     * 菜单id
     */
    private String menuId;
}
