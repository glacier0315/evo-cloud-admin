package com.glacier.modules.sys.service.impl;

import com.glacier.common.core.constant.SysConstants;
import com.glacier.common.core.utils.StringUtil;
import com.glacier.common.core.utils.TreeBuildFactory;
import com.glacier.modules.sys.entity.Menu;
import com.glacier.modules.sys.entity.dto.menu.MenuForm;
import com.glacier.modules.sys.entity.dto.menu.MenuQuery;
import com.glacier.modules.sys.entity.dto.menu.MenuVo;
import com.glacier.modules.sys.mapper.MenuMapper;
import com.glacier.modules.sys.service.MenuService;
import com.google.common.collect.Lists;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
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
        if (StringUtil.isBlank(id)) {
            return 0;
        }
        return this.menuMapper.deleteByPrimaryKey(id);
    }

    @Override
    public List<String> findByRole(String roleId) {
        return this.menuMapper.findMenuIdsByRoleId(roleId);
    }

    @Override
    public List<MenuVo> findList(MenuQuery menuQuery) {
        if (menuQuery == null) {
            return new ArrayList<>(1);
        }
        return this.modelMapper.map(
                this.menuMapper.selectList(menuQuery),
                new TypeToken<List<MenuVo>>() {
                }.getType());
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
        List<Integer> typeList = Lists.newArrayList(SysConstants.TYPE_DIR, SysConstants.TYPE_MENU);
        List<MenuVo> allMenuVolist = this.findList(
                MenuQuery.builder()
                        .status(SysConstants.STATUS_ENABLED)
                        .typeList(typeList)
                        .build());
        if (SysConstants.SYS_USER_ID.equals(userId)) {
            menuList.addAll(allMenuVolist);
        } else {
            List<MenuVo> menus = this.findList(
                    MenuQuery.builder()
                            .status(SysConstants.STATUS_ENABLED)
                            .typeList(typeList)
                            .userId(userId)
                            .build());
            // 处理,得到需要的菜单集合
            menuList.addAll(
                    this.findAllMenus(menus, allMenuVolist));
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
        List<MenuVo> menuList = null;
        if (SysConstants.SYS_USER_ID.equals(userId)) {
            menuList = this.findList(
                    MenuQuery.builder()
                            .status(SysConstants.STATUS_ENABLED)
                            .build());
        } else {
            menuList = this.findList(
                    MenuQuery.builder()
                            .status(SysConstants.STATUS_ENABLED)
                            .userId(userId)
                            .build());
        }
        return Optional.ofNullable(menuList)
                .orElseGet(ArrayList::new)
                .stream()
                .map(MenuVo::getPermission)
                .collect(Collectors.toSet());
    }

    /**
     * 查询所有菜单的父节点
     *
     * @param menuVos    待查询，最后一个元素是带查询元素
     * @param allMenuVos 查询范围菜单集合
     * @return 查询所有节点(自身和其父节点)
     */
    private List<MenuVo> findAllMenus(final List<MenuVo> menuVos, final List<MenuVo> allMenuVos) {
        if (menuVos == null
                || menuVos.isEmpty()
                || allMenuVos == null
                || allMenuVos.isEmpty()) {
            return menuVos;
        }
        // 记录
        List<MenuVo> menuVoList = new ArrayList<>(1);
        // 迭代查出父级，并添加到集合中
        for (MenuVo menuVo : menuVos) {
            List<MenuVo> list = new ArrayList<>(1);
            list.add(menuVo);
            menuVoList.addAll(
                    this.findParent(list, allMenuVos));
        }
        // 去除重复菜单
        return menuVoList.stream()
                .collect(
                        Collectors.collectingAndThen(
                                Collectors.toCollection(() ->
                                        new TreeSet<>(Comparator.comparing(MenuVo::getId))
                                ),
                                ArrayList::new
                        )
                );
    }


    /**
     * 查询所有菜单的父节点
     *
     * @param menuVos    最后一个元素需要查父级
     * @param allMenuVos 查询范围菜单集合
     * @return 查出的父级添加到menuVos最后面
     */
    private List<MenuVo> findParent(final List<MenuVo> menuVos, final List<MenuVo> allMenuVos) {
        if (menuVos == null
                || menuVos.isEmpty()
                || allMenuVos == null
                || allMenuVos.isEmpty()) {
            return menuVos;
        }
        // 取出待查询父级元素
        MenuVo lastMenuVos = menuVos.get(menuVos.size() - 1);
        // 父级为空，直接返回
        if (lastMenuVos == null
                || StringUtil.isBlank(lastMenuVos.getParentId())) {
            return menuVos;
        }
        Optional<MenuVo> menuVoOptional = allMenuVos.stream()
                .filter(menuVo ->
                        StringUtil.equals(lastMenuVos.getParentId(), menuVo.getId()))
                .findFirst();
        if (menuVoOptional.isPresent()) {
            menuVos.add(menuVoOptional.get());
            return this.findParent(menuVos, allMenuVos);
        }
        return menuVos;
    }
}
