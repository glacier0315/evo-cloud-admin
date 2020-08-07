package com.glacier.modules.sys.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.glacier.common.core.entity.page.PageRequest;
import com.glacier.common.core.entity.page.PageResponse;
import com.glacier.modules.sys.entity.pojo.Log;
import com.glacier.modules.sys.mapper.LogMapper;
import com.glacier.modules.sys.service.LogService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

/**
 * @author glacier
 * @version 1.0
 * @date 2019-12-18 15:29
 */
@Slf4j
@Service("sysLogService")
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class LogServiceImpl implements LogService {

    private final LogMapper logMapper;

    @Override
    public PageResponse<Log> findPage(PageRequest<Log> pageRequest) {
        Page<Log> page = this.logMapper.selectPage(new Page<>(pageRequest.getCurrent(), pageRequest.getSize()),
                new QueryWrapper<>(pageRequest.getParams()));
        return PageResponse.<Log>builder()
                .pageNum(page.getCurrent())
                .pageSize(page.getSize())
                .total(page.getTotal())
                .list(page.getRecords())
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
            update = this.logMapper.updateById(record);
        } else {
            update = this.logMapper.insert(record);
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
        this.logMapper.insert(record);
    }
}
