package com.glacier.sys.service;

import com.glacier.common.core.entity.dto.IdDto;
import com.glacier.common.core.page.PageRequest;
import com.glacier.common.core.page.PageResponse;
import com.glacier.sys.entity.Config;

import java.util.List;

/**
 * @author glacier
 * @version 1.0
 * @description
 * @date 2019-11-21 17:37
 */
public interface ConfigService {


    /**
     * 分页查找配置
     *
     * @param pageRequest
     * @return
     */
    PageResponse<Config> findPage(PageRequest<Config> pageRequest);

    /**
     * 保存操作
     *
     * @param record
     * @return
     */
    int save(Config record);

    /**
     * 根据Id批量删除
     *
     * @param idDtos
     * @return
     */
    int batchDelete(List<IdDto> idDtos);
}
