package com.glacier.common.core.entity.pojo;

import com.glacier.common.core.utils.AppContextHolder;
import com.glacier.common.core.utils.IdGen;

import java.util.Calendar;
import java.util.Date;

/**
 * @author glacier
 * @version 1.0
 * @date 2020-08-10 17:26
 */
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

    public static long getSerialVersionUID() {
        return serialVersionUID;
    }

    public String getCreateBy() {
        return this.createBy;
    }

    public void setCreateBy(String createBy) {
        this.createBy = createBy;
    }

    public Date getCreateDate() {
        return this.createDate;
    }

    public void setCreateDate(Date createDate) {
        this.createDate = createDate;
    }

    public String getUpdateBy() {
        return this.updateBy;
    }

    public void setUpdateBy(String updateBy) {
        this.updateBy = updateBy;
    }

    public Date getUpdateDate() {
        return this.updateDate;
    }

    public void setUpdateDate(Date updateDate) {
        this.updateDate = updateDate;
    }

    public String getDelFlag() {
        return this.delFlag;
    }

    public void setDelFlag(String delFlag) {
        this.delFlag = delFlag;
    }

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

    @Override
    public String toString() {
        return "AbstractDataPojo{" +
                "createBy='" + this.createBy + '\'' +
                ", createDate=" + this.createDate +
                ", updateBy='" + this.updateBy + '\'' +
                ", updateDate=" + this.updateDate +
                ", delFlag='" + this.delFlag + '\'' +
                ", id='" + this.id + '\'' +
                '}';
    }
}
