package com.glacier.modules.sys.service;

import com.glacier.common.core.entity.page.PageRequest;
import com.glacier.common.core.entity.page.PageResponse;
import com.glacier.modules.sys.entity.dto.role.RoleForm;
import com.glacier.modules.sys.entity.dto.role.RoleQuery;
import com.glacier.modules.sys.entity.dto.role.RoleVo;

import java.util.List;

/**
 * 角色业务层
 *
 * @author glacier
 * @version 1.0
 * @date 2019-08-11 21:20
 */
public interface RoleService {

    /**
     * 根据id 查询角色
     *
     * @param id
     * @return
     */
    RoleVo findById(String id);

    /**
     * 分页查询
     *
     * @param pageRequest
     * @return
     */
    PageResponse<RoleVo> findPage(PageRequest<RoleQuery> pageRequest);

    /**
     * 根绝用户id 查询用户所拥有的角色
     *
     * @param userId
     * @return
     */
    List<RoleVo> findByUserId(String userId);

    /**
     * 查询所有
     *
     * @return
     */
    List<RoleVo> findAllList();

    /**
     * 校验
     *
     * @param role
     * @return
     */
    boolean checkCode(RoleForm role);

    /**
     * 保存操作
     *
     * @param roleForm
     * @return
     */
    int save(RoleForm roleForm);

    /**
     * 根据Id删除
     *
     * @param id
     * @return
     */
    int delete(String id);

}
