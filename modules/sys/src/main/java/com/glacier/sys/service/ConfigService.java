package com.glacier.sys.service;

import com.glacier.common.core.entity.form.IdForm;
import com.glacier.common.core.entity.page.PageRequest;
import com.glacier.common.core.entity.page.PageResponse;
import com.glacier.sys.entity.pojo.Config;

import java.util.List;

/**
 * @author glacier
 * @version 1.0
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
     * @param idForms
     * @return
     */
    int batchDelete(List<IdForm> idForms);
}
