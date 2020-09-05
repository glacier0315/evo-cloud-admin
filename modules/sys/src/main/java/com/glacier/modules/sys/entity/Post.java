package com.glacier.modules.sys.entity;

import com.glacier.common.core.entity.AbstractDataEntity;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

/**
 * 岗位
 *
 * @author glacier
 * @version 1.0
 * @date 2019-10-09 11:03
 */
@Data
@ToString(callSuper = true)
@EqualsAndHashCode(callSuper = false)
public class Post extends AbstractDataEntity {
    private static final long serialVersionUID = -8716186319813001885L;
    /**
     * 名称
     */
    private String name;
    /**
     * 编码
     */
    private String code;
    /**
     * 状态 1 正常 2 禁用
     */
    private Integer status;
    /**
     * 排序号
     */
    protected Integer orderNum;
    /**
     * 描述
     */
    private String description;
    /**
     * 备注
     */
    private String remarks;
}
