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
 * date 2019-08-11 21:20
 */
public interface RoleService {

    /**
     * 根据id 查询角色
     *
     * @param id 主键
     * @return 角色
     */
    RoleVo findById(String id);

    /**
     * 分页查询
     *
     * @param pageRequest 分页请求
     * @return 分页响应
     */
    PageResponse<RoleVo> findPage(PageRequest<RoleQuery> pageRequest);

    /**
     * 根绝用户id 查询用户所拥有的角色
     *
     * @param userId 用户id
     * @return 角色集合
     */
    List<RoleVo> findByUserId(String userId);

    /**
     * 查询所有
     *
     * @return 所有角色
     */
    List<RoleVo> findAllList();

    /**
     * 校验
     *
     * @param role 角色
     * @return 校验结果
     */
    boolean checkCode(RoleForm role);

    /**
     * 保存操作
     *
     * @param roleForm 记录实体
     * @return 保存记录数
     */
    int save(RoleForm roleForm);

    /**
     * 根据Id删除
     *
     * @param id 主键
     * @return 删除记录数
     */
    int delete(String id);

}
