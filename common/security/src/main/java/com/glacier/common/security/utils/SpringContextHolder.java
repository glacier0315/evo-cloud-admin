package com.glacier.common.security.utils;

import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.stereotype.Component;

/**
 * spring工具类
 *
 * @author glacier
 * @version 1.0
 * date 2019-11-07 16:06
 */
@Component
public class SpringContextHolder implements ApplicationContextAware {
    private static ApplicationContext context;


    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        context = applicationContext;
    }

    /**
     * 获取applicationContext
     *
     * @return applicationContext实例
     */
    public static ApplicationContext getApplicationContext() {
        return context;
    }

    /**
     * 通过name获取 Bean
     *
     * @param name 名称
     * @return 实例
     */
    public static Object getBean(String name) {
        return getApplicationContext().getBean(name);
    }


    /**
     * 通过class获取Bean
     *
     * @param clazz 类型
     * @param <T>   具体类型
     * @return 实例
     */
    public static <T> T getBean(Class<T> clazz) {
        return getApplicationContext().getBean(clazz);
    }

    /**
     * 通过name,以及Clazz返回指定的Bean
     *
     * @param name 名称
     * @param clazz 类型
     * @param <T> 具体类型
     * @return 实例
     */
    public static <T> T getBean(String name, Class<T> clazz) {
        return getApplicationContext().getBean(name, clazz);
    }
}
