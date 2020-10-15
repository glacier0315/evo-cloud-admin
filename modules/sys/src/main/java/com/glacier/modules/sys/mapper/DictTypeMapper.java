package com.glacier.modules.sys.mapper;

import com.glacier.common.core.mapper.BaseMapper;
import com.glacier.modules.sys.entity.DictType;

import java.util.List;

/**
 * 字典类别
 *
 * @author glacier
 * @version 1.0
 * date 2019-10-09 11:03
 */
public interface DictTypeMapper extends BaseMapper<DictType, String> {

    /**
     * 根据条件查询
     *
     * @param params 查询参数
     * @return 字典类别集合
     */
    List<DictType> selectList(DictType params);
}
