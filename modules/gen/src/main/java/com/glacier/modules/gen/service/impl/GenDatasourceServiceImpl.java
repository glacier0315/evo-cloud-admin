package com.glacier.modules.gen.service.impl;

import com.baomidou.dynamic.datasource.annotation.DS;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.glacier.common.core.entity.page.PageRequest;
import com.glacier.common.core.entity.page.PageResponse;
import com.glacier.common.core.utils.StringUtil;
import com.glacier.modules.gen.constant.DataSourceConstant;
import com.glacier.modules.gen.entity.GenDatasource;
import com.glacier.modules.gen.entity.dto.datasource.GenDatasourceDto;
import com.glacier.modules.gen.entity.dto.datasource.GenDatasourceForm;
import com.glacier.modules.gen.entity.dto.datasource.GenDatasourceQuery;
import com.glacier.modules.gen.mapper.GenDatasourceMapper;
import com.glacier.modules.gen.service.GenDatasourceService;
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
    private final GenDatasourceMapper genDatasourceMapper;
    private final ModelMapper modelMapper;

    @Autowired
    public GenDatasourceServiceImpl(GenDatasourceMapper genDatasourceMapper,
                                    ModelMapper modelMapper) {
        this.genDatasourceMapper = genDatasourceMapper;
        this.modelMapper = modelMapper;
    }

    @Override
    public GenDatasourceDto findById(String id) {
        return Optional.ofNullable(this.genDatasourceMapper.selectByPrimaryKey(id))
                .map(genDatasource ->
                        this.modelMapper.map(genDatasource, GenDatasourceDto.class)
                ).orElse(null);
    }

    @Override
    public PageResponse<GenDatasourceDto> findPage(PageRequest<GenDatasourceQuery> pageRequest) {
        PageHelper.startPage(pageRequest.getPageNum(), pageRequest.getPageSize());
        List<GenDatasource> list = this.genDatasourceMapper.selectList(pageRequest.getParams());
        PageInfo<GenDatasource> pageInfo = PageInfo.of(list);
        return new PageResponse<>(
                pageInfo.getPageNum(),
                pageInfo.getPages(),
                pageInfo.getTotal(),
                this.modelMapper.map(
                        pageInfo.getList(),
                        new TypeToken<List<GenDatasourceDto>>() {
                        }.getType()));
    }

    @Transactional(rollbackFor = {})
    @Override
    public int save(GenDatasourceForm form) {
        if (form == null) {
            return 0;
        }
        GenDatasource datasource = this.modelMapper.map(form, GenDatasource.class);
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
        if (StringUtil.isBlank(id)) {
            return 0;
        }
        return this.genDatasourceMapper.deleteByPrimaryKey(id);
    }
}
