package com.glacier.modules.sys.entity.pojo;

import com.baomidou.mybatisplus.annotation.TableLogic;
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
@TableName(excludeProperty = {"level", "children"})
public class Menu extends BasePojo {

    private static final long serialVersionUID = 1207728347319595982L;
    /**
     * 资源名称
     */
    private String name;
    /**
     * 端点相对路径
     */
    private String path;
    /**
     * 组件
     */
    private String component;
    /**
     * 图标
     */
    private String icon;
    /**
     * 权限标识
     */
    private String permission;
    /**
     * 1 目录  2 端点  3 权限标识
     */
    private Integer type;
    /**
     * 1 显示  2 隐藏
     */
    private Integer visible;

    /**
     * 是否外链 1 是  2 否
     */
    private Integer isFrame;

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
    @TableLogic
    private String delFlag;

    /**
     *
     */
    private Integer level;
    /**
     * 子级菜单
     */
    private List<Menu> children;
}
