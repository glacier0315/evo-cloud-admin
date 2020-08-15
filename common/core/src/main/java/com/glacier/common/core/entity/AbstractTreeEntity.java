package com.glacier.common.core.entity;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

import java.util.List;

/**
 * 树形基类
 *
 * @author glacier
 * @version 1.0
 * @date 2020-08-10 17:40
 */
@EqualsAndHashCode(callSuper = true)
@Data
@ToString
public abstract class AbstractTreeEntity<T extends AbstractTreeEntity<T>> extends AbstractDataEntity {
    private static final long serialVersionUID = 5789176651104516887L;
    /**
     * 名称
     */
    protected String name;
    /**
     * 父级id 顶级id默认为空
     */
    protected String parentId;
    /**
     * 排序号
     */
    protected Integer orderNum;
    /**
     * 层级
     */
    protected Integer grade;
    /**
     * 子类
     */
    protected List<T> children;
}
