package com.glacier.modules.sys.entity.dto.menu;

import com.fasterxml.jackson.annotation.JsonInclude;
import io.swagger.annotations.ApiModel;
import lombok.*;

import java.io.Serializable;

/**
 * 元数据显示
 * @author glacier
 * @version 1.0
 * @date 2020-06-06 10:14
 */
@ApiModel(description = "路由元数据模型")
@Data
@ToString
@NoArgsConstructor
@AllArgsConstructor
@Builder
@JsonInclude(JsonInclude.Include.NON_EMPTY)
public class Meta implements Serializable {
    private static final long serialVersionUID = 8544347198911886718L;
    /**
     * 设置该路由在侧边栏和面包屑中展示的名字
     */
    private String title;
    /**
     * 设置该路由的图标，对应路径src/icons/svg
     */
    private String icon;
}
