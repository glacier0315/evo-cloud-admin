package com.glacier.modules.gen.entity;

import com.glacier.common.core.entity.AbstractDataEntity;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

import javax.validation.constraints.NotBlank;

/**
 * 代码生成表 模型层
 *
 * @author glacier
 * @version 1.0
 * @date 2019-08-04 21:53
 */
@Data
@ToString(callSuper = true)
@EqualsAndHashCode(callSuper = false)
public class GenTable extends AbstractDataEntity {
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
}
