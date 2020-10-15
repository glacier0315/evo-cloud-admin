package com.glacier.modules.gen.service;

import com.glacier.modules.gen.entity.dto.column.GenTableColumnDto;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

/**
 * @author glacier
 * @version 1.0
 * date 2020-09-20 15:01
 */
@SpringBootTest
public class GenTableColumnServiceTest {
    private static final Logger log = LoggerFactory.getLogger(GenTableColumnServiceTest.class);
    @Autowired
    private GenTableColumnService genTableColumnService;


    @BeforeEach
    void setUp() {
    }

    @AfterEach
    void tearDown() {
    }

    @Test
    void selectListByTableId() {
        List<GenTableColumnDto> genTableColumnDtos = this.genTableColumnService.selectListByTableId("");
        log.info("查询结果 {}", genTableColumnDtos);
    }
}
