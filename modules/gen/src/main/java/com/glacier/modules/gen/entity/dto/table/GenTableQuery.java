package com.glacier.modules.gen.entity.dto.table;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import java.io.Serializable;

/**
 * 表查询模型
 * @author glacier
 * @version 1.0
 * date 2020-08-26 16:56
 */
@ApiModel(description = "表查询模型")
public class GenTableQuery implements Serializable {
    private static final long serialVersionUID = 8039085389833762809L;
    /**
     * 数据源id
     */
    @ApiModelProperty(value = "数据源id")
    private String datasourceId;
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

    @Override
    public String toString() {
        return "GenTableQuery{" +
                "datasourceId='" + this.datasourceId + '\'' +
                ", tableName='" + this.tableName + '\'' +
                ", tableComment='" + this.tableComment + '\'' +
                '}';
    }
}
