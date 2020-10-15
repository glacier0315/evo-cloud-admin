package com.glacier.modules.sys.service;

import com.glacier.modules.sys.entity.dto.dept.DeptForm;
import com.glacier.modules.sys.entity.dto.dept.DeptVo;

import java.util.List;

/**
 * @author glacier
 * @version 1.0
 * date 2019-10-24 17:07
 */
public interface DeptService {

    /**
     * 查找所有 组织机构
     *
     * @return 组织机构
     */
    List<DeptVo> findAllList();

    /**
     * 根据用户ID查找所有 组织机构
     *
     * @param userId 用户ID
     * @return 组织机构
     */
    List<DeptVo> findListByUserId(String userId);

    /**
     * 根据用户id 查找单位树
     *
     * @param userId 用户id
     * @return 单位树
     */
    List<DeptVo> findTree(String userId);

    /**
     * 保存操作
     *
     * @param deptForm 保存实体类
     * @return 插入或者更新记录数
     */
    int save(DeptForm deptForm);

    /**
     * 根据Id删除
     *
     * @param id 待删除id
     * @return 删除记录数
     */
    int delete(String id);

    /**
     * 根据角色id 查询所具有的单位
     *
     * @param roleId 角色id
     * @return 角色具有的单位id集合
     */
    List<String> findByRole(String roleId);
}
