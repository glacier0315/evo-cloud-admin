package com.glacier.common.core.utils;

import com.google.common.collect.Maps;

import java.util.Map;
import java.util.Optional;

/**
 * 用户上下文
 *
 * @author glacier
 * @version 1.0
 * @date 2020-05-18 12:37
 */
public class AppContextHolder {
    /**
     * 线程上下文
     */
    private final ThreadLocal<Map<String, String>> threadLocal;

    private AppContextHolder() {
        this.threadLocal = new ThreadLocal<>();
    }

    /**
     * 创建实例
     *
     * @return
     */
    public static AppContextHolder getInstance() {
        return SingletonHolder.SINGLETON_INSTANCE;
    }

    /**
     * 静态内部类单例模式
     * 单例初使化
     */
    private static class SingletonHolder {
        private static final AppContextHolder SINGLETON_INSTANCE = new AppContextHolder();
    }

    /**
     * 用户上下文中放入信息
     *
     * @param map
     */
    public void setContext(Map<String, String> map) {
        this.threadLocal.set(map);
    }

    /**
     * 获取上下文中的信息
     *
     * @return
     */
    public Map<String, String> getContext() {
        return this.threadLocal.get();
    }

    /**
     * 获取上下文中的用户名
     *
     * @return
     */
    public String getUsername() {
        return Optional.ofNullable(this.threadLocal.get())
                .orElse(Maps.newHashMap())
                .get("username");
    }

    /**
     * 清空上下文
     */
    public void clear() {
        this.threadLocal.remove();
    }
}
