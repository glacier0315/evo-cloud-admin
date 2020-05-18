package com.glacier.sys.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.glacier.common.core.page.PageRequest;
import com.glacier.common.core.page.PageResponse;
import com.glacier.sys.entity.Log;
import com.glacier.sys.mapper.LogMapper;
import com.glacier.sys.service.LogService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

/**
 * @author glacier
 * @version 1.0
 * @description
 * @date 2019-12-18 15:29
 */
@Slf4j
@Service("SysLogService")
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class LogServiceImpl implements LogService {

    private final LogMapper logMapper;

    @Override
    public PageResponse<Log> findPage(PageRequest<Log> pageRequest) {
        Page<Log> page = logMapper.selectPage(new Page<>(pageRequest.getCurrent(), pageRequest.getSize()),
                new QueryWrapper<>(pageRequest.getParams()));
        return PageResponse.<Log>builder()
                .current(page.getCurrent())
                .size(page.getSize())
                .total(page.getTotal())
                .records(page.getRecords())
                .build();
    }

    /**
     * 保存
     *
     * @param record
     * @return
     */
    @Override
    public int insert(Log record) {
        int update = 0;
        if (record.getId() != null && !record.getId().isEmpty()) {
            update = logMapper.updateById(record);
        } else {
            update = logMapper.insert(record);
        }
        return update;
    }

    /**
     * 异步调用保存
     *
     * @param record
     */
    @Async
    @Override
    public void insertAsync(Log record) {
        logMapper.insert(record);
    }
}
