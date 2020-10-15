package com.glacier.modules.sys.service;

import com.glacier.common.core.entity.page.PageRequest;
import com.glacier.common.core.entity.page.PageResponse;
import com.glacier.modules.sys.entity.SysLog;

/**
 * @author glacier
 * @version 1.0
 * date 2019-12-18 16:08
 */
public interface SysLogService {
    /**
     * 分页查询
     *
     * @param pageRequest 分页请求
     * @return 分页响应
     */
    PageResponse<SysLog> findPage(PageRequest<SysLog> pageRequest);

    /**
     * 插入
     *
     * @param record 记录
     * @return 保存记录数
     */
    int insert(SysLog record);

    /**
     * 异步调用保存
     *
     * @param record 记录
     */
    void insertAsync(SysLog record);
}
