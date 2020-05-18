package com.glacier.sys.entity;


import com.baomidou.mybatisplus.annotation.TableId;
import lombok.*;

import java.io.Serializable;
import java.util.Date;

/**
 * @author glacier
 * @version 1.0
 * @description
 * @date 2019-11-06 11:11
 */
@Data
@ToString
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Config implements Serializable {

    private static final long serialVersionUID = 3535850238275216235L;
    /**
     * 主键
     */
    @TableId
    private String id;
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
     * 创建人
     */
    private String createBy;
    /**
     * 创建时间
     */
    private Date createDate;
    /**
     * 更新人
     */
    private String updateBy;
    /**
     * 更新时间
     */
    private Date updateDate;
    /**
     * 删除标记
     */
    private String delFlag;
}
