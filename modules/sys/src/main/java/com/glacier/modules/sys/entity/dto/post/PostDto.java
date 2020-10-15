package com.glacier.modules.sys.entity.dto.post;

import com.glacier.common.core.entity.dto.IdDto;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

/**
 * 岗位查询条件
 * @author glacier
 * @version 1.0
 * @date 2020-09-05 21:26
 */
@ApiModel(description = "岗位查询条件模型")
public class PostDto extends IdDto {
    private static final long serialVersionUID = 8688278856685935029L;
    /**
     * 名称
     */
    @ApiModelProperty(value = "名称")
    private String name;
    /**
     * 编码
     */
    @ApiModelProperty(value = "编码")
    private String code;
    /**
     * 状态 1 正常 2 禁用
     */
    @ApiModelProperty(value = "状态 1 正常 2 禁用")
    private Integer status;
    /**
     * 排序号
     */
    @ApiModelProperty(value = "排序号")
    protected Integer orderNum;
    /**
     * 描述
     */
    @ApiModelProperty(value = "描述")
    private String description;
    /**
     * 备注
     */
    @ApiModelProperty(value = "备注")
    private String remarks;

    public static long getSerialVersionUID() {
        return serialVersionUID;
    }

    public String getName() {
        return this.name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCode() {
        return this.code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public Integer getStatus() {
        return this.status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public Integer getOrderNum() {
        return this.orderNum;
    }

    public void setOrderNum(Integer orderNum) {
        this.orderNum = orderNum;
    }

    public String getDescription() {
        return this.description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getRemarks() {
        return this.remarks;
    }

    public void setRemarks(String remarks) {
        this.remarks = remarks;
    }

    @Override
    public String toString() {
        return "PostDto{" +
                "name='" + this.name + '\'' +
                ", code='" + this.code + '\'' +
                ", status=" + this.status +
                ", orderNum=" + this.orderNum +
                ", description='" + this.description + '\'' +
                ", remarks='" + this.remarks + '\'' +
                ", id='" + this.id + '\'' +
                '}';
    }
}
