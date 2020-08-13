package com.glacier.common.core.entity.pojo;

import com.glacier.common.core.utils.AppContextHolder;
import com.glacier.common.core.utils.IdGen;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

import java.util.Calendar;
import java.util.Date;

/**
 * @author glacier
 * @version 1.0
 * @date 2020-08-10 17:26
 */
@EqualsAndHashCode(callSuper = true)
@Data
@ToString
public abstract class AbstractDataPojo extends AbstractBasePojo {
    private static final long serialVersionUID = -6067130213584450184L;
    /**
     * 创建人
     */
    protected String createBy;
    /**
     * 创建时间
     */
    protected Date createDate;
    /**
     * 更新人
     */
    protected String updateBy;
    /**
     * 更新时间
     */
    protected Date updateDate;
    /**
     * 删除标记 0 正常 （默认） 1 删除
     */
    protected String delFlag = "0";

    @Override
    public void preInsert() {
        // 设置新的id
        this.setId(IdGen.uuid());
        // 设置插入时间
        this.setCreateDate(Calendar.getInstance()
                .getTime());
        // 设置操作人
        this.setCreateBy(AppContextHolder.getInstance()
                .getUserId());
    }

    @Override
    public void preUpdate() {
        // 设置更新时间
        this.setUpdateDate(Calendar.getInstance()
                .getTime());
        // 设置操作人
        this.setUpdateBy(AppContextHolder.getInstance()
                .getUserId());
    }
}
