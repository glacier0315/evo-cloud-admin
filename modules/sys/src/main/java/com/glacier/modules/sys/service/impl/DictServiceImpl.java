package com.glacier.modules.sys.service.impl;

import com.glacier.common.core.entity.dto.IdDto;
import com.glacier.common.core.utils.TreeBuildFactory;
import com.glacier.modules.sys.entity.Dict;
import com.glacier.modules.sys.entity.dto.dict.DictVo;
import com.glacier.modules.sys.mapper.DictMapper;
import com.glacier.modules.sys.service.DictService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.modelmapper.TypeToken;
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
@Slf4j
@Transactional(readOnly = true)
@Service("dictService")
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class DictServiceImpl implements DictService {
    private final ModelMapper modelMapper;
    private final DictMapper dictMapper;

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
     * 根据id批量删除
     *
     * @param idDtos
     * @return
     */
    @Transactional(rollbackFor = {})
    @Override
    public int batchDelete(List<IdDto> idDtos) {
        if (idDtos != null
                && !idDtos.isEmpty()) {
            return this.dictMapper.deleteBatchIds(idDtos);
        }
        return 0;
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
