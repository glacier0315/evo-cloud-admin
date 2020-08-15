package com.glacier.modules.sys.service;

import com.glacier.common.core.entity.page.PageRequest;
import com.glacier.common.core.entity.page.PageResponse;
import com.glacier.modules.sys.entity.SysLog;

/**
 * @author glacier
 * @version 1.0
 * @date 2019-12-18 16:08
 */
public interface SysLogService {
    /**
     * 分页查询
     *
     * @param pageRequest
     * @return
     */
    PageResponse<SysLog> findPage(PageRequest<SysLog> pageRequest);

    /**
     * 插入
     *
     * @param record
     * @return
     */
    int insert(SysLog record);

    /**
     * 异步调用保存
     *
     * @param record
     */
    void insertAsync(SysLog record);
}
