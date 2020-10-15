package com.glacier.modules.sys.mapper;

import com.glacier.modules.sys.entity.RoleDept;

/**
 * 角色单位关联关系 数据层
 *
 * @author glacier
 * @version 1.0
 * date 2019-12-18 15:29
 */
public interface RoleDeptMapper {
    /**
     * 删除 角色单位关联关系
     *
     * @param deptId 单位id
     * @return 删除行数
     */
    int deleteByDeptId(String deptId);

    /**
     * 删除 角色单位关联关系
     *
     * @param roleId 角色id
     * @return 删除行数
     */
    int deleteByRoleId(String roleId);

    /**
     * 保存角色单位关联关系
     *
     * @param record 角色单位关联关系记录
     * @return 插入行数
     */
    int insert(RoleDept record);
}
