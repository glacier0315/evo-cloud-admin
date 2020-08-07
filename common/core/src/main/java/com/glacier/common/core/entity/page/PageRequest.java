package com.glacier.common.core.entity.page;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.io.Serializable;

/**
 * 分页请求类
 *
 * @author glacier
 * @version 1.0
 * @date 2019-10-14 15:53
 */
@Data
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class PageRequest<T> implements Serializable {
    private static final long serialVersionUID = 4076080201697869870L;
    /**
     * 当前页码
     */
    private int pageNum = 1;
    /**
     * 每页数量
     */
    private int pageSize = 10;
    /**
     * 查询参数
     */
    private T params;

}
