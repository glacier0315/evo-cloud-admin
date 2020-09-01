package com.glacier.modules.sys.service.impl;

import com.glacier.common.core.constant.SysConstants;
import com.glacier.common.core.utils.TreeBuildFactory;
import com.glacier.modules.sys.entity.Menu;
import com.glacier.modules.sys.entity.dto.menu.MenuForm;
import com.glacier.modules.sys.entity.dto.menu.MenuVo;
import com.glacier.modules.sys.mapper.MenuMapper;
import com.glacier.modules.sys.service.MenuService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.modelmapper.ModelMapper;
import org.modelmapper.TypeToken;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;
import java.util.stream.Collectors;

/**
 * 菜单业务层
 *
 * @author glacier
 * @version 1.0
 * @date 2019-10-09 15:45
 */
@Slf4j
@Transactional(readOnly = true)
@Service("menuService")
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class MenuServiceImpl implements MenuService {

    private final MenuMapper menuMapper;
    private final ModelMapper modelMapper;

    /**
     * 保存
     *
     * @param menuForm
     * @return
     */
    @Transactional(rollbackFor = {})
    @Override
    public int save(MenuForm menuForm) {
        Menu menu = this.modelMapper.map(menuForm, Menu.class);
        if (menu.isNewRecord()) {
            menu.preInsert();
            return this.menuMapper.insert(menu);
        }
        menu.preUpdate();
        return this.menuMapper.updateByPrimaryKey(menu);
    }


    /**
     * 根据id删除
     *
     * @param id
     * @return
     */
    @Transactional(rollbackFor = {})
    @Override
    public int delete(String id) {
        if (StringUtils.isNotEmpty(id)) {
            return this.menuMapper.deleteByPrimaryKey(id);
        }
        return 0;
    }

    @Override
    public List<String> findByRole(String roleId) {
        return this.menuMapper.findMenuIdsByRoleId(roleId);
    }

    @Override
    public List<MenuVo> findAllList() {
        return this.modelMapper.map(
                this.menuMapper.selectAll(),
                new TypeToken<List<MenuVo>>() {
                }.getType());
    }

    @Override
    public List<MenuVo> findMenuTree() {
        return TreeBuildFactory.buildMenuTree(
                this.findAllList());
    }

    /**
     * 根据用户id 查询菜单树
     *
     * @param userId
     * @return
     */
    @Override
    public List<MenuVo> findMenuTreeByUserId(String userId) {
        List<MenuVo> menuList = new ArrayList<>(1);
        if (userId == null || userId.isEmpty()) {
            return menuList;
        }
        if (SysConstants.SYS_USER_ID.equals(userId)) {
            // 处理
            menuList = Optional.ofNullable(this.findAllList())
                    .orElseGet(ArrayList::new)
                    .stream()
                    .filter(menu ->
                            // 正常 且 非权限标识
                            menu.getStatus() != null
                                    && menu.getStatus() == SysConstants.STATUS_ENABLED
                                    && menu.getType() != null
                                    && menu.getType() != SysConstants.TYPE_BUTTON
                    )
                    .collect(Collectors.toList());
        } else {
            menuList = this.modelMapper.map(
                    this.menuMapper.findByUserId(userId),
                    new TypeToken<List<MenuVo>>() {
                    }.getType());
        }
        return TreeBuildFactory.buildMenuTree(menuList);
    }

    /**
     * 根据用户ID 查询权限标识
     *
     * @param userId
     * @return
     */
    @Override
    public Set<String> findPermissionsByUserId(String userId) {
        if (userId == null || userId.isEmpty()) {
            return new HashSet<>(1);
        }
        Set<String> permissions = null;
        if (SysConstants.SYS_USER_ID.equals(userId)) {
            permissions = this.menuMapper.findAllPermissions();
        } else {
            permissions = this.menuMapper.findPermissionsByUserId(userId);
        }
        return Optional.ofNullable(permissions)
                .orElseGet(HashSet::new);
    }
}
