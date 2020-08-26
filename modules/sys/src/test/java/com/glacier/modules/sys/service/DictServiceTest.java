package com.glacier.modules.sys.service;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import javax.annotation.Resource;

/**
 * @author glacier
 * @version 1.0
 * @date 2020-08-15 12:03
 */
@SpringBootTest
class DictServiceTest {

    @Resource
    private DictService dictService;

    @BeforeEach
    void setUp() {
    }

    @AfterEach
    void tearDown() {
    }

    @Test
    void save() {
    }

    @Test
    void delete() {
        String id = "123";
        int delete = this.dictService.delete(id);
        System.out.println(delete);
    }

    @Test
    void findDictTree() {

    }
}
