package com.glacier.modules.gen.service;

import com.glacier.common.core.entity.page.PageRequest;
import com.glacier.common.core.entity.page.PageResponse;
import com.glacier.modules.gen.entity.dto.GenTableDto;
import com.glacier.modules.gen.entity.dto.GenTableQueryForm;

/**
 * 生成表  业务层
 *
 * @author glacier
 * @version 1.0
 * @date 2020-08-26 16:32
 */
public interface GenTableService {

    /**
     * 根据用户id 查询
     *
     * @param id
     * @return
     */
    GenTableDto findById(String id);

    /**
     * 分页查询
     *
     * @param pageRequest
     * @return
     */
    PageResponse<GenTableDto> findPage(PageRequest<GenTableQueryForm> pageRequest);

    /**
     * 保存用户
     *
     * @param form 用户封装实体
     * @param <T>  用户封装类型
     * @return
     */
    <T> int save(T form);

    /**
     * 根据Id删除
     *
     * @param id
     * @return
     */
    int delete(String id);
}
