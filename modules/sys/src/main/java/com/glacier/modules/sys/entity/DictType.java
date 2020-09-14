package com.glacier.modules.sys.entity;

import com.glacier.common.core.entity.AbstractDataEntity;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

/**
 * 字典类别
 *
 * @author glacier
 * @version 1.0
 * @date 2019-10-09 11:03
 */
@Data
@ToString(callSuper = true)
@EqualsAndHashCode(callSuper = false)
public class DictType extends AbstractDataEntity {

    private static final long serialVersionUID = 2884044409649620265L;
    /**
     * 字典名称
     */
    private String dictName;

    /**
     * 字典类型
     */
    private String dictType;

    /**
     * 状态（1正常 2停用）
     */
    private Integer status;

    /**
     * 备注
     */
    private String remarks;

}
