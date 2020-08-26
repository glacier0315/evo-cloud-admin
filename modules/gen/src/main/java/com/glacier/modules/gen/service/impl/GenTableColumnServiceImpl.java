package com.glacier.modules.gen.service.impl;

import com.glacier.modules.gen.entity.dto.GenTableColumnDto;
import com.glacier.modules.gen.entity.dto.GenTableDto;
import com.glacier.modules.gen.mapper.GenTableColumnMapper;
import com.glacier.modules.gen.service.GenTableColumnService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.modelmapper.TypeToken;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * 生成表字段  业务层
 *
 * @author glacier
 * @version 1.0
 * @date 2020-08-26 17:27
 */
@Slf4j
@Transactional(readOnly = true)
@Service("genTableService")
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class GenTableColumnServiceImpl implements GenTableColumnService {
    private final GenTableColumnMapper genTableColumnMapper;
    private final ModelMapper modelMapper;

    @Override
    public List<GenTableColumnDto> selectListByTableId(String tableId) {
        return this.modelMapper.map(
                this.genTableColumnMapper.selectListByTableId(tableId),
                new TypeToken<List<GenTableDto>>() {
                }.getType());
    }
}
