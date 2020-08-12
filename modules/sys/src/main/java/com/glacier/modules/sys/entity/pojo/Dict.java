package com.glacier.modules.sys.entity.pojo;

import com.glacier.common.core.entity.pojo.AbstractTreePojo;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

/**
 * 字典
 * @author glacier
 * @version 1.0
 * @date 2019-12-01 21:16
 */
@Data
@ToString(callSuper = true)
@EqualsAndHashCode(callSuper = false)
public class Dict extends AbstractTreePojo<Dict> {
    private static final long serialVersionUID = -8004367732541881835L;
    /**
     * 字典编码
     */
    private String code;
    /**
     * 字典类型
     */
    private String type;
    /**
     * 描述
     */
    private String description;
    /**
     * 备注
     */
    private String remarks;
    /**
     * 父级名称
     */
    private String parentName;
}
