package com.glacier.modules.gen.entity;

import com.glacier.common.core.entity.AbstractDataEntity;

import javax.validation.constraints.NotBlank;

/**
 * 代码生成表字段 模型层
 *
 * @author glacier
 * @version 1.0
 * date 2020-08-25 16:22
 */
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

    public static long getSerialVersionUID() {
        return serialVersionUID;
    }

    public String getTableId() {
        return this.tableId;
    }

    public void setTableId(String tableId) {
        this.tableId = tableId;
    }

    public String getColumnName() {
        return this.columnName;
    }

    public void setColumnName(String columnName) {
        this.columnName = columnName;
    }

    public String getColumnComment() {
        return this.columnComment;
    }

    public void setColumnComment(String columnComment) {
        this.columnComment = columnComment;
    }

    public String getColumnType() {
        return this.columnType;
    }

    public void setColumnType(String columnType) {
        this.columnType = columnType;
    }

    public String getJavaType() {
        return this.javaType;
    }

    public void setJavaType(String javaType) {
        this.javaType = javaType;
    }

    public String getJavaField() {
        return this.javaField;
    }

    public void setJavaField(String javaField) {
        this.javaField = javaField;
    }

    public String getIsPk() {
        return this.isPk;
    }

    public void setIsPk(String isPk) {
        this.isPk = isPk;
    }

    public String getIsRequired() {
        return this.isRequired;
    }

    public void setIsRequired(String isRequired) {
        this.isRequired = isRequired;
    }

    public String getIsInsert() {
        return this.isInsert;
    }

    public void setIsInsert(String isInsert) {
        this.isInsert = isInsert;
    }

    public String getIsEdit() {
        return this.isEdit;
    }

    public void setIsEdit(String isEdit) {
        this.isEdit = isEdit;
    }

    public String getIsList() {
        return this.isList;
    }

    public void setIsList(String isList) {
        this.isList = isList;
    }

    public String getIsQuery() {
        return this.isQuery;
    }

    public void setIsQuery(String isQuery) {
        this.isQuery = isQuery;
    }

    public String getQueryType() {
        return this.queryType;
    }

    public void setQueryType(String queryType) {
        this.queryType = queryType;
    }

    public String getHtmlType() {
        return this.htmlType;
    }

    public void setHtmlType(String htmlType) {
        this.htmlType = htmlType;
    }

    public String getDictType() {
        return this.dictType;
    }

    public void setDictType(String dictType) {
        this.dictType = dictType;
    }

    public Integer getSort() {
        return this.sort;
    }

    public void setSort(Integer sort) {
        this.sort = sort;
    }

    @Override
    public String toString() {
        return "GenTableColumn{" +
                "tableId='" + this.tableId + '\'' +
                ", columnName='" + this.columnName + '\'' +
                ", columnComment='" + this.columnComment + '\'' +
                ", columnType='" + this.columnType + '\'' +
                ", javaType='" + this.javaType + '\'' +
                ", javaField='" + this.javaField + '\'' +
                ", isPk='" + this.isPk + '\'' +
                ", isRequired='" + this.isRequired + '\'' +
                ", isInsert='" + this.isInsert + '\'' +
                ", isEdit='" + this.isEdit + '\'' +
                ", isList='" + this.isList + '\'' +
                ", isQuery='" + this.isQuery + '\'' +
                ", queryType='" + this.queryType + '\'' +
                ", htmlType='" + this.htmlType + '\'' +
                ", dictType='" + this.dictType + '\'' +
                ", sort=" + this.sort +
                ", createBy='" + this.createBy + '\'' +
                ", createDate=" + this.createDate +
                ", updateBy='" + this.updateBy + '\'' +
                ", updateDate=" + this.updateDate +
                ", delFlag='" + this.delFlag + '\'' +
                ", id='" + this.id + '\'' +
                '}';
    }
}
