package com.glacier.modules.gen.entity;

import com.glacier.common.core.entity.DataEntity;

import javax.validation.constraints.NotBlank;

/**
 * 代码生成表 模型层
 *
 * @author glacier
 * @version 1.0
 * date 2019-08-04 21:53
 */
public class GenTable extends DataEntity {
    private static final long serialVersionUID = -1221312518526422323L;
    /**
     * 数据源id
     */
    @NotBlank(message = "数据源id不能为空")
    private String datasourceId;
    /**
     * 表名称
     */
    @NotBlank(message = "表名称不能为空")
    private String tableName;
    /**
     * 表描述
     */
    @NotBlank(message = "表描述不能为空")
    private String tableComment;
    /**
     * 实体类名称(首字母大写)
     */
    @NotBlank(message = "实体类名称不能为空")
    private String className;
    /**
     * 使用的模板（crud单表操作 tree树表操作）
     */
    private String templateCategory;
    /**
     * 生成包路径
     */
    @NotBlank(message = "生成包路径不能为空")
    private String packageName;
    /**
     * 生成模块名
     */
    @NotBlank(message = "生成模块名不能为空")
    private String moduleName;
    /**
     * 生成业务名
     */
    @NotBlank(message = "生成业务名不能为空")
    private String businessName;
    /**
     * 生成功能名
     */
    @NotBlank(message = "生成功能名不能为空")
    private String functionName;
    /**
     * 生成作者
     */
    @NotBlank(message = "作者不能为空")
    private String functionAuthor;
    /**
     * 其它生成选项
     */
    private String options;
    /**
     * 树编码字段
     */
    private String treeCode;
    /**
     * 树父编码字段
     */
    private String treeParentCode;
    /**
     * 树名称字段
     */
    private String treeName;

    public static long getSerialVersionUID() {
        return serialVersionUID;
    }

    public String getDatasourceId() {
        return this.datasourceId;
    }

    public void setDatasourceId(String datasourceId) {
        this.datasourceId = datasourceId;
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

    public String getTemplateCategory() {
        return this.templateCategory;
    }

    public void setTemplateCategory(String templateCategory) {
        this.templateCategory = templateCategory;
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
        return "GenTable{" +
                "datasourceId='" + this.datasourceId + '\'' +
                ", tableName='" + this.tableName + '\'' +
                ", tableComment='" + this.tableComment + '\'' +
                ", className='" + this.className + '\'' +
                ", templateCategory='" + this.templateCategory + '\'' +
                ", packageName='" + this.packageName + '\'' +
                ", moduleName='" + this.moduleName + '\'' +
                ", businessName='" + this.businessName + '\'' +
                ", functionName='" + this.functionName + '\'' +
                ", functionAuthor='" + this.functionAuthor + '\'' +
                ", options='" + this.options + '\'' +
                ", treeCode='" + this.treeCode + '\'' +
                ", treeParentCode='" + this.treeParentCode + '\'' +
                ", treeName='" + this.treeName + '\'' +
                ", id='" + this.id + '\'' +
                '}';
    }
}
