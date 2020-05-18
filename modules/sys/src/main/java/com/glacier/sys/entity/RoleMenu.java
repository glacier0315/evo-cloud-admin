package com.glacier.sys.entity;

import com.baomidou.mybatisplus.annotation.TableId;
import lombok.*;

import java.io.Serializable;

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
@Builder
public class RoleMenu implements Serializable {

    private static final long serialVersionUID = -234428421997899712L;
    /**
     * id
     */
    @TableId
    private String id;
    /**
     * 角色id
     */
    private String roleId;
    /**
     * 菜单id
     */
    private String menuId;
}
