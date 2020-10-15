package com.glacier.common.core.utils;

import com.glacier.common.core.constant.CommonConstant;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

/**
 * 用户上下文
 *
 * @author glacier
 * @version 1.0
 * date 2020-05-18 12:37
 */
public class AppContextHolder {
    /**
     * 线程上下文
     */
    private final ThreadLocal<Map<String, Object>> threadLocal;

    private AppContextHolder() {
        this.threadLocal = new ThreadLocal<>();
    }

    /**
     * 创建实例
     *
     * @return 实例
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
     * @param map 放入上下文的map
     */
    public void setContext(final Map<String, Object> map) {
        Optional.ofNullable(map)
                .ifPresent(this.threadLocal::set);
    }

    /**
     * 获取上下文中的信息
     *
     * @return 取出上下文的map
     */
    public Map<String, Object> getContext() {
        return Optional.ofNullable(this.threadLocal.get())
                .orElseGet(HashMap::new);
    }

    /**
     * 获取上下文中的用户名
     *
     * @return 用户名
     */
    public String getUsername() {
        return String.valueOf(this.getContext().get(CommonConstant.OAUTH_USERNAME));
    }

    /**
     * 获取上下文中的用户id
     *
     * @return 用户id
     */
    public String getUserId() {
        return String.valueOf(this.getContext().get(CommonConstant.OAUTH_USER_ID));
    }

    /**
     * 清空上下文
     */
    public void clear() {
        Optional.of(this.threadLocal)
                .ifPresent(ThreadLocal::remove);
    }
}
