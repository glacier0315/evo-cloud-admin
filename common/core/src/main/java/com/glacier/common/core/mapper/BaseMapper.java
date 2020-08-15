package com.glacier.common.core.mapper;

import java.io.Serializable;
import java.util.List;
import java.util.Optional;

/**
 * 通用mapper
 *
 * @author glacier
 * @version 1.0
 * @date 2020-08-07 16:25
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
    Optional<T> selectByPrimaryKey(ID id);

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

    /**
     * 根据条件查询
     *
     * @param params
     * @return
     */
    <M extends Serializable> List<T> selectList(M params);
}
