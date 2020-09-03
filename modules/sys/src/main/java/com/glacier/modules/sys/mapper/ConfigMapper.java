package com.glacier.modules.sys.mapper;

import com.glacier.common.core.mapper.BaseMapper;
import com.glacier.modules.sys.entity.Config;

import java.util.List;

/**
 * 配置 数据层
 *
 * @author glacier
 * @version 1.0
 * @date 2019-08-04 21:53
 */
public interface ConfigMapper extends BaseMapper<Config, String> {

    /**
     * 根据条件查询
     * @param params
     * @return
     */
    List<Config> selectList(Config params);
}
