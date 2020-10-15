package com.glacier.common.core.utils;


import org.junit.jupiter.api.Test;

/**
 * @author glacier
 * @version 1.0
 * date 2020-08-08 10:15
 */
public class IdGenTest {

    @Test
    public void uuid() {
        String uuid = IdGen.uuid();
        System.out.println(uuid);
    }
}
