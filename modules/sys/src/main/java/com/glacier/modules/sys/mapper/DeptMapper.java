package com.glacier.modules.sys.mapper;

import com.glacier.common.core.mapper.BaseMapper;
import com.glacier.modules.sys.entity.Dept;

import java.util.List;


/**
 * 组织机构 数据层
 *
 * @author glacier
 * @version 1.0
 * @date 2019-08-04 21:53
 */
public interface DeptMapper extends BaseMapper<Dept, String> {
    /**
     * 根据用户id 查询组织机构
     *
     * @param userId 用户id
     * @return 组织机构
     */
    List<Dept> findByUserId(String userId);

    /**
     * 根据角色id  查询组织机构
     *
     * @param roleId 角色id
     * @return 组织机构
     */
    List<Dept> findByRoleId(String roleId);
}
