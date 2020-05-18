package com.glacier.auth.service;

import java.util.List;

/**
 * @author glacier
 * @version 1.0
 * @date 2020-05-21 17:06
 */
public interface RoleService {
    /**
     * 根绝用户id 查询用户所拥有的角色
     *
     * @param userId
     * @return
     */
    List<String> findCodeByUserId(String userId);
}
