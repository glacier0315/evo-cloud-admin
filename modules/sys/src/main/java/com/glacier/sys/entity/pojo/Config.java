package com.glacier.sys.entity.pojo;


import com.glacier.common.core.entity.pojo.BasePojo;
import lombok.*;

/**
 * 配置
 * @author glacier
 * @version 1.0
 * @date 2019-11-06 11:11
 */
@Data
@ToString
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(callSuper=false)
@Builder
public class Config extends BasePojo {

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
    /**
     * 删除标记
     */
    private String delFlag;
}
