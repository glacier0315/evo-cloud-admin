package com.glacier.modules.dfs.exception;

import com.glacier.common.core.constant.ServiceNameConstants;
import com.glacier.common.core.exception.BaseException;

/**
 * @author glacier
 * @version 1.0
 * date 2020-11-08 18:51
 */
public class DfsExcetion extends BaseException {
    private static final long serialVersionUID = -65509677962109710L;

    public DfsExcetion(String code, String msg, Object[] args) {
        super(ServiceNameConstants.DFS_SERVICE, code, msg, args);
    }
}
