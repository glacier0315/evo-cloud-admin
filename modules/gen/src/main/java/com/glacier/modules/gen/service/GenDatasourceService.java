package com.glacier.modules.gen.service;

import com.glacier.common.core.entity.page.PageRequest;
import com.glacier.common.core.entity.page.PageResponse;
import com.glacier.modules.gen.entity.GenDatasource;
import com.glacier.modules.gen.entity.dto.datasource.GenDatasourceDto;
import com.glacier.modules.gen.entity.dto.datasource.GenDatasourceForm;
import com.glacier.modules.gen.entity.dto.datasource.GenDatasourceQuery;

/**
 * @author glacier
 * @version 1.0
 * date 2020-09-20 16:13
 */
public interface GenDatasourceService {

    /**
     * 根据id 查询
     *
     * @param id
     * @return
     */
    GenDatasource findDatasourceById(String id);

    /**
     * 根据id 查询
     *
     * @param id
     * @return
     */
    GenDatasourceDto findById(String id);

    /**
     * 分页查询
     *
     * @param pageRequest
     * @return
     */
    PageResponse<GenDatasourceDto> findPage(PageRequest<GenDatasourceQuery> pageRequest);

    /**
     * 保存
     *
     * @param form 封装实体
     * @return
     */
    int save(GenDatasourceForm form);

    /**
     * 根据Id删除
     *
     * @param id
     * @return
     */
    int delete(String id);
}
