package com.glacier.modules.sys.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.glacier.modules.sys.entity.pojo.Menu;
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

    /**
     * 根据角色id 查询角色所拥有的菜单id
     * @param roleId
     * @return
     */
    List<String> findByRole(@Param("roleId") String roleId);
}
