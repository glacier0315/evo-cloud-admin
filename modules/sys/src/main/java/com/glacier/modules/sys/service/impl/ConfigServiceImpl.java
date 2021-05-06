package com.glacier.modules.sys.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.glacier.common.core.entity.page.PageRequest;
import com.glacier.common.core.entity.page.PageResponse;
import com.glacier.modules.sys.entity.Config;
import com.glacier.modules.sys.mapper.ConfigMapper;
import com.glacier.modules.sys.service.ConfigService;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * @author glacier
 * @version 1.0
 * date 2019-11-21 17:37
 */
@Transactional(readOnly = true)
@Service("configService")
public class ConfigServiceImpl implements ConfigService {
    private static final Logger log = LoggerFactory.getLogger(ConfigServiceImpl.class);
    @Autowired
    private ConfigMapper configMapper;
    
    /**
     * 保存
     *
     * @param record 记录
     * @return 保存记录数
     */
    @Transactional(rollbackFor = {})
    @Override
    public int save(Config record) {
        if (record.isNewRecord()) {
            record.preInsert();
            return this.configMapper.insert(record);
        }
        record.preUpdate();
        return this.configMapper.updateByPrimaryKey(record);
    }

    /**
     * 根据id删除
     *
     * @param id 主键
     * @return 删除记录数
     */
    @Transactional(rollbackFor = {})
    @Override
    public int delete(String id) {
        if (StringUtils.isBlank(id)) {
            return 0;
        }
        return this.configMapper.deleteByPrimaryKey(id);
    }

    /**
     * 分页查找
     *
     * @param pageRequest 分页请求
     * @return 分页响应
     */
    @Override
    public PageResponse<Config> findPage(PageRequest<Config> pageRequest) {
        PageHelper.startPage(pageRequest.getPageNum(), pageRequest.getPageSize());
        PageInfo<Config> pageInfo = PageInfo.of(this.configMapper.selectList(pageRequest.getParams()));
        return new PageResponse<>(
                pageInfo.getPageNum(),
                pageInfo.getPages(),
                pageInfo.getTotal(),
                pageInfo.getList());
    }
}
