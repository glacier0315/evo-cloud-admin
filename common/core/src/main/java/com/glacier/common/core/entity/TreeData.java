package com.glacier.common.core.entity;

import java.io.Serializable;
import java.util.List;

/**
 * 树形数据接口
 *
 * @author glacier
 * @version 1.0
 * @date 2020-08-15 08:50
 */
public interface TreeData<T extends TreeData<T>> extends Serializable {

    /**
     * 获取id
     *
     * @return id
     */
    String getId();

    /**
     * 获取名称
     *
     * @return 名称
     */
    String getName();

    /**
     * 获取父级id
     *
     * @return 父级id
     */
    String getParentId();

    /**
     * 获取父级名称
     *
     * @return 父级名称
     */
    String getParentName();

    /**
     * 获取排序号
     *
     * @return 排序号
     */
    Integer getOrderNum();

    /**
     * 获取层级
     *
     * @return 层级
     */
    Integer getGrade();

    /**
     * 设置层级
     *
     * @param grade 层级
     */
    void setGrade(Integer grade);

    /**
     * 获取子类
     *
     * @return 子类
     */
    List<T> getChildren();

    /**
     * 设置子类
     *
     * @param children 子类
     */
    void setChildren(List<T> children);
}
