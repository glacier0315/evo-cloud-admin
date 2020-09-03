package com.glacier.modules.sys.mapper;

import com.glacier.common.core.mapper.BaseMapper;
import com.glacier.modules.sys.entity.Menu;
import com.glacier.modules.sys.entity.dto.menu.MenuQuery;

import java.util.List;


/**
 * 菜单 数据层
 *
 * @author glacier
 * @version 1.0
 * @date 2019-12-18 15:29
 */
public interface MenuMapper extends BaseMapper<Menu, String> {

    /**
     * 根据角色id 查询所有菜单id
     *
     * @param roleId 角色id
     * @return 菜单id 集合
     */
    List<String> findMenuIdsByRoleId(String roleId);

    /**
     * 根据条件查询
     * @param params
     * @return
     */
    List<Menu> selectList(MenuQuery params);
}
