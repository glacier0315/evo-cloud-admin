package com.glacier.modules.sys.mapper;

import com.glacier.common.core.mapper.BaseMapper;
import com.glacier.modules.sys.entity.SysLog;

import java.util.List;

/**
 * 日志数据层
 *
 * @author glacier
 * @version 1.0
 * @date 2019-08-04 21:53
 */
public interface SysLogMapper extends BaseMapper<SysLog, String> {

    /**
     * 根据条件查询
     * @param params
     * @return
     */
    List<SysLog> selectList(SysLog params);
}
