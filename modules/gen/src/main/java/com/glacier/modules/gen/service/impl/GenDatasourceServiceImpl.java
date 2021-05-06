package com.glacier.modules.gen.service.impl;

import com.baomidou.dynamic.datasource.annotation.DS;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.glacier.common.core.entity.page.PageRequest;
import com.glacier.common.core.entity.page.PageResponse;
import com.glacier.modules.gen.constant.DataSourceConstant;
import com.glacier.modules.gen.convert.GenDatasourceConvert;
import com.glacier.modules.gen.entity.GenDatasource;
import com.glacier.modules.gen.entity.dto.datasource.GenDatasourceDto;
import com.glacier.modules.gen.entity.dto.datasource.GenDatasourceForm;
import com.glacier.modules.gen.entity.dto.datasource.GenDatasourceQuery;
import com.glacier.modules.gen.mapper.GenDatasourceMapper;
import com.glacier.modules.gen.service.GenDatasourceService;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

/**
 * 数据源表  业务层
 *
 * @author glacier
 * @version 1.0
 * date 2020-09-20 16:13
 */
@Transactional(readOnly = true)
@DS(DataSourceConstant.DATASOURCE_GEN)
@Service("genDatasourceService")
public class GenDatasourceServiceImpl implements GenDatasourceService {
    private static final Logger log = LoggerFactory.getLogger(GenDatasourceServiceImpl.class);
    @Autowired
    private GenDatasourceMapper genDatasourceMapper;
    @Autowired
    private GenDatasourceConvert genDatasourceConvert;
    
    
    @Override
    public GenDatasource findDatasourceById(String id) {
        return this.genDatasourceMapper.selectByPrimaryKey(id);
    }
    
    @Override
    public GenDatasourceDto findById(String id) {
        return Optional.ofNullable(this.genDatasourceMapper.selectByPrimaryKey(id))
                .map(genDatasource ->
                        this.genDatasourceConvert.toGenDatasourceDto(genDatasource)
                ).orElse(null);
    }
    
    @Override
    public PageResponse<GenDatasourceDto> findPage(PageRequest<GenDatasourceQuery> pageRequest) {
        PageHelper.startPage(pageRequest.getPageNum(), pageRequest.getPageSize());
        PageInfo<GenDatasource> pageInfo = PageInfo.of(this.genDatasourceMapper.selectList(pageRequest.getParams()));
        return new PageResponse<>(
                pageInfo.getPageNum(),
                pageInfo.getPages(),
                pageInfo.getTotal(),
                this.genDatasourceConvert.GenDatasourceDto(pageInfo.getList()));
    }
    
    @Transactional(rollbackFor = {})
    @Override
    public int save(GenDatasourceForm form) {
        if (form == null) {
            return 0;
        }
        GenDatasource datasource = this.genDatasourceConvert.map(form);
        if (!datasource.isNewRecord()) {
            datasource.preUpdate();
            return this.genDatasourceMapper.updateByPrimaryKey(datasource);
        }
        datasource.preInsert();
        return this.genDatasourceMapper.insert(datasource);
    }
    
    @Transactional(rollbackFor = {})
    @Override
    public int delete(String id) {
        if (StringUtils.isBlank(id)) {
            return 0;
        }
        return this.genDatasourceMapper.deleteByPrimaryKey(id);
    }
}
