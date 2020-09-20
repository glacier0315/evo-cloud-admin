package com.glacier.modules.gen.entity;

import com.glacier.common.core.entity.AbstractDataEntity;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

import javax.validation.constraints.NotBlank;

/**
 * 代码生成表字段 模型层
 *
 * @author glacier
 * @version 1.0
 * @date 2020-08-25 16:22
 */
@Data
@ToString(callSuper = true)
@EqualsAndHashCode(callSuper = false)
public class GenTableColumn extends AbstractDataEntity {
    private static final long serialVersionUID = 5902937795879793255L;
    /**
     * 表id
     */
    private String tableId;
    /**
     * 列名称
     */
    private String columnName;
    /**
     * 列描述
     */
    private String columnComment;
    /**
     * 列类型
     */
    private String columnType;
    /**
     * JAVA类型
     */
    private String javaType;
    /**
     * JAVA字段名
     */
    @NotBlank(message = "Java属性不能为空")
    private String javaField;
    /**
     * 是否主键（1是 2 否）
     */
    private String isPk;
    /**
     * 是否必填（1是 2 否）
     */
    private String isRequired;
    /**
     * 是否为插入字段（1是 2 否）
     */
    private String isInsert;
    /**
     * 是否编辑字段（1是 2 否）
     */
    private String isEdit;
    /**
     * 是否列表字段（1是 2 否）
     */
    private String isList;
    /**
     * 是否查询字段（1是 2 否）
     */
    private String isQuery;
    /**
     * 查询方式（EQ等于、NE不等于、GT大于、LT小于、LIKE模糊、BETWEEN范围）
     */
    private String queryType;
    /**
     * 显示类型（input文本框、textarea文本域、select下拉框、checkbox复选框、radio单选框、datetime日期控件）
     */
    private String htmlType;
    /**
     * 字典类型
     */
    private String dictType;
    /**
     * 排序
     */
    private Integer sort;
}
