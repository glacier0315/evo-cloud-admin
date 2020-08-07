package com.glacier.common.core.entity.pojo;

import lombok.Data;
import lombok.ToString;

import java.io.Serializable;
import java.util.Date;

/**
 * @author glacier
 * @version 1.0
 * @date 2020-05-18 15:59
 */
@Data
@ToString
public class BasePojo implements Serializable {
    private static final long serialVersionUID = -3733490542838696950L;

    public final static String DEFAULT_USERNAME = "system";
    /**
     * 主键
     */
    private String id;
    /**
     * 创建人
     */
    private String createBy;
    /**
     * 创建时间
     */
    private Date createDate;
    /**
     * 更新人
     */
    private String updateBy;
    /**
     * 更新时间
     */
    private Date updateDate;
}
