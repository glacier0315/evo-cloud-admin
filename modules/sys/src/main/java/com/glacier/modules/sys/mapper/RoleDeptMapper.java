package com.glacier.modules.sys.mapper;

import com.glacier.modules.sys.entity.pojo.RoleDept;

import java.util.List;

/**
 * 角色单位关联关系 数据层
 *
 * @author glacier
 * @version 1.0
 * @date 2019-12-18 15:29
 */
public interface RoleDeptMapper {
    /**
     * 删除 角色单位关联关系
     *
     * @param deptId 单位id
     * @return
     */
    int deleteByDeptId(String deptId);

    /**
     * 批量删除
     *
     * @param list 待删除deptId集合
     * @return 删除记录数
     */
    int deleteByDeptIds(List<String> list);

    /**
     * 删除 角色单位关联关系
     *
     * @param roleId 角色id
     * @return
     */
    int deleteByRoleId(String roleId);

    /**
     * 保存角色单位关联关系
     *
     * @param record
     * @return
     */
    int insert(RoleDept record);
}
