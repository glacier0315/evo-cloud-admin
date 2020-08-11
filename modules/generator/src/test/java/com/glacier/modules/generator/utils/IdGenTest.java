package com.glacier.modules.generator.utils;

import com.glacier.common.core.utils.IdGen;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

/**
 * @author glacier
 * @version 1.0
 * @date 2020-08-08 10:15
 */
@SpringBootTest
public class IdGenTest {

    @Test
    public void uuid() {
        String uuid = IdGen.uuid();
        Assertions.assertNotNull(uuid);
        Assertions.assertTrue(uuid.length() > 0);
        System.out.println(uuid);
    }
}
