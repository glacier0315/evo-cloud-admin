package com.glacier.common.core.entity.page;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import java.io.Serializable;

/**
 * 分页请求类
 *
 * @author glacier
 * @version 1.0
 * @date 2019-10-14 15:53
 */
@ApiModel(description = "分页请求模型")
public class PageRequest<T> implements Serializable {
    private static final long serialVersionUID = 4076080201697869870L;
    /**
     * 当前页码
     */
    @ApiModelProperty(value = "当前页码")
    private int pageNum = 1;
    /**
     * 每页数量
     */
    @ApiModelProperty(value = "每页数量")
    private int pageSize = 10;
    /**
     * 查询参数
     */
    @ApiModelProperty(value = "查询参数")
    private T params;

    public PageRequest() {
    }

    public PageRequest(int pageNum, int pageSize, T params) {
        this.pageNum = pageNum;
        this.pageSize = pageSize;
        this.params = params;
    }

    public static long getSerialVersionUID() {
        return serialVersionUID;
    }

    public int getPageNum() {
        return this.pageNum;
    }

    public void setPageNum(int pageNum) {
        this.pageNum = pageNum;
    }

    public int getPageSize() {
        return this.pageSize;
    }

    public void setPageSize(int pageSize) {
        this.pageSize = pageSize;
    }

    public T getParams() {
        return this.params;
    }

    public void setParams(T params) {
        this.params = params;
    }

    @Override
    public String toString() {
        return "PageRequest{" +
                "pageNum=" + this.pageNum +
                ", pageSize=" + this.pageSize +
                ", params=" + this.params +
                '}';
    }
}
