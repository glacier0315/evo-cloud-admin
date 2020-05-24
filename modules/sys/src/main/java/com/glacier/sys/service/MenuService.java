package com.glacier.sys.service;

import com.glacier.common.core.entity.form.IdDto;
import com.glacier.sys.entity.pojo.Menu;

import java.util.List;
import java.util.Set;

/**
 * @author glacier
 * @version 1.0
 * @date 2019-10-09 15:45
 */
public interface MenuService {

    /**
     * 根据角色id 查询对应的菜单
     *
     * @param roleId
     * @return
     */
    List<Menu> findMenusByRoleId(String roleId);

    /**
     * 查找所有菜单
     *
     * @return
     */
    List<Menu> findMenuTree();

    /**
     * 根据用户ID查找菜单
     *
     * @param userId
     * @return
     */
    List<Menu> findTree(String userId);

    /**
     * 根据用户ID查找权限
     *
     * @param userId
     * @return
     */
    Set<String> findPermissions(String userId);

    /**
     * 保存操作
     *
     * @param record
     * @return
     */
    int save(Menu record);

    /**
     * 根据Id批量删除
     *
     * @param idDtos
     * @return
     */
    int batchDelete(List<IdDto> idDtos);
}
