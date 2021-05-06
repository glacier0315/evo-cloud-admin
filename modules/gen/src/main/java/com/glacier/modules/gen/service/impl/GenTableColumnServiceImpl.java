package com.glacier.modules.gen.service.impl;

import com.baomidou.dynamic.datasource.annotation.DS;
import com.glacier.modules.gen.constant.DataSourceConstant;
import com.glacier.modules.gen.convert.GenTableColumnConvert;
import com.glacier.modules.gen.entity.dto.column.GenTableColumnDto;
import com.glacier.modules.gen.mapper.GenTableColumnMapper;
import com.glacier.modules.gen.service.GenTableColumnService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * 生成表字段  业务层
 *
 * @author glacier
 * @version 1.0
 * date 2020-08-26 17:27
 */
@Transactional(readOnly = true)
@DS(DataSourceConstant.DATASOURCE_GEN)
@Service("genTableColumnService")
public class GenTableColumnServiceImpl implements GenTableColumnService {
    private static final Logger log = LoggerFactory.getLogger(GenTableColumnServiceImpl.class);
    @Autowired
    private GenTableColumnMapper genTableColumnMapper;
    @Autowired
    private GenTableColumnConvert genTableColumnConvert;
    
    @Override
    public List<GenTableColumnDto> selectListByTableId(String tableId) {
        return this.genTableColumnConvert.toGenTableColumnDto(
                this.genTableColumnMapper.selectListByTableId(tableId));
    }
}
