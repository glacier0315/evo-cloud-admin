package com.glacier.modules.sys.service;

import com.glacier.modules.sys.entity.dto.menu.MenuForm;
import com.glacier.modules.sys.entity.dto.menu.MenuVo;

import java.util.List;
import java.util.Set;

/**
 * @author glacier
 * @version 1.0
 * @date 2019-10-09 15:45
 */
public interface MenuService {

    /**
     * 查询所有菜单树
     *
     * @return
     */
    List<MenuVo> findAllList();


    /**
     * 查找菜单树
     *
     * @return
     */
    List<MenuVo> findMenuTree();

    /**
     * 根据用户ID查找菜单树
     *
     * @param userId
     * @return
     */
    List<MenuVo> findMenuTreeByUserId(String userId);

    /**
     * 根据用户ID查找权限
     *
     * @param userId
     * @return
     */
    Set<String> findPermissionsByUserId(String userId);

    /**
     * 保存操作
     *
     * @param record
     * @return
     */
    int save(MenuForm record);

    /**
     * 根据Id删除
     *
     * @param id
     * @return
     */
    int delete(String id);

    /**
     * 根据角色id 查询角色所拥有的菜单id
     * @param roleId
     * @return
     */
    List<String> findByRole(String roleId);
}
