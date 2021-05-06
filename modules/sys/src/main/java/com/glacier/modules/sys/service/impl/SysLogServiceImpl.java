package com.glacier.modules.sys.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.glacier.common.core.entity.page.PageRequest;
import com.glacier.common.core.entity.page.PageResponse;
import com.glacier.common.core.utils.IdGen;
import com.glacier.modules.sys.entity.SysLog;
import com.glacier.modules.sys.handler.GlobalExceptionHandler;
import com.glacier.modules.sys.mapper.SysLogMapper;
import com.glacier.modules.sys.service.SysLogService;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * @author glacier
 * @version 1.0
 * date 2019-12-18 15:29
 */
@Transactional(readOnly = true)
@Service("sysLogService")
public class SysLogServiceImpl implements SysLogService {
    private static final Logger log = LoggerFactory.getLogger(GlobalExceptionHandler.class);
    @Autowired
    private SysLogMapper sysLogMapper;

    @Override
    public PageResponse<SysLog> findPage(PageRequest<SysLog> pageRequest) {
        PageHelper.startPage(pageRequest.getPageNum(), pageRequest.getPageSize());
        List<SysLog> sysLogList = this.sysLogMapper.selectList(pageRequest.getParams());
        PageInfo<SysLog> pageInfo = PageInfo.of(sysLogList);
        return new PageResponse<>(
                pageInfo.getPageNum(),
                pageInfo.getPages(),
                pageInfo.getTotal(),
                pageInfo.getList());
    }

    /**
     * 保存
     *
     * @param record 记录
     * @return 保存记录数
     */
    @Transactional(rollbackFor = {})
    @Override
    public int insert(SysLog record) {
        if (StringUtils.isNotEmpty(record.getId())) {
            return this.sysLogMapper.updateByPrimaryKey(record);
        }
        record.setId(IdGen.uuid());
        return this.sysLogMapper.insert(record);
    }

    /**
     * 异步调用保存
     *
     * @param record 记录
     */
    @Transactional(rollbackFor = {})
    @Async
    @Override
    public void insertAsync(SysLog record) {
        record.setId(IdGen.uuid());
        this.sysLogMapper.insert(record);
    }
}
