package com.glacier.modules.sys.service;

import com.glacier.common.core.entity.dto.IdDto;
import com.glacier.modules.sys.entity.dto.dept.DeptForm;
import com.glacier.modules.sys.entity.dto.dept.DeptVo;

import java.util.List;

/**
 * @author glacier
 * @version 1.0
 * @date 2019-10-24 17:07
 */
public interface DeptService {

    /**
     * 查找所有 组织机构
     *
     * @return
     */
    List<DeptVo> findAllList();

    /**
     * 根据用户ID查找所有 组织机构
     *
     * @param userId
     * @return
     */
    List<DeptVo> findListByUserId(String userId);

    /**
     * 根据用户id 查找单位树
     *
     * @param userId
     * @return
     */
    List<DeptVo> findTree(String userId);

    /**
     * 保存操作
     *
     * @param deptForm
     * @return
     */
    int save(DeptForm deptForm);

    /**
     * 根据Id批量删除
     *
     * @param idDtos
     * @return
     */
    int batchDelete(List<IdDto> idDtos);

    /**
     * 根据角色id 查询所具有的单位
     *
     * @param roleId
     * @return
     */
    List<String> findByRole(String roleId);
}
