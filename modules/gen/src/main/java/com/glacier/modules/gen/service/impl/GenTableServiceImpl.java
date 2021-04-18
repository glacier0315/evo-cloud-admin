package com.glacier.modules.gen.service.impl;

import com.baomidou.dynamic.datasource.annotation.DS;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.glacier.common.core.entity.page.PageRequest;
import com.glacier.common.core.entity.page.PageResponse;
import com.glacier.modules.gen.constant.DataSourceConstant;
import com.glacier.modules.gen.entity.GenTable;
import com.glacier.modules.gen.entity.dto.table.GenTableDto;
import com.glacier.modules.gen.entity.dto.table.GenTableQuery;
import com.glacier.modules.gen.mapper.GenTableMapper;
import com.glacier.modules.gen.service.GenTableService;
import org.apache.commons.lang3.StringUtils;
import org.modelmapper.ModelMapper;
import org.modelmapper.TypeToken;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

/**
 * 生成表  业务层
 *
 * @author glacier
 * @version 1.0
 * date 2020-08-26 16:35
 */
@Transactional(readOnly = true)
@DS(DataSourceConstant.DATASOURCE_GEN)
@Service("genTableService")
public class GenTableServiceImpl implements GenTableService {
    private static final Logger log = LoggerFactory.getLogger(GenTableColumnServiceImpl.class);
    private final GenTableMapper genTableMapper;
    private final ModelMapper modelMapper;

    @Autowired
    public GenTableServiceImpl(GenTableMapper genTableMapper,
                               ModelMapper modelMapper) {
        this.genTableMapper = genTableMapper;
        this.modelMapper = modelMapper;
    }

    @Override
    public GenTableDto findById(String id) {
        return Optional.ofNullable(this.genTableMapper.selectByPrimaryKey(id))
                .map(table ->
                        this.modelMapper.map(table, GenTableDto.class)
                ).orElse(null);
    }

    @Override
    public PageResponse<GenTableDto> findPage(PageRequest<GenTableQuery> pageRequest) {
        PageHelper.startPage(pageRequest.getPageNum(), pageRequest.getPageSize());
        List<GenTable> list = this.genTableMapper.selectList(pageRequest.getParams());
        PageInfo<GenTable> pageInfo = PageInfo.of(list);
        return new PageResponse<>(
                pageInfo.getPageNum(),
                pageInfo.getPages(),
                pageInfo.getTotal(),
                this.modelMapper.map(
                        pageInfo.getList(),
                        new TypeToken<List<GenTableDto>>() {
                        }.getType()));
    }

    @Transactional(rollbackFor = {})
    @Override
    public int save(GenTableDto form) {
        if (form == null) {
            return 0;
        }
        GenTable genTable = this.modelMapper.map(form, GenTable.class);
        if (!genTable.isNewRecord()) {
            genTable.preUpdate();
            return this.genTableMapper.updateByPrimaryKey(genTable);
        }
        genTable.preInsert();
        return this.genTableMapper.insert(genTable);
    }

    @Transactional(rollbackFor = {})
    @Override
    public int delete(String id) {
        if (StringUtils.isBlank(id)) {
            return 0;
        }
        return this.genTableMapper.deleteByPrimaryKey(id);
    }
}
