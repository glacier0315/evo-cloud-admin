package com.glacier.common.core.entity.page;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.ToString;

import java.io.Serializable;
import java.util.List;

/**
 * 分页响应类
 *
 * @author glacier
 * @version 1.0
 * @date 2020-02-14 18:10
 */
@ApiModel(description = "分页响应模型")
@Data
@ToString
public class PageResponse<T> implements Serializable {
    private static final long serialVersionUID = -8475013295639046286L;
    /**
     * 当前页码
     */
    @ApiModelProperty(value = "当前页码")
    private long pageNum;
    /**
     * 每页数量
     */
    @ApiModelProperty(value = "每页数量")
    private long pageSize;
    /**
     * 总数
     */
    @ApiModelProperty(value = "总数")
    private long total;
    /**
     * 查询结果集
     */
    @ApiModelProperty(value = "查询结果集")
    private List<T> list;

    public PageResponse() {
    }

    public PageResponse(long pageNum, long pageSize, long total, List<T> list) {
        this.pageNum = pageNum;
        this.pageSize = pageSize;
        this.total = total;
        this.list = list;
    }
}
