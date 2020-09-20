package com.glacier.modules.gen.service.impl;

import com.baomidou.dynamic.datasource.annotation.DS;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.glacier.common.core.entity.page.PageRequest;
import com.glacier.common.core.entity.page.PageResponse;
import com.glacier.common.core.utils.StringUtils;
import com.glacier.modules.constant.DataSourceConstant;
import com.glacier.modules.gen.entity.GenDatasource;
import com.glacier.modules.gen.entity.dto.datasource.GenDatasourceDto;
import com.glacier.modules.gen.entity.dto.datasource.GenDatasourceForm;
import com.glacier.modules.gen.entity.dto.datasource.GenDatasourceQuery;
import com.glacier.modules.gen.mapper.GenDatasourceMapper;
import com.glacier.modules.gen.service.GenDatasourceService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.modelmapper.TypeToken;
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
 * @date 2020-09-20 16:13
 */
@Slf4j
@Transactional(readOnly = true)
@DS(DataSourceConstant.DATASOURCE_MASTER)
@Service("genDatasourceService")
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class GenDatasourceServiceImpl implements GenDatasourceService {

    private final GenDatasourceMapper genDatasourceMapper;
    private final ModelMapper modelMapper;

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

    @Override
    public int delete(String id) {
        if (StringUtils.isBlank(id)) {
            return 0;
        }
        return this.genDatasourceMapper.deleteByPrimaryKey(id);
    }
}
