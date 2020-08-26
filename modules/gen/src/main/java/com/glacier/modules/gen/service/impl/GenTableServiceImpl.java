package com.glacier.modules.gen.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.glacier.common.core.entity.page.PageRequest;
import com.glacier.common.core.entity.page.PageResponse;
import com.glacier.modules.gen.entity.GenTable;
import com.glacier.modules.gen.entity.dto.GenTableDto;
import com.glacier.modules.gen.entity.dto.GenTableQueryForm;
import com.glacier.modules.gen.mapper.GenTableMapper;
import com.glacier.modules.gen.service.GenTableService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.modelmapper.TypeToken;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * 生成表  业务层
 *
 * @author glacier
 * @version 1.0
 * @date 2020-08-26 16:35
 */
@Slf4j
@Transactional(readOnly = true)
@Service("genTableService")
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class GenTableServiceImpl implements GenTableService {
    private final GenTableMapper genTableMapper;
    private final ModelMapper modelMapper;

    @Override
    public GenTableDto findById(String id) {
        return this.modelMapper.map(
                this.genTableMapper.selectByPrimaryKey(id), GenTableDto.class);
    }

    @Override
    public PageResponse<GenTableDto> findPage(PageRequest<GenTableQueryForm> pageRequest) {
        PageHelper.startPage(pageRequest.getPageNum(), pageRequest.getPageSize());
        List<GenTable> userList = this.genTableMapper.selectList(pageRequest.getParams());
        PageInfo<GenTable> pageInfo = PageInfo.of(userList);
        return new PageResponse<>(
                pageInfo.getPageNum(),
                pageInfo.getPages(),
                pageInfo.getTotal(),
                this.modelMapper.map(
                        pageInfo.getList(),
                        new TypeToken<List<GenTableDto>>() {
                        }.getType()));
    }

    @Override
    public <T> int save(T form) {
        if (form == null) {
            return 0;
        }
        GenTable map = this.modelMapper.map(form, GenTable.class);
        if (!map.isNewRecord()) {
            map.preUpdate();
            return this.genTableMapper.updateByPrimaryKey(map);
        }
        map.preInsert();
        return this.genTableMapper.insert(map);
    }

    @Override
    public int delete(String id) {
        return this.genTableMapper.deleteByPrimaryKey(id);
    }
}
