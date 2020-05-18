package com.glacier.common.core.utils;

/**
 * @author glacier
 * @version 1.0
 * @date 2020-02-13 11:00
 */
public class StringUtils {

    /**
     * 判断字符串为空或者空字符串
     *
     * @param str 输入字符串
     * @return 判断结果
     */
    public static boolean isEmpty(String str) {
        return str == null || str.isEmpty();
    }
}
