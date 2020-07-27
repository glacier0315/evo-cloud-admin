package com.glacier.modules.sys.service;

import com.glacier.common.core.entity.page.PageRequest;
import com.glacier.common.core.entity.page.PageResponse;
import com.glacier.modules.sys.entity.pojo.Log;

/**
 * @author glacier
 * @version 1.0
 * @date 2019-12-18 16:08
 */
public interface LogService {
    /**
     * 分页查询
     *
     * @param pageRequest
     * @return
     */
    PageResponse<Log> findPage(PageRequest<Log> pageRequest);

    /**
     * 插入
     *
     * @param record
     * @return
     */
    int insert(Log record);

    /**
     * 异步调用保存
     *
     * @param record
     */
    void insertAsync(Log record);
}
