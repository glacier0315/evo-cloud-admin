package com.glacier.common.core.utils;

import com.glacier.common.core.entity.TreeData;
import org.apache.commons.lang3.StringUtils;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

/**
 * 菜单构建工厂
 *
 * @author glacier
 * @version 1.0
 * date 2020-06-16 21:08
 */
public class TreeBuildFactory {

    private TreeBuildFactory() {
    }

    /**
     * 组装菜单树
     *
     * @param list 菜单集合
     * @param <T>  菜单类型
     * @return 菜单树
     */
    public static <T extends TreeData<T>> List<T> buildMenuTree(final List<T> list) {
        // 分离出所有一级
        List<T> topList = Optional.ofNullable(list)
                .orElseGet(ArrayList::new)
                .stream()
                .filter(t -> StringUtils.isBlank(t.getParentId()))
                .peek(t -> t.setGrade(1))
                .sorted(Comparator.comparingInt(T::getOrderNum))
                .collect(Collectors.toList());
        // 组装子类菜单
        buildChildren(topList, list);
        return topList;
    }

    /**
     * 递归组装菜单
     *
     * @param topList 当前父级菜单
     * @param list    待查询菜单
     */
    private static <T extends TreeData<T>> void buildChildren(final List<T> topList, final List<T> list) {
        Optional.ofNullable(topList)
                .orElseGet(ArrayList::new)
                .forEach(t -> {
                    List<T> subList = Optional.ofNullable(list)
                            .orElseGet(ArrayList::new)
                            .stream()
                            .filter(t1 -> t.getId().equals(t1.getParentId()))
                            .peek(t1 -> t1.setGrade(t.getGrade() + 1))
                            .sorted(Comparator.comparingInt(T::getOrderNum))
                            .collect(Collectors.toList());
                    t.setChildren(subList);
                    buildChildren(subList, list);
                });
    }
}
