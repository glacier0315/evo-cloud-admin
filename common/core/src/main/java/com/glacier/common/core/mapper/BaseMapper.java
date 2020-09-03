package com.glacier.common.core.mapper;

import java.io.Serializable;
import java.util.List;

/**
 * 通用mapper
 *
 * @author glacier
 * @version 1.0
 * @date 2020-08-07 16:25
 * @param <T> 实体类型
 * @param <ID> 主键类型
 */
public interface BaseMapper<T, ID extends Serializable> {

    /**
     * 根据主键 查询
     *
     * @param id
     * @return
     */
    int deleteByPrimaryKey(ID id);

    /**
     * 插入 新记录
     *
     * @param record
     * @return
     */
    int insert(T record);

    /**
     * 根据主键查询
     *
     * @param id
     * @return
     */
    T selectByPrimaryKey(ID id);

    /**
     * 查询所有
     *
     * @return
     */
    List<T> selectAll();

    /**
     * 更新记录
     *
     * @param record
     * @return
     */
    int updateByPrimaryKey(T record);
}
