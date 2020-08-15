package com.glacier.modules.sys.service;

import com.glacier.common.core.entity.dto.IdDto;
import com.glacier.modules.sys.entity.Dict;
import com.glacier.modules.sys.entity.dto.dict.DictVo;

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
    List<DictVo> findDictTree();

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
     * @param idDtos
     * @return
     */
    int batchDelete(List<IdDto> idDtos);
}
