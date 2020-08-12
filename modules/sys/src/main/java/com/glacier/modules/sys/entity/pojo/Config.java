package com.glacier.modules.sys.entity.pojo;


import com.glacier.common.core.entity.pojo.AbstractDataPojo;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

/**
 * 配置
 * @author glacier
 * @version 1.0
 * @date 2019-11-06 11:11
 */
@Data
@ToString(callSuper = true)
@EqualsAndHashCode(callSuper = false)
public class Config extends AbstractDataPojo {

    private static final long serialVersionUID = 3535850238275216235L;
    /**
     * 值
     */
    private String value;
    /**
     * 显示名称
     */
    private String label;
    /**
     * 类型
     */
    private String type;
    /**
     * 描述
     */
    private String description;
    /**
     * 排序号
     */
    private Integer orderNum;
    /**
     * 备注
     */
    private String remarks;
}
