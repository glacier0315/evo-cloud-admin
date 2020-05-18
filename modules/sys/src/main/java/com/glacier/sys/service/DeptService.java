package com.glacier.sys.service;

import com.glacier.common.core.entity.dto.IdDto;
import com.glacier.sys.entity.Dept;

import java.util.List;

/**
 * @author glacier
 * @version 1.0
 * @description
 * @date 2019-10-24 17:07
 */
public interface DeptService {

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
     * @param idDtos
     * @return
     */
    int batchDelete(List<IdDto> idDtos);
}
