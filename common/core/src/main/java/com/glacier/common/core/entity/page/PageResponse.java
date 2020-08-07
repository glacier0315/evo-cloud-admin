package com.glacier.common.core.entity.page;

import lombok.*;

import java.io.Serializable;
import java.util.List;

/**
 * 分页响应类
 *
 * @author glacier
 * @version 1.0
 * @date 2020-02-14 18:10
 */
@Data
@ToString
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class PageResponse<T> implements Serializable {
    private static final long serialVersionUID = -8475013295639046286L;
    /**
     * 当前页码
     */
    private long pageNum;
    /**
     * 每页数量
     */
    private long pageSize;
    /**
     * 总数
     */
    private long total;
    /**
     * 查询结果集
     */
    private List<T> list;
}
