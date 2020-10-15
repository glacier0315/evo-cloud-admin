package com.glacier.modules.sys.service;

import com.glacier.modules.sys.entity.Dict;
import com.glacier.modules.sys.entity.dto.dict.DictVo;

import java.util.List;

/**
 * @author glacier
 * @version 1.0
 * date 2019-12-01 21:36
 */
public interface DictService {

    /**
     * 查找字典
     *
     * @return 字典
     */
    List<DictVo> findDictTree();

    /**
     * 保存操作
     *
     * @param record 记录实体
     * @return 影响记录数
     */
    int save(Dict record);

    /**
     * 根据Id删除
     *
     * @param id 待删除id
     * @return 删除记录数
     */
    int delete(String id);
}
