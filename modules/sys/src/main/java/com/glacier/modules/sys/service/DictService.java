package com.glacier.modules.sys.service;

import com.glacier.common.core.entity.form.IdForm;
import com.glacier.modules.sys.entity.pojo.Dict;

import java.util.List;

/**
 * @author glacier
 * @version 1.0
 * @date 2019-12-01 21:36
 */
public interface DictService {

    /**
     * 查找字典
     *
     * @return
     */
    List<Dict> findDictTree();

    /**
     * 保存操作
     *
     * @param record
     * @return
     */
    int save(Dict record);

    /**
     * 根据Id批量删除
     *
     * @param idForms
     * @return
     */
    int batchDelete(List<IdForm> idForms);
}
