package com.glacier.modules.sys.entity.dto.dict;

import com.glacier.common.core.entity.TreeData;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import java.util.List;

/**
 * 字典显示模型
 *
 * @author glacier
 * @version 1.0
 * @date 2020-08-11 22:03
 */
@ApiModel(description = "字典显示模型")
public class DictVo extends DictForm implements TreeData<DictVo> {
    private static final long serialVersionUID = 3386109145574281760L;
    /**
     * 子类
     */
    @ApiModelProperty(value = "子类")
    protected List<DictVo> children;

    public static long getSerialVersionUID() {
        return serialVersionUID;
    }

    @Override
    public List<DictVo> getChildren() {
        return this.children;
    }

    @Override
    public void setChildren(List<DictVo> children) {
        this.children = children;
    }

    @Override
    public String toString() {
        return "DictVo{" +
                "children=" + this.children +
                ", id='" + this.id + '\'' +
                ", name='" + this.name + '\'' +
                ", parentId='" + this.parentId + '\'' +
                ", parentName='" + this.parentName + '\'' +
                ", orderNum=" + this.orderNum +
                ", grade=" + this.grade +
                '}';
    }
}
