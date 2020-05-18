package com.glacier.sys.entity;

import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.*;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

/**
 * @author glacier
 * @version 1.0
 * @description 字典
 * @date 2019-12-01 21:16
 */
@Data
@ToString
@NoArgsConstructor
@AllArgsConstructor
@Builder
@TableName(excludeProperty = {"level", "children", "parentName"})
public class Dict implements Serializable {
    private static final long serialVersionUID = -8004367732541881835L;
    /**
     * 主键
     */
    @TableId
    private String id;
    /**
     * 编码名称
     */
    private String code;
    /**
     * 字典编码
     */
    private String name;
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
     * 父级id 顶级id默认为0
     */
    private String parentId;
    /**
     * 排序号
     */
    private Integer orderNum;
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

    /**
     * 层级
     */
    private Integer level;
    /**
     * 下级单位
     */
    private List<Dict> children;
    /**
     * 父级名称
     */
    private String parentName;
}
