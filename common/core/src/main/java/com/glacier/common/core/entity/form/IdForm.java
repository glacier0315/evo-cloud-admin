package com.glacier.common.core.entity.form;

import java.io.Serializable;

/**
 * @author glacier
 * @version 1.0
 * @date 2020-05-23 21:53
 */
public class IdForm implements Serializable {
    private static final long serialVersionUID = -5618778600490092506L;
    /**
     * 主键
     */
    private String id;

    public IdForm() {
    }

    public IdForm(String id) {
        this.id = id;
    }

    public static long getSerialVersionUID() {
        return serialVersionUID;
    }

    public String getId() {
        return this.id;
    }

    public void setId(String id) {
        this.id = id;
    }

    @Override
    public String toString() {
        return "IdForm{" +
                "id='" + this.id + '\'' +
                '}';
    }
}
