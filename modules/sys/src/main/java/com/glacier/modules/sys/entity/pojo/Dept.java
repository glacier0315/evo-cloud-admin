package com.glacier.modules.sys.entity.pojo;

import com.glacier.common.core.entity.pojo.AbstractTreePojo;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

/**
 * 组织机构
 *
 * @author glacier
 * @version 1.0
 * @date 2019-10-14 17:06
 */
@Data
@ToString(callSuper = true)
@EqualsAndHashCode(callSuper = false)
public class Dept extends AbstractTreePojo<Dept> {
    private static final long serialVersionUID = 7605652474322748904L;
    /**
     * 单位编码
     */
    private String code;
    /**
     * 类型
     */
    private Integer type;
    /**
     * 状态
     */
    private Integer status;
    /**
     * 父级名称
     */
    private String parentName;
    /**
     * 父级Id,格式：,id1,id2,
     */
    private String parentIds;
}
