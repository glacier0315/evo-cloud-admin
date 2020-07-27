package com.glacier.authorization.resource.entity.pojo;

import com.glacier.common.core.entity.pojo.BasePojo;
import lombok.*;

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
@EqualsAndHashCode(callSuper = false)
@Builder
public class UserRole extends BasePojo {

    private static final long serialVersionUID = -7193974752669679122L;
    /**
     * 用户id
     */
    private String userId;
    /**
     * 角色id
     */
    private String roleId;
}
