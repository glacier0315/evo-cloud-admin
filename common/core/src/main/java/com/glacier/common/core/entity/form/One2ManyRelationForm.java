package com.glacier.common.core.entity.form;

import java.io.Serializable;
import java.util.List;

/**
 * @author glacier
 * @version 1.0
 * @date 2020-05-18 16:02
 */
public class One2ManyRelationForm implements Serializable {
    private static final long serialVersionUID = 2858625583098650664L;
    /**
     * 父级id
     */
    private String pid;
    /**
     * 子级id集合
     */
    private List<String> ids;

    public One2ManyRelationForm() {
    }

    public One2ManyRelationForm(String pid, List<String> ids) {
        this.pid = pid;
        this.ids = ids;
    }

    public static long getSerialVersionUID() {
        return serialVersionUID;
    }

    public String getPid() {
        return this.pid;
    }

    public void setPid(String pid) {
        this.pid = pid;
    }

    public List<String> getIds() {
        return this.ids;
    }

    public void setIds(List<String> ids) {
        this.ids = ids;
    }

    @Override
    public String toString() {
        return "One2ManyRelationForm{" +
                "pid='" + this.pid + '\'' +
                ", ids=" + this.ids +
                '}';
    }
}
