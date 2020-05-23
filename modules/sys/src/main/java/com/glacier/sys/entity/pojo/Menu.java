package com.glacier.sys.entity.pojo;

import com.baomidou.mybatisplus.annotation.TableName;
import com.glacier.common.core.entity.pojo.BasePojo;
import lombok.*;

import java.util.List;

/**
 * 菜单
 *
 * @author glacier
 * @version 1.0
 * @date 2019-10-09 11:03
 */
@Data
@ToString
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(callSuper=false)
@Builder
@TableName(excludeProperty = {"level", "children", "parentName"})
public class Menu extends BasePojo {

    private static final long serialVersionUID = 1207728347319595982L;
    /**
     * 资源名称
     */
    private String name;
    /**
     * 端点路径
     */
    private String url;
    /**
     * 权限
     */
    private String perms;
    /**
     * 1 目录  2 端点  3 权限标识
     */
    private Integer type;
    /**
     * 端点打开方式  默认 1 正常  2 iframe  3 新窗口
     */
    private Integer open;
    /**
     * 1 正常  2 隐藏
     */
    private Integer status;
    /**
     * 图标
     */
    private String icon;
    /**
     * 父级id 顶级id默认为0
     */
    private String parentId;
    /**
     * 排序号
     */
    private Integer orderNum;
    /**
     * 删除标记
     */
    private String delFlag;

    /**
     * 层级
     */
    private Integer level;
    /**
     * 下级单位
     */
    private List<Menu> children;
    /**
     * 父级名称
     */
    private String parentName;
}
