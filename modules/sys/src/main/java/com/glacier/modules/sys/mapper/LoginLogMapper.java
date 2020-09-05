package com.glacier.modules.sys.mapper;

import com.glacier.common.core.mapper.BaseMapper;
import com.glacier.modules.sys.entity.LoginLog;

import java.util.List;

/**
 * 登录日志 持久层
 *
 * @author glacier
 * @version 1.0
 * @date 2019-10-09 11:03
 */
public interface LoginLogMapper extends BaseMapper<LoginLog, String> {
    /**
     * 根据条件查询
     * @param params
     * @return
     */
    List<LoginLog> selectList(LoginLog params);
}
