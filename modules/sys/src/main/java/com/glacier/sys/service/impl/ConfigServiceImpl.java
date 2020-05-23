package com.glacier.sys.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.glacier.common.core.entity.dto.IdDto;
import com.glacier.common.core.entity.page.PageRequest;
import com.glacier.common.core.entity.page.PageResponse;
import com.glacier.sys.entity.pojo.Config;
import com.glacier.sys.mapper.ConfigMapper;
import com.glacier.sys.service.ConfigService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

/**
 * @author glacier
 * @version 1.0
 * @date 2019-11-21 17:37
 */
@Slf4j
@Transactional(readOnly = true)
@Service("ConfigService")
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class ConfigServiceImpl implements ConfigService {

    private final ConfigMapper configMapper;

    /**
     * 保存
     *
     * @param record
     * @return
     */
    @Transactional(rollbackFor = {})
    @Override
    public int save(Config record) {
        int update = 0;
        if (record.getId() != null && !record.getId().isEmpty()) {
            update = this.configMapper.updateById(record);
        } else {
            update = this.configMapper.insert(record);
        }
        return update;
    }

    /**
     * 根据id批量删除
     *
     * @param idDtos
     * @return
     */
    @Transactional(rollbackFor = {})
    @Override
    public int batchDelete(List<IdDto> idDtos) {
        if (idDtos != null && !idDtos.isEmpty()) {
            List<String> list = idDtos.stream()
                    .map(IdDto::getId)
                    .collect(Collectors.toList());
            return this.configMapper.deleteBatchIds(list);
        }
        return 0;
    }

    /**
     * 分页查找
     *
     * @param pageRequest
     * @return
     */
    @Override
    public PageResponse<Config> findPage(PageRequest<Config> pageRequest) {
        Page<Config> page = this.configMapper.selectPage(new Page<>(pageRequest.getCurrent(), pageRequest.getSize()),
                new QueryWrapper<>(pageRequest.getParams()));
        return PageResponse.<Config>builder()
                .current(page.getCurrent())
                .size(page.getSize())
                .total(page.getTotal())
                .records(page.getRecords())
                .build();
    }
}
