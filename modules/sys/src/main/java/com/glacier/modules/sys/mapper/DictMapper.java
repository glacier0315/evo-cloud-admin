package com.glacier.modules.sys.mapper;

import com.glacier.common.core.mapper.BaseMapper;
import com.glacier.modules.sys.entity.Dict;

import java.util.List;


/**
 * 字典 数据层
 *
 * @author glacier
 * @version 1.0
 * date 2019-08-04 21:53
 */
public interface DictMapper extends BaseMapper<Dict, String> {

    /**
     * 根据条件查询
     *
     * @param params 查询参数
     * @return 字典集合
     */
    List<Dict> selectList(Dict params);
}
