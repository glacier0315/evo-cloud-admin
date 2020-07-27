package com.glacier.modules.sys.service;

import com.glacier.common.core.entity.form.IdForm;
import com.glacier.modules.sys.entity.pojo.Dept;

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
    List<Dept> findList();

    /**
     * 根据用户ID查找所有 组织机构
     *
     * @param userId
     * @return
     */
    List<Dept> findListByUserId(String userId);

    /**
     * 根据用户id 查找单位树
     *
     * @param userId
     * @return
     */
    List<Dept> findTree(String userId);

    /**
     * 保存操作
     *
     * @param record
     * @return
     */
    int save(Dept record);

    /**
     * 根据Id批量删除
     *
     * @param idForms
     * @return
     */
    int batchDelete(List<IdForm> idForms);
}
