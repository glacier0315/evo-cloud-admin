package com.glacier.modules.gen.entity.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.ToString;

import java.io.Serializable;
import java.util.List;

/**
 * @author glacier
 * @version 1.0
 * @date 2020-08-26 16:46
 */
@ApiModel(description = "生成表模型")
@Data
@ToString
public class GenTableDto implements Serializable {
    private static final long serialVersionUID = -6637370863263789478L;
    /**
     * 主键
     */
    @ApiModelProperty(value = "主键")
    private String id;
    /**
     * 表名称
     */
    @ApiModelProperty(value = "表名称")
    private String tableName;
    /**
     * 表描述
     */
    @ApiModelProperty(value = "表描述")
    private String tableComment;
    /**
     * 实体类名称(首字母大写)
     */
    @ApiModelProperty(value = "实体类名称(首字母大写)")
    private String className;
    /**
     * 使用的模板（crud单表操作 tree树表操作）
     */
    @ApiModelProperty(value = "使用的模板（crud单表操作 tree树表操作）")
    private String tplCategory;
    /**
     * 生成包路径
     */
    @ApiModelProperty(value = "生成包路径")
    private String packageName;
    /**
     * 生成模块名
     */
    @ApiModelProperty(value = "生成模块名")
    private String moduleName;
    /**
     * 生成业务名
     */
    @ApiModelProperty(value = "生成业务名")
    private String businessName;
    /**
     * 生成功能名
     */
    @ApiModelProperty(value = "生成功能名")
    private String functionName;
    /**
     * 生成作者
     */
    @ApiModelProperty(value = "生成作者")
    private String functionAuthor;
    /**
     * 主键信息
     */
    @ApiModelProperty(value = "主键信息")
    private GenTableColumnDto pkColumn;
    /**
     * 表列信息
     */
    @ApiModelProperty(value = "表列信息")
    private List<GenTableColumnDto> columns;
    /**
     * 其它生成选项
     */
    @ApiModelProperty(value = "其它生成选项")
    private String options;
    /**
     * 树编码字段
     */
    @ApiModelProperty(value = "树编码字段")
    private String treeCode;
    /**
     * 树父编码字段
     */
    @ApiModelProperty(value = "树父编码字段")
    private String treeParentCode;
    /**
     * 树名称字段
     */
    @ApiModelProperty(value = "树名称字段")
    private String treeName;
}
