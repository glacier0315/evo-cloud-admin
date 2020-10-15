package com.glacier.modules.sys.service;

import com.glacier.common.core.entity.page.PageRequest;
import com.glacier.common.core.entity.page.PageResponse;
import com.glacier.modules.sys.entity.Config;

/**
 * @author glacier
 * @version 1.0
 * date 2019-11-21 17:37
 */
public interface ConfigService {


    /**
     * 分页查找配置
     *
     * @param pageRequest 分页请求
     * @return 分页响应
     */
    PageResponse<Config> findPage(PageRequest<Config> pageRequest);

    /**
     * 保存操作
     *
     * @param record 实体
     * @return 影响行数
     */
    int save(Config record);

    /**
     * 根据Id删除
     *
     * @param id 待删除id
     * @return 删除记录数
     */
    int delete(String id);
}
