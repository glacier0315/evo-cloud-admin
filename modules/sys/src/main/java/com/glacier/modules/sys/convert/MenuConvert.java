package com.glacier.modules.sys.convert;

import com.glacier.modules.sys.entity.Menu;
import com.glacier.modules.sys.entity.dto.menu.MenuForm;
import com.glacier.modules.sys.entity.dto.menu.MenuVo;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;

import java.util.List;

/**
 * date 2021-05-06 16:43
 * 菜单类型转换
 *
 * @author glacier
 * @version 1.0
 */
@Mapper(componentModel = "spring",
        unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface MenuConvert {
    
    /**
     * 转换
     *
     * @param menu
     * @return
     */
    MenuVo toMenuVo(Menu menu);
    
    /**
     * 转换
     *
     * @param menuList
     * @return
     */
    List<MenuVo> toMenuVo(List<Menu> menuList);
    
    /**
     * 转换
     *
     * @param menuForm
     * @return
     */
    Menu map(MenuForm menuForm);
}
