package com.glacier.modules.sys.entity;

import com.glacier.common.core.entity.AbstractDataEntity;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

/**
 * 字典数据
 *
 * @author glacier
 * @version 1.0
 * @date 2019-10-09 11:03
 */
@Data
@ToString(callSuper = true)
@EqualsAndHashCode(callSuper = false)
public class DictData extends AbstractDataEntity {
    private static final long serialVersionUID = 2538417218158807473L;
    /**
     * 字典标签
     */
    private String label;

    /**
     * 字典键值
     */
    private String value;

    /**
     * 字典类型
     */
    private String type;

    /**
     * 是否默认（1是 2否）
     */
    private String defaultFlag;

    /**
     * 字典类型
     */
    private String dictType;

    /**
     * 状态（1正常 2停用）
     */
    private Integer status;

    /**
     * 排序号
     */
    private Integer orderNum;

    /**
     * 备注
     */
    private String remarks;

}
