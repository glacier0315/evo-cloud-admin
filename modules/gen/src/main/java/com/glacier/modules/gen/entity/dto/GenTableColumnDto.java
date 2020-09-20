package com.glacier.modules.gen.entity.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.ToString;

import java.io.Serializable;

/**
 * 表字段模型
 * @author glacier
 * @version 1.0
 * @date 2020-08-26 16:51
 */
@ApiModel(description = "表字段模型")
@Data
@ToString
public class GenTableColumnDto implements Serializable {
    private static final long serialVersionUID = -7669992486290023797L;
    /**
     * 主键
     */
    @ApiModelProperty(value = "主键")
    private String id;
    /**
     * 表id
     */
    @ApiModelProperty(value = "表id")
    private String tableId;
    /**
     * 列名称
     */
    @ApiModelProperty(value = "列名称")
    private String columnName;
    /**
     * 列描述
     */
    @ApiModelProperty(value = "列描述")
    private String columnComment;
    /**
     * 列类型
     */
    @ApiModelProperty(value = "列类型")
    private String columnType;
    /**
     * JAVA类型
     */
    @ApiModelProperty(value = "JAVA类型")
    private String javaType;
    /**
     * JAVA字段名
     */
    @ApiModelProperty(value = "JAVA字段名")
    private String javaField;
    /**
     * 是否主键（1是 2否）
     */
    @ApiModelProperty(value = "是否主键（1是 2否）")
    private String isPk;
    /**
     * 是否必填（1是 2否）
     */
    @ApiModelProperty(value = "是否必填（1是 2否）")
    private String isRequired;
    /**
     * 是否为插入字段（1是 2否）
     */
    @ApiModelProperty(value = "是否为插入字段（1是 2否）")
    private String isInsert;
    /**
     * 是否编辑字段（1是 2否）
     */
    @ApiModelProperty(value = "是否编辑字段（1是 2否）")
    private String isEdit;
    /**
     * 是否列表字段（1是 2否）
     */
    @ApiModelProperty(value = "是否列表字段（1是 2否）")
    private String isList;
    /**
     * 是否查询字段（1是 2否）
     */
    @ApiModelProperty(value = "是否查询字段（1是 2否）")
    private String isQuery;
    /**
     * 查询方式（EQ等于、NE不等于、GT大于、LT小于、LIKE模糊、BETWEEN范围）
     */
    @ApiModelProperty(value = "查询方式（EQ等于、NE不等于、GT大于、LT小于、LIKE模糊、BETWEEN范围）")
    private String queryType;
    /**
     * 显示类型（input文本框、textarea文本域、select下拉框、checkbox复选框、radio单选框、datetime日期控件）
     */
    @ApiModelProperty(value = "显示类型（input文本框、textarea文本域、select下拉框、checkbox复选框、radio单选框、datetime日期控件）")
    private String htmlType;
    /**
     * 字典类型
     */
    @ApiModelProperty(value = "字典类型")
    private String dictType;
    /**
     * 排序
     */
    @ApiModelProperty(value = "排序")
    private Integer sort;
}
