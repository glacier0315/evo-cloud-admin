package com.glacier.sys.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.glacier.sys.entity.Menu;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Set;

/**
 * 菜单dao层
 *
 * @author glacier
 * @version 1.0
 * @date 2019-10-09 14:50
 */
public interface MenuMapper extends BaseMapper<Menu> {


    /**
     * 根据角色id 查询所有菜单
     *
     * @param roleId
     * @return
     */
    List<Menu> findMenusByRoleId(@Param("roleId") String roleId);

    /**
     * 根据用户名 查询所有菜单
     *
     * @param userId
     * @return
     */
    List<Menu> findMenusByUserId(@Param("userId") String userId);

    /**
     * 根据用户名 查询所有按钮权限
     *
     * @param userId
     * @return
     */
    Set<String> findPermissionsByUserId(@Param("userId") String userId);

    /**
     * 根据查询所有按钮权限
     *
     * @return
     */
    Set<String> findAllPermissions();
}
