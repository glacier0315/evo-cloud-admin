package com.glacier.modules.gen.entity.dto.table;

import com.glacier.common.core.entity.dto.IdDto;
import com.glacier.modules.gen.entity.dto.column.GenTableColumnDto;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import java.util.List;

/**
 * @author glacier
 * @version 1.0
 * @date 2020-08-26 16:46
 */
@ApiModel(description = "生成表模型")
public class GenTableDto extends IdDto {
    private static final long serialVersionUID = -6637370863263789478L;
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

    public static long getSerialVersionUID() {
        return serialVersionUID;
    }

    public String getTableName() {
        return this.tableName;
    }

    public void setTableName(String tableName) {
        this.tableName = tableName;
    }

    public String getTableComment() {
        return this.tableComment;
    }

    public void setTableComment(String tableComment) {
        this.tableComment = tableComment;
    }

    public String getClassName() {
        return this.className;
    }

    public void setClassName(String className) {
        this.className = className;
    }

    public String getTplCategory() {
        return this.tplCategory;
    }

    public void setTplCategory(String tplCategory) {
        this.tplCategory = tplCategory;
    }

    public String getPackageName() {
        return this.packageName;
    }

    public void setPackageName(String packageName) {
        this.packageName = packageName;
    }

    public String getModuleName() {
        return this.moduleName;
    }

    public void setModuleName(String moduleName) {
        this.moduleName = moduleName;
    }

    public String getBusinessName() {
        return this.businessName;
    }

    public void setBusinessName(String businessName) {
        this.businessName = businessName;
    }

    public String getFunctionName() {
        return this.functionName;
    }

    public void setFunctionName(String functionName) {
        this.functionName = functionName;
    }

    public String getFunctionAuthor() {
        return this.functionAuthor;
    }

    public void setFunctionAuthor(String functionAuthor) {
        this.functionAuthor = functionAuthor;
    }

    public GenTableColumnDto getPkColumn() {
        return this.pkColumn;
    }

    public void setPkColumn(GenTableColumnDto pkColumn) {
        this.pkColumn = pkColumn;
    }

    public List<GenTableColumnDto> getColumns() {
        return this.columns;
    }

    public void setColumns(List<GenTableColumnDto> columns) {
        this.columns = columns;
    }

    public String getOptions() {
        return this.options;
    }

    public void setOptions(String options) {
        this.options = options;
    }

    public String getTreeCode() {
        return this.treeCode;
    }

    public void setTreeCode(String treeCode) {
        this.treeCode = treeCode;
    }

    public String getTreeParentCode() {
        return this.treeParentCode;
    }

    public void setTreeParentCode(String treeParentCode) {
        this.treeParentCode = treeParentCode;
    }

    public String getTreeName() {
        return this.treeName;
    }

    public void setTreeName(String treeName) {
        this.treeName = treeName;
    }

    @Override
    public String toString() {
        return "GenTableDto{" +
                "tableName='" + this.tableName + '\'' +
                ", tableComment='" + this.tableComment + '\'' +
                ", className='" + this.className + '\'' +
                ", tplCategory='" + this.tplCategory + '\'' +
                ", packageName='" + this.packageName + '\'' +
                ", moduleName='" + this.moduleName + '\'' +
                ", businessName='" + this.businessName + '\'' +
                ", functionName='" + this.functionName + '\'' +
                ", functionAuthor='" + this.functionAuthor + '\'' +
                ", pkColumn=" + this.pkColumn +
                ", columns=" + this.columns +
                ", options='" + this.options + '\'' +
                ", treeCode='" + this.treeCode + '\'' +
                ", treeParentCode='" + this.treeParentCode + '\'' +
                ", treeName='" + this.treeName + '\'' +
                ", id='" + this.id + '\'' +
                '}';
    }
}
