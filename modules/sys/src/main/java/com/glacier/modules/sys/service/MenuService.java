package com.glacier.modules.sys.service;

import com.glacier.modules.sys.entity.dto.menu.MenuForm;
import com.glacier.modules.sys.entity.dto.menu.MenuQuery;
import com.glacier.modules.sys.entity.dto.menu.MenuVo;

import java.util.List;
import java.util.Set;

/**
 * @author glacier
 * @version 1.0
 * date 2019-10-09 15:45
 */
public interface MenuService {

    /**
     * 根据查询条件查询菜单
     *
     * @param menuQuery 菜单查询条件封装类
     * @return 菜单集合
     */
    List<MenuVo> findList(MenuQuery menuQuery);

    /**
     * 根据用户ID查找菜单树
     *
     * @param userId 用户id
     * @return 菜单树
     */
    List<MenuVo> findMenuTreeByUserId(String userId);

    /**
     * 根据用户ID查找权限
     *
     * @param userId 用户id
     * @return 权限集合
     */
    Set<String> findPermissionsByUserId(String userId);

    /**
     * 保存操作
     *
     * @param record 实体
     * @return 影响记录数
     */
    int save(MenuForm record);

    /**
     * 根据Id删除
     *
     * @param id 主键
     * @return 删除记录数
     */
    int delete(String id);

    /**
     * 根据角色id 查询角色所拥有的菜单id
     *
     * @param roleId 角色id
     * @return 菜单树
     */
    List<String> findByRole(String roleId);
}
