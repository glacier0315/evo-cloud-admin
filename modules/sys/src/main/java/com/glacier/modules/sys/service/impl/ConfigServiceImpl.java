package com.glacier.modules.sys.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.glacier.common.core.entity.page.PageRequest;
import com.glacier.common.core.entity.page.PageResponse;
import com.glacier.common.core.utils.StringUtil;
import com.glacier.modules.sys.entity.Config;
import com.glacier.modules.sys.mapper.ConfigMapper;
import com.glacier.modules.sys.service.ConfigService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * @author glacier
 * @version 1.0
 * @date 2019-11-21 17:37
 */
@Slf4j
@Transactional(readOnly = true)
@Service("configService")
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
     * @param id
     * @return
     */
    @Transactional(rollbackFor = {})
    @Override
    public int delete(String id) {
        if (StringUtil.isBlank(id)) {
            return 0;
        }
        return this.configMapper.deleteByPrimaryKey(id);
    }

    /**
     * 分页查找
     *
     * @param pageRequest
     * @return
     */
    @Override
    public PageResponse<Config> findPage(PageRequest<Config> pageRequest) {
        PageHelper.startPage(pageRequest.getPageNum(), pageRequest.getPageSize());
        List<Config> userList = this.configMapper.selectList(pageRequest.getParams());
        PageInfo<Config> pageInfo = PageInfo.of(userList);
        return new PageResponse<>(
                pageInfo.getPageNum(),
                pageInfo.getPages(),
                pageInfo.getTotal(),
                pageInfo.getList());
    }
}
