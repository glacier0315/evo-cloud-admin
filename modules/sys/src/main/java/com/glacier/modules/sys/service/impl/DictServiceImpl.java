package com.glacier.modules.sys.service.impl;

import com.glacier.common.core.utils.StringUtil;
import com.glacier.common.core.utils.TreeBuildFactory;
import com.glacier.modules.sys.entity.Dict;
import com.glacier.modules.sys.entity.dto.dict.DictVo;
import com.glacier.modules.sys.mapper.DictMapper;
import com.glacier.modules.sys.service.DictService;
import org.modelmapper.ModelMapper;
import org.modelmapper.TypeToken;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * 字典业务类
 * @author glacier
 * @version 1.0
 * @date 2019-12-01 21:36
 */
@Transactional(readOnly = true)
@Service("dictService")
public class DictServiceImpl implements DictService {
    private static final Logger log = LoggerFactory.getLogger(DictServiceImpl.class);
    private final ModelMapper modelMapper;
    private final DictMapper dictMapper;

    @Autowired
    public DictServiceImpl(ModelMapper modelMapper, DictMapper dictMapper) {
        this.modelMapper = modelMapper;
        this.dictMapper = dictMapper;
    }

    @Transactional(rollbackFor = {})
    @Override
    public int save(Dict record) {
        if (record.isNewRecord()) {
            record.preInsert();
            return this.dictMapper.insert(record);
        }
        record.preUpdate();
        return this.dictMapper.updateByPrimaryKey(record);
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
        return this.dictMapper.deleteByPrimaryKey(id);
    }

    @Override
    public List<DictVo> findDictTree() {
        return TreeBuildFactory.buildMenuTree(
                this.modelMapper.map(
                        this.dictMapper.selectAll(),
                        new TypeToken<List<DictVo>>() {
                        }.getType()));
    }
}
